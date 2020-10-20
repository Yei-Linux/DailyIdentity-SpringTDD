package pe.yeilinux.identity.mapper;

import org.springframework.jdbc.core.RowMapper;
import pe.yeilinux.identity.domain.Client;
import pe.yeilinux.identity.domain.Company;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyMapper implements RowMapper<Company> {

    @Override
    public Company mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getInt("id"));
        company.setName(resultSet.getString("name"));
        company.setToken_id(resultSet.getInt("token_id"));
        return  company;
    }
}
