/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidepuzzle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import slidepuzzle.gui.Inicio;
import static slidepuzzle.gui.Inicio.contenido;

/**
 *
 * @author erick
 */
public class DrawPuzzle implements ActionListener {

    public JButton[] fichas;
    private Object ventanaHome;
    public SlidePuzzle slidePuzzle = new SlidePuzzle();

    public String[] nombresImagenes() {

        File dirImg = new File("src/imgpuzzle/");
        String[] names = dirImg.list();

        return names;
    }

    public JButton[] addImagen(String[] names) {

        fichas = new JButton[names.length];
        Random rnd = new Random();
        int fila = 0;
        int col = 0;
        int vacio = 0;
        int blanco = (int) (rnd.nextDouble() * names.length + 0);

        for (int i = 0; i < names.length; i++) {

            fichas[i] = new JButton();

            if (i == blanco && vacio == 0) {

                fichas[i].setBounds(col * 184, fila, 180, 106);
                vacio++;

            } else {

                ImageIcon icon = new ImageIcon("src/imgpuzzle/" + names[i]);
                fichas[i].setIcon(icon);
                fichas[i].setBounds(col * 184, fila, 180, 106);

            }

            fichas[i].addActionListener(this);

            col++;

            if (col == 3) {
                fila += 110;
                col = 0;
            }

        }

        return fichas;
    }

    public JPanel drawscenario(JButton[] fichas) {

        JPanel home = new JPanel();
        home.removeAll();
        home.setLayout(null);

        for (int i = 0; i < fichas.length; i++) {
            home.add(fichas[i]);
        }

        home.repaint();
        return home;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton icon = null;
        int actual = 0;
        int vacio = 0;

        for (int i = 0; i < fichas.length; i++) { // Saber el clikeado
            if (e.getSource() == fichas[i]) {
                icon = fichas[i];
                actual = i;
            }
        }

        for (int i = 0; i < fichas.length; i++) { //Saber el vacio
            if (fichas[i].getIcon() == null) {
                vacio = i;
            }
        }

        if (slidePuzzle.moverFicha(fichas, actual, vacio)) {
            fichas[vacio].setIcon(icon.getIcon());
            fichas[actual].setIcon(null);
            JButton icontmp = fichas[vacio];
            System.out.println(icontmp.getIcon());
        }

        contenido.removeAll();
        contenido.addTab("Puzzle", drawscenario(fichas));

        if (slidePuzzle.ganoPartida(fichas)) {
            JOptionPane.showMessageDialog(null, "Acaba de ganar la partida.");
            contenido.removeAll();
            SlidePuzzle slidePuzzle = new SlidePuzzle();
            contenido.addTab("Puzzle", slidePuzzle.initialize());
        }
    }

}
