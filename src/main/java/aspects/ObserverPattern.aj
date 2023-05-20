package aspects;

import controller.MainController;
import domain.dtos.RaceDTO;
import domain.dtos.RaceDetailsDTO;
import observer.Subject;
import observer.Observer;
import service.Service;

import java.util.List;

public aspect ObserverPattern {
    declare parents:Service implements Subject;
    declare parents:MainController implements Observer;

    private Observer Subject.observer = null;

    public void Subject.addObserver(Observer ob) {
        observer = ob;
        System.out.println("Observer added!");
    }

    public void Subject.notifyObserver(List<RaceDetailsDTO> raceDetailsDTOs) {
        observer.update(raceDetailsDTOs);
        System.out.println("Observer notified!");
    }

    public void MainController.update(List<RaceDetailsDTO> raceDetailsDTOs) {
        List<RaceDTO> newData = raceDTOsModel.stream()
                .map(raceDTO -> {
                    if (raceDetailsDTOs.stream().anyMatch(raceDetailsDTO -> raceDetailsDTO.getSwimmingDistance() == raceDTO.getDistance() && raceDetailsDTO.getSwimmingStyle() == raceDTO.getStyle())) {
                        return new RaceDTO(raceDTO.getDistance(), raceDTO.getStyle(), raceDTO.getNoSwimmers() + 1);
                    } else {
                        return raceDTO;
                    }
                })
                .toList();
        raceDTOsModel.setAll(newData);
        System.out.println("Observer updated!");
    }

    // adding an Observer
    after(MainController controller, Service s): execution(void controller.Controller.setService(..)) && this(controller) && args(s) {
        s.addObserver(controller);
    }

    // notifying an Observer
    private static List<RaceDetailsDTO> raceDetailsList;

    before(Service s, List<RaceDetailsDTO> raceDetailsDTOs): execution(* service.Service.addSwimmer(..)) && target(s) && args(.., raceDetailsDTOs) {
        raceDetailsList = raceDetailsDTOs;
    }

    after(Service s): execution(* service.Service.addSwimmer(..)) && target(s) {
        s.notifyObserver(raceDetailsList);
    }
}
