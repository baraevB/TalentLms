package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Student;
import peaksoft.repository.GroupRepository;
import peaksoft.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/student")
@PreAuthorize("hasAnyAuthority('ADMIN','EDITOR')")
public class StudentController {

    private final StudentService studentService;
    private final GroupRepository groupRepository;

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        student.setGroup(groupRepository.getGroupByName(student.getGroupName()));
        return new ResponseEntity<>(studentService.create(student), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> update(@PathVariable long id, @RequestBody Student student) {
        return new ResponseEntity<>(studentService.update(id, student), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getBuId(@PathVariable long id) {
        return new ResponseEntity<>(studentService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        studentService.deleteById(id);
        return new ResponseEntity<>("Successfully delete by id Student -->" + id, HttpStatus.OK);
    }
}
