package pe.yeilinux.identity.mapper;

import org.springframework.jdbc.core.RowMapper;
import pe.yeilinux.identity.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet,int rowNum) throws SQLException {
        User user = new User();
        user.setUserName(resultSet.getString("userName"));
        user.setPhoneNumber(resultSet.getString("phone_number"));
        try {
            user.setPassword(resultSet.getString("password_hash"));
        } catch (java.sql.SQLException e) {
            System.out.println(e.getErrorCode());
        }
        try {
            user.getPersonDetails().setFullName(resultSet.getString("fullname"));
        } catch (java.sql.SQLException e) {
            System.out.println(e.getErrorCode());
        }
        try {
            user.getPersonDetails().setAge(resultSet.getString("age"));
        } catch (java.sql.SQLException e) {
            System.out.println(e.getErrorCode());
        }

        return user;
    }
}
