package ru.isys.groupwagering.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class for Entity from table "TypeOfWagering"
 *
 * @author a.rahmaev
 * @version 1.0
 */

@Entity(name = "type_of_wagering")
public class TypeOfWagering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "rules")
    private String rules;

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


    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeOfWagering that = (TypeOfWagering) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(rules, that.rules);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, rules);
    }

    @Override
    public String toString() {
        return "TypeOfWagering{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rules='" + rules + '\'' +
                '}';
    }

    public TypeOfWagering(int id, String name, String rules) {
        this.id = id;
        this.name = name;
        this.rules = rules;
    }

    public TypeOfWagering(int id) {
        this.id=id;
    }

    public TypeOfWagering() {
    }
}
