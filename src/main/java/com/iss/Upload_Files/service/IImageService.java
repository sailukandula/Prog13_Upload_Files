package com.iss.Upload_Files.service;

import com.iss.Upload_Files.entities.Image;

import java.util.List;

public interface IImageService {
    public void save(Image image);
    public List<Image> listImages();
}
