import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Server {
    private int PORT;
    private ServerSocket server;
    private Socket cliente;

    Server(int PORT){
        this.PORT = PORT;
        try{
            server = new ServerSocket(PORT);
            cliente = server.accept();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int receber(){
        System.out.println("Recebendo do Cliente...");
        int entrada = -1;
        try{
            BufferedReader inCliente = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            entrada = inCliente.read();
        }catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entrada;
    }

    public void enviar(int mensagem){
        try{
            System.out.println("Enviando para o cliente...");
            DataOutputStream dos = new DataOutputStream(this.cliente.getOutputStream());
            dos.flush();
            dos.writeInt(mensagem);
        }catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fechar(){
        try {
            this.server.close();
        }catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}