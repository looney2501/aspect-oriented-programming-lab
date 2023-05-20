package observer;

import domain.dtos.RaceDTO;
import domain.dtos.RaceDetailsDTO;

import java.util.List;

public interface Observer {
    void update(List<RaceDetailsDTO> raceDetailsDTOs);
}
