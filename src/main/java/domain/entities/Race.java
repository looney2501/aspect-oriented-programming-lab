package domain.entities;

import domain.enums.SwimmingDistances;
import domain.enums.SwimmingStyles;

import java.io.Serializable;
import java.util.Objects;

public class Race implements Identifiable<Integer>, Serializable {

    private Integer ID;
    private SwimmingDistances distance;
    private SwimmingStyles style;
    private Integer swimmersNumber;

    public Race(Integer ID, SwimmingDistances distance, SwimmingStyles style, Integer swimmersNumber) {
        this.ID = ID;
        this.distance = distance;
        this.style = style;
        this.swimmersNumber = swimmersNumber;
    }

    public Race(SwimmingDistances distance, SwimmingStyles style, Integer swimmersNumber) {
        this.ID = null;
        this.distance = distance;
        this.style = style;
        this.swimmersNumber = swimmersNumber;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer id) {
        ID = id;
    }

    public SwimmingDistances getDistance() {
        return distance;
    }

    public void setDistance(SwimmingDistances distance) {
        this.distance = distance;
    }

    public SwimmingStyles getStyle() {
        return style;
    }

    public void setStyle(SwimmingStyles style) {
        this.style = style;
    }

    public Integer getSwimmersNumber() {
        return swimmersNumber;
    }

    public void setSwimmersNumber(Integer swimmersNumber) {
        this.swimmersNumber = swimmersNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Race race = (Race) o;
        return distance == race.distance && style == race.style && Objects.equals(swimmersNumber, race.swimmersNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        return "Race{" +
                "ID=" + ID +
                ", distance=" + distance +
                ", style=" + style +
                ", swimmersNumber=" + swimmersNumber +
                '}';
    }
}
