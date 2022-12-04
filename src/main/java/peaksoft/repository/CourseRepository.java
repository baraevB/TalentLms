package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.entity.Teacher;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query("select c from Course c where c.id=?1")
     Course findByCourseId(long id);

    @Query("select c from  Course  c where c.courseName like ?1")
    Course findByCourseName(String name);


    @Query("select g from Group g join g.courses c where c.id=?1")
    List<Group>getGroupByCourseName(long id );

    @Query("select t from Teacher t join t.course c where c.id=?1")
    List<Teacher>getTeacherByCourse(long id);


}
