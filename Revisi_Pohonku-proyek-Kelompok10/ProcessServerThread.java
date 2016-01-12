
/**
 * Write a description of class ProcessThread here.
 * 
 * @author () 
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
import java.util.Date;
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
            Date date =new Date();

            System.out.println("kirim");
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            baris = masukanReader.readLine();
            String [] ar = baris.split(",");
            String waktuStr = sdf.format(date);
            data pohon = new data();
            pohon.nama = ar[0];
            pohon.waktu= waktuStr;
            pohon.suhu = ar[1];
            pohon.uv = ar[2];
            pohon.nitro = ar[3];
            pohon.lembab = ar[4];

            //pohon.lembab = Integer.parseInt(ar[1]);
            // pohon.uv = Integer.parseInt(ar[2]);

            isi.add(pohon);
            // Kirim ke Client
            keluaran = koneksi.getOutputStream();
            keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran));
            keluaranBuf.write("");
            keluaranBuf.newLine();
            keluaranBuf.flush();
            System.out.println("Data tersimpan");

        }
        else if(baris.equalsIgnoreCase("MINTA"))
        {
            //String waktuStr = kalender.getTime().toString();
            baris = masukanReader.readLine();
            String hasil = "";

            String split[] = baris.split(" ");
            if(split.length==2){
                for(data pohon : isi){
                    if(pohon.nama.equalsIgnoreCase(split[0])){
                        if(split[1].equalsIgnoreCase("suhu")){
                            hasil += "\t\t"+pohon.waktu;
                            hasil += " "+pohon.nama;
                            hasil += " "+pohon.suhu;//+"\n";
                        }
                        else if(split[1].equalsIgnoreCase("uv")){
                            hasil += " "+pohon.waktu;
                            hasil += " "+pohon.nama;
                            hasil += " "+pohon.uv;//+"\n";
                        }
                        else if(split[1].equalsIgnoreCase("nitro")){
                            hasil += " "+pohon.waktu;
                            hasil += " "+pohon.nama;
                            hasil += " "+pohon.nitro;//+"\n";
                        }
                        else if(split[1].equalsIgnoreCase("lembab")){
                            hasil += " "+pohon.waktu;
                            hasil += " "+pohon.nama;
                            hasil += " "+pohon.lembab;//+"\n";
                        }

                        /*
                        hasil += pohon.nama;
                        hasil += ",";
                        hasil +=pohon.lembab;
                        hasil += ",";
                        hasil += pohon.uv;
                        hasil += ",";
                        hasil += pohon.nitro;
                        hasil += ",";
                        hasil +=pohon.suhu;
                         */

                    }
                }
            }

            if(split.length==3){

                for(data pohon : isi){
                    if(pohon.nama.equalsIgnoreCase(split[0])&&split[2].equals(pohon.waktu) ){
                        if(split[1].equalsIgnoreCase("suhu")){
                            hasil += "\t\t"+pohon.waktu;
                            hasil += " "+pohon.nama;
                            hasil += " "+pohon.suhu;//+"\n";
                        }
                        else if(split[1].equalsIgnoreCase("uv")){
                            hasil += " "+pohon.waktu;
                            hasil += " "+pohon.nama;
                            hasil += " "+pohon.uv;//+"\n";
                        }
                        else if(split[1].equalsIgnoreCase("nitro")){
                            hasil += " "+pohon.waktu;
                            hasil += " "+pohon.nama;
                            hasil += " "+pohon.nitro;//+"\n";
                        }
                        else if(split[1].equalsIgnoreCase("lembab")){
                            hasil += " "+pohon.waktu;
                            hasil += " "+pohon.nama;
                            hasil += " "+pohon.lembab;//+"\n";
                        }

                        /*
                        hasil += pohon.nama;
                        hasil += ",";
                        hasil +=pohon.lembab;
                        hasil += ",";
                        hasil += pohon.uv;
                        hasil += ",";
                        hasil += pohon.nitro;
                        hasil += ",";
                        hasil +=pohon.suhu;
                         */

                    }
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
