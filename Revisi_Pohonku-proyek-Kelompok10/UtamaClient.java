
/**
 * Write a description of class UtamaClient here.
 * 
 * @author  
 * @version (5 Januari 2016)
 */
import java.io.IOException;

import java.net.UnknownHostException;

public class UtamaClient {
    public static void main(String[] args)
                  throws UnknownHostException, IOException {
        Client client = new Client();
        client.pohon();
    }
}
