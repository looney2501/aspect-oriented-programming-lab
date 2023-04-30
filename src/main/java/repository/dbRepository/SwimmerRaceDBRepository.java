package repository.dbRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import domain.entities.Race;
import domain.entities.Swimmer;
import domain.entities.SwimmerRace;
import domain.enums.SwimmingDistances;
import domain.enums.SwimmingStyles;
import repository.interfaces.RaceRepository;
import repository.interfaces.SwimmerRaceRepository;
import repository.interfaces.SwimmerRepository;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SwimmerRaceDBRepository implements SwimmerRaceRepository {

    private final JdbcUtils jdbcUtils;
    private static final Logger logger = LogManager.getLogger();
    private SwimmerRepository swimmerRepository;
    private RaceRepository raceRepository;

    public SwimmerRaceDBRepository(Properties properties, SwimmerRepository swimmerRepository, RaceRepository raceRepository) {
        logger.info("Initialising SwimmerRaceDBRepository with properties {}", properties);
        jdbcUtils = new JdbcUtils(properties);
        this.swimmerRepository = swimmerRepository;
        this.raceRepository = raceRepository;
    }

    public void setSwimmerRepository(SwimmerRepository swimmerRepository) {
        this.swimmerRepository = swimmerRepository;
    }

    public void setRaceRepository(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    public Integer add(SwimmerRace elem) {
        logger.traceEntry("add(SwimmerRace = {})", elem);
        Integer id = null;

        Connection connection = jdbcUtils.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into main.SwimmersRaces (id_swimmer, id_race) values (?, ?);"
        ))
        {
            preparedStatement.setInt(1, elem.getSwimmer().getID());
            preparedStatement.setInt(2, elem.getRace().getID());
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
    public void delete(SwimmerRace elem) {

    }

    @Override
    public void update(SwimmerRace elem, Integer id) {

    }

    @Override
    public SwimmerRace findById(Integer id) {
        return null;
    }

    @Override
    public Integer getNumberOfSwimmersForRace(Race race) {
        logger.traceEntry("getNumberOfSwimmersForRace(race = {})", race);

        int numberOfSwimmers = 0;

        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select count(*) from main.SwimmersRaces where id_race = ?;"
        )) {
            preparedStatement.setInt(1, race.getID());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            numberOfSwimmers = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logger.traceExit("Result: numberOfSwimmers = {}", numberOfSwimmers);
    }

    @Override
    public List<Swimmer> findAllSwimmersForRace(Race race) {
        logger.traceEntry("getNumberOfSwimmersForRace(race = {})", race);
        List<Swimmer> allSwimmersForRace = new ArrayList<>();

        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select S.id, S.firstName, S.lastName, S.age from main.Swimmers S inner join SwimmersRaces SR on S.id = SR.id_swimmer where SR.id_race = ?;"
        )) {
            preparedStatement.setInt(1, race.getID());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                Integer age = resultSet.getInt(4);
                Swimmer swimmer = new Swimmer(id, firstName, lastName, age);
                allSwimmersForRace.add(swimmer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logger.traceExit("Result: allSwimmersForRace = {}", allSwimmersForRace);
    }

    @Override
    public List<Race> findAllRacesForSwimmer(Swimmer swimmer) {
        logger.traceEntry("findAllRacesForSwimmer(swimmer = {})", swimmer);

        List<Race> allRacesForSwimmer = new ArrayList<>();

        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select R.id, R.distance, R.style, R.swimmersNumber from main.Races R inner join SwimmersRaces SR on R.id = SR.id_race where SR.id_swimmer = ?;"
        )) {
            preparedStatement.setInt(1, swimmer.getID());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                Integer distance = resultSet.getInt(2);
                Integer style = resultSet.getInt(3);
                Integer age = resultSet.getInt(4);
                Race race = new Race(id,
                        SwimmingDistances.distanceFromInteger(distance),
                        SwimmingStyles.styleFromInteger(style),
                        age);
                allRacesForSwimmer.add(race);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logger.traceExit("Result: allRacesForSwimmer = {}", allRacesForSwimmer);
    }
}
