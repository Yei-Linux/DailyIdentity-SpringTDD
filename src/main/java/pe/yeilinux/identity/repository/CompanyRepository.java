package pe.yeilinux.identity.repository;

import pe.yeilinux.identity.domain.Company;

import java.util.List;

public interface CompanyRepository {
    public List<Company> getCompanies();
    public int createCompany(Company company);
}
