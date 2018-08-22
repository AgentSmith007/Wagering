package ru.isys.groupwagering.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.isys.groupwagering.model.enums.WageringDataType;
import ru.isys.groupwagering.model.enums.WageringStatus;

import javax.persistence.*;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * dao Class for Entity from table "Wagering"
 *
 * @author a.rahmaev
 * @version 1.0
 */
@Entity(name = "wagering")
public class Wagering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private WageringStatus status;

    @Column(name = "prizeDescription")
    private String prizeDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "wagering_data_type")
    private WageringDataType wageringDataType;

    @Column(name = "wagering_result")
    private String wageringResult;

    @Column(name = "create_time")
    private long createTime;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private TypicalUser typicalUser;

    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private TypeOfWagering typeOfWagering;

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

    public String getWageringResult() {
        return wageringResult;
    }

    public void setWageringResult(String wageringResult) {
        this.wageringResult = wageringResult;
    }

    public TypicalUser getTypicalUser() {
        return typicalUser;
    }

    public void setTypicalUser(TypicalUser typicalUser) {
        this.typicalUser = typicalUser;
    }

    public TypeOfWagering getTypeOfWagering() {
        return typeOfWagering;
    }

    public void setTypeOfWagering(TypeOfWagering typeOfWagering) {
        this.typeOfWagering = typeOfWagering;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Wagering(String name, String description, WageringStatus status, String prizeDescription,
                    WageringDataType wageringDataType, String wageringResult, long createTime, TypicalUser typicalUser,
                    TypeOfWagering typeOfWagering) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.prizeDescription = prizeDescription;
        this.wageringDataType = wageringDataType;
        this.wageringResult = wageringResult;
        this.createTime = createTime;
        this.typicalUser = typicalUser;
        this.typeOfWagering = typeOfWagering;
    }

    public Wagering(int wageringID,String name, String description, WageringStatus status, String prizeDescription,
                    WageringDataType wageringDataType, String wageringResult, long createTime, TypicalUser typicalUser,
                    TypeOfWagering typeOfWagering) {
        this.id=wageringID;
        this.name = name;
        this.description = description;
        this.status = status;
        this.prizeDescription = prizeDescription;
        this.wageringDataType = wageringDataType;
        this.wageringResult = wageringResult;
        this.createTime = createTime;
        this.typicalUser = typicalUser;
        this.typeOfWagering = typeOfWagering;
    }


    public Wagering() {
    }

    @Override
    public String toString() {
        return "Wagering{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", prizeDescription='" + prizeDescription + '\'' +
                ", wageringDataType='" + wageringDataType + '\'' +
                ", wageringResult='" + wageringResult + '\'' +
                ", createTime=" + createTime +
                ", typicalUser=" + typicalUser +
                ", typeOfWagering=" + typeOfWagering +
                '}';
    }
}
