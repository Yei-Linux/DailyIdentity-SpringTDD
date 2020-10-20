package pe.yeilinux.identity.util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

@Component
public class SimpleJdbcCallFactory {
    public SimpleJdbcCall create(JdbcTemplate jdbcTemplate, String procName) {
        return new SimpleJdbcCall(jdbcTemplate).withProcedureName(procName);
    }

    public SimpleJdbcCall create(JdbcTemplate jdbcTemplate, String procName,String catalogName) {
        return new SimpleJdbcCall(jdbcTemplate).withCatalogName(catalogName).withProcedureName(procName);
    }
}
