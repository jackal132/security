package acc.security.user.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="tb_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long roleId;

    @Column(name="name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
