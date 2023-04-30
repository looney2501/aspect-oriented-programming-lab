package service;

import domain.dtos.RaceDTO;
import domain.dtos.RaceDetailsDTO;
import domain.dtos.SwimmerDTO;
import domain.entities.Race;
import domain.entities.Swimmer;
import domain.entities.SwimmerRace;
import domain.enums.SwimmingDistances;
import domain.enums.SwimmingStyles;
import repository.interfaces.AdminRepository;
import repository.interfaces.RaceRepository;
import repository.interfaces.SwimmerRaceRepository;
import repository.interfaces.SwimmerRepository;
import utils.PasswordHashingUtils;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private AdminRepository adminRepository;
    private RaceRepository raceRepository;
    private SwimmerRepository swimmerRepository;
    private SwimmerRaceRepository swimmerRaceRepository;

    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void setRaceRepository(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public void setSwimmerRepository(SwimmerRepository swimmerRepository) {
        this.swimmerRepository = swimmerRepository;
    }

    public void setSwimmerRaceRepository(SwimmerRaceRepository swimmerRaceRepository) {
        this.swimmerRaceRepository = swimmerRaceRepository;
    }

    public boolean isExistingUser(String username, String password) throws NoSuchAlgorithmException {
        return adminRepository.findByUsernameAndPassword(username, PasswordHashingUtils.MD5Hashing(password)) != null;
    }

    public List<RaceDTO> findAllRacesDetails() {
        List<RaceDTO> raceDTOS = new ArrayList<>();
        for (Race race: raceRepository.findAllRaces()) {
            raceDTOS.add(new RaceDTO(race.getDistance(), race.getStyle(), swimmerRaceRepository.getNumberOfSwimmersForRace(race)));
        }
        return raceDTOS;
    }

    public List<SwimmerDTO> findAllSwimmersDetailsForRace(SwimmingDistances swimmingDistance, SwimmingStyles swimmingStyle) {
        List<SwimmerDTO> swimmerDTOS = new ArrayList<>();
        Race race = raceRepository.findRaceByDistanceAndStyle(swimmingDistance, swimmingStyle);
        for (Swimmer swimmer: swimmerRaceRepository.findAllSwimmersForRace(race)) {
            List<Race> racesForSwimmer = swimmerRaceRepository.findAllRacesForSwimmer(swimmer);
            List<RaceDetailsDTO> raceDetailsDTOS = racesForSwimmer.stream()
                            .map(x -> new RaceDetailsDTO(x.getDistance(), x.getStyle()))
                                    .toList();
            swimmerDTOS.add(new SwimmerDTO(swimmer, raceDetailsDTOS));
        }
        return swimmerDTOS;
    }

    public void addSwimmer(String firstName, String lastName, Integer age, List<RaceDetailsDTO> raceDetailsDTOS) {
        Swimmer swimmer = new Swimmer(firstName, lastName, age);
        Integer swimmerID = swimmerRepository.add(swimmer);
        swimmer.setID(swimmerID);

        for (var raceDetailDTO : raceDetailsDTOS) {
            Race race = raceRepository.findRaceByDistanceAndStyle(raceDetailDTO.getSwimmingDistance(), raceDetailDTO.getSwimmingStyle());
            SwimmerRace swimmerRace = new SwimmerRace(swimmer, race);
            swimmerRaceRepository.add(swimmerRace);
        }
    }
}
