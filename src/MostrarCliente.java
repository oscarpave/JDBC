import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MostrarCliente {

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

    private JButton botonBuscar;
    private JButton botonEditar;
    private JButton botonBorrar;
    private JButton botonInicio;

    public void datosCliente(int botonDif) {
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

        String[] datos = new String[7];

        datos[0] = tdni.getText();
        datos[1] = tnombre.getText();
        datos[2] = tapellido.getText();
        datos[3] = tsegundoApellido.getText();
        datos[4] = tempresa.getText();
        datos[5] = ttelefono.getText();
        datos[6] = temail.getText();

        // Crear botón "Guardar o Buscar"
        switch (botonDif) {
            case 0:
            botonBuscar = new JButton("Buscar");   
            botonBuscar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
    
                    try {
                        ventana.dispose();
                        mostrarClientes(datos);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            panelButtons.add(botonBuscar);

                }
                break;
            case 1:
                botonBuscar = new JButton("Guardar");
                botonBuscar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
    
                        try {
                            ventana.dispose();
                            modificarCliente(datos);
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
                break;
            default:
                break;
                panelButtons.add(botonBuscar);
            });
        }
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

    public void mostrarClientes(String datos[]) throws SQLException, IOException {
        System.out.println("entra1");

        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        try (conn) {
            System.out.println("entra2");

            String sql = "SELECT * FROM users WHERE 1=1";

            if (!datos[0].isEmpty()) {
                sql += " AND dni='" + datos[0] + "'";
            }
            if (!datos[1].isEmpty()) {
                sql += " AND nombre='" + datos[1] + "'";
            }
            if (!datos[2].isEmpty()) {
                sql += " AND apellido='" + datos[2] + "'";
            }
            if (!datos[3].isEmpty()) {
                sql += " AND segApellido='" + datos[3] + "'";
            }
            if (!datos[4].isEmpty()) {
                sql += " AND empresa='" + datos[4] + "'";
            }
            if (!datos[5].isEmpty()) {
                sql += " AND telefono='" + datos[5] + "'";
            }
            if (!datos[6].isEmpty()) {
                sql += " AND email='" + datos[6] + "'";
            }

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            ventana = new JFrame("Panel Ejemplo"); // Crear ventana
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            panel = new JPanel(); // Crear panel
            panel.setLayout(new GridLayout(8, 1));

            while (resultSet.next()) {
                System.out.println("entra3");
                JLabel label;

                String nif = resultSet.getString("dni");
                String nombre = resultSet.getString("nombre");
                String apellido1 = resultSet.getString("apellido");
                String apellido2 = resultSet.getString("segApellido");
                String empresa = resultSet.getString("empresa");
                String telefono = resultSet.getString("telefono");
                String email = resultSet.getString("email");

                label = new JLabel("NIF: " + nif);
                panel.add(label);

                label = new JLabel("Nombre: " + nombre);
                panel.add(label);

                label = new JLabel("Apellido 1: " + apellido1);
                panel.add(label);

                label = new JLabel("Apellido 2: " + apellido2);
                panel.add(label);

                label = new JLabel("Empresa: " + empresa);
                panel.add(label);

                label = new JLabel("Teléfono: " + telefono);
                panel.add(label);

                label = new JLabel("Correo electrónico: " + email);
                panel.add(label);

                // Espacio en blanco entre resultados
                panel.add(new JLabel(""));

                panelButtons = new JPanel();
                panelButtons.setLayout(new GridLayout(1, 3));

                // Crear botón "Editar"
                botonEditar = new JButton("Editar");
                botonEditar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ventana.dispose();
                        datosCliente(1);

                    }
                });
                panelButtons.add(botonEditar);

                // Crear botón "Borrar"
                botonBorrar = new JButton("Borrar");
                botonBorrar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // clase borrar
                    }
                });
                panelButtons.add(botonBorrar);

                // Crear botón "Inicio"
                botonInicio = new JButton("Inicio");
                botonInicio.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ventana.dispose();
                        CrearCliente.mensajePrincipal();
                    }
                });
                panelButtons.add(botonInicio);

            }
            // Configurar layout manager de la ventana
            ventana.setLayout(new BorderLayout());

            // Añadir paneles a la ventana
            ventana.add(panel, BorderLayout.CENTER);
            ventana.add(panelButtons, BorderLayout.SOUTH);

            // ventana.add(panelButtons, BorderLayout.SOUTH);

            // Configurar la ventana
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.pack();
            ventana.setVisible(true);

        }
    }
}
