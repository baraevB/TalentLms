package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Teacher;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository  extends JpaRepository<Teacher,Long> {

   @Query(value = "select t from Teacher t where t.id=?1")
   Teacher getById(long id);
}
