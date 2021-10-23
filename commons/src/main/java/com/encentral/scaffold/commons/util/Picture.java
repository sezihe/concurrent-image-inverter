package com.encentral.scaffold.commons.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 23/10/2021
 */
public class Picture {
    String imageName;
    BufferedImage bufferedImage;
    int width;
    int height;

    public Picture(String name) {
        this.imageName = name;

        try {
            bufferedImage = ImageIO.read(new File(imageName));
        } catch (IOException ex) {
            Logger.getLogger(Picture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Picture(int w, int h) {
        this.width = w;
        this.height = h;
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public int getWidth() {
        width = bufferedImage.getWidth();
        return width;
    }

    public int getHeight() {
        height = bufferedImage.getHeight();
        return height;
    }

    public Color get(int col, int row) {
        Color color = new Color(bufferedImage.getRGB(col, row));
        return color;
    }

    public void set(int col, int row, Color color) {
        bufferedImage.setRGB(col, row, color.getRGB());
    }

}
