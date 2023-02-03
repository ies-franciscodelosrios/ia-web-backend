package apirestful.iawebbackend.services;

import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.repository.UserRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    private UserRepository userRepository;

    /*
     * Constructor de Cloudinary con los parámetros de la API para conectarse a ella
     */
    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dmcgwm5nm",
            "api_key", "791175161344374",
            "api_secret", "qIeeadio8YTwpehl8dZ1satXiSU",
            "secure", true));


    /**
     *  Método para subir a cloudinary a traves de su API una imagen subida por el usuario
     * @param file
     * @param codigo
     * @return URL de la imagen subibda por el usuario
     */
    public String uploadPhoto(MultipartFile file, String codigo) {
        try {
            User userbyID = userRepository.getByCodigo(codigo);
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String publicId = uploadResult.get("url").toString();
            userbyID.setProfile_Picture(publicId);
            userRepository.save(userbyID);
            return publicId;
        } catch (Exception ex) {
            ex.getMessage();
            return null;
        }

    }




}