import java.net.Socket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.lang.String;

import java.io.IOException;
public class ProcessClientThread implements Runnable {
    private Socket coneksi;
    private String Bilangan;
    
    public ProcessClientThread(Socket coneksiKiriman,int bilangan) {
        coneksi = coneksiKiriman;
        this.Bilangan = ""+Bilangan;
    }

    public void run() {
        try{
            if (coneksi != null)
                prosesPermintaanClient();
        }catch (IOException err){
            System.out.println(err);
        }
    }

    private void prosesPermintaanClient() throws IOException 
    {
        String ip = coneksi.getInetAddress().getHostAddress();
        System.out.println("Dari: " + ip);
        int i =0;
        String pesanServer=null;
        OutputStream keluaran=null;
        BufferedWriter keluaranBuf=null;

        for(; i<3;i++)
        {
            // Ambil dan tampilkan masukan
            InputStream masukan = coneksi.getInputStream();
            BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
            String baris = masukanReader.readLine();
            System.out.println("Tebakan dari Client : "+baris);

            if(Bilangan.equalsIgnoreCase(baris))
                pesanServer="Benar";
            else
                pesanServer="Salah";

            
            keluaran = coneksi.getOutputStream();
            keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
            keluaranBuf.write(pesanServer);
            keluaranBuf.newLine();
            keluaranBuf.flush();

            if(pesanServer.equalsIgnoreCase("Benar"))
                break;
        }
        if(i==3){
            pesanServer="Salah, angka = "+Bilangan;
            keluaranBuf.write(pesanServer);
            keluaranBuf.newLine();
            keluaranBuf.flush();
        }
    }
}
