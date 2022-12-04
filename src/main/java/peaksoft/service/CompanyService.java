package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Company;
import peaksoft.entity.Student;
import peaksoft.repository.CompanyRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company create(Company company) {

        company.setCreated(LocalDate.now());
        return companyRepository.save(company);
    }

    public Company update(Long id, Company company) {

        Company company1 = companyRepository.findById(id).get();
        company1.setCompanyName(company.getCompanyName());
        company1.setCreated(LocalDate.now());
        company1.setLocatedCountry(company.getLocatedCountry());
        return companyRepository.save(company1);
    }

    public Company getById(long id) {
        return companyRepository.findCompanyById(id);
    }

    public void delete(long id) {
        companyRepository.deleteById(id);
    }

    public List<Company> findAllCompany() {
        return companyRepository.findAll();
    }

    public List<Student>getCompanyStudent(long id){

        return companyRepository.getCompanyByStudent(id);
    }

    public int sizeCompanyStudent(long id){
        List<Student>size= companyRepository.getCompanyByStudent(id);
        return size.size();
    }

}
