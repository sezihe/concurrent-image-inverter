package controllers;

import com.encentral.image.inverter.api.IImageInverter;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import io.swagger.annotations.*;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.io.File;
import java.io.IOException;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 23/10/2021
 */
@Api(value = "ImageInverter")
public class ImageController extends Controller {
    @Inject
    IImageInverter iimageInverter;

    @ApiOperation(
            value = "Inverts An Image",
            notes = "Takes an image and inverts it's color",
            consumes = "multipart/form-data",
            produces = "application/json"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Image inverted Successfully"),
                    @ApiResponse(code = 400, message = "Bad Request. Can't find image!"),
                    @ApiResponse(code = 500, message = "Unexpected Internal Server Error. Try again later")
            }
    )
    @ApiImplicitParams(
            @ApiImplicitParam(
                    name = "image",
                    value = "Image to be inverted",
                    paramType = "formData",
                    type = "file",
                    required = true
            )
    )
    public Result invertImage() {
        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> picture = body.getFile("image");
        ObjectNode responseJson = JsonNodeFactory.instance.objectNode();

        if(picture != null) {
            File file = picture.getFile();
            try {
                iimageInverter.invertImage(file);
                responseJson.put("status", "Success");
                responseJson.put("message", "Image Inverted Successfully");
                return ok(responseJson);
            } catch (IOException e) {
                e.printStackTrace();
                return internalServerError();
            }
        } else {
            responseJson.put("status", "Error");
            responseJson.put("message", "Image is Null!");
            return badRequest(responseJson);
        }
    }

    @ApiOperation(
            value = "Gets an Inverted Image",
            produces = "image/png, application/json"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Inverted Image Retrieved Successfully"),
                    @ApiResponse(code = 400, message = "Bad Request. Can't find image!"),
                    @ApiResponse(code = 500, message = "Unexpected Internal Server Error. Try again later")
            }
    )
    @ApiImplicitParams(
            {}
    )
    public Result getInvertedImage() {
        ObjectNode responseJson = JsonNodeFactory.instance.objectNode();
        File image = iimageInverter.getInvertedImage();

        if(image != null && image.isFile())
            return ok(image);
        else {
            responseJson.put("status", "error");
            responseJson.put("message", "No Inverted Image Found");
            return badRequest(responseJson);
        }
    }
}
