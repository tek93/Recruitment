package com.example.codiii.repository;

import com.example.codiii.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByDriverId(Long postId);
    Optional<Category> findByIdAndDriverId(Long id, Long postId);


}