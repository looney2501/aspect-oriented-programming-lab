package observer;

import domain.dtos.RaceDTO;
import domain.dtos.RaceDetailsDTO;

import java.util.List;

public interface Subject {
    void addObserver(Observer observer);
    void notifyObserver(List<RaceDetailsDTO> raceDetailsDTOs);
}
