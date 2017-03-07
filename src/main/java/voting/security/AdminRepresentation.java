package voting.security;

import voting.model.Admin;
import voting.model.CountyRep;
import voting.utils.Formatter;

/**
 * Created by andrius on 3/7/17.
 */

public class AdminRepresentation {

    private Long id;
    private String personalId;
    private String firstName;
    private String lastName;
    private String username;
    private String[] roles;
    private String password;

    public AdminRepresentation() { }

    public AdminRepresentation(Admin admin) {
        this.id = admin.getId();
        this.personalId = admin.getPersonalId();
        this.firstName = admin.getFirstName();
        this.lastName = admin.getLastName();
        this.username = admin.getUsername();
        this.roles = admin.getRoles();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
