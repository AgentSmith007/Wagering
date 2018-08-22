package ru.isys.groupwagering.model.dto;

import ru.isys.groupwagering.model.enums.WageringDataType;
import ru.isys.groupwagering.model.enums.WageringStatus;

public class WageringDTO {

    private String name;

    private String description;

    private WageringStatus status;

    private String prizeDescription;

    private WageringDataType wageringDataType;

    private String idOfTypeOfWagering;

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

    public WageringStatus getStatus() {
        return status;
    }

    public void setStatus(WageringStatus status) {
        this.status = status;
    }

    public String getPrizeDescription() {
        return prizeDescription;
    }

    public void setPrizeDescription(String prizeDescription) {
        this.prizeDescription = prizeDescription;
    }

    public WageringDataType getWageringDataType() {
        return wageringDataType;
    }

    public void setWageringDataType(WageringDataType wageringDataType) {
        this.wageringDataType = wageringDataType;
    }

    public String getIdOfTypeOfWagering() {
        return idOfTypeOfWagering;
    }

    public void setIdOfTypeOfWagering(String idOfTypeOfWagering) {
        this.idOfTypeOfWagering = idOfTypeOfWagering;
    }

    public WageringDTO(String name, String description, WageringStatus status,
                       String prizeDescription, WageringDataType wageringDataType,
                       String idOfTypeOfWagering) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.prizeDescription = prizeDescription;
        this.wageringDataType = wageringDataType;

        this.idOfTypeOfWagering = idOfTypeOfWagering;

    }

    @Override
    public String toString() {
        return "WageringDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", prizeDescription='" + prizeDescription + '\'' +
                ", wageringDataType='" + wageringDataType + '\'' +
                ", idOfTypeOfWagering='" + idOfTypeOfWagering + '\'' +
                '}';
    }

    public WageringDTO() {
    }
}
