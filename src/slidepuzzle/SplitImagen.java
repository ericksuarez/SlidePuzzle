/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidepuzzle;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author erick Clase que creara la divicion de las imagenes que se cargen
 */
public class SplitImagen {

    public BufferedImage cargaImagen() {

        File file = null;
        BufferedImage image = null;
        FileInputStream fis = null;

        try {
            file = new File("src/imagenes/gato.jpg");
            fis = new FileInputStream(file);
            image = ImageIO.read(fis);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "La imagen no se cargo correctamente.");
        }

        return image;
    }

    public BufferedImage[] splitImagen(BufferedImage image, Integer rows, Integer cols) {

        int chunks = rows * cols;
        int chunkWidth = image.getWidth() / cols;
        int chunkHeight = image.getHeight() / rows;
        int count = 0;

        BufferedImage imgs[] = new BufferedImage[chunks];

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }

        return imgs;
    }

    public void guardaImagenes(BufferedImage[] imgs) {
        try {
            for (int i = 0; i < imgs.length; i++) {
                ImageIO.write(imgs[i], "jpg", new File("src/imgpuzzle/img_" + i + ".jpg"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se crearon las imagenes correctamente.");
        }
    }
}
