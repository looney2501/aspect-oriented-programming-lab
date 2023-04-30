package domain.entities;

import java.io.Serializable;

public class SwimmerRace implements Identifiable<Integer>, Serializable {
    private Integer ID;
    private final Swimmer swimmer;
    private final Race race;

    public SwimmerRace(Swimmer swimmer, Race race) {
        this.swimmer = swimmer;
        this.race = race;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer id) {
        this.ID = id;
    }

    public Swimmer getSwimmer() {
        return swimmer;
    }

    public Race getRace() {
        return race;
    }

    @Override
    public String toString() {
        return "SwimmerRace{" +
                "ID=" + ID +
                ", swimmer=" + swimmer +
                ", race=" + race +
                '}';
    }
}
