package Libreria.Paginas.Usuario;

import Libreria.Acciones.CitasManager;
import Libreria.Paginas.TextPrompt;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class VisitasYsobreNosotros {

    protected JFrame frame;
    private JPanel header;
    private JPanel body;
    private Color miColor;
    private CitasManager citasManager;

    public VisitasYsobreNosotros() {
        initFrame();
        initHeader();
        initBody();
        frame.pack();
        frame.setVisible(true);
    }

    private void initFrame() {
        frame = new JFrame("Bibliopolis");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(900, 600));
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
    }

    private void initHeader() {
        header = new JPanel();
        header.setBackground(Color.BLACK);
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(700, 100));

        JPanel headerPanel = createHeaderPanel();
        header.add(headerPanel, BorderLayout.CENTER);

        frame.add(header, BorderLayout.NORTH);
    }

    protected JPanel createHeaderPanel() {
        JLabel etiquetaFoto1 = createLogoLabel();
        JPanel grupoBotones = createMenuButtonsPanel();

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.BLACK);
        headerPanel.add(grupoBotones, BorderLayout.CENTER);
        headerPanel.add(etiquetaFoto1, BorderLayout.WEST);

        return headerPanel;
    }

    protected JLabel createLogoLabel() {
        ImageIcon logo = new ImageIcon("src/Libreria/imagenes/logo_blanco.png");
        JLabel etiquetaFoto1 = new JLabel(logo);
        etiquetaFoto1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        etiquetaFoto1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new UserMainPage();
            }
        });
        return etiquetaFoto1;
    }

    protected JPanel createMenuButtonsPanel() {
        JLabel ayuda = createMenuLabel("Ayuda con...");
        JLabel colecciones = createMenuLabel("Colecciones");
        JLabel eventosYNoticias = createMenuLabel("Eventos y Noticias");
        JLabel sobreNosotros = createMenuLabel("Visitas y Sobre nosotros");
        sobreNosotros.setForeground(Color.WHITE);

        JPanel vertical = createUserIconPanel();

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
        grupoBotones.add(Box.createHorizontalStrut(70));
        grupoBotones.add(vertical);

        addMenuListeners(ayuda, colecciones, eventosYNoticias, sobreNosotros);

        return grupoBotones;
    }

    private JPanel createUserIconPanel() {
        ImageIcon userLogedIcon = new ImageIcon("src/Libreria/imagenes/user_icon_white_resize.png");
        JLabel inicioSesion = new JLabel(userLogedIcon);
        JLabel underUser = new JLabel("User");

        JPanel vertical = new JPanel();
        vertical.setLayout(new FlowLayout());
        vertical.setPreferredSize(new Dimension(50, 50));
        vertical.setBackground(Color.BLACK);
        vertical.add(inicioSesion);
        vertical.add(underUser);

        return vertical;
    }

    protected JLabel createMenuLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return label;
    }

    protected void addMenuListeners(JLabel ayuda, JLabel colecciones, JLabel eventosYNoticias, JLabel sobreNosotros) {
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
                new EventosYnoticias();
            }
        });

        sobreNosotros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new VisitasYsobreNosotros();
            }
        });
    }

    private void initBody() {
        body = new JPanel(new GridLayout(2, 1));
        miColor = new Color(210, 210, 210);

        JPanel programarVisitaPanel = createProgramarVisitaPanel();
        body.add(programarVisitaPanel);

        JPanel panelAdicional = createPanelAdicional();
        body.add(panelAdicional);

        frame.add(body, BorderLayout.CENTER);
    }

    private JPanel createProgramarVisitaPanel() {
        JPanel programarVisita = new JPanel(new BorderLayout());
        programarVisita.setBackground(miColor);
        programarVisita.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JPanel subPanel = createSubPanel();
        programarVisita.add(subPanel, BorderLayout.CENTER);

        return programarVisita;
    }

    private JPanel createSubPanel() {
        JPanel subPanel = new JPanel(new BorderLayout());
        subPanel.setBackground(Color.WHITE);

        JPanel noticiasInfoPanel = new JPanel(new GridLayout(0, 1));
        subPanel.add(noticiasInfoPanel, BorderLayout.CENTER);

        addProgramarVisitaComponents(noticiasInfoPanel);

        return subPanel;
    }

    private void addProgramarVisitaComponents(JPanel noticiasInfoPanel) {
        JLabel tituloProgramarVisita = new JLabel("Programar una visita");
        tituloProgramarVisita.setHorizontalAlignment(SwingConstants.CENTER);
        tituloProgramarVisita.setFont(new Font("Arial", Font.BOLD, 28));
        tituloProgramarVisita.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        noticiasInfoPanel.add(tituloProgramarVisita);

        JLabel correoLabel = new JLabel("Correo electrónico:");
        noticiasInfoPanel.add(correoLabel);

        JTextField correoText = new JTextField(10);
        TextPrompt placeholder1 = new TextPrompt("example@gmail.com", correoText);
        placeholder1.changeAlpha(0.75f);
        placeholder1.changeStyle(Font.ITALIC);
        noticiasInfoPanel.add(correoText);

        JLabel fechaLabel = new JLabel("Fecha:");
        noticiasInfoPanel.add(fechaLabel);

        JXDatePicker datePicker = new JXDatePicker();
        datePicker.setDate(Calendar.getInstance().getTime());
        datePicker.setFormats(new SimpleDateFormat("yyyy/MM/dd"));
        noticiasInfoPanel.add(datePicker);

        JButton reservarButton = new JButton("Reservar visita");
        reservarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        noticiasInfoPanel.add(reservarButton);

        citasManager = new CitasManager();
        addReservarButtonListener(reservarButton, correoText, datePicker);
    }

    private void addReservarButtonListener(JButton reservarButton, JTextField correoText, JXDatePicker datePicker) {
        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = correoText.getText();
                Date fecha = datePicker.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String fechaStr = sdf.format(fecha);
                try {
                    citasManager.reservarCita(email, fechaStr);
                    JOptionPane.showMessageDialog(null, "Cita reservada exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private JPanel createPanelAdicional() {
        JPanel panelAdicional = new JPanel(new BorderLayout());
        panelAdicional.setBackground(miColor);
        panelAdicional.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));

        JLabel tituloPanelAdicional = new JLabel("Sobre nosotros");
        tituloPanelAdicional.setHorizontalAlignment(SwingConstants.CENTER);
        tituloPanelAdicional.setFont(new Font("Arial", Font.BOLD, 28));
        panelAdicional.add(tituloPanelAdicional, BorderLayout.NORTH);

        JPanel contenidoPanelAdicional = createContenidoPanelAdicional();
        panelAdicional.add(contenidoPanelAdicional, BorderLayout.CENTER);

        return panelAdicional;
    }

    private JPanel createContenidoPanelAdicional() {
        JPanel contenidoPanelAdicional = new JPanel();
        contenidoPanelAdicional.setBackground(Color.WHITE);

        JLabel subtituloPanelAdicional = new JLabel("<html><div style='text-align: center; width: 300px;'>Bibliopolis es una innovadora aplicación diseñada para satisfacer las necesidades de bibliotecas y librerías de todo el mundo. Con una interfaz intuitiva y funciones avanzadas, Bibliopolis simplifica la gestión de libros, préstamos, eventos y más. Desde pequeñas bibliotecas comunitarias hasta grandes librerías, nuestra plataforma ofrece soluciones adaptadas a cada necesidad. ¡Únete a nosotros y lleva tu biblioteca al siguiente nivel con Bibliopolis!</div></html>");
        subtituloPanelAdicional.setHorizontalAlignment(SwingConstants.CENTER);
        subtituloPanelAdicional.setFont(new Font("Centaur", Font.BOLD, 14));
        contenidoPanelAdicional.add(subtituloPanelAdicional, BorderLayout.NORTH);

        JPanel containerPanelAdicional = new JPanel();
        containerPanelAdicional.setBackground(Color.LIGHT_GRAY);
        containerPanelAdicional.setPreferredSize(new Dimension(300, 350));
        containerPanelAdicional.setMinimumSize(new Dimension(300, 350));
        containerPanelAdicional.setLayout(new BoxLayout(containerPanelAdicional, BoxLayout.Y_AXIS));

        addAdditionalPanels(containerPanelAdicional);

        return contenidoPanelAdicional;
    }

    private void addAdditionalPanels(JPanel containerPanelAdicional) {
        JPanel[] panelAdicionalPanels = new JPanel[4];
        for (int i = 0; i < panelAdicionalPanels.length; i++) {
            panelAdicionalPanels[i] = new JPanel();
            panelAdicionalPanels[i].setBackground(miColor);
            panelAdicionalPanels[i].setBorder(BorderFactory.createBevelBorder(1, Color.WHITE, Color.WHITE));
            containerPanelAdicional.add(panelAdicionalPanels[i]);
        }
    }

    public static void main(String[] args) {
        new VisitasYsobreNosotros();
    }
}
