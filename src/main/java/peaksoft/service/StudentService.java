package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Student;
import peaksoft.repository.GroupRepository;
import peaksoft.repository.StudentRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final EntityManager entityManager;

    public List<Student> getAllStudents() {
        List<Student> students =entityManager.createQuery("from Student",Student.class).getResultList();
        Comparator<Student> comparator=((o1, o2)->(int)(o1.getId()-o2.getId()));
        students.sort(comparator);
        return students;
    }

    public Student create(Student student) {
        student.setCreated(LocalDate.now());
        return studentRepository.save(student);
    }

    public Student update(long id, Student student) {
        Student studentDto = studentRepository.getByStudentId(id);
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setEmail(student.getEmail());
        studentDto.setStudyFormat(student.getStudyFormat());
        studentDto.setAge(student.getAge());
        studentDto.setCreated(LocalDate.now());
        studentDto.setGroup(groupRepository.getGroupByName(student.getGroupName()));
        return studentRepository.save(studentDto);
    }

    public Student getById(long id) {
        return studentRepository.getByStudentId(id);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public void deleteById(long id) {
        studentRepository.deleteById(id);
    }
}
