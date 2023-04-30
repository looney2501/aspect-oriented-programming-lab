package repository.dbRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import domain.entities.Admin;
import repository.interfaces.AdminRepository;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class AdminDBRepository implements AdminRepository {

    private final JdbcUtils jdbcUtils;
    private static final Logger logger = LogManager.getLogger();

    public AdminDBRepository(Properties properties) {
        logger.info("Initialising AdminDBRepository with properties {}", properties);
        this.jdbcUtils = new JdbcUtils(properties);
    }

    @Override
    public Integer add(Admin elem) {
        return null;
    }

    @Override
    public void delete(Admin elem) {

    }

    @Override
    public void update(Admin elem, Integer id) {

    }

    @Override
    public Admin findById(Integer id) {

        return null;
    }

    @Override
    public Admin findByUsernameAndPassword(String username, String password) {
        logger.traceEntry("findByUsernameAndPassword(username = {}, password {})", username, password);

        Admin admin = null;

        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select id from main.Admins where username = ? and password = ?;"
        )) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                admin = new Admin(id, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logger.traceExit("Result: admin = {}", admin);
    }
}
