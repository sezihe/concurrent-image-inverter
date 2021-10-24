package com.encentral.image.inverter.impl;

import akka.actor.typed.ActorSystem;
import com.encentral.image.inverter.api.IImageInverter;
import com.encentral.image.inverter.impl.actor.ImageInverterActor;

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

        ActorSystem<ImageInverterActor.ImageInvertRequest> mSystem
                = ActorSystem.create(ImageInverterActor.create(), "main");

        int actorCount = (int) Math.ceil(((double) width) / 200);
        int widthEndingPoint = 0;
        for(int i = 0; i < actorCount; i++) {
            int widthStartingPoint = i * 200;
            widthEndingPoint += 200;
            if(widthStartingPoint < width) {
                if(widthEndingPoint <= width) {
                    mSystem.tell(new ImageInverterActor.ImageInvertRequest(actorCount, bufferedImage, 0, widthStartingPoint, widthEndingPoint, height));
                    // System.out.println("Sending request. SWidth: " + widthStartingPoint + "; EWidth: " + widthEndingPoint);
                } else {
                    mSystem.tell(new ImageInverterActor.ImageInvertRequest(actorCount, bufferedImage, 0, widthStartingPoint, width, height));
                }
            } else {
                System.out.println("SWidth: " + widthStartingPoint);
            }
        }
    }

    @Override
    public File getInvertedImage() {
        String userHomeFolder = System.getProperty("user.home");
        File BICIFolder = new File(userHomeFolder, "BICI");

        return new File(BICIFolder, "newImage.png");
    }
}
