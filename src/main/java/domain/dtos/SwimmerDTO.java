package domain.dtos;

import domain.entities.Swimmer;

import java.util.List;

public class SwimmerDTO {
    private final Swimmer swimmer;
    private final List<RaceDetailsDTO> raceDetailsDTOS;

    public SwimmerDTO(Swimmer swimmer, List<RaceDetailsDTO> races) {
        this.swimmer = swimmer;
        this.raceDetailsDTOS = races;
    }

    public Swimmer getSwimmer() {
        return swimmer;
    }

    public List<RaceDetailsDTO> getRaceDetailsDTOS() {
        return raceDetailsDTOS;
    }

    public String getRaces() {
        return raceDetailsDTOS.stream()
                .map(RaceDetailsDTO::toString)
                .reduce("", (subtotal, element) -> subtotal + element);
    }
}
