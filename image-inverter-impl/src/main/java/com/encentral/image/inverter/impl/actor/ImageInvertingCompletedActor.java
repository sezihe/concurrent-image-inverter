package com.encentral.image.inverter.impl.actor;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 24/10/2021
 */
public class ImageInvertingCompletedActor extends AbstractBehavior<ImageInvertingCompletedActor.ImageInvertCompleteRequest> {
    private int requestCount = 1;
    public static final class ImageInvertCompleteRequest {
        public final int actorCount;
        public final BufferedImage image;

        public ImageInvertCompleteRequest(int actorCount, BufferedImage image) {
            this.actorCount = actorCount;
            this.image = image;
        }
    }

    public static Behavior<ImageInvertCompleteRequest> create() {
        return Behaviors.setup(ImageInvertingCompletedActor::new);
    }

    private ImageInvertingCompletedActor(ActorContext<ImageInvertCompleteRequest> context) {
        super();
    }

    @Override
    public Receive<ImageInvertCompleteRequest> createReceive() {
        return newReceiveBuilder()
                .onMessage(ImageInvertCompleteRequest.class, this::onImageInvertCompleteRequest)
                .build();
    }

    private Behavior<ImageInvertCompleteRequest> onImageInvertCompleteRequest(ImageInvertCompleteRequest imgInvertCompleteRequest) {
        if(requestCount == imgInvertCompleteRequest.actorCount) {
            // write to disk
            String userHomeFolder = System.getProperty("user.home");
            File BICIFolder = new File(userHomeFolder, "BICI");
            if(!BICIFolder.isDirectory())
                BICIFolder.mkdir();

            File newImage = new File(BICIFolder, "newImage.png");
            try {
                ImageIO.write(imgInvertCompleteRequest.image, "png", newImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            requestCount++;
        return this;
    }
}
