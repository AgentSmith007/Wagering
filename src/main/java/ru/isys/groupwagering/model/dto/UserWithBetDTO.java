package ru.isys.groupwagering.model.dto;

public class UserWithBetDTO extends UserProfileDTO {
    private String usersBet;

    public UserWithBetDTO(String login, String email, String name, String usersBet) {
        super(login, email, name);
        this.usersBet = usersBet;
    }

    public String getUsersBet() {
        return usersBet;
    }

    public void setUsersBet(String usersBet) {
        this.usersBet = usersBet;
    }
}
