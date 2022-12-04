package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Company;
import peaksoft.entity.Student;
import peaksoft.service.CompanyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
@PreAuthorize("hasAuthority('ADMIN')")

public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<Company>create(@RequestBody Company company){
        return new ResponseEntity<>(companyService.create(company),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Company> update (@PathVariable long id ,@RequestBody Company company){
        return new ResponseEntity<>(companyService.update(id,company),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Company>  getById(@PathVariable long id){
       return new ResponseEntity<>(companyService.getById(id),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Company>>getAll(){
        return new ResponseEntity<>(companyService.findAllCompany(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteById(@PathVariable long id ){
        companyService.delete(id);
        return new ResponseEntity<>("Successfully deleted by id "+ id,HttpStatus.OK);
    }

    @GetMapping("{id}/students")
    public ResponseEntity<List<Student>>getStudent(@PathVariable long id){
        return new ResponseEntity<>(companyService.getCompanyStudent(id), HttpStatus.OK);

    }
    @GetMapping("{id}/studentsSize")
    public ResponseEntity<String>sizeStudent(@PathVariable long id){
        return new ResponseEntity<>("There are  <"+companyService.sizeCompanyStudent(id)+">  students in this company",HttpStatus.OK);
    }

}
