package pe.yeilinux.identity.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import pe.yeilinux.identity.constant.Constant;
import pe.yeilinux.identity.domain.Client;
import pe.yeilinux.identity.domain.Token;
import pe.yeilinux.identity.mapper.ClientMapper;
import pe.yeilinux.identity.mapper.TokenMapper;
import pe.yeilinux.identity.repository.TokenRepository;
import pe.yeilinux.identity.util.SimpleJdbcCallFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TokenRepositoryImpl implements TokenRepository {
    @Resource(name= Constant.CURRENTJDBCTEMPLATE)
    private JdbcTemplate jdbcTemplate;

    @Autowired
    SimpleJdbcCallFactory simpleJdbcCallFactory;

    @Override
    public Token getSignKeyAndVerifierKey() {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "find_verifier_signing_key",Constant.CURRENTDATABASE)
                .returningResultSet("result",new TokenMapper());

        Map<String,Object> result= simpleJdbcCall.execute();
        List<Token> detailsToken= (List<Token>) result.get("result");

        if(detailsToken == null && detailsToken.isEmpty()){
            return null;
        }
        return detailsToken.get(0);
    }

    @Override
    public void updateSignKeyAndVerifierKey(Token token) {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "update_verifier_signing_key",Constant.CURRENTDATABASE);

        Map<String,Object> params = new HashMap<>();
        params.put("p_signing_key",token.getSigningKey());
        params.put("p_verifier_key",token.getVerifierKey());
        params.put("p_token_id",token.getId());

        simpleJdbcCall.execute(params);
    }

    @Override
    public int createToken() {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "create_token",Constant.CURRENTDATABASE);

        Map<String,Object> result= simpleJdbcCall.execute();
        return (int) result.get("p_result");
    }
}
