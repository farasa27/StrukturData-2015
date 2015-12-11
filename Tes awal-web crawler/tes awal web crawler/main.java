

 import java.io.IOException;
import java.net.UnknownHostException;

public class main {
    public static void main(String[] args) {
        try {
            webcrawler tanya = new webcrawler();
            tanya.chat("GET index.html.newline");
        }
        catch (UnknownHostException ex) {
            System.err.println(ex);
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
}