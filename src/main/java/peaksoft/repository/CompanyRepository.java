package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Company;
import peaksoft.entity.Student;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("SELECT C FROM Company C WHERE C.id = ?1")
    Company findCompanyById(long id);

    @Query("select c from  Company  c where  c.companyName like ?1")
    Company findCompanyByName(String name);
@Query("select s from Student s join Group g on g.id=s.group.id join g.courses c join Company com on com.id=c.company.id where com.id=?1")
    List<Student>getCompanyByStudent(long id);
}
