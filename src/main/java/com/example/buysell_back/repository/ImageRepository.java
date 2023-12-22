package com.example.buysell_back.repository;

import com.example.buysell_back.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findImageById(Long id);

    Optional<Image> findImageByProductId(Long id);
}
