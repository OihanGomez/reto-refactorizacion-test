package Libreria.Paginas.Admin;

import Libreria.Acciones.AdminManagement;
import Libreria.Acciones.ConexionBD;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminMainPage {
    private JFrame frame;
    private JPanel header;
    private JPanel panelEdit;
    private JComboBox<String> comboBox;
    private JTextField direccion;
    private JTextField nombre;
    private JTextField apellido;
    private JTextField correo;
    private JTextField contraseña;

    public AdminMainPage() {
        setupFrame();
        setupHeader();
        setupBody();
        setupComboBox();
        setupAceptarEditListener();
    }

    private void setupFrame() {
        frame = new JFrame("Bibliopolis");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(900, 600));
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
    }

    private void setupHeader() {
        header = new JPanel();
        header.setBackground(Color.BLACK);
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(900, 100));

        setupHeaderLogo();
        setupHeaderButtons();
        setupUserPanel();
        setupHeaderPanel();
    }

    private void setupHeaderLogo() {
        ImageIcon logo = new ImageIcon("src/Libreria/imagenes/logo_blanco.png");
        JLabel etiquetaFoto1 = new JLabel(logo);
        header.add(etiquetaFoto1, BorderLayout.WEST);
    }

    private void setupHeaderButtons() {
        JPanel grupoBotones = new JPanel();
        grupoBotones.setBackground(Color.BLACK);
        grupoBotones.setLayout(new BoxLayout(grupoBotones, BoxLayout.X_AXIS));
        grupoBotones.setPreferredSize(new Dimension(700, 100));

        JLabel ayuda = createHeaderButton("Ayuda con...");
        JLabel colecciones = createHeaderButton("Colecciones");
        JLabel eventosYNoticias = createHeaderButton("Eventos y Noticias");
        JLabel sobreNosotros = createHeaderButton("Visitas y Sobre nosotros");

        grupoBotones.add(Box.createHorizontalStrut(40));
        grupoBotones.add(ayuda);
        grupoBotones.add(Box.createHorizontalStrut(40));
        grupoBotones.add(colecciones);
        grupoBotones.add(Box.createHorizontalStrut(40));
        grupoBotones.add(eventosYNoticias);
        grupoBotones.add(Box.createHorizontalStrut(40));
        grupoBotones.add(sobreNosotros);
        grupoBotones.add(Box.createHorizontalStrut(70));

        header.add(grupoBotones, BorderLayout.CENTER);
    }

    private JLabel createHeaderButton(String text) {
        JLabel button = new JLabel(text);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void setupUserPanel() {
        ImageIcon userLogedIcon = new ImageIcon(("src/Libreria/imagenes/user_icon_white_resize.png"));
        JLabel inicioSesion = new JLabel(userLogedIcon);
        JLabel underUser = new JLabel("Admin");
        underUser.setForeground(Color.WHITE);

        JPanel vertical = new JPanel();
        vertical.setLayout(new FlowLayout());
        vertical.setBackground(Color.BLACK);
        vertical.add(inicioSesion);
        vertical.add(underUser);

        header.add(vertical, BorderLayout.EAST);
    }

    private void setupHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.BLACK);
        headerPanel.add(header, BorderLayout.CENTER);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(headerPanel, BorderLayout.NORTH);
    }

    private void setupBody() {
        JPanel body = new JPanel();
        body.setPreferredSize(new Dimension(900, 500));
        body.setLayout(null);

        panelEdit = createPanelEdit();  // Initialize panelEdit
        body.add(panelEdit);

        frame.add(body, BorderLayout.CENTER);
    }

    private JPanel createPanelEdit() {
        JPanel panelEdit = new JPanel();
        panelEdit.setBounds(300, 25, 300, 400);
        panelEdit.setLayout(null);

        comboBox = new JComboBox<>();
        comboBox.setBounds(50, 50, 200, 30);
        panelEdit.add(comboBox);

        direccion = createTextField(50, 100);
        nombre = createTextField(50, 150);
        apellido = createTextField(50, 200);
        correo = createTextField(50, 250);
        contraseña = createTextField(50, 300);

        JLabel direccionEtiqueta = createLabel("Dirección:", 50, 77);
        JLabel nombreEtiqueta = createLabel("Nombre:", 50, 127);
        JLabel apellidoEtiqueta = createLabel("Apellido:", 50, 177);
        JLabel correoEtiqueta = createLabel("Correo:", 50, 227);
        JLabel contraseñaEtiqueta = createLabel("Contraseña:", 50, 277);

        JLabel aceptarEdit = new JLabel("Aceptar cambios");
        aceptarEdit.setBounds(100, 350, 150, 30);
        aceptarEdit.setFont(new Font("Arial", Font.BOLD, 14));
        aceptarEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelEdit.add(aceptarEdit);

        panelEdit.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Usuarios",
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

        return panelEdit;
    }


    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 200, 30);
        return textField;
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 30);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private void setupComboBox() {
        ConexionBD conexionBD = new ConexionBD();
        AdminManagement adminManagement = new AdminManagement(conexionBD);
        adminManagement.mostrarUsuarios(comboBox);
    }

    private void setupAceptarEditListener() {
        JLabel aceptarEdit = (JLabel) panelEdit.getComponent(6); // Adjust index as needed
        aceptarEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String selectedUser = (String) comboBox.getSelectedItem();
                String nuevaDireccion = direccion.getText();
                String nuevoNombre = nombre.getText();
                String nuevosApellidos = apellido.getText();
                String nuevoCorreo = correo.getText();
                String nuevaContraseña = contraseña.getText();

                if (nuevaDireccion.isEmpty() || nuevoNombre.isEmpty() || nuevosApellidos.isEmpty() || nuevoCorreo.isEmpty() || nuevaContraseña.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    ConexionBD conexionBD = new ConexionBD();
                    AdminManagement adminManagement = new AdminManagement(conexionBD);
                    adminManagement.actualizarUsuario(selectedUser, nuevaDireccion, nuevoNombre, nuevosApellidos, nuevoCorreo, nuevaContraseña);
                }
            }
        });
    }



    public static void main(String[] args) {
        new AdminMainPage();
    }
}
