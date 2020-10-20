package pe.yeilinux.identity.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import pe.yeilinux.identity.constant.Constant;
import pe.yeilinux.identity.domain.Client;
import pe.yeilinux.identity.domain.Company;
import pe.yeilinux.identity.mapper.ClientMapper;
import pe.yeilinux.identity.mapper.CompanyMapper;
import pe.yeilinux.identity.repository.CompanyRepository;
import pe.yeilinux.identity.util.SimpleJdbcCallFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository {
    @Resource(name= Constant.CURRENTJDBCTEMPLATE)
    private JdbcTemplate jdbcTemplate;

    @Autowired
    SimpleJdbcCallFactory simpleJdbcCallFactory;

    @Override
    public List<Company> getCompanies() {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "find_companies_all",Constant.CURRENTDATABASE)
                .returningResultSet("result",new CompanyMapper());

        Map<String,Object> result= simpleJdbcCall.execute();
        List<Company> companies = (List<Company>) result.get("result");

        if( companies != null && !companies.isEmpty() ){
            return companies;
        }
        return null;
    }

    @Override
    public int createCompany(Company company) {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "create_company",Constant.CURRENTDATABASE);

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("p_name",company.getName());
        parameters.put("p_token_id",company.getToken_id());

        Map<String,Object> result= simpleJdbcCall.execute(parameters);
        return (int) result.get("p_result");
    }
}
