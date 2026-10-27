package MVCpuro.vista;

import MVCpuro.controlador.ControladorClientes;
import MVCpuro.modelo.Cliente;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// La clase Visualizar hereda de JPanel y representa el panel donde se muestran los clientes.
public class Visualizar extends JPanel{
    // Etiquetas que mostrarán los datos del cliente actual.
    private JLabel etiquetaNumero;
    private JLabel etiquetaNombre;
    private JLabel etiquetaPuntos;
    private JLabel etiquetaFechaNacimiento;
    // Botones para desplazarse entre los clientes y borrar el actual.
    private JButton botonPrimero;
    private JButton botonAnterior;
    private JButton botonSiguiente;
    private JButton botonUltimo;
    private JButton botonBorrar;
    // Referencia al controlador que gestiona los clientes.
    private ControladorClientes controlador;
    
    // Constructor que recibe el controlador desde la ventana principal.
    public Visualizar(ControladorClientes controlador){
        // Guardamos el controlador recibido para utilizarlo en este panel.
        this.controlador = controlador;
        // Establecemos un diseño con zonas para los datos y los botones.
        setLayout(new BorderLayout(10, 10));
        // Creamos un panel con cuatro filas y dos columnas para mostrar los datos.
        JPanel panelDatos = new JPanel(new GridLayout(4, 2, 10, 10));
        
        // Añadimos la etiqueta del número y una etiqueta vacía para mostrar su valor.
        panelDatos.add(new JLabel("Número:"));
        etiquetaNumero = new JLabel();
        panelDatos.add(etiquetaNumero);

        panelDatos.add(new JLabel("Nombre:"));
        etiquetaNombre = new JLabel();
        panelDatos.add(etiquetaNombre);

        panelDatos.add(new JLabel("Puntos:"));
        etiquetaPuntos = new JLabel();
        panelDatos.add(etiquetaPuntos);

        panelDatos.add(new JLabel("Fecha de nacimiento:"));
        etiquetaFechaNacimiento = new JLabel();
        panelDatos.add(etiquetaFechaNacimiento);
        // Colocamos el panel de datos en la zona central.
        add(panelDatos, BorderLayout.CENTER);

        // Creamos el panel inferior para organizar los botones.
        JPanel panelInferior = new JPanel(new BorderLayout(5, 5));

        // Creamos un panel con dos filas y dos columnas para los botones de navegación.
        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 5, 5));

        botonPrimero = new JButton("Primero");
        botonAnterior = new JButton("Anterior");
        botonSiguiente = new JButton("Siguiente");
        botonUltimo = new JButton("Último");

        panelBotones.add(botonPrimero);
        panelBotones.add(botonAnterior);
        panelBotones.add(botonSiguiente);
        panelBotones.add(botonUltimo);

        // Colocamos el panel de navegación en la zona central del panel inferior.
        panelInferior.add(panelBotones, BorderLayout.CENTER);

        // Creamos el botón para borrar el cliente actual.
        botonBorrar = new JButton("Borrar cliente");
        
        // Colocamos el botón de borrar en la parte inferior.
        panelInferior.add(botonBorrar, BorderLayout.SOUTH);

        // Añadimos el panel inferior a la parte inferior de este panel.
        add(panelInferior, BorderLayout.SOUTH);
        
        // Configuramos las acciones que ejecutará cada botón.
        configurarBotones();
        
        // Mostramos los datos del cliente actual al iniciar el panel.
        mostrarCliente();
    }
    
    public void mostrarCliente() {
        // Comprobamos si la lista de clientes está vacía.
        if (controlador.esVacia() ) {
            // Si no hay clientes, dejamos todas las etiquetas sin contenido.
            etiquetaNumero.setText("");
            etiquetaNombre.setText("");
            etiquetaPuntos.setText("");
            etiquetaFechaNacimiento.setText("");
        } else {
            Cliente cliente = controlador.obtenerClienteActual();

            etiquetaNumero.setText(String.valueOf(cliente.getNumero()));
            etiquetaNombre.setText(cliente.getNombre());
            etiquetaPuntos.setText(String.valueOf(cliente.getPuntos()));
            etiquetaFechaNacimiento.setText(
                    cliente.getFechaNacimiento().toString()
            );
        }
    }
    
    // Método que configura las acciones de todos los botones.
    private void configurarBotones() {
        botonPrimero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Seleccionamos el primer cliente de la lista.
                controlador.irAlPrimero();
                // Actualizamos las etiquetas para mostrarlo.
                mostrarCliente();
            }
        });

        botonAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.retroceder();
                mostrarCliente();
            }
        });

        botonSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.avanzar();
                mostrarCliente();
            }
        });

        botonUltimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.irAlUltimo();
                mostrarCliente();
            }
        });
        
        botonBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.borrarCliente();
                mostrarCliente();
            }
        });
        
    }
    
}
