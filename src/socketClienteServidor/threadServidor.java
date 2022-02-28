package socketClienteServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author JazBass
 */
public class threadServidor implements Runnable {

    //Canales de comunicación
    DataOutputStream out = null;
    DataInputStream in = null;

    private JLabel mensaje1;
    private JTextArea mensaje2;
    
    public threadServidor(JLabel m1, JTextArea m2) {
        mensaje1 = m1;
        mensaje2 = m2;
    }

    @Override
    public void run() {
        try {
            //Creamos el socket en el puerto 10600
            ServerSocket ss = new ServerSocket(10600);
            //Mostramos el puerto conectado en pantalla
            mensaje1.setText("Puerto abierto: " + ss.getLocalPort());
            
            //Esperamos la conexión del cliente
            Socket sc  = ss.accept();
            
            //Canales de comunicación
            out = new DataOutputStream(sc.getOutputStream());
            in = new DataInputStream(sc.getInputStream());
            
            //Enviamos un mensaje al cliente solicitando su nombre
            out.writeUTF("Indique su nombre");
            
            //Recibimos el nombre del cliente
            String nombreCliente = in.readUTF();
            
            //Mostramos la conexión con el cliente en pantalla
            mensaje2.setText("Conexión con el cliente "+nombreCliente+" realizada.");
            
        } catch (IOException e) {
        }
    }

}
