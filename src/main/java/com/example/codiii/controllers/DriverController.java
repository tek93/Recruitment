package com.example.codiii.controllers;

import com.example.codiii.ResourceNotFoundException;
import com.example.codiii.model.Driver;
import com.example.codiii.repository.DriverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
public class DriverController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private DriverRepository driverRepository;
    @CrossOrigin(value = "http://localhost:4200", methods =   RequestMethod.GET)
    @GetMapping("/drivers")
    public List<Driver> getAllDriver() {

        return driverRepository.findAll();
    }



    @GetMapping("/driver/{id}")
    public Optional<Driver> getDriverById(@PathVariable Long id){
        Optional<Driver> driver = driverRepository.findById(id);

        return driver;


    }
    @CrossOrigin(value = "http://localhost:4200", methods =   RequestMethod.POST)
    @PostMapping("/drivers")
    public Driver createDriver(@Valid @RequestBody Driver driver) throws Exception {

        String firstName = driver.getFirstName();
        String lastName = driver.getLastName();
       Optional<Driver> firstNameDB =driverRepository.findByFirstName( firstName);
       Optional<Driver> lastNameDB =driverRepository.findByLastName( lastName);


        if((lastNameDB.isPresent())&&(firstNameDB.isPresent())){

          throw new Exception("Driver with this First name and last name already exist!");

        }
        else {
            return driverRepository.save(driver);
        }

    }
    @CrossOrigin(value = "http://localhost:4200", methods =   RequestMethod.PUT)
    @PutMapping("/drivers/{driverId}")
    public Driver updateDriver(@PathVariable Long driverId, @Valid @RequestBody Driver driverRequest) {
        return driverRepository.findById(driverId).map(post -> {
            post.setFirstName(driverRequest.getFirstName());
            post.setLastName(driverRequest.getLastName());

            return driverRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("DriverId " + driverId + " not found"));
    }

    @CrossOrigin(value = "http://localhost:4200")
    @DeleteMapping("/drivers/{driverId}")
    public ResponseEntity<?> deleteDriver(@PathVariable Long driverId) {
        return driverRepository.findById(driverId).map(post -> {
            driverRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("DriverId " + driverId + " not found"));
    }
}
