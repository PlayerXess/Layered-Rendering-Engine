package net.playerxess.plre.rendering;

import net.playerxess.plre.PLRE;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageGen {
    private static final BufferedImage finalImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

    // Overlays The Images On Each Other And Writes The Final Image To A File
    public static String generateImage(String[] Paths, String textureName, String ModID) throws IOException {

        // If There Are No Images This Will Not Work
        if (Paths == null) {
            throw new NullPointerException("Paths is null");
        }

        // Repeats For Every Image
        int howManyPaths = Paths.length;
        for (int i = 0; i < howManyPaths; i++) {
            try {
                // Sets The Image To Be Used
                BufferedImage baseImage = ImageIO.read(new File(Paths[i]));
                // Just In Case The File Isn't Found Or Readable
                if (baseImage == null) {
                    throw new IOException("Failed to read image from file");
                }

                // Overlays The Image On The Final Edition
                Graphics2D g = finalImage.createGraphics();
                g.drawImage(baseImage, 0, 0, null);

            } catch (Exception e) { // Just In Case Someone Breaks
                e.printStackTrace();
            }
        }

        // Write The Final Image To A File
        ImageIO.write(finalImage, "png", new File(PLRE.plreTexturesDir + ModID + "-" + textureName + ".png"));
        return PLRE.plreTexturesDir + ModID + "-" + textureName + ".png";
    }

}
