package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Teacher;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.TeacherRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {


    private final TeacherRepository teacherRepository;

    private final CourseRepository courseRepository;


    public Teacher create(Teacher teacher) {
        teacher.setCreated(LocalDate.now());
        return teacherRepository.save(teacher);
    }

    public Teacher update(long id, Teacher teacher) {
        Teacher teacherDto = teacherRepository.getById(id);
        teacherDto.setLastName(teacher.getLastName());
        teacherDto.setFirstName(teacher.getFirstName());
        teacherDto.setEmail(teacher.getEmail());
        teacherDto.setCreated(LocalDate.now());
        teacherDto.setAge(teacher.getAge());
        teacherDto.setCourse(courseRepository.findByCourseName(teacher.getCourseName()));
        return teacherRepository.save(teacherDto);

    }

    public Teacher getById(long id) {
        return teacherRepository.findById(id).get();
    }

    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    public void deleteById(Long id){
        teacherRepository.deleteById(id);
    }

}

