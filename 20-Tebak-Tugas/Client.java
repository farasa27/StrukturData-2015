import java.net.Socket;
import java.net.UnknownHostException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.BufferedWriter;

import java.io.OutputStream;

import java.util.Scanner;
import java.lang.*;

public class Client {
    public void chat() 
                throws UnknownHostException, IOException {
        Socket socket = new Socket("192.168.43.167", 33333);
        
        try {
            for(int i=0;i<3;i++){
                
                Scanner keyboard = new Scanner(System.in);
                System.out.print("Silahkan Tebak: ");
                String ketikBaris = keyboard.nextLine();
                        
                
                Writer keluaranWriter = new OutputStreamWriter(socket.getOutputStream()); 
                BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
                keluaranBuff.write(ketikBaris);
                keluaranBuff.write("\n");
                keluaranBuff.flush();
                    
                
                System.out.print("Pesan Dari server: ");
                Reader masukan = new InputStreamReader(socket.getInputStream()); 
                BufferedReader masukanBuff = new BufferedReader(masukan);
                String baris = masukanBuff.readLine();           
                System.out.println(baris);
                if(baris.equalsIgnoreCase("Benar")) {
                    break;
                }else if(i==3){
                    baris = masukanBuff.readLine();
                    System.out.println(baris);
                }
            }
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
