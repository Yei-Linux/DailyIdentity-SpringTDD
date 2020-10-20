package pe.yeilinux.identity.mapper;

import org.springframework.jdbc.core.RowMapper;
import pe.yeilinux.identity.domain.User;
import pe.yeilinux.identity.domain.UserDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailsMapper implements RowMapper<UserDetails> {
    @Override
    public UserDetails mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        UserDetails userDetails = new UserDetails();
        userDetails.setCompanyId(resultSet.getInt("companyId"));
        userDetails.setCompanyName(resultSet.getString("companyName"));
        userDetails.setTokenId(resultSet.getInt("tokenId"));
        return userDetails;
    }
}
