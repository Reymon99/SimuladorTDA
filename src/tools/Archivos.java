package tools;
import org.yaml.snakeyaml.Yaml;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
public abstract class Archivos {
    /**
     * Convierte y moldea una Imagen para ser utilizada en el proyecto
     * @param path String
     * @param width int
     * @param height int
     * @return ImageIcon
     */
    public static ImageIcon image(String path, int width, int height) {
        try{
            return new ImageIcon(ImageIO.read(Archivos.class.getResource(path)).getScaledInstance(
                    width,
                    height,
                    Image.SCALE_DEFAULT
            ));
        } catch (IOException e) {
            return null;
        }
    }
    /**
     * Exporta una determinada imagen tomada de un componente del proyecto a la ruta indicada
     * @param file ruta a exportar imagen
     * @param image imagen de un componente del proyecto
     * @return informa acerca del camino favorable o no de la exportación de la imagen
     */
    public static String exportImage(File file, RenderedImage image){
        try {
            return ImageIO.write(image, "jpg", file) ?
                    "Lienzo exportado exitosamente como imagen" : "No se pudo exportar el lienzo como imagen";
        } catch (IOException e) {
            return "No se pudo exportar el lienzo como imagen";
        }
    }
    /**
     * Exporta un determinado archivo de código Java de un determinado {@link gui.editor.Editor}
     * @param file ruta a exportar código Java
     * @param text código a fijar en el archivo Java
     * @return informa acerca del camino favorable o no de la exportación del archivo Java
     */
    public static String exportCode(File file, String text) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(text);
            bw.close();
            return "Código exportado exitosamente como archivo Java";
        } catch (IOException e) {
            return "No se pudo exportar el código del editor como archivo";
        }
    }
    public static boolean createdFolder(File file, String folder) {
        if (!file.exists() && file.isDirectory()) return new File(file.getAbsolutePath(), folder).mkdir();
        return false;
    }
    /**
     * Contiene cada uno de los pseudocódigos usados en el proyecto SEROS
     */
    private static LinkedHashMap<String, ArrayList<ArrayList<String>>> codes;
    /**
     * Carga el archivo .yml que contiene los diversos pseudocódigos del proyecto en el caso de haber
     * sidos cargados con anterioridad, de lo contrario a esto solo retornará los pseudocódigos
     * preestablecidos y cargados con anterioridad
     * @return pseudocódigos del proyecto SEROS
     */
    public static LinkedHashMap<String, ArrayList<ArrayList<String>>> getCodes() {
        if (Optional.ofNullable(codes).isEmpty()) {
            Yaml yaml = new Yaml();
            codes = yaml.load(Archivos.class.getResourceAsStream("/resources/codes/codes.yml"));
        }
        return codes;
    }
}