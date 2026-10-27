package MVCpuro.vista;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import MVCpuro.controlador.ControladorClientes;

// La clase VentanaGrafica hereda de JFrame, por lo que es una ventana.
public class VentanaGrafica extends JFrame{
    
    // CardLayout permite cambiar entre diferentes paneles dentro de una misma ventana.
    private final CardLayout cardLayout = new CardLayout();
    
    // Creamos el contenedor que utilizará el CardLayout para mostrar los paneles.
    private final JPanel contenedor = new JPanel(cardLayout);
    
    // Creamos el controlador que gestionará los clientes de la aplicación.
    private final ControladorClientes controlador = new ControladorClientes();

    // Creamos el panel para visualizar los clientes y le pasamos el controlador.
    private final Visualizar panelVisualizar = new Visualizar(controlador);
    
    // Creamos el panel para añadir clientes y le pasamos el mismo controlador.
    private final Nuevo panelNuevo = new Nuevo(controlador);
    
    public VentanaGrafica(){
        super("Gestión de clientes");
        // Indicamos que la aplicación se cierre al cerrar la ventana.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Añadimos el panel de visualización al contenedor con el nombre VISUALIZAR.
        contenedor.add(panelVisualizar, "VISUALIZAR");
        
        // Añadimos el panel para crear clientes con el nombre NUEVO.
        contenedor.add(panelNuevo, "NUEVO");
        
        // Añadimos el contenedor principal a la ventana.
        add(contenedor);
        
        setJMenuBar(crearMenu() );
        
        setSize(500, 500);
        
        // Centramos la ventana en la pantalla
        setLocationRelativeTo(null);
    }
    
    private JMenuBar crearMenu(){
        // Creamos las opciones del menú.
        JMenuItem itemA = new JMenuItem("Visualizar");
        JMenuItem itemB = new JMenuItem("Nuevo");
        JMenuItem itemSalir = new JMenuItem("Salir");
        
        //Configuramos la opción de Visualizar, nuevo y salir
        itemA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contenedor, "VISUALIZAR");
            }
        });
        
        itemB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contenedor, "NUEVO");
            }
        });
        
        itemSalir.addActionListener(e -> System.exit(0) );
        
        JMenu menu = new JMenu("Menú");
        
        // Añadimos las opciones al menú.
        menu.add(itemA);
        menu.add(itemB);
        
        // Añadimos una línea separadora antes de la opción Salir.
        menu.addSeparator();
        menu.add(itemSalir);
        
        // Creamos la barra de menú.
        JMenuBar barra = new JMenuBar();
        
        // Añadimos el menú principal a la barra.
        barra.add(menu);
        
        // Devolvemos la barra de menú creada.
        return barra;         
    }
    
    // Método para actualizar los datos del cliente que se está visualizando.
    public void mostrarClienteActual() {
        panelVisualizar.mostrarCliente();
    }
    
    // Método principal desde el que se inicia la aplicación.
    public static void main(String[] args) {
        // Swing debe crearse en el hilo de eventos (EDT)
        // Creamos la ventana y la hacemos visible.
        SwingUtilities.invokeLater(() -> new VentanaGrafica().setVisible(true));
    }
        
}
