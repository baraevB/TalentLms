package peaksoft.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    @ManyToMany(targetEntity = User.class,mappedBy = "roles",
            cascade = {CascadeType.DETACH,CascadeType.PERSIST,
                    CascadeType.MERGE,CascadeType.REFRESH,})
    private List<User>users;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
