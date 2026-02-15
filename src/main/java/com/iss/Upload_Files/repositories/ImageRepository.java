package com.iss.Upload_Files.repositories;

import com.iss.Upload_Files.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Integer > {
}
