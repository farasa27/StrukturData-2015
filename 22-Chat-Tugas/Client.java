import java.net.Socket;
import java.net.UnknownHostException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.BufferedWriter;

import java.util.Scanner;

public class Client {
    public void chat() 
                throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 33333);
        
        try {
            // Ketik
                Scanner keyboard = new Scanner(System.in);
                System.out.print("Pesan       : ");
               String ketikanSatuBaris = keyboard.nextLine();
                    
            
                 Writer keluaranWriter = new OutputStreamWriter(socket.getOutputStream()); 
                 BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
                 keluaranBuff.write(ketikanSatuBaris);
                 keluaranBuff.write("\n");
                  keluaranBuff.flush();
                
            
                System.out.print("Dari server harap tunggu : ");
                Reader masukan = new InputStreamReader(socket.getInputStream()); 
                BufferedReader masukanBuff = new BufferedReader(masukan);
                String baris = masukanBuff.readLine();
                System.out.println(baris); 
            
            
            baris = baris.toUpperCase();
            
            
                  keluaranBuff.write(baris);
                  keluaranBuff.flush();
            
            
            System.out.println("Selamat Pesan UPPER berhasil terkirim");
            System.out.println();
        }
             catch(IOException salah) {
            System.out.println(salah);
        }
             finally {
            if (socket != null)
            socket.close();
        }
    }
}
