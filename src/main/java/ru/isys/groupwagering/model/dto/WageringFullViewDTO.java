package ru.isys.groupwagering.model.dto;

import java.util.List;

public class WageringFullViewDTO extends WageringViewDTO {

    private String typeDescription;

    private String prizeDescription;

    private WageringDataTypeDTO wageringDataType;

    private String wageringResult;

    private long createTime;

    private List<UserWithBetDTO> usersWithBet;

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public String getPrizeDescription() {
        return prizeDescription;
    }

    public void setPrizeDescription(String prizeDescription) {
        this.prizeDescription = prizeDescription;
    }

    public String getWageringResult() {
        return wageringResult;
    }

    public void setWageringResult(String wageringResult) {
        this.wageringResult = wageringResult;
    }

    public List<UserWithBetDTO> getUsersWithBet() {
        return usersWithBet;
    }

    public void setUsersWithBet(List<UserWithBetDTO> usersWithBet) {
        this.usersWithBet = usersWithBet;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }


    public WageringDataTypeDTO getWageringDataType() {
        return wageringDataType;
    }

    public void setWageringDataType(WageringDataTypeDTO wageringDataType) {
        this.wageringDataType = wageringDataType;
    }


    public WageringFullViewDTO(int id, String name, String description, WageringStatusDTO statusDTO,
                               String authorLogin, String typeDescription, String prizeDescription,
                               WageringDataTypeDTO wageringDataType, String wageringResult,
                               long createTime,
                               List<UserWithBetDTO> usersWithBet) {
        super(id, name, description, statusDTO, authorLogin);
        this.typeDescription = typeDescription;
        this.prizeDescription = prizeDescription;
        this.wageringDataType = wageringDataType;
        this.wageringResult = wageringResult;
        this.createTime = createTime;
        this.usersWithBet = usersWithBet;
    }

    public WageringFullViewDTO() {
    }
}
