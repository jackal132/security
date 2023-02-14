package acc.security.user.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="tb_user")
@DynamicUpdate
public class User {

    @Id
    @Column(name="user_id")
    private String userId;

    @Column(name="password")
    private String password;

    @Column(name="name")
    private String name;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="tb_user_roles", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles;

    @Builder
    public User(String userId, String password, String name, Set<Role> roles){
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.roles = roles;
    }

}
