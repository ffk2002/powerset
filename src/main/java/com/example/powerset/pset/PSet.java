package com.example.powerset.pset;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
//import java.util.Date;
import java.util.Objects;

@Entity
public class PSet {

    private @Id @GeneratedValue Long id;
    private String type;
    private Long reps;
    private Long weight;
    private LocalDate date = LocalDate.now();

    public PSet() {
    }

    public PSet(String type, Long reps, Long weight, LocalDate date) {
        this.type = type;
        this.reps = reps;
        this.weight = weight;
        this.date = date;
    }


    public PSet(String type, Long reps, Long weight) {
        this.type = type;
        this.reps = reps;
        this.weight = weight;
//        this.date = new Date();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getReps() {
        return this.reps;
    }

    public void setReps(Long reps) {
        this.reps = reps;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof PSet set))
            return false;
        return Objects.equals(this.id, set.id)
                && Objects.equals(this.type, set.type)
                && Objects.equals(this.reps, set.reps)
                && Objects.equals(this.weight, set.weight)
                && Objects.equals(this.date, set.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.type, this.reps, this.weight, this.date);
    }

    @Override
    public String toString() {
        return "Set{" +
                "id=" + this.id +
                ", type='" + this.type + '\'' +
                ", reps=" + this.reps +
                ", weight=" + this.weight +
                ", date=" + this.date +
                '}';
    }
}
