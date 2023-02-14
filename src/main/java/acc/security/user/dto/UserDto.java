package acc.security.user.dto;

import acc.security.user.model.Role;
import acc.security.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String userId;

    private String password;

    private String name;

    private Set<Long> roles;

    public User toEntity(Set<Role> roles){
        return User
                .builder()
                .userId(userId)
                .password(password)
                .name(name)
                .roles(roles)
                .build();
    }

}
