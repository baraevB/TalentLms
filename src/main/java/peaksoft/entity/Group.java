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
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "group_name")
    private String groupName;
    @CreatedDate
    @Column(name = "date_of_start")
    private LocalDate dateOfStart;
    @Column(name = "date_of_finish")
    private LocalDate dateOfFinish;
    private Boolean isActive = true;
    private Boolean delete = false;
    @Transient
    private String courseName;
    @JsonIgnore
    @OneToMany(mappedBy = "group")
    private Set<Student> students;

    private void removeStudent(Student student) {
        this.getStudents().remove(student);
        student.setGroup(null);
    }

    public void removeStudents() {
        for (Student s : new HashSet<>(students)) {
            removeStudent(s);
        }
    }

    @ManyToMany(
            cascade = {CascadeType.MERGE,
                    CascadeType.REFRESH})
    @JoinTable(name = "groups_courses",
            joinColumns = @JoinColumn(name = "groups_id"),
            inverseJoinColumns = @JoinColumn(name = "courses_id"))
    private Set<Course> courses = new HashSet<>();

    public void removeCourse(Course course) {
        this.getCourses().remove(course);
        course.getGroups().remove(this);
    }

    public void removeCourses() {
        for (Course course : new HashSet<>(courses)) {
            removeCourse(course);
        }
    }
}
