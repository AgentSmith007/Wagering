package ru.isys.groupwagering.model.dto;

import ru.isys.groupwagering.model.enums.WageringDataType;

public class WageringDataTypeDTO {
    WageringDataType wageringDataType;
    String description;

    public WageringDataTypeDTO(WageringDataType wageringDataType, String description) {
        this.wageringDataType = wageringDataType;
        this.description = description;
    }

    public WageringDataType getWageringDataType() {
        return wageringDataType;
    }

    public void setWageringDataType(WageringDataType wageringDataType) {
        this.wageringDataType = wageringDataType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
