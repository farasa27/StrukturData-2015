
/**
 * Write a description of class ProcessThread here.
 * 
 * @author (Rizka Pribadi) 
 * @version (5 Januari 2016)
 */
import java.net.Socket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ProcessServerThread implements Runnable {
    public ProcessServerThread(Socket koneksiKiriman, ArrayList<data> arrKiriman) {
        koneksi = koneksiKiriman;
        isi = arrKiriman;
    }

    public void run() {
            try {
                if(koneksi != null)
                   prosesPermintaanClient();
            }
            catch(IOException err) {
                System.out.println(err);
            }
            
    }

    private void prosesPermintaanClient() 
                 throws IOException {
        String ip = koneksi.getInetAddress().getHostAddress();
        System.out.println("Dari: " + ip);
        
        String baris=null;
        OutputStream keluaran=null;
        BufferedWriter keluaranBuf=null;
       
        // Ambil dan tampilkan masukan
        InputStream masukan = koneksi.getInputStream();
        BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
        baris = masukanReader.readLine();
        
        Calendar kalender = Calendar.getInstance();
        
        if(baris.equalsIgnoreCase("KIRIM"))
        {
            System.out.println("kirim");
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            baris = masukanReader.readLine();
            String [] ar = baris.split(",");
            
            data pohon = new data();
            pohon.nama = ar[0];
            //pohon.lembab = Integer.parseInt(ar[1]);
           // pohon.uv = Integer.parseInt(ar[2]);
           // pohon.nitro = Integer.parseInt(ar[3]);
           // pohon.suhu = Integer.parseInt(ar[4]);
           // pohon.waktu = sdf.format(kalender.getTime());
            
            isi.add(pohon);
            
            // Kirim ke Client
            keluaran = koneksi.getOutputStream();
            keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran));
            keluaranBuf.write("");
            keluaranBuf.newLine();
            keluaranBuf.flush();
        }
        else if(baris.equalsIgnoreCase("MINTA"))
        {
            //String waktuStr = kalender.getTime().toString();
            baris = masukanReader.readLine();
            String hasil = "";
            
            String split[] = baris.split(" ");
            
            for(data pohon : isi){
                if(pohon.nama.equalsIgnoreCase(split[0])){
                    hasil += pohon.nama;
                    hasil += ",";
                    hasil +=pohon.lembab;
                    hasil += ",";
                    hasil += pohon.uv;
                    hasil += ",";
                    hasil += pohon.nitro;
                    hasil += ",";
                    hasil +=pohon.suhu;
                    break;
                }
            }

            // Kirim ke Client
            keluaran = koneksi.getOutputStream();
            keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran));
            keluaranBuf.write(""+hasil);
            keluaranBuf.newLine();
            keluaranBuf.flush();
        }
        else
        {
            // Kirim ke Client
            keluaran = koneksi.getOutputStream();
            keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran));
            keluaranBuf.write("Perintah tidak dikenal !");
            keluaranBuf.newLine();
            keluaranBuf.flush();
        }
}
    private Socket koneksi; 
    private ArrayList<data> isi;
}
