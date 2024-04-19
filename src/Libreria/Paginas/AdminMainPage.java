package Libreria.Paginas;

import javax.swing.*;
import java.awt.*;

public class AdminMainPage {
    public AdminMainPage() {
        JFrame frame = new JFrame("Bibliopolis");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(900, 600));

        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        //Header
        JPanel header = new JPanel();
        header.setBackground(Color.BLACK);
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(700, 100));

        ImageIcon logo = new ImageIcon("src/Libreria/imagenes/logo_blanco.png");
        JLabel etiquetaFoto1 = new JLabel(logo);

        JLabel ayuda = new JLabel("Ayuda con...");
        JLabel colecciones = new JLabel("Colecciones");
        JLabel eventosYNoticias = new JLabel("Eventos y Noticias");
        JLabel sobreNosotros = new JLabel("Visitas y Sobre nosotros");

        ImageIcon userLogedIcon = new ImageIcon(("src/Libreria/imagenes/user_icon_white_resize.png"));
        JLabel inicioSesion = new JLabel(userLogedIcon);

        JLabel underUser = new JLabel("Admin");

        ayuda.setFont(new Font("Arial",Font.BOLD,14));
        ayuda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        colecciones.setFont(new Font("Arial",Font.BOLD,14));
        colecciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        eventosYNoticias.setFont(new Font("Arial",Font.BOLD,14));
        eventosYNoticias.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sobreNosotros.setFont(new Font("Arial",Font.BOLD,14));
        sobreNosotros.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        inicioSesion.setFont(new Font("Arial",Font.BOLD,14));
        inicioSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        ayuda.setFont(new Font("Arial", Font.BOLD, 14));
        colecciones.setFont(new Font("Arial", Font.BOLD, 14));
        eventosYNoticias.setFont(new Font("Arial", Font.BOLD, 14));
        sobreNosotros.setFont(new Font("Arial", Font.BOLD, 14));
        underUser.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel vertical = new JPanel();
        vertical.setLayout(new BoxLayout(vertical, BoxLayout.Y_AXIS));
        vertical.setLayout(new FlowLayout());
        vertical.setPreferredSize(new Dimension(50, 50));
        vertical.setBackground(Color.BLACK);
        vertical.add(inicioSesion);
        vertical.add(underUser);

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


        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.BLACK);
        headerPanel.add(grupoBotones, BorderLayout.CENTER);
        headerPanel.add(etiquetaFoto1, BorderLayout.WEST);
        header.add(headerPanel, BorderLayout.CENTER);


        //Body
        JPanel body = new JPanel();
        body.setPreferredSize(new Dimension(700, 500));


        frame.add(header, BorderLayout.NORTH);
        frame.add(body, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args){
        AdminMainPage ver = new AdminMainPage();
    }
}
