package MVCpuro.vista;

import MVCpuro.controlador.ControladorClientes;
import MVCpuro.modelo.Cliente;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import javax.swing.*;

// La clase Nuevo hereda de JPanel y permite introducir nuevos clientes.
public class Nuevo extends JPanel {

    private ControladorClientes controlador;

    private JTextField campoNumero;
    private JTextField campoNombre;
    private JTextField campoPuntos;
    private JTextField campoFecha;
    // Etiqueta donde se mostrarán los mensajes de error o confirmación.
    private JLabel etiquetaError;
    
    public Nuevo(ControladorClientes controlador) {
        this.controlador = controlador;
        // Organizamos los componentes en seis filas y dos columnas.
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Número:"));
        campoNumero = new JTextField();
        add(campoNumero);

        add(new JLabel("Nombre:"));
        campoNombre = new JTextField();
        add(campoNombre);

        add(new JLabel("Puntos:"));
        campoPuntos = new JTextField();
        add(campoPuntos);

        add(new JLabel("Fecha de nacimiento (AAAA-MM-DD):"));
        campoFecha = new JTextField();
        add(campoFecha);

        JButton botonAñadir = new JButton("Añadir cliente");
        add(botonAñadir);

        etiquetaError = new JLabel("");
        etiquetaError.setForeground(Color.RED);
        add(etiquetaError);

        botonAñadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoPuntos = campoPuntos.getText();
                boolean correcto = true;
                boolean hayPunto = false;
                float puntos = 0;

                if (textoPuntos.isEmpty() ) {
                    etiquetaError.setText("Introduce los puntos.");
                    correcto = false;
                }

                if (correcto) {
                    puntos = Float.parseFloat(textoPuntos);

                    if (puntos < 0 || puntos > 1000) {
                        etiquetaError.setText("Los puntos deben estar entre 0 y 1000.");
                        correcto = false;
                    }
                }
                
                String textoFecha = campoFecha.getText();
                boolean fechaCorrecta = true;

                if (textoFecha.length() != 10) {
                    etiquetaError.setText("Introduce la fecha como AAAA-MM-DD.");
                    fechaCorrecta = false;
                }

                if (!fechaCorrecta) {
                    etiquetaError.setText("La fecha contiene caracteres incorrectos.");
                }

                if (correcto) {
                    int numero = Integer.parseInt(campoNumero.getText());
                    String nombre = campoNombre.getText();
                    LocalDate fecha = LocalDate.parse(campoFecha.getText());

                    Cliente cliente = new Cliente(numero, nombre, puntos, fecha);
                    controlador.añadirCliente(cliente);

                    campoNumero.setText("");
                    campoNombre.setText("");
                    campoPuntos.setText("");
                    campoFecha.setText("");

                    etiquetaError.setText("Cliente añadido");
                }
            }
        });
        
    }
}