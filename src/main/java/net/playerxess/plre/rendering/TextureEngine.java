package net.playerxess.plre.rendering;

import net.playerxess.plre.PLRE;

import java.util.ArrayList;
import java.util.List;

public class TextureEngine {

    private final List<String> texturePaths;
    private final String modId;
    private final String itemName;

    public TextureEngine(List<String> initialTextures, String modId, String itemName) {
        // Initialize with a copy of the provided textures
        this.texturePaths = new ArrayList<>(initialTextures);
        this.modId = modId;
        this.itemName = itemName;
    }

    public List<String> getTexturePaths() {
        return texturePaths;
    }

    public String getModId() {
        return modId;
    }

    public String getItemName() {
        return itemName;
    }

    // Method to insert a texture at a specific index without overwriting
    public void addTextureAt(int index, String texturePath) {
        if (index < 0 || index > texturePaths.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        texturePaths.add(index, texturePath);
    }

    public static void initializeTextureEngine() {
        PLRE.LOGGER.info("Initializing PLRE");
        TextureGenerator.initializeGenerator();
    }
}
