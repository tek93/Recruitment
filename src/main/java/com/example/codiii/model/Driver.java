package com.example.codiii.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Driver extends Worker {
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "driver")
    private Set<Category> categories = new HashSet<>();


    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
