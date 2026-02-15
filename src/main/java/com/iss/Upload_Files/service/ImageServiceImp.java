package com.iss.Upload_Files.service;

import com.iss.Upload_Files.entities.Image;
import com.iss.Upload_Files.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImageServiceImp implements IImageService {
    @Autowired
    ImageRepository imageRepository;

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }

    @Override
    public List<Image> listImages() {
        return imageRepository.findAll();
    }
}
