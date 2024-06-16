package Libreria.Paginas.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EventosYnoticias {

    protected JFrame frame;

    public EventosYnoticias(){
        initializeFrame();
        JPanel header = createHeader();
        JPanel body = createBody();

        frame.add(header, BorderLayout.NORTH);
        frame.add(body, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    private void initializeFrame() {
        frame = new JFrame("Bibliopolis");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(900,600));
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
    }

    protected JPanel createHeader() {
        JPanel header = new JPanel();
        header.setBackground(Color.BLACK);
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(700,100));

        JLabel etiquetaFoto1 = createLogoLabel();
        JPanel grupoBotones = createMenuButtons();

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.BLACK);
        headerPanel.add(grupoBotones, BorderLayout.CENTER);
        headerPanel.add(etiquetaFoto1, BorderLayout.WEST);
        header.add(headerPanel, BorderLayout.CENTER);

        return header;
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

    protected JPanel createMenuButtons() {
        JLabel ayuda = createMenuLabel("Ayuda con...");
        JLabel colecciones = createMenuLabel("Colecciones");
        JLabel eventosYNoticias = createMenuLabel("Eventos y Noticias");
        JLabel sobreNosotros = createMenuLabel("Visitas y Sobre nosotros");

        // Poner la letra de la página seleccionada en blanco
        eventosYNoticias.setForeground(Color.WHITE);

        JLabel inicioSesion = createUserIconLabel();
        JLabel underUser = new JLabel("User");

        // Configuración de fuentes
        Font font = new Font("Arial", Font.BOLD, 14);
        underUser.setFont(font);

        JPanel vertical = createVerticalPanel(inicioSesion, underUser);

        JPanel grupoBotones = new JPanel();
        grupoBotones.setBackground(Color.BLACK);
        grupoBotones.setLayout(new BoxLayout(grupoBotones, BoxLayout.X_AXIS));
        grupoBotones.setPreferredSize(new Dimension(200,100));
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

    protected JLabel createMenuLabel(String text) {
        JLabel label = new JLabel(text);
        Font font = new Font("Arial", Font.BOLD, 14);
        label.setFont(font);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return label;
    }

    protected JLabel createUserIconLabel() {
        ImageIcon userLogedIcon = new ImageIcon("src/Libreria/imagenes/user_icon_white_resize.png");
        JLabel inicioSesion = new JLabel(userLogedIcon);
        inicioSesion.setFont(new Font("Arial", Font.BOLD, 14));
        inicioSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return inicioSesion;
    }

    protected JPanel createVerticalPanel(JLabel inicioSesion, JLabel underUser) {
        JPanel vertical = new JPanel();
        vertical.setLayout(new BoxLayout(vertical, BoxLayout.Y_AXIS));
        vertical.setLayout(new FlowLayout());
        vertical.setPreferredSize(new Dimension(50, 50));
        vertical.setBackground(Color.BLACK);
        vertical.add(inicioSesion);
        vertical.add(underUser);
        return vertical;
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

    private JPanel createBody() {
        JPanel body = new JPanel(new GridLayout(1, 2));
        body.add(createNoticiasPanel());
        body.add(createEventosPanel());
        return body;
    }

    private JPanel createNoticiasPanel() {
        JPanel noticiasPanel = new JPanel(new BorderLayout());
        noticiasPanel.setBackground(new Color(210, 210, 210));
        noticiasPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir margen

        JLabel noticiasLabel = new JLabel("NOTICIAS");
        noticiasLabel.setHorizontalAlignment(SwingConstants.CENTER);
        noticiasLabel.setFont(new Font("Arial", Font.BOLD, 24));
        noticiasPanel.add(noticiasLabel, BorderLayout.NORTH);

        JPanel noticiasInfoPanel = new JPanel();
        noticiasInfoPanel.setBackground(Color.WHITE);
        noticiasPanel.add(noticiasInfoPanel, BorderLayout.CENTER);

        JLabel subtituloNoticias = new JLabel("~Ultimas noticias de todas nuestras bibliotecas~");
        subtituloNoticias.setHorizontalAlignment(SwingConstants.CENTER);
        subtituloNoticias.setFont(new Font("Centaur", Font.BOLD, 14));

        GridBagConstraints gbcSubtitulo = new GridBagConstraints();
        gbcSubtitulo.gridx = 0;
        gbcSubtitulo.gridy = 0;
        gbcSubtitulo.anchor = GridBagConstraints.CENTER;
        gbcSubtitulo.insets = new Insets(0, 0, 10, 0);

        noticiasInfoPanel.add(subtituloNoticias, gbcSubtitulo);

        JPanel containerNoticias = createContainerPanel();
        noticiasInfoPanel.add(containerNoticias, createContainerConstraints());

        createSubPanels(containerNoticias, 4, new Color(210, 210, 210));

        return noticiasPanel;
    }

    private JPanel createEventosPanel() {
        JPanel eventosPanel = new JPanel(new BorderLayout());
        eventosPanel.setBackground(new Color(210, 210, 210));
        eventosPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir margen

        JLabel eventosLabel = new JLabel("PROXIMOS EVENTOS");
        eventosLabel.setHorizontalAlignment(SwingConstants.CENTER);
        eventosLabel.setFont(new Font("Arial", Font.BOLD, 24));
        eventosPanel.add(eventosLabel, BorderLayout.NORTH);

        JPanel eventosInfoPanel = new JPanel();
        eventosInfoPanel.setBackground(Color.WHITE);
        eventosPanel.add(eventosInfoPanel, BorderLayout.CENTER);

        JLabel subtituloProximosEventos = new JLabel("~Proximos eventos en nuestras bibliotecas~");
        subtituloProximosEventos.setHorizontalAlignment(SwingConstants.CENTER);
        subtituloProximosEventos.setFont(new Font("Centaur", Font.BOLD, 14));

        GridBagConstraints gbcSubtituloEventos = new GridBagConstraints();
        gbcSubtituloEventos.gridx = 0;
        gbcSubtituloEventos.gridy = 0;
        gbcSubtituloEventos.anchor = GridBagConstraints.CENTER;
        gbcSubtituloEventos.insets = new Insets(0, 0, 10, 0);

        eventosInfoPanel.add(subtituloProximosEventos, gbcSubtituloEventos);

        JPanel containerEventos = createContainerPanel();
        eventosInfoPanel.add(containerEventos, createContainerConstraints());

        createSubPanels(containerEventos, 4, new Color(210, 210, 210));

        return eventosPanel;
    }

    private JPanel createContainerPanel() {
        JPanel containerPanel = new JPanel();
        containerPanel.setBackground(Color.LIGHT_GRAY);
        containerPanel.setPreferredSize(new Dimension(320, 350));
        containerPanel.setMinimumSize(new Dimension(320, 350));
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        return containerPanel;
    }

    private GridBagConstraints createContainerConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        return gbc;
    }

    private void createSubPanels(JPanel container, int numPanels, Color color) {
        for (int i = 0; i < numPanels; i++) {
            JPanel panel = new JPanel();
            panel.setBackground(color);
            panel.setBorder(BorderFactory.createBevelBorder(1, Color.WHITE, Color.WHITE));
            container.add(panel);
        }
    }

    public static void main(String[] args){
        new EventosYnoticias();
    }
}
