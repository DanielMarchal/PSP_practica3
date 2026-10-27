package MVCpuro.controlador;

import MVCpuro.modelo.Cliente;
import MVCpuro.modelo.GestorClientes;
import MVCpuro.vista.VistaClientes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/** CONTROLADOR: lleva el índice del cliente actual y conecta modelo y vista. */
public class ControladorClientes {
    private ArrayList<Cliente> clientes;
    private int actual = 0;
    
    public ControladorClientes(){
        clientes = new ArrayList<>();
    }
    
    public boolean esVacia(){
        return clientes.isEmpty();
    }
    
    public void avanzar(){
        if(actual < clientes.size()-1){
            actual++;
        }
    }
    
    public void retroceder(){
        if(actual > 0){
            actual--;
        }
    }
    
    public void irAlPrimero(){
        if(!clientes.isEmpty() ){
            actual = 0;
        }
    }
    
    public void irAlUltimo(){
        if(!clientes.isEmpty() ){
            actual = clientes.size()-1;
        }
    }
    
    public boolean esPrimero(){
        if(!clientes.isEmpty() && actual == 0){
            return true;
        }
        return false;
    }
    
    public boolean esUltimo(){
        if(!clientes.isEmpty() && actual == clientes.size()-1){
            return true;
        }
        return false;
    }
    
    public void añadirCliente(Cliente cliente){
        clientes.add(cliente);
    }
    
    public void borrarCliente() {
        if (!clientes.isEmpty()) {
            clientes.remove(actual);

            if (clientes.isEmpty()) {
                actual = 0;
            } else if (actual >= clientes.size()) {
                actual = clientes.size() - 1;
            }
        }
    }
    
    public Cliente obtenerClienteActual(){
        if(!clientes.isEmpty() ){
            return clientes.get(actual);
        }
        return null;
    }
    
    public void ordenarNumeroCliente(){
        Collections.sort(clientes, new Comparator<Cliente>() {
        
            @Override
            public int compare(Cliente c1, Cliente c2) {
                if (c1.getNumero() < c2.getNumero()) {
                    return -1;
                } else if (c1.getNumero() > c2.getNumero()) {
                    return 1;
                } else {
                    return 0;
                }
            }
            
        });
    }
    
}
