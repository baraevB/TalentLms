package peaksoft.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Course;
import peaksoft.entity.Teacher;
import peaksoft.repository.CourseRepository;
import peaksoft.service.TeacherService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/teacher")
@PreAuthorize("hasAnyAuthority('ADMIN','EDITOR')")
public class TeacherController {

    private final TeacherService teacherService;
    private final CourseRepository courseRepository;

    @PostMapping

    public ResponseEntity<Teacher> create(@RequestBody Teacher teacher){
        Course course=courseRepository.findByCourseName(teacher.getCourseName());
        teacher.setCourse(course);
        return new ResponseEntity<>(teacherService.create(teacher), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> update(@PathVariable long id,@RequestBody Teacher teacher){
        return new ResponseEntity<>(teacherService.update(id,teacher),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getById(@PathVariable long id ){
        return new ResponseEntity<>(teacherService.getById(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Teacher>>getAll(){
        return new ResponseEntity<>(teacherService.getAll(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String>deleteById(@PathVariable Long id){
        teacherService.deleteById(id);
        return new ResponseEntity<>("Successfully delete by id Teacher  -->  "+id,HttpStatus.OK);
    }
}
