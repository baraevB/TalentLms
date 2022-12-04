package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Group;
import peaksoft.entity.Student;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("select g from  Group g where g.id = ?1")
    Group findGroupById(long id);

    @Query("select g from  Group g where g.groupName like ?1")
    Group getGroupByName(String name);
    @Query("select s from Student  s join s.group g where g.id=?1")
    List<Student>getStudentByGroup(long id);
}
