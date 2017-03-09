package voting.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import voting.dto.admin.AdminRepresentation;
import voting.dto.countyrep.CountyRepresentativeDTO;
import voting.repository.AdminRepository;
import voting.service.CountyRepService;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by andrius on 3/6/17.
 */

//@Controller
@RequestMapping("/api/auth/")
public class AuthController {



}
