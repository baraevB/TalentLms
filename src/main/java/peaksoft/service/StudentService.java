package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Student;
import peaksoft.repository.GroupRepository;
import peaksoft.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

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
