import java.net.Socket;
import java.net.ServerSocket;
import java.net.BindException;
import java.net.InetAddress;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Server {
    private int bilanganRahasia = 125;
    
    public Server() throws BindException, IOException {
        serverSocket = new ServerSocket(33333);
    }
    
    public void dengar() throws IOException{
        while (true) {
            try  {
                
                Socket koneksi = serverSocket.accept();
                
                
                ProcessClientThread satuProcess = new ProcessClientThread(koneksi, bilanganRahasia);
                Thread satuProcessThread = new Thread(satuProcess);
                satuProcessThread.start();                
            }
            catch(IOException err) {
                System.out.println(err);
            }
        }
    }
   
    private ServerSocket serverSocket = null;
}
