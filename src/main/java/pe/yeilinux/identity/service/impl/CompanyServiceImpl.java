package pe.yeilinux.identity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.yeilinux.identity.domain.Company;
import pe.yeilinux.identity.repository.CompanyRepository;
import pe.yeilinux.identity.service.CompanyService;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public List<Company> getCompanies() {
        return this.companyRepository.getCompanies();
    }

    @Override
    public int createCompany(Company company) {
        return this.companyRepository.createCompany(company);
    }
}
