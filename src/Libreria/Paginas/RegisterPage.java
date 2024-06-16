package Libreria.Paginas;

import Libreria.Acciones.ConexionBD;
import Libreria.Acciones.RegisterManager;
import Libreria.Paginas.Usuario.UserMainPage;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage {

        private JFrame frame;
        private JTextField anadirCorreo;
        private JTextField nombreText;
        private JTextField apellidosText;
        private JTextField direccionText;
        private JPasswordField passwordText;
        private JPasswordField passwordConfirm;
        private JButton regist;
        private ConexionBD conexionBD;
        private RegisterManager registerManager;

        public RegisterPage() {
                initializeFrame();
                initializeComponents();
                configureHeaderPanel();
                configureContentPanel();
                configureRegisterButton();

                frame.pack();
                frame.setVisible(true);
        }

        private void initializeFrame() {
                frame = new JFrame("Bibliopolis");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.setPreferredSize(new Dimension(600, 700));
        }

        private void initializeComponents() {
                initializeTextFields();
                initializeButtons();
                initializePlaceholders();
                initializeManagers();
        }

        private void initializeTextFields() {
                passwordText = new JPasswordField(10);
                passwordConfirm = new JPasswordField(30);
                anadirCorreo = new JTextField(30);
                nombreText = new JTextField(30);
                apellidosText = new JTextField(30);
                direccionText = new JTextField(30);
        }

        private void initializeButtons() {
                regist = new JButton("Registrarse");
                regist.setBounds(100, 450, 120, 30);
                regist.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        private void initializePlaceholders() {
                createPlaceholder(passwordText, "Ingresa tu contraseña", 50, 115);
                createPlaceholder(passwordConfirm, "Confirma tu contraseña", 50, 175);
                createPlaceholder(anadirCorreo, "Ingresa tu correo electrónico", 50, 235);
                createPlaceholder(nombreText, "Ingresa tu nombre", 50, 295);
                createPlaceholder(apellidosText, "Ingresa tus apellidos", 50, 355);
                createPlaceholder(direccionText, "Ingresa tu dirección", 50, 415);
        }

        private void createPlaceholder(JTextComponent component, String text, int x, int y) {
                TextPrompt placeholder = new TextPrompt(text, component);
                placeholder.changeAlpha(0.75f);
                placeholder.changeStyle(Font.ITALIC);
                component.setBounds(x, y, 175, 30);
        }

        private void initializeManagers() {
                conexionBD = new ConexionBD();
                registerManager = new RegisterManager(conexionBD);
        }

        private void configureHeaderPanel() {
                JPanel panelNorte = new JPanel();
                panelNorte.setLayout(new BorderLayout());
                panelNorte.setPreferredSize(new Dimension(600, 100));
                panelNorte.setBackground(Color.black);

                ImageIcon logo = new ImageIcon("src/Libreria/imagenes/logo_blanco.png");
                JLabel etiquetaFoto1 = new JLabel(logo);

                panelNorte.add(etiquetaFoto1);
                frame.add(panelNorte, BorderLayout.NORTH);
        }

        private void configureContentPanel() {
                JPanel panelCentro = new JPanel();
                panelCentro.setPreferredSize(new Dimension(900, 500));

                JPanel contenido = new JPanel();
                contenido.setLayout(null);
                contenido.setPreferredSize(new Dimension(300, 500));

                addLabelsToPanel(contenido);
                addTextFieldsToPanel(contenido);
                contenido.add(regist);

                contenido.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Registro",
                        TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

                panelCentro.add(contenido, BorderLayout.CENTER);
                frame.add(panelCentro, BorderLayout.CENTER);
        }

        private void addLabelsToPanel(JPanel panel) {
                JLabel contraseña = createLabel("Contraseña:", 50, 90);
                JLabel contraseñaConfirmacion = createLabel("Confirmar contraseña:", 50, 150);
                JLabel correo = createLabel("Correo electrónico:", 50, 210);
                JLabel nombre = createLabel("Nombre:", 50, 270);
                JLabel apellidos = createLabel("Apellidos:", 50, 330);
                JLabel direccion = createLabel("Dirección:", 50, 390);

                panel.add(contraseña);
                panel.add(contraseñaConfirmacion);
                panel.add(correo);
                panel.add(nombre);
                panel.add(apellidos);
                panel.add(direccion);
        }

        private JLabel createLabel(String text, int x, int y) {
                JLabel label = new JLabel(text);
                label.setBounds(x, y, 150, 30);
                return label;
        }

        private void addTextFieldsToPanel(JPanel panel) {
                panel.add(passwordText);
                panel.add(passwordConfirm);
                panel.add(anadirCorreo);
                panel.add(nombreText);
                panel.add(apellidosText);
                panel.add(direccionText);
        }

        private void configureRegisterButton() {
                regist.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                handleRegister();
                        }
                });
        }

        private void handleRegister() {
                String password = new String(passwordText.getPassword());
                String confirmPassword = new String(passwordConfirm.getPassword());
                String email = anadirCorreo.getText();
                String name = nombreText.getText();
                String lastName = apellidosText.getText();
                String address = direccionText.getText();

                if (areFieldsEmpty(password, confirmPassword, email, name, lastName, address)) {
                        JOptionPane.showMessageDialog(frame, "Por favor, completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!password.equals(confirmPassword)) {
                        JOptionPane.showMessageDialog(frame, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (registerManager.register(email, password, name, lastName, address)) {
                        new UserMainPage();
                        frame.dispose();
                }
        }

        private boolean areFieldsEmpty(String... fields) {
                for (String field : fields) {
                        if (field.isEmpty()) {
                                return true;
                        }
                }
                return false;
        }

        public static void main(String[] args) {
                new RegisterPage();
        }
}
