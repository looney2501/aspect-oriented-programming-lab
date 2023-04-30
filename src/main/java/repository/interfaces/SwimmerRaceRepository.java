package repository.interfaces;

import domain.entities.Race;
import domain.entities.Swimmer;
import domain.entities.SwimmerRace;

import java.util.List;

public interface SwimmerRaceRepository extends Repository<Integer, SwimmerRace> {
    Integer getNumberOfSwimmersForRace(Race race);
    List<Swimmer> findAllSwimmersForRace(Race race);
    List<Race> findAllRacesForSwimmer(Swimmer swimmer);
}
