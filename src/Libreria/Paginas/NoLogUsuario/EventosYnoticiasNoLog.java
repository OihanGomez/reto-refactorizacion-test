package Libreria.Paginas.NoLogUsuario;
import Libreria.Paginas.Login;
import Libreria.Paginas.Usuario.EventosYnoticias;
import Libreria.Paginas.Usuario.VisitasYsobreNosotros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EventosYnoticiasNoLog extends EventosYnoticias {

    public EventosYnoticiasNoLog() {
        super();
    }

    @Override
    protected JPanel createHeader() {
        JPanel header = new JPanel();
        header.setBackground(Color.BLACK);
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(700, 100));

        JLabel etiquetaFoto1 = createLogoLabel();
        JPanel grupoBotones = createMenuButtons();

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.BLACK);
        headerPanel.add(grupoBotones, BorderLayout.CENTER);
        headerPanel.add(etiquetaFoto1, BorderLayout.WEST);
        header.add(headerPanel, BorderLayout.CENTER);

        return header;
    }

    @Override
    protected JPanel createMenuButtons() {
        JLabel ayuda = createMenuLabel("Ayuda con...");
        JLabel colecciones = createMenuLabel("Colecciones");
        JLabel eventosYNoticias = createMenuLabel("Eventos y Noticias");
        JLabel sobreNosotros = createMenuLabel("Visitas y Sobre nosotros");
        JLabel iniciarSesion = createMenuLabel("Iniciar sesión");

        // Poner la letra de la página seleccionada en blanco
        eventosYNoticias.setForeground(Color.WHITE);

        JPanel grupoBotones = new JPanel();
        grupoBotones.setBackground(Color.BLACK);
        grupoBotones.setLayout(new BoxLayout(grupoBotones, BoxLayout.X_AXIS));
        grupoBotones.setPreferredSize(new Dimension(200, 100));
        grupoBotones.add(Box.createHorizontalStrut(40));
        grupoBotones.add(ayuda);
        grupoBotones.add(Box.createHorizontalStrut(40));
        grupoBotones.add(colecciones);
        grupoBotones.add(Box.createHorizontalStrut(40));
        grupoBotones.add(eventosYNoticias);
        grupoBotones.add(Box.createHorizontalStrut(40));
        grupoBotones.add(sobreNosotros);
        grupoBotones.add(Box.createHorizontalStrut(30));
        grupoBotones.add(iniciarSesion);

        addMenuListeners(ayuda, colecciones, eventosYNoticias, sobreNosotros, iniciarSesion);

        return grupoBotones;
    }

    protected void addMenuListeners(JLabel ayuda, JLabel colecciones, JLabel eventosYNoticias, JLabel sobreNosotros, JLabel iniciarSesion) {
        ayuda.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });

        colecciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });

        eventosYNoticias.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new EventosYnoticiasNoLog();
            }
        });

        sobreNosotros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            new VisitasYSobreNosotrosNoLog();
            }
        });

        iniciarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new Login();
            }
        });
    }

    protected JLabel createMenuLabel(String text) {
        JLabel label = new JLabel(text);
        Font font = new Font("Arial", Font.BOLD, 14);
        label.setFont(font);
        label.setForeground(Color.WHITE); // Ajustar el color según sea necesario
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return label;
    }

    public static void main(String[] args) {
        new EventosYnoticiasNoLog();
    }
}
