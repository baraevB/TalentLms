package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Group;
import peaksoft.entity.Student;
import peaksoft.service.GroupService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group")
@PreAuthorize("hasAnyAuthority('ADMIN','EDITOR')")
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<Group> save(@RequestBody Group group) {
        return new ResponseEntity<>(groupService.create(group), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Group> update(@PathVariable long id, @RequestBody Group group) {
        return new ResponseEntity<>(groupService.update(id, group), HttpStatus.OK);

    }

    @GetMapping("{id}")
    public ResponseEntity<Group> getById(@PathVariable long id) {
        return new ResponseEntity<>(groupService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Group>> getAllGroup() {
        return new ResponseEntity<>(groupService.findAll(), HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        groupService.deleteById(id);
        return new ResponseEntity<>("Successfully deleted by id Group --> " + id, HttpStatus.OK);
    }

    @GetMapping("{id}/student")
    public ResponseEntity<List<Student>> getStudent(@PathVariable long id) {
        return new ResponseEntity<>(groupService.studentList(id), HttpStatus.OK);
    }


}
