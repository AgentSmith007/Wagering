package ru.isys.groupwagering.model.dto;

import ru.isys.groupwagering.model.enums.WageringStatus;

/**
 * DTO for sending statuses with descriptions to the page.
 * @author a.rahmaev
 * @version 1.0
 */
public class WageringStatusDTO {
    WageringStatus wageringStatus;
    String description;

    public WageringStatusDTO(WageringStatus wageringStatus, String description) {
        this.wageringStatus = wageringStatus;
        this.description = description;
    }
    public WageringStatusDTO(){}

    public WageringStatus getWageringStatus() {
        return wageringStatus;
    }

    public void setWageringStatus(WageringStatus wageringStatus) {
        this.wageringStatus = wageringStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


