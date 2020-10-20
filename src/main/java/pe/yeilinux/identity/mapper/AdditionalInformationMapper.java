package pe.yeilinux.identity.mapper;

import org.springframework.jdbc.core.RowMapper;
import pe.yeilinux.identity.domain.AdditionalInformation;
import pe.yeilinux.identity.domain.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdditionalInformationMapper implements RowMapper<AdditionalInformation> {

    @Override
    public AdditionalInformation mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        AdditionalInformation additionalInformation = new AdditionalInformation();
        additionalInformation.setField(resultSet.getString("field"));
        try {
            additionalInformation.setValue(resultSet.getString("value"));
        } catch (java.sql.SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return additionalInformation;
    }
}