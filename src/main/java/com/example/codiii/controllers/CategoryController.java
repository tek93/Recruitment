package com.example.codiii.controllers;

import com.example.codiii.ResourceNotFoundException;
import com.example.codiii.model.Category;
import com.example.codiii.repository.CategoryRepository;
import com.example.codiii.repository.DriverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DriverRepository driverRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @CrossOrigin(value = "http://localhost:4200", methods =   RequestMethod.GET)
    @GetMapping("/drivers/{driverId}/category")
    public List<Category> getAllCategoryByDriverId(@PathVariable (value = "driverId") Long driverId) {
        return  categoryRepository.findByDriverId(driverId);
    }
    @CrossOrigin(value = "http://localhost:4200", methods =   RequestMethod.POST)
    @PostMapping("/drivers/{driverId}/category")
    public Category createCategory(@PathVariable (value = "driverId") Long driverId,
                                 @Valid @RequestBody Category category) {
        return driverRepository.findById(driverId).map(driver -> {
            category.setDriver(driver);
            return categoryRepository.save(category);
        }).orElseThrow(() -> new ResourceNotFoundException("DriverId " + driverId + " not found"));
    }
    @CrossOrigin(value = "http://localhost:4200", methods =   RequestMethod.PUT)
    @PutMapping("/drivers/{driverId}/category/{categoryId}")
    public Category updateCategory(@PathVariable(value = "driverId") Long driverId,
                                 @PathVariable (value = "categoryId") Long categoryId,
                                 @Valid @RequestBody Category categoryRequest) {
        if(!driverRepository.existsById(driverId)) {
            throw new ResourceNotFoundException("Driverd " + driverId + " not found");
        }

        return categoryRepository.findById(categoryId).map(category -> {
            category.setText(categoryRequest.getText());
            return categoryRepository.save(category);
        }).orElseThrow(() -> new ResourceNotFoundException("CategoryId " + categoryId + "not found"));
    }
//    @CrossOrigin(value = "http://localhost:4200")
//    @DeleteMapping("/drivers/{driverId}/category/{categoryId}")
//    public ResponseEntity<?> deletecCategory(@PathVariable (value = "driverId") Long driverId,
//                                           @PathVariable (value = "categoryId") Long categoryId) {
//        return categoryRepository.findByIdAndDriverId(categoryId, driverId).map(category -> {
//            categoryRepository.delete(category);
//            return ResponseEntity.ok().build();
//        }).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId + " and driverId " + driverId));
//    }
    @CrossOrigin(value = "http://localhost:4200")
    @DeleteMapping("/drivers/category/{categoryId}")
    public ResponseEntity<?> deletecCategory2(
                                             @PathVariable (value = "categoryId") Long categoryId) {
        return categoryRepository.findById(categoryId).map(category -> {
            categoryRepository.delete(category);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId + " and driverId "));
    }
}
