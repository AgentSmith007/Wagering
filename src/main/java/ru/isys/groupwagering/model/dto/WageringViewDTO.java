package ru.isys.groupwagering.model.dto;

import ru.isys.groupwagering.model.dao.WageringDAO;
import ru.isys.groupwagering.model.enums.WageringStatus;

public class WageringViewDTO {
    private int id;

    private String name;

    private String description;

    protected WageringStatusDTO statusDTO;

    private String authorLogin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WageringStatusDTO getStatusDTO() {
        return statusDTO;
    }

    public void setStatusDTO(WageringStatusDTO status) {
        this.statusDTO = status;
    }

    public String getAuthorLogin() {
        return authorLogin;
    }

    public void setAuthorLogin(String authorLogin) {
        this.authorLogin = authorLogin;
    }

    public WageringViewDTO(int id, String name, String description, WageringStatusDTO statusDTO, String authorLogin) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.statusDTO = statusDTO;
        this.authorLogin = authorLogin;
    }

    public WageringViewDTO(){

    }


}
