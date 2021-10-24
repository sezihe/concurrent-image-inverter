package com.encentral.image.inverter.api;

import java.io.File;
import java.io.IOException;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 23/10/2021
 */
public interface IImageInverter {
    void invertImage(File image) throws IOException;
    File getInvertedImage();
}
