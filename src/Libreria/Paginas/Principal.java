package Libreria.Paginas;

import Libreria.Paginas.Usuario.UserMainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal extends UserMainPage {

    @Override
    protected JPanel createUserPanel() {
        JPanel vertical = new JPanel();
        vertical.setLayout(new BoxLayout(vertical, BoxLayout.Y_AXIS));
        vertical.setLayout(new FlowLayout());
        vertical.setPreferredSize(new Dimension(70, 50));
        vertical.setBackground(Color.BLACK);

        JLabel underUser = new JLabel("Iniciar sesión");
        underUser.setForeground(Color.WHITE);
        underUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Agregar un listener al label
        underUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Login();
                frame.dispose();
            }
        });

        vertical.add(underUser);
        return vertical;
    }

    public static void main(String[] args) {
        new Principal();
    }
}
