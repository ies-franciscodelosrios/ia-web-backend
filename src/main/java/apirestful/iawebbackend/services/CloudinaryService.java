package apirestful.iawebbackend.services;

import apirestful.iawebbackend.exceptions.RecordNotFoundException;
import apirestful.iawebbackend.model.User;
import apirestful.iawebbackend.repository.UserRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


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
            "cloud_name", "dgzlsuwnt",
            "api_key", "941853375929259",
            "api_secret", "pnDDq0I0worwC252ijbwohp-Oj8",
            "secure", true));


    /**
     * Method to upload a profile photo to Cloudinary
     * @param file
     * @param idnavision
     * @return a profile photo on Cloudinary
     */
    public String uploadPhoto(MultipartFile file, String idnavision) throws RecordNotFoundException, NullPointerException, IllegalArgumentException  {
            if (file != null && idnavision != null) {
                try {
                    User userbyID = userRepository.getByIdNavision(idnavision);
                    if(userbyID!=null ){
                        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                        String publicId = uploadResult.get("url").toString();
                        userbyID.setProfile_Picture(publicId);
                        userRepository.save(userbyID);
                        return publicId;
                    }else{
                        throw new RecordNotFoundException("The user with IdNavision: " + idnavision + " dont exist");
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
            } else {
                throw new NullPointerException("Null value");
        }
      }
    }
