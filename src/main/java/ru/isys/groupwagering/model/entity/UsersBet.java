package ru.isys.groupwagering.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class for Entity from table "UsersBet"
 *
 * @author a.rahmaev
 * @version 1.0
 */

@Entity(name = "users_bet")
public class UsersBet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "users_answer")
    private String usersAnswer;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "wagering_id")
    private Wagering wagering;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private TypicalUser typicalUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsersAnswer() {
        return usersAnswer;
    }

    public void setUsersAnswer(String usersAnswer) {
        this.usersAnswer = usersAnswer;
    }

    public Wagering getWagering() {
        return wagering;
    }

    public void setWagering(Wagering wagering) {
        this.wagering = wagering;
    }

    public TypicalUser getTypicalUser() {
        return typicalUser;
    }

    public void setTypicalUser(TypicalUser typicalUser) {
        this.typicalUser = typicalUser;
    }

    public UsersBet( String usersAnswer, Wagering wagering, TypicalUser typicalUser) {
        this.usersAnswer = usersAnswer;
        this.wagering = wagering;
        this.typicalUser = typicalUser;
    }

    @Override
    public String toString() {
        return "UsersBet{" +
                "id=" + id +
                ", usersAnswer='" + usersAnswer + '\'' +
                ", wagering=" + wagering +
                ", typicalUser=" + typicalUser +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersBet usersBet = (UsersBet) o;
        return id == usersBet.id &&
                Objects.equals(usersAnswer, usersBet.usersAnswer) &&
                Objects.equals(wagering, usersBet.wagering) &&
                Objects.equals(typicalUser, usersBet.typicalUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, usersAnswer, wagering, typicalUser);
    }

    public UsersBet() {
    }
}
