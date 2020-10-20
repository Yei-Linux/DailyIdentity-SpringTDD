package pe.yeilinux.identity.service;

import pe.yeilinux.identity.domain.Company;

import java.util.List;

public interface CompanyService {
    public List<Company> getCompanies();
    public int createCompany(Company company);
}
