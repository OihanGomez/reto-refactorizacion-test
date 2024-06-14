package Libreria.Paginas;

import Libreria.Acciones.ConexionBD;
import Libreria.Acciones.LoginManager;
import Libreria.Paginas.Admin.AdminMainPage;
import Libreria.Paginas.Usuario.UserMainPage;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {

    private JFrame frame;
    private JTextField userText;
    private JPasswordField passwordText;
    private JLabel login;
    private JLabel regist;

    public Login(){
        initializeFrame();
        initializeComponents();
        configureHeaderPanel();
        configureContentPanel();
        configureLoginButton();
        configureRegisterButton();

        frame.pack();
        frame.setVisible(true);
    }

    private void initializeFrame() {
        frame = new JFrame("Bibliopolis");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(600,600));
    }

    private void initializeComponents() {
        userText = new JTextField(10);
        passwordText = new JPasswordField(10);
        login = new JLabel("Iniciar Sesión");
        regist = new JLabel("Regístrate");

        // Configuración de los campos de texto con placeholders
        TextPrompt placeholder1 = new TextPrompt("example@gmail.com", userText);
        placeholder1.changeAlpha(0.75f);
        placeholder1.changeStyle(Font.ITALIC);
        userText.setBounds(50, 100, 175, 30);

        TextPrompt placeholder2 = new TextPrompt("", passwordText);
        placeholder2.changeAlpha(0.75f);
        passwordText.setBounds(50, 200, 175, 30);

        // Configuración de las etiquetas
        login.setBounds(100, 260, 90, 30);
        login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        regist.setBounds(110, 240, 90, 30);
        regist.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void configureHeaderPanel() {
        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BorderLayout());
        panelNorte.setPreferredSize(new Dimension(600,100));
        panelNorte.setBackground(Color.black);

        // Icono del logo en el encabezado
        ImageIcon logo = new ImageIcon("src/Libreria/imagenes/logo_blanco.png");
        JLabel etiquetaFoto1 = new JLabel(logo);

        panelNorte.add(etiquetaFoto1);
        frame.add(panelNorte, BorderLayout.NORTH);
    }

    private void configureContentPanel() {
        JPanel panelCentro = new JPanel();
        panelCentro.setPreferredSize(new Dimension(900,500));

        JPanel contenido = new JPanel();
        contenido.setLayout(null);
        contenido.setPreferredSize(new Dimension(300,400));

        JLabel usuario = new JLabel("Correo electronico:");
        usuario.setBounds(50, 50, 175, 50);
        JLabel contraseña = new JLabel("Contraseña:");
        contraseña.setBounds(50, 150, 100, 50);

        contenido.add(usuario);
        contenido.add(contraseña);
        contenido.add(userText);
        contenido.add(passwordText);
        contenido.add(regist);
        contenido.add(login);

        contenido.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Inicio de Sesión",
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

        panelCentro.add(contenido, BorderLayout.CENTER);
        frame.add(panelCentro, BorderLayout.CENTER);
    }

    private void configureLoginButton() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        };

        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        });
    }

    private void handleLogin() {
        String email = userText.getText();
        String password = passwordText.getText();
        ConexionBD conexionBD = new ConexionBD();
        LoginManager loginManager = new LoginManager(conexionBD);

        boolean estaLogueado = loginManager.login(email, password);
        boolean esAdmin = loginManager.isAdmin(email);
        if (estaLogueado && esAdmin){
            frame.dispose();
            AdminMainPage adminMainPage = new AdminMainPage();
        }else if (estaLogueado){
            frame.dispose();
            UserMainPage userMainPage = new UserMainPage();
        }else {
            JOptionPane.showMessageDialog(frame,"Credenciales incorrectas. Por favor, inténtalo de nuevo.", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configureRegisterButton() {
        regist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new RegisterPage();
            }
        });
    }

    public static void main(String[] args) {
        new Login();
    }
}

