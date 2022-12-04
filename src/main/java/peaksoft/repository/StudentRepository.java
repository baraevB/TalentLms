package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query ("select s from  Student  s where s.id = ?1")
    Student getByStudentId(long id );
}
