package apirestful.iawebbackend.controller;

import apirestful.iawebbackend.services.CloudinaryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/photo")
public class CloudinaryController {

    @Autowired
    CloudinaryService cloudinaryService;



    /**
     * Method to upload a profile photo to Cloudinary
     * @param file
     * @return la URL asignada al archivo subido o null en su caso
     * error
     */
    @ApiOperation(value = "Upload to Cloudinary a profile photo", notes = "Return a profile photo on Cloudinary")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully petition"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "No token authorised"),
            @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PutMapping("/{idnavision}")
    public @ResponseBody
    String update(@RequestBody MultipartFile file, @PathVariable String idnavision) {
        return cloudinaryService.uploadPhoto(file,idnavision);
    }



}
