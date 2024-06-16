package Libreria.Paginas.NoLogUsuario;

import Libreria.Paginas.Login;
import Libreria.Paginas.Usuario.EventosYnoticias;
import Libreria.Paginas.Usuario.VisitasYsobreNosotros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VisitasYSobreNosotrosNoLog extends VisitasYsobreNosotros {

    public VisitasYSobreNosotrosNoLog() {
        super();
    }

    @Override
    protected JPanel createHeaderPanel() {
        JLabel etiquetaFoto1 = createLogoLabel();
        JPanel grupoBotones = createMenuButtonsPanel();

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.BLACK);
        headerPanel.add(grupoBotones, BorderLayout.CENTER);
        headerPanel.add(etiquetaFoto1, BorderLayout.WEST);

        return headerPanel;
    }

    @Override
    protected JPanel createMenuButtonsPanel() {
        JLabel ayuda = createMenuLabel("Ayuda con...");
        JLabel colecciones = createMenuLabel("Colecciones");
        JLabel eventosYNoticias = createMenuLabel("Eventos y Noticias");
        JLabel sobreNosotros = createMenuLabel("Visitas y Sobre nosotros");
        JLabel iniciarSesion = createMenuLabel("Iniciar sesión");
        sobreNosotros.setForeground(Color.WHITE);

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

    public static void main(String[] args) {
        new VisitasYSobreNosotrosNoLog();
    }
}
