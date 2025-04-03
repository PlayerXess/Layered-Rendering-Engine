package net.playerxess.plre.rendering;

import java.io.File;
import java.io.IOException;

public class TextureEngine {

    private static String ModID;
    private static String TextureName;
    private static String TexturePNGName;
    private static String TexturePath;
    private static String TextureType;

    public TextureEngine(String MOD_ID, String TEXTURE_NAME, String TEXTURE_TYPE) {
        ModID = MOD_ID;
        TextureName = TEXTURE_NAME;
        TextureType = TEXTURE_TYPE;
    }

    public static void createTexture(String[] texturePaths) throws IOException {

        File file = new File("src/main/resources/assets/plre/" + TextureType + "/item", ModID + "-" + TextureName + ".png");

        if (file.exists()) {
            // The file exists
        } else {
            // The file does not exist
            TexturePNGName = ImageGen.generateImage(texturePaths, TextureName, ModID);
        }

    }

    private static void loadTexture() throws IOException {
        JSONGen.genItemJsonFile("src/main/resources/assets/plre/" + TextureType, ModID + "-" + TextureName + ".json", TexturePNGName);
    }
}
