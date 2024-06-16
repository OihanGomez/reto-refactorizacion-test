package Libreria.Paginas;

import Libreria.Paginas.Usuario.UserMainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal extends UserMainPage {

    @Override
    protected JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.BLACK);
        header.setPreferredSize(new Dimension(700,100));

        String[] buttonNames = {"Ayuda con...", "Colecciones", "Eventos y Noticias", "Visitas y Sobre nosotros", "Inicio Sesion"};
        JLabel etiquetaFoto1 = createLabelWithIcon("src/Libreria/imagenes/logo_blanco.png");
        JPanel grupoBotones = createHeaderButtons(buttonNames);
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.BLACK);
        headerPanel.add(grupoBotones, BorderLayout.CENTER);
        headerPanel.add(etiquetaFoto1, BorderLayout.WEST);
        header.add(headerPanel, BorderLayout.CENTER);

        etiquetaFoto1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new UserMainPage();
            }
        });

        return header;
    }

    public static void main(String[] args) {
        new Principal();
    }
}
