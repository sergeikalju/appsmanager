package sk.appsmanager.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;

import sk.appsmanager.dao.ServiceDao;
import sk.appsmanager.domain.Service;
import sk.appsmanager.domain.ServiceSubType;
import sk.appsmanager.domain.ServiceType;

public class ServiceDaoJdbcImpl extends AbstractCrudDaoImpl<Service> implements ServiceDao {
    private static final String SAVE_QUERY = 
            "INSERT INTO services (app_code, name, type, sub_type, description, last_modified) "
                    + "values (?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM services WHERE service_code = ?;";
    private static final String FIND_BY_NAME_QUERY = "SELECT * FROM services WHERE name = ?;";
    private static final String FIND_BY_APP_CODE_QUERY = "SELECT * FROM services WHERE app_code = ?;";
    
    private final JdbcTemplate jdbcTemplate;

    public ServiceDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_BY_NAME_QUERY);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    protected Object[] getSaveArgs(Service service) {
        return new Object[] { 
                service.getAppCode(),
                service.getName(),
                service.getType().toString(),
                service.getSubType().toString(),
                service.getDescription(),
                service.getLastModified()
        };
    }

    @Override
    protected Optional<Service> getEntity(ResultSet resultSet) throws SQLException {
        return Optional.ofNullable(Service.builder()
                .withAppCode(resultSet.getInt("app_code"))
                .withServiceCode(resultSet.getInt("service_code"))
                .withName(resultSet.getString("name"))
                .withType(ServiceType.valueOf(resultSet.getString("type")))
                .withSubType(ServiceSubType.valueOf(resultSet.getString("sub_type")))
                .withDescription(resultSet.getString("description"))
                .withLastModified(resultSet.getTimestamp("last_modified"))
                .build());
    }
    
    @Override
    public List<Service> findAllByAppCode(Integer appCode) {
        return jdbcTemplate.query(FIND_BY_APP_CODE_QUERY, entityRowMapper, appCode);
    }
}
