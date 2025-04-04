package net.playerxess.plre.rendering;

import net.playerxess.plre.PLRE;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.DynamicTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TextureGenerator {

    /**
     * Overlays a series of textures (given by file paths) in order,
     * saves the resulting image to the Minecraft instance directory,
     * and registers it as a dynamic texture.
     *
     * @param itemTexture A DynamicItemTexture holding the texture paths, mod id, and item name.
     * @return The Identifier for the generated texture or null if an error occurred.
     */
    public static Identifier generateCombinedTexture(TextureEngine itemTexture) {
        String[] textures = itemTexture.getTexturePaths();
        BufferedImage baseImage = null;

        try {
            // Loop through each texture path
            for (String texturePath : textures) {
                // Load the texture image (adjust the base path as needed)
                File textureFile = new File("mods/" + itemTexture.getModId() + "/textures/" + texturePath);
                BufferedImage img = ImageIO.read(textureFile);

                if (img == null) {
                    System.err.println("Failed to load texture: " + textureFile.getAbsolutePath());
                    continue;
                }

                // If this is the first image, initialize the base image
                if (baseImage == null) {
                    baseImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2d = baseImage.createGraphics();
                    g2d.drawImage(img, 0, 0, null);
                    g2d.dispose();
                } else {
                    // Overlay subsequent images on top of the base image
                    Graphics2D g2d = baseImage.createGraphics();
                    g2d.drawImage(img, 0, 0, null);
                    g2d.dispose();
                }
            }

            if (baseImage == null) {
                System.err.println("No textures were loaded.");
                return null;
            }

            // Determine the output directory in the Minecraft instance folder
            File instanceDir = MinecraftClient.getInstance().runDirectory;
            File outputDir = new File(instanceDir, "plre_generated_textures");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            // Create an output file name based on mod id and item name
            File outputFile = new File(outputDir, itemTexture.getModId() + "_" + itemTexture.getItemName() + ".png");
            ImageIO.write(baseImage, "png", outputFile);

            // Create a DynamicTexture from the combined BufferedImage
            DynamicTexture dynamicTexture = (DynamicTexture) baseImage;

            // Register the dynamic texture using Minecraft's TextureManager
            TextureManager textureManager = MinecraftClient.getInstance().getTextureManager();
            Identifier textureId = new Identifier(itemTexture.getModId(), "generated/" + itemTexture.getItemName());
            textureManager.registerTexture(textureId, (AbstractTexture) dynamicTexture);

            // Return the texture Identifier for further use (like rendering the item)
            return textureId;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void initializeGenerator() {

        PLRE.LOGGER.info("Creating Directory For Texture Storage");

        // Determine the output directory in the Minecraft instance folder
        File instanceDir = MinecraftClient.getInstance().runDirectory;
        File outputDir = new File(instanceDir, "plre_generated_textures");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
    }

}
