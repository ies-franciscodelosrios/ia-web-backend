package apirestful.iawebbackend.controller;

import apirestful.iawebbackend.services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/photo")
public class CloudinaryController {

    @Autowired
    CloudinaryService cloudinaryService;

    /**
     * Subir un MultipartFile a Cloudinary.
     * @param file
     * @return la URL asignada al archivo subido o null en su caso
     * error
     */
    @PostMapping("/upload/{codigo}")
    public @ResponseBody
    String upload(@RequestBody MultipartFile file, @PathVariable ("codigo") String codigo) {
        return cloudinaryService.uploadPhoto(file,codigo);
    }


    /**
     * Subir un MultipartFile a Cloudinary para sustituir al que ya hay.
     * @param file
     * @return la URL asignada al archivo subido o null en su caso
     * error
     */
    @PutMapping("/update/{codigo}")
    public @ResponseBody
    String update(@RequestBody MultipartFile file, @PathVariable ("codigo") String codigo) {
        return cloudinaryService.uploadPhoto(file,codigo);
    }



}
