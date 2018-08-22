package ru.isys.groupwagering.model.dto;

public class BetDTO {

    private String usersAnswer;

    private String wageringId;

    private String userLogin;

    public String getUsersAnswer() {
        return usersAnswer;
    }

    public void setUsersAnswer(String usersAnswer) {
        this.usersAnswer = usersAnswer;
    }

    public String getWageringId() {
        return wageringId;
    }

    public void setWageringId(String wageringId) {
        this.wageringId = wageringId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public BetDTO(String usersAnswer, String wageringId, String userLogin) {
        this.usersAnswer = usersAnswer;
        this.wageringId = wageringId;
        this.userLogin = userLogin;
    }
    public BetDTO(){}

}
