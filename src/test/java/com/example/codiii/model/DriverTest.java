package com.example.codiii.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {
    Driver driver;

    @Test
    void getLastName() {
        driver = new Driver();
        driver.setLastName("Lukasz");
        String name = "Lukasz";
        Assert.assertSame(driver.getLastName(), name );
    }
}