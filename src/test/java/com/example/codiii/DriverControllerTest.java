package com.example.codiii;

import com.example.codiii.model.Driver;
import com.example.codiii.repository.DriverRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@WebMvcTest(DriverController.class)
@SpringBootTest
public class DriverControllerTest {



    MockMvc mockMvc;
    @Autowired
     WebApplicationContext context;
    @Autowired
    DriverRepository driverRepository;

    ObjectMapper om = new ObjectMapper();
    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void shouldThatDriverNameHaveTheSameNameInDB() throws Exception {
        Driver driver = new Driver();
        driver.setFirstName("Tomek4");
        String jsnoRequest = om.writeValueAsString(driver);
        MvcResult result = mockMvc.perform(post("/drivers").content(jsnoRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent= result.getResponse().getContentAsString();
        Driver response = om.readValue(resultContent, Driver.class);
        Assert.assertEquals("Check that field First name is the same ", response.getFirstName(), ("Tomek4"));

    }
    @Test
    public void shouldThatDriverLastNameHaveTheSameLastNameInDB() throws Exception {
        Driver driver = new Driver();
        driver.setLastName("Kowalski");
        String jsnoRequest = om.writeValueAsString(driver);
        MvcResult result = mockMvc.perform(post("/drivers").content(jsnoRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent= result.getResponse().getContentAsString();
        Driver response = om.readValue(resultContent, Driver.class);
        Assert.assertEquals("Check that field First name is the same ", response.getLastName(), ("Kowalski"));

    }

    @Test
    public void shouldThatDriverIsAddedToDB() throws Exception {
        Driver driver = new Driver();

        driver.setFirstName("Tomek8");
        String jsnoRequest = om.writeValueAsString(driver);
        MvcResult result = mockMvc.perform(post("/drivers").content(jsnoRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
         Assert.assertNotNull((driverRepository.findAll()));

    }








}