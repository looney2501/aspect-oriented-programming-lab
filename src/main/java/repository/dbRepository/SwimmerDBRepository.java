package repository.dbRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import domain.entities.Swimmer;
import repository.interfaces.SwimmerRepository;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class SwimmerDBRepository implements SwimmerRepository {

    private final JdbcUtils jdbcUtils;
    private static final Logger logger = LogManager.getLogger();

    public SwimmerDBRepository(Properties properties) {
        logger.info("Initialising SwimmerDBRepository with properties {}", properties);
        this.jdbcUtils = new JdbcUtils(properties);
    }

    @Override
    public Integer add(Swimmer elem) {
        logger.traceEntry("add(Swimmer = {})", elem);
        Connection connection = jdbcUtils.getConnection();
        Integer id = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into Swimmers (firstName, lastName, age) values (?, ?, ?);"
        ))
        {
            preparedStatement.setString(1, elem.getFirstName());
            preparedStatement.setString(2, elem.getLastName());
            preparedStatement.setInt(3, elem.getAge());
            int result = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            id = resultSet.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return logger.traceExit("Result: id = {}", id);
    }

    @Override
    public void delete(Swimmer elem) {
    }

    @Override
    public void update(Swimmer elem, Integer id) {
    }

    @Override
    public Swimmer findById(Integer id) {
        logger.traceEntry("findById(id = {})", id);
        Swimmer foundSwimmer = null;

        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select firstName, lastName, age from main.Swimmers where id = ?;"
        )) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Integer age = resultSet.getInt("age");
                foundSwimmer = new Swimmer(id, firstName, lastName, age);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logger.traceExit("Result: Swimmer = {}", foundSwimmer);
    }
}
