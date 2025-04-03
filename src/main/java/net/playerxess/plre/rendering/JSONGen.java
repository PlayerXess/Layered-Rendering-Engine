package net.playerxess.plre.rendering;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONGen {

    private static void genItemJsonFile(String directory, String filename, String texturePNGName) throws IOException {
        String exampleJsonFile = "src/main/resources/assets/plre/itemExample.json";
        String jsonContent = new String(Files.readAllBytes(Paths.get(exampleJsonFile)));

        String textureNameWithoutExtension = texturePNGName.substring(0, texturePNGName.lastIndexOf('.'));
        jsonContent = jsonContent.replaceAll("texturename", textureNameWithoutExtension);

        File file = new File(directory, filename);
        FileWriter writer = new FileWriter(file);
        writer.write(jsonContent);
        writer.close();
    }

}
