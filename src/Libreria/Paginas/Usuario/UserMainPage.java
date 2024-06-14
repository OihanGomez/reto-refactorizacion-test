package Libreria.Paginas.Usuario;

import Libreria.Acciones.BuscadorLibro;
import Libreria.Acciones.ConexionBD;
import Libreria.Paginas.TextPrompt;
import Libreria.objetos.Libro;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class UserMainPage {
    private JFrame frame;
    private JPanel panelIzquerda;
    private JTextField buscadorTexto;

    public UserMainPage(){
        frame = createFrame();
        frame.add(createHeader(), BorderLayout.NORTH);
        frame.add(createBody(), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private JFrame createFrame() {
        JFrame frame = new JFrame("Bibliopolis");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(900,600));
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        return frame;
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.BLACK);
        header.setPreferredSize(new Dimension(700,100));

        JLabel etiquetaFoto1 = createLabelWithIcon("src/Libreria/imagenes/logo_blanco.png");
        JPanel grupoBotones = createHeaderButtons();
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

    private JLabel createLabelWithIcon(String path) {
        ImageIcon icon = new ImageIcon(path);
        JLabel label = new JLabel(icon);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return label;
    }

    private JPanel createHeaderButtons() {
        JPanel grupoBotones = new JPanel();
        grupoBotones.setBackground(Color.BLACK);
        grupoBotones.setLayout(new BoxLayout(grupoBotones, BoxLayout.X_AXIS));
        grupoBotones.setPreferredSize(new Dimension(200,100));

        String[] buttonNames = {"Ayuda con...", "Colecciones", "Eventos y Noticias", "Visitas y Sobre nosotros"};
        for (String name : buttonNames) {
            JLabel button = createHeaderButton(name);
            grupoBotones.add(button);
            grupoBotones.add(Box.createHorizontalStrut(40));
        }

        JPanel vertical = createUserPanel();
        grupoBotones.add(Box.createHorizontalStrut(70));
        grupoBotones.add(vertical);

        return grupoBotones;
    }

    private JLabel createHeaderButton(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new HeaderButtonMouseListener());
        return label;
    }

    private JPanel createUserPanel() {
        JPanel vertical = new JPanel();
        vertical.setLayout(new BoxLayout(vertical, BoxLayout.Y_AXIS));
        vertical.setLayout(new FlowLayout());
        vertical.setPreferredSize(new Dimension(50,50));
        vertical.setBackground(Color.BLACK);

        ImageIcon userLogedIcon = new ImageIcon("src/Libreria/imagenes/user_icon_white_resize.png");
        JLabel inicioSesion = new JLabel(userLogedIcon);
        JLabel underUser = new JLabel("User");
        underUser.setForeground(Color.WHITE);

        vertical.add(inicioSesion);
        vertical.add(underUser);
        return vertical;
    }

    private JPanel createBody() {
        JPanel body = new JPanel(new BorderLayout());
        body.setPreferredSize(new Dimension(700,500));

        JPanel bodyArriba = createBodyArriba();
        JPanel bodyAbajo = createBodyAbajo();

        body.add(bodyArriba, BorderLayout.NORTH);
        body.add(bodyAbajo, BorderLayout.CENTER);

        return body;
    }

    private JPanel createBodyArriba() {
        JPanel bodyArriba = new JPanel(null);
        bodyArriba.setPreferredSize(new Dimension(700,125));

        JPanel arribaContenido = new JPanel();
        arribaContenido.setPreferredSize(new Dimension(400,30));
        arribaContenido.setLayout(new BoxLayout(arribaContenido, BoxLayout.X_AXIS));
        arribaContenido.setBounds(300,40,300,30);

        buscadorTexto = new JTextField(30);
        TextPrompt placeHolder = new TextPrompt("Busca tu libro...", buscadorTexto);
        placeHolder.changeAlpha(0.75f);
        placeHolder.changeStyle(Font.ITALIC);
        buscadorTexto.setPreferredSize(new Dimension(300,30));

        ImageIcon buscadorIcono = new ImageIcon("src/Libreria/imagenes/buscador.png");
        JLabel buscadorEtiqueta = new JLabel(buscadorIcono);
        buscadorEtiqueta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buscadorEtiqueta.addMouseListener(new BuscadorMouseListener());

        arribaContenido.add(buscadorTexto);
        arribaContenido.add(buscadorEtiqueta);

        bodyArriba.add(arribaContenido, BorderLayout.NORTH);

        return bodyArriba;
    }

    private JPanel createBodyAbajo() {
        JPanel bodyAbajo = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bodyAbajo.setPreferredSize(new Dimension(700,300));
        bodyAbajo.setLayout(null);

        JPanel abajoContenido = new JPanel();
        abajoContenido.setBounds(5,5,874,326);
        abajoContenido.setLayout(null);

        panelIzquerda = createPanel("Resultados de búsqueda", 5, 5, 427, 316);
        JPanel panelDerecha = createPanel("Libros populares", 440, 5, 427, 316);

        abajoContenido.add(panelIzquerda);
        abajoContenido.add(panelDerecha);

        bodyAbajo.add(abajoContenido);

        return bodyAbajo;
    }

    private JPanel createPanel(String title, int x, int y, int width, int height) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, width, height);
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), title,
                TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        return panel;
    }

    private class HeaderButtonMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            frame.dispose();
            String command = ((JLabel) e.getSource()).getText();
            switch (command) {
                case "Eventos y Noticias":
                    new EventosYnoticias();
                    break;
                case "Visitas y Sobre nosotros":
                    new VisitasYsobreNosotros();
                    break;
                default:
                    new UserMainPage();
                    break;
            }
        }
    }

    private class BuscadorMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            String nombreLibro = buscadorTexto.getText();
            ConexionBD conexion = new ConexionBD();
            BuscadorLibro buscadorLibro = new BuscadorLibro(conexion);
            Libro libro = buscadorLibro.buscarLibro(nombreLibro);
            displayBookResults(libro);
        }
    }

    private void displayBookResults(Libro libro) {
        if (libro != null) {
            panelIzquerda.removeAll();
            panelIzquerda.setLayout(new BoxLayout(panelIzquerda, BoxLayout.Y_AXIS));

            JPanel bookCard = new JPanel();
            bookCard.setLayout(new BoxLayout(bookCard, BoxLayout.X_AXIS));
            bookCard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.add(new JLabel("Título: " + libro.getTitulo()));
            infoPanel.add(new JLabel("<html>Descripción: " + libro.getDescripcion() + "</html>"));
            infoPanel.add(new JLabel("Precio: " + libro.getPrecio()));
            infoPanel.add(new JLabel("ID Editorial: " + libro.getIdEditorial()));

            String imagePath = libro.getPortadaRuta();
            File imageFile = new File(imagePath);
            if (!imageFile.isAbsolute()) {
                imageFile = new File(System.getProperty("user.dir"), imagePath);
            }

            ImageIcon originalIcon = new ImageIcon(imageFile.getAbsolutePath());
            Image originalImage = originalIcon.getImage();
            int newWidth = 100;
            int newHeight = (originalIcon.getIconHeight() * newWidth) / originalIcon.getIconWidth();
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

            JLabel coverLabel = new JLabel(resizedIcon.getIconWidth() > 0 ? resizedIcon : new ImageIcon("Imagen no disponible"));

            bookCard.add(coverLabel);
            bookCard.add(Box.createRigidArea(new Dimension(10, 0)));
            bookCard.add(infoPanel);

            panelIzquerda.add(bookCard);

            panelIzquerda.revalidate();
            panelIzquerda.repaint();
        } else {
            JOptionPane.showMessageDialog(null, "El libro no fue encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new UserMainPage();
    }
}
