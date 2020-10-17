package sk.appsmanager.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;

import sk.appsmanager.dao.ApplicationDao;
import sk.appsmanager.domain.AppType;
import sk.appsmanager.domain.Application;

public class ApplicationDaoJdbcImpl extends AbstractCrudDaoImpl<Application> implements ApplicationDao {
    private static final String SAVE_QUERY = 
            "INSERT INTO applications (name, app_group, app_type, description, app_cost, last_modified) "
                    + "values (?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM applications WHERE app_code = ?;";
    private static final String FIND_BY_NAME_QUERY = "SELECT * FROM applications WHERE name = ?;";

    public ApplicationDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_BY_NAME_QUERY);
    }

    @Override
    protected Object[] getSaveArgs(Application application) {
        return new Object[] { 
                application.getName(),
                application.getAppGroup(),
                application.getAppType().toString(),
                application.getDescription(),
                application.getAppCost(),
                application.getLastModified()
        };
    }

    @Override
    protected Optional<Application> getEntity(ResultSet resultSet) throws SQLException {
        return Optional.ofNullable(Application.builder()
                .withAppCode(resultSet.getInt("app_code"))
                .withName(resultSet.getString("name"))
                .withAppGroup(resultSet.getInt("app_group"))
                .withAppType(AppType.valueOf(resultSet.getString("app_type")))
                .withDescription(resultSet.getString("description"))
                .withAppCost(resultSet.getDouble("app_cost"))
                .withLastModified(resultSet.getTimestamp("last_modified"))
                .build());
    }
}
