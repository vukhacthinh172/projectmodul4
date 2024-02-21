package ra.demoprojectmodul4.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class AuthencationUser extends User {
    private Integer id;
    private String firstName;
    private String lastName;


    public AuthencationUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer id,
                            String firstName, String lastName) {
        super(username, password, authorities);
        this.firstName = firstName;
        this.lastName = lastName;
        this.id=id;
    }

}
