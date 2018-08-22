package ru.isys.groupwagering.model.entity;


import javax.persistence.*;
import java.util.Objects;

/**
 * Class for Entity from table "TypicalUser"
 *
 * @author a.rahmaev
 * @version 1.0
 */


@Entity(name = "typical_user")
public class TypicalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "user_name")
    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypicalUser that = (TypicalUser) o;
        return id == that.id &&
                password == that.password &&
                Objects.equals(login, that.login) &&
                Objects.equals(email, that.email) &&
                Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, login, password, email, userName);
    }

    @Override
    public String toString() {
        return "TypicalUser{" +
                "login='" + login + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public TypicalUser( String login, String password, String email, String userName) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.userName = userName;
    }


    public TypicalUser(String login) {
        this.login = login;
    }
    public TypicalUser() {
    }
}





