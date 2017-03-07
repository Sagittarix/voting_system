package voting.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import voting.dto.CountyRepresentativeRepresentation;
import voting.repository.AdminRepository;
import voting.service.CountyRepService;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andrius on 3/6/17.
 */

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final CountyRepService countyRepService;
    private final AdminRepository adminRepository;

    @Autowired
    public AuthController(CountyRepService countyRepService, AdminRepository adminRepository) {
        this.countyRepService = countyRepService;
        this.adminRepository = adminRepository;
    }

    @PostMapping(path = "authorities")
    @ResponseBody
    public Object checkSecuredAuthorities() {
        boolean representative = isContextContainsRole("ROLE_REPRESENTATIVE");
        boolean admin = isContextContainsRole("ROLE_ADMIN");

        if (representative) {
            return "REPRESENTATIVE";
        } else if (admin) {
            return "ADMIN";
        } else {
            return null;
        }
    }

    @PostMapping(path = "username")
    @ResponseBody
    public String getSecuredUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (userDetails instanceof UserDetails) ? ((UserDetails) userDetails).getUsername() : "";
    }

    @PostMapping(path = "role")
    @ResponseBody
    public boolean getSecuredRole(@RequestParam("role") String role) {
        System.out.println(role);
        return isContextContainsRole(role);
    }

    @PostMapping(path = "details")
    public Object getSecuredPrincipal() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = (userDetails instanceof UserDetails) ? ((UserDetails) userDetails).getUsername() : null;
        boolean representative = isContextContainsRole("ROLE_REPRESENTATIVE");
        boolean admin = isContextContainsRole("ROLE_ADMIN");

        if (representative) {
            return new CountyRepresentativeRepresentation(countyRepService.getCountyRepByUsername(username));
        } else if (admin) {
            return new AdminRepresentation(adminRepository.findOneByUsername(username));
        } else {
            return null;
        }
    }

    private boolean isContextContainsRole(String role) {
        Collection<? extends GrantedAuthority> authorities =
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()).contains(role);
    }

}
