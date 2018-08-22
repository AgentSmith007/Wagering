package ru.isys.groupwagering.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat
public enum WageringStatus {
    DRAFT("Пари еще не открыто - черновик"),
    OPEN("Ставки принимаются."),
    DONE("Ставки сделаны."),
    FINISHED("Пари окончено."),
    CANCELLED("Пари временно остановлено.");

    private String description;

    WageringStatus(String description) {
        this.description = description;
    }

    @JsonCreator
    public static WageringStatus fromValue(String value) {
        return WageringStatus.valueOf(value);
    }

    @JsonValue
    public String getValue() {
        return this.toString();
    }

    public String getDescription() {
        return description;
    }
}
