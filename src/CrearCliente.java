import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.*;

public class CrearCliente {

    private static final String URL = "jdbc:mysql://localhost:3306/crud";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private JFrame ventana;
    private JPanel panel;
    private JPanel panelButtons;

    private JLabel dni;
    private JTextField tdni;
    private JLabel nombre;
    private JTextField tnombre;
    private JLabel apellido;
    private JTextField tapellido;
    private JLabel segundoApellido;
    private JTextField tsegundoApellido;
    private JLabel empresa;
    private JTextField tempresa;
    private JLabel telefono;
    private JTextField ttelefono;
    private JLabel email;
    private JTextField temail;

    private JButton botonGuardar;
    private JButton botonLimpiar;
    private JButton botonSalir;

    public void datosCliente() {
        ventana = new JFrame("Panel Ejemplo"); // Crear ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(); // Crear panel
        panel.setLayout(new GridLayout(10, 2));

        dni = new JLabel("DNI:"); // Crear etiqueta
        panel.add(dni);
        tdni = new JTextField(); // Crear campo de texto
        panel.add(tdni);

        nombre = new JLabel("Nombre:"); // Crear etiqueta
        panel.add(nombre);
        tnombre = new JTextField(); // Crear campo de texto
        panel.add(tnombre);

        apellido = new JLabel("Apellido:"); // Crear etiqueta
        panel.add(apellido);
        tapellido = new JTextField(); // Crear campo de texto
        panel.add(tapellido);

        segundoApellido = new JLabel("Segundo apellido:"); // Crear etiqueta
        panel.add(segundoApellido);
        tsegundoApellido = new JTextField(); // Crear campo de texto
        panel.add(tsegundoApellido);

        empresa = new JLabel("Empresa:"); // Crear etiqueta
        panel.add(empresa);
        tempresa = new JTextField(); // Crear campo de texto
        panel.add(tempresa);

        telefono = new JLabel("Teléfono:"); // Crear etiqueta
        panel.add(telefono);
        ttelefono = new JTextField(); // Crear campo de texto
        panel.add(ttelefono);

        email = new JLabel("Email:"); // Crear etiqueta
        panel.add(email);
        temail = new JTextField(); // Crear campo de texto
        panel.add(temail);

        panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(1, 3));

        // Crear botón "Guardar"
        botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] datos = new String[7];

                datos[0] = tdni.getText();
                datos[1] = tnombre.getText();
                datos[2] = tapellido.getText();
                datos[3] = tsegundoApellido.getText();
                datos[4] = tempresa.getText();
                datos[5] = ttelefono.getText();
                datos[6] = temail.getText();

                crearCliente(datos);
            }
        });
        panelButtons.add(botonGuardar);

        // Crear botón "Limpiar"
        botonLimpiar = new JButton("Limpiar");
        botonLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tdni.setText("");
                tnombre.setText("");
                tapellido.setText("");
                tsegundoApellido.setText("");
                tempresa.setText("");
                ttelefono.setText("");
                temail.setText("");
            }
        });
        panelButtons.add(botonLimpiar);

        // Crear botón "Salir"
        botonSalir = new JButton("Salir");
        botonSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();
                mensajePrincipal();
            }
        });
        panelButtons.add(botonSalir);

        // Configurar layout manager de la ventana
        ventana.setLayout(new BorderLayout());

        // Añadir paneles a la ventana
        ventana.add(panel, BorderLayout.CENTER);
        ventana.add(panelButtons, BorderLayout.SOUTH);

        // Configurar la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack();
        ventana.setVisible(true);
    }

    public void crearCliente(String valores[]) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO users (dni, nombre, apellido, segApellido, empresa, telefono, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            for (int i = 0; i < valores.length; i++) {
                statement.setString(i + 1, valores[i]); // increment the parameter index
                System.out.println(valores[i] + " ");
            }

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Cliente insertado correctamente.");

            }
        } catch (SQLException ex) {
            System.err.println("Error al insertar cliente: " + ex.getMessage());
        }
    }

    public static void mensajePrincipal() {
        String[] opciones = { "Crear nuevo cliente", "Buscar cliente" };
        int opcion = JOptionPane.showOptionDialog(null, "¿Qué deseas hacer?", "Menú de clientes",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        switch (opcion) {
            case 0:
                CrearCliente crear = new CrearCliente();
                crear.datosCliente();
                break;
            case 1:
                MostrarCliente mostrar = new MostrarCliente();
                mostrar.datosCliente(0);
                break;
            default:
                break;
        }
    }
}
