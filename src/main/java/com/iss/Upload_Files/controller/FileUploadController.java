package com.iss.Upload_Files.controller;

import com.iss.Upload_Files.entities.Image;
import com.iss.Upload_Files.service.IImageService;
import com.iss.Upload_Files.service.ImageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/image")
public class FileUploadController {

    @Value("${app.upload.images.dir}")
    String imageUploadPath;

    @Autowired
    IImageService imageService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/uploadfile")
    public String uploadFile(@RequestParam("image") MultipartFile multipartFile, Model model) {
        try {
            Image image = new Image();
            image.setImage(multipartFile.getBytes());
            image.setFilename(multipartFile.getOriginalFilename());
            Path path = Paths.get(imageUploadPath+"/"+multipartFile.getOriginalFilename());

            multipartFile.transferTo(path);
            imageService.save(image);

            model.addAttribute("message", "File uploaded successfully");
            return "index";
        } catch (IOException ex) {
            ex.printStackTrace();
            model.addAttribute("message", "file not uploaded");
        }
        return "index";
    }

    @GetMapping("/displayImage")
    public ResponseEntity<byte[]> getImage(String filename) throws IOException {

            Path path = Paths.get(imageUploadPath + "/" + filename);
            FileInputStream fin = new FileInputStream(path.toFile());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(fin.readAllBytes());
    }

    @GetMapping("/listImages")
    public String getImages(Model model) throws IOException {

        List<Image> imageList = imageService.listImages();
        model.addAttribute("images", imageService.listImages());
        return "listImages";
    }

}
