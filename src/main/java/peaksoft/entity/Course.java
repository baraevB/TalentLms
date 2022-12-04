package peaksoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "duration_in_month")
    private String durationInMonth;
    private Boolean isActive = true;
    private Boolean delete = false;
    @CreatedDate
    private LocalDate created;
    private String companyName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToMany(
            cascade = {CascadeType.MERGE,
                    CascadeType.REFRESH},
            mappedBy = "courses")
    @JsonIgnore
    private Set<Group> groups = new HashSet<>();

    private void removeGroup(Group group) {
        this.getGroups().remove(group);
        group.getCourses().remove(this);
    }

    public void removeGroups() {
        for (Group group : new HashSet<>(groups)) {
            removeGroup(group);
        }
    }

}