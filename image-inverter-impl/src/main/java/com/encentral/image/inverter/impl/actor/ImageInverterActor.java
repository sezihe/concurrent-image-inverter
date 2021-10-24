package com.encentral.image.inverter.impl.actor;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.awt.image.BufferedImage;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 23/10/2021
 */
public class ImageInverterActor extends AbstractBehavior<ImageInverterActor.ImageInvertRequest> {
    public static final class ImageInvertRequest {
        public final int actorCount;
        public final BufferedImage image;
        public final int heightStartingPoint;
        public final int widthStartingPoint;
        public final int width;
        public final int height;

        public ImageInvertRequest(int actorCount, BufferedImage image, int heightStartingPoint, int widthStartingPoint, int width, int height) {
            this.actorCount = actorCount;
            this.image = image;
            this.heightStartingPoint = heightStartingPoint;
            this.widthStartingPoint = widthStartingPoint;
            this.width = width;
            this.height = height;
        }
    }

    public static Behavior<ImageInvertRequest> create() {
        return Behaviors.setup(ImageInverterActor::new);
    }

    private final ActorRef<ImageInvertingCompletedActor.ImageInvertCompleteRequest> invertingComplete;
    private ImageInverterActor (ActorContext<ImageInvertRequest> context) {
        super();
        invertingComplete = context.spawn(ImageInvertingCompletedActor.create(), "invertingComplete");
    }

    @Override
    public Receive<ImageInvertRequest> createReceive() {
        return newReceiveBuilder()
                .onMessage(ImageInvertRequest.class, this::onNewImageInvertRequest)
                .build();
    }

    private Behavior<ImageInvertRequest> onNewImageInvertRequest(ImageInvertRequest imageInvertRequest) {
        BufferedImage image = imageInvertRequest.image;
        for(int x = imageInvertRequest.widthStartingPoint; x < imageInvertRequest.width; x++) {
            for(int y = imageInvertRequest.heightStartingPoint; y < imageInvertRequest.height; y++) {
                int p = image.getRGB(x, y);

                int a = (p>>24) & 0xff;
                int r = (p>>16) & 0xff;
                int g = (p>>8) & 0xff;
                int b = p & 0xff;

                // check if it's red
                if(g == 0 && b == 0) {
                    // invert to green
                    p = (a<<24) | (0<<16) | (255<<8) | 0;
                }
                // check for green
                else if(r == 0 && b == 0) {
                    // invert to blue
                    p = (a<<24) | (0<<16) | (0<<8) | 225;
                }
                // check for blue
                else if(r == 0 && g == 0) {
                    // invert to red
                    p = (a<<24) | (255<<16) | (0<<8) | 0;
                } else {
                    p = (a<<24) | (r<<16) | (g<<8) | b;
                }

                image.setRGB(x, y, p);
            }
        }

        invertingComplete.tell(new ImageInvertingCompletedActor.ImageInvertCompleteRequest(imageInvertRequest.actorCount, image));

        return this;
    }
}
