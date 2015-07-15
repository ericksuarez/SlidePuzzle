/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidepuzzle;

import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JPanel;
import slidepuzzle.gui.Inicio;

/**
 *
 * @author Erick Suarez Buendia IPN
 */
public class SlidePuzzle {

    /**
     * @param args the command line arguments
     */
    public static final Inicio ventanaHome = new Inicio();

    public JPanel initialize() {

        SplitImagen splitimg = new SplitImagen();

        BufferedImage imagen = splitimg.cargaImagen();
        BufferedImage[] pedazos = splitimg.splitImagen(imagen, 3, 3);
        splitimg.guardaImagenes(pedazos);

        DrawPuzzle drawPuzzle = new DrawPuzzle();

        String[] nombres = drawPuzzle.nombresImagenes();
        JButton[] fichas = drawPuzzle.addImagen(nombres);
        JPanel home = drawPuzzle.drawscenario(fichas);

        return home;
    }

    public boolean moverFicha(JButton[] fichas, int actual, int vacio) {
        boolean mover = false;
        System.out.println(actual + " - " + vacio);
        if ((actual + 1) == vacio && actual != 2 && actual != 5 && actual != 8) { //Mover ficha derecha
            mover = true;
        }

        if ((actual - 1) == vacio && actual != 0 && actual != 3 && actual != 6) { //Mover ficha derecha
            mover = true;
        }

        switch (actual) {
            case 0:
                if (vacio == 3) {
                    mover = true;
                }
                break;
            case 1:
                if (vacio == 4) {
                    mover = true;
                }
                break;
            case 2:
                if (vacio == 5) {
                    mover = true;
                }
                break;
            case 3:
                if (vacio == 0 || vacio == 6) {
                    mover = true;
                }
                break;
            case 4:
                if (vacio == 1 || vacio == 7) {
                    mover = true;
                }
                break;
            case 5:
                if (vacio == 2 || vacio == 8) {
                    mover = true;
                }
                break;
            case 6:
                if (vacio == 3) {
                    mover = true;
                }
                break;
            case 7:
                if (vacio == 4) {
                    mover = true;
                }
                break;
            case 8:
                if (vacio == 5) {
                    mover = true;
                }
                break;
        }

        return mover;
    }

    public boolean ganoPartida(JButton[] fichas) {
        boolean gano = false;
        int cnt = 0;

        for (int i = 0; i < fichas.length; i++) {
            try {
                String name = fichas[i].getIcon().toString();
                System.out.println("i: " + i + name);
                if (name.equalsIgnoreCase("src/imgpuzzle/img_" + i + ".jpg")) {
                    cnt++;
                }
            } catch (NullPointerException e) {
            }

        }

        if (cnt == 8) {
            gano = true;
        } else {
            cnt = 0;
        }

        return gano;
    }

    public static void main(String[] args) {
        ventanaHome.setVisible(true);
    }

}
