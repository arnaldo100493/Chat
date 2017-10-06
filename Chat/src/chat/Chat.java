package chat;

import control.Conector;
import vista.VistaServidor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author E304
 */
public class Chat {

    public static Conector servidor, cliente;

    public Chat() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        VistaServidor vistaServidor = new VistaServidor();
        vistaServidor.setVisible(true);
    }

    public static void initServer() {
        servidor = new Conector();
    }

    public static void initCliente(String ip) {
        cliente = new Conector(ip);
    }

}
