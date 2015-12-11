
/**
 * Write a description of class webcrawler here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.BufferedInputStream;

public class webcrawler {    
    public void chat(String pesan) 
                throws UnknownHostException, IOException {
        
        koneksi = new Socket("google.co.id",80);

        
        kirimPerintah(pesan);
       
        tungguBalasan();

       
        koneksi.close();
    }
    
    public void kirimPerintah(String pesan) throws IOException {
        // 1 & 2. Minta socket untuk ditulis dan Langsung dibuka
        OutputStream keluaran = koneksi.getOutputStream();
        // Note: Karena protokol-nya berbasis teks pakai writer aja.
        Writer keluaranWriter = new OutputStreamWriter(keluaran); 
        // 3. Selagi ada data kirim
        keluaranWriter.write(pesan);
        
        keluaranWriter.write("\r\n"); 
        keluaranWriter.flush(); 
    }
    
    public void tungguBalasan() throws IOException {
        // 1 & 2. Minta socket untuk baca -> Langsung dibuka
        InputStream masukan = koneksi.getInputStream();
        // Karena keluarannya panjang sehingga harus dibuffer
        BufferedInputStream masukanBuffer = new BufferedInputStream(masukan);
        // Selagi masih ada data baca
        int data = masukanBuffer.read();
        while (data != -1) {
            System.out.write((char) data);
            data = masukanBuffer.read();
        }
    }
    
    private Socket koneksi = null;
}