package repository.interfaces;

import domain.entities.Race;
import domain.enums.SwimmingDistances;
import domain.enums.SwimmingStyles;

import java.util.List;

public interface RaceRepository extends Repository<Integer, Race> {
    Race findRaceByDistanceAndStyle(SwimmingDistances swimmingDistance, SwimmingStyles swimmingStyle);
    List<Race> findAllRaces();
}
