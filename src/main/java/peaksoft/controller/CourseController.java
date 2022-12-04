package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.entity.Teacher;
import peaksoft.repository.CompanyRepository;
import peaksoft.service.CourseService;
import peaksoft.service.TeacherService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
@PreAuthorize("hasAnyAuthority('EDITOR','ADMIN')")

public class CourseController {

    private final CourseService courseService;
    private final CompanyRepository companyRepository;
    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        course.setCompany(companyRepository.findCompanyByName(course.getCompanyName()));
        return new ResponseEntity<>( courseService.create(course),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course>  update(@PathVariable long id, @RequestBody Course course) {
        return new ResponseEntity<>(courseService.update(id, course),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable long id) {
        return new ResponseEntity<>( courseService.getById(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAll() {
        return new ResponseEntity<>(courseService.getAllCourses(),HttpStatus.OK) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <String>deleteById(@PathVariable long id) {
        courseService.deleteById(id);
        return new ResponseEntity<>("Successfully deleted by id "+id,HttpStatus.OK);
    }

    @GetMapping("/{id}/groups")
    public ResponseEntity<List<Group>> getGroupCourse(@PathVariable long id){

       return new ResponseEntity<>(courseService.getGroupByCourse(id), HttpStatus.OK) ;
    }

    @GetMapping("/{id}/teachers")
    public ResponseEntity<List<Teacher>> geTeacherCourse(@PathVariable long id){

        return new ResponseEntity<>(courseService.getTeacherById(id), HttpStatus.OK) ;
    }
}




