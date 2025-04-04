package net.playerxess.plre.rendering;

import net.playerxess.plre.PLRE;

public class TextureEngine {

    private final String[] texturePaths;
    private final String modId;
    private final String itemName;

    public TextureEngine(String[] texturePaths, String modId, String itemName) {
        this.texturePaths = texturePaths;
        this.modId = modId;
        this.itemName = itemName;
    }

    public String[] getTexturePaths() {
        return texturePaths;
    }

    public String getModId() {
        return modId;
    }

    public String getItemName() {
        return itemName;
    }

    public static void initializeTextureEngine () {
        PLRE.LOGGER.info("Initializing PLRE");
        TextureGenerator.initializeGenerator();
    }
}
