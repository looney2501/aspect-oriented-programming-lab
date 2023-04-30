package domain.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * Abstract data type representing the administrator of the application.
 */
public class Admin implements Identifiable<Integer>, Serializable {

    private Integer ID;
    private String username;
    private String password;

    public Admin(Integer ID, String username, String password) {
        this.ID = ID;
        this.username = username;
        this.password = password;
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer id) {
       this.ID = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(username, admin.username) && Objects.equals(password, admin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "ID=" + ID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
