package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.entity.Teacher;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.GroupRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;
    private final GroupRepository groupRepository;



    public Course create(Course course) {
        course.setCreated(LocalDate.now());
        return courseRepository.save(course);
    }

    public Course update(Long id, Course course) {
        Course courseDto = courseRepository.findById(id).get();
        courseDto.setCourseName(course.getCourseName());
        courseDto.setDurationInMonth(course.getDurationInMonth());
        courseDto.setCreated(LocalDate.now());
        courseDto.setIsActive(course.getIsActive());
        courseDto.setDelete(course.getDelete());
        courseDto.setCompany(companyRepository.findCompanyByName(course.getCompanyName()));
        return courseRepository.save(courseDto);
    }
    public Course getById(long id) {
        return courseRepository.findByCourseId(id);
    }

    public void deleteById(long id) {
        Optional<Course>course = courseRepository.findById(id);
        if (course.isPresent())
            course.get().removeGroups();
        courseRepository.deleteById(course.get().getId());
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Group>getGroupByCourse(long id ) {
           return courseRepository.getGroupByCourseName(id);
    }
    public List<Teacher>getTeacherById(long id){
        return courseRepository.getTeacherByCourse(id);
    }

}


