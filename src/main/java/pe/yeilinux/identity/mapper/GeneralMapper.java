package pe.yeilinux.identity.mapper;

import org.springframework.jdbc.core.RowMapper;
import pe.yeilinux.identity.domain.AdditionalInformation;
import pe.yeilinux.identity.domain.GeneralObject;
import pe.yeilinux.identity.domain.Token;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneralMapper implements RowMapper<GeneralObject> {

    @Override
    public GeneralObject mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        GeneralObject generalObject = new GeneralObject();
        generalObject.setGeneral(resultSet.getString("generalValue"));
        return generalObject;
    }
}
