import java.net.Socket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.Calendar;

public class Process implements Runnable {
    private String SIAPA="SIAPA";
    private String WAKTU="WAKTU";
    private String WAKTU_N="WAKTU N";
    String pesansingkat = null;
    String alamat = null;
    String waktu = null;
    OutputStream keluaran =null;
    BufferedWriter keluaranBuf = null;
    public String pesanerror="Perintah tidak dikenal!!";

    Calendar kalender = Calendar.getInstance();
    String waktuStr = kalender.getTime().toString();

    public Process(Socket koneksiKiriman) {
        koneksi = koneksiKiriman;
    }

    public void run() {
        try{
            if (koneksi != null)
                prosesPermintaanClient(koneksi);
        }   
        catch(IOException err) {
            System.out.println(err);
        }
        catch(InterruptedException err) {
            System.out.println(err);
        }
    }

    private void prosesPermintaanClient(Socket koneksi) 
    throws InterruptedException, IOException {
        String ip = koneksi.getInetAddress().getHostAddress();
        System.out.println("Dari: " + ip);

        // Ambil dan tampilkan masukan
        InputStream masukan = koneksi.getInputStream();
        BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
        String baris = masukanReader.readLine();

        System.out.println(baris);

        OutputStream keluaran = koneksi.getOutputStream();
        BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran));

        if (baris.equals(SIAPA)){
            alamat=""+ip;
            keluaran = koneksi.getOutputStream();
            keluaranBuf = new BufferedWriter (new OutputStreamWriter(keluaran));
            keluaranBuf.write(alamat);
            keluaranBuf.newLine();
            keluaranBuf.flush();
        }   
        else if(baris.equals(WAKTU)){ 
            waktu = ""+waktuStr;
            keluaran = koneksi.getOutputStream();
            keluaranBuf = new BufferedWriter (new OutputStreamWriter(keluaran));
            keluaranBuf.write(waktu);
            keluaranBuf.newLine();
            keluaranBuf.flush();

            
        }
        
        else{
            pesansingkat=""+pesanerror;

            keluaran = koneksi.getOutputStream();
            keluaranBuf = new BufferedWriter (new OutputStreamWriter(keluaran));
            keluaranBuf.write(pesansingkat);
            keluaranBuf.newLine();
            keluaranBuf.flush();
        }

        keluaranBuf.newLine();
        keluaranBuf.flush();

    }
    private void prosesPermintaanClient() {
        try {
            // IP address asal koneksi                                                                   
            String ip = koneksi.getInetAddress().getHostAddress();
            // Tampilkan pesan
            System.out.println("Dari: " + ip);

            // Baca dari socket
            InputStream masukan = koneksi.getInputStream();
            BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
            String baris = masukanReader.readLine();
            while (baris != null) {
                System.out.println(baris);
                baris = masukanReader.readLine();
            }

            // Tunggu 2 detik
            System.out.println("Tunggu...");
            Thread.sleep(2000);
            System.out.println("Selesai tunggu...");

            koneksi.close();
        }
        catch(IOException err) {
            System.out.println(err);
        }
        catch(InterruptedException err) {
            System.out.println(err);
        }
    }

    private Socket koneksi; 
}