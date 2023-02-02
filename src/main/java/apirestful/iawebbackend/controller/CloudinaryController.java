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
     * Upload a MultipartFile to Cloudinary.
     * @param file
     * @return the publicId assigned to the uploaded file, or null in case of
     * error
     */
    @PostMapping("/upload/{codigo}")
    public @ResponseBody
    String upload(@RequestBody MultipartFile file, @PathVariable ("codigo") String codigo) {
        return cloudinaryService.uploadPhoto(file,codigo);
    }



}
