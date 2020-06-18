package com.example.codiii.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "worker")
@Inheritance
public class Worker {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)

    private String firstName;

    @NotNull
    @Size(max = 250)
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
