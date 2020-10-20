package pe.yeilinux.identity.mapper;

import org.springframework.jdbc.core.RowMapper;
import pe.yeilinux.identity.domain.Client;
import pe.yeilinux.identity.domain.Token;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenMapper implements RowMapper<Token> {
    @Override
    public Token mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Token token = new Token();
        token.setSigningKey(resultSet.getString("signingKey"));
        token.setVerifierKey(resultSet.getString("verifierKey"));
        return  token;
    }
}
