package domain.entities;

import java.io.Serializable;
import java.util.Objects;

public class Swimmer implements Identifiable<Integer>, Serializable {

    private Integer ID;
    private String firstName;
    private String lastName;
    private Integer age;

    public Swimmer(Integer ID, String firstName, String lastName, Integer age) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Swimmer(String firstName, String lastName, Integer age) {
        this.ID = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer id) {
        ID = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Swimmer{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Swimmer swimmer = (Swimmer) o;
        return Objects.equals(firstName, swimmer.firstName) && Objects.equals(lastName, swimmer.lastName) && Objects.equals(age, swimmer.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
