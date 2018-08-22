package ru.isys.groupwagering.model.dto;

public class UserProfileDTO {

    private String login;

    private String email;

    private String name;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserProfileDTO(String login, String email, String name) {
        this.login = login;
        this.email = email;
        this.name = name;
    }
}
