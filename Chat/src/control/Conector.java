package control;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author E304
 */
public class Conector {

    private Socket socket;
    private ServerSocket servidorSocket;
    private InputStreamReader entradaSocket;
    private DataOutputStream salida;
    private BufferedReader entrada;
    final int puerto = 4314;

    public Conector() {
        this.conectarServidor();
    }

    public Conector(String ip) {
        this.conectarCliente(ip);
    }

    private void conectarServidor() {
        try {
            this.servidorSocket = new ServerSocket(this.puerto);
            this.socket = this.servidorSocket.accept();

            //Creación de entrada de datos para la lectura de mensajes.
            this.entradaSocket = new InputStreamReader(this.socket.getInputStream());
            this.entrada = new BufferedReader(this.entradaSocket);

            //Creación de salida de datos para el envío de mensajes.
            this.salida = new DataOutputStream(this.socket.getOutputStream());

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void conectarCliente(String ip) {
        try {
            this.socket = new Socket(ip, this.puerto);

            //Creación de entrada de datos para la lectura de mensajes.
            this.entradaSocket = new InputStreamReader(this.socket.getInputStream());
            this.entrada = new BufferedReader(this.entradaSocket);

            //Creación de salida de datos para el envío de mensajes.
            this.salida = new DataOutputStream(this.socket.getOutputStream());

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void enviarMensaje(String mensaje) {
        try {
            this.salida.writeUTF(mensaje + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public String leerMensaje() {
        try {
            return this.entrada.readLine();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + ex.getMessage());
        } finally {
            return null;
        }
    }

    public void desconectar() {
        try {
            this.socket.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + ex.getMessage());
        }
        try {
            this.servidorSocket.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
