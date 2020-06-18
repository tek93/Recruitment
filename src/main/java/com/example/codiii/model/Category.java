package com.example.codiii.model;


import com.example.codiii.model.Driver;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "driver_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Driver driver;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}