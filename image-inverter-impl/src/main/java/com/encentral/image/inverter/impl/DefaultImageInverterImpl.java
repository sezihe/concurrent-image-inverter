package com.encentral.image.inverter.impl;

import akka.actor.typed.ActorSystem;
import com.encentral.image.inverter.api.IImageInverter;
import com.encentral.image.inverter.impl.actor.ImageInverterActor;
import play.libs.F;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 23/10/2021
 */
public class DefaultImageInverterImpl implements IImageInverter {
    @Override
    public void invertImage(File image) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(image);
        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();

        System.out.println(width + ";" + height);

        ActorSystem<ImageInverterActor.ImageInvertRequest> mSystem
                = ActorSystem.create(ImageInverterActor.create(), "main");

        mSystem.tell(new ImageInverterActor.ImageInvertRequest(bufferedImage, 0, 0, width, height));
        // mSystem.tell(new ImageInverterActor.ImageInvertRequest(bufferedImage, 0, 250, width, height));

    }
}
