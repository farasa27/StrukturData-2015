
/**
 * Write a description of class Client here.
 * 
 * @author  
 * @version (5 Januari 2016)
 */
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
    public void pohon()throws UnknownHostException, IOException {
       Socket socket = new Socket("192.168.43.240", 33333);
       // Socket socket = new Socket("localhost", 33333);
        String perintah=null;

        try {
            Reader masukan = null;
            BufferedReader masukanBuff=null;
            String baris = null;
            // Ketik
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Program PohonKu");
            System.out.print("Masukkan Perintah : ");
            perintah = keyboard.nextLine();

            // Tulis ke socket
            Writer keluaranWriter = new OutputStreamWriter(socket.getOutputStream()); 
            BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
            keluaranBuff.write(perintah);
            keluaranBuff.write("\n");
            keluaranBuff.flush();

            if(perintah.equalsIgnoreCase("KIRIM"))
            {
                String nama;
                int klbb, uv, nit, suhu;
                System.out.println("Masukkan Data Pohon");
                
                System.out.println("ID,SUHU(C),UV(nm),LEMBAB(%),NITRO(%)(semua tanpa spasi hanya koma)\n");

                nama = keyboard.nextLine();

                /*System.out.printf("Nama Pohon           : ");
                nama = keyboard.nextLine();
                System.out.printf("Kelembaban Tanah     : ");
                klbb = keyboard.nextInt();
                System.out.printf("Intensitas UV        : ");
                uv = keyboard.nextInt();
                System.out.printf("Kadar Nitrogen       : ");
                nit = keyboard.nextInt();
                System.out.printf("Suhu                 : ");
                suhu = keyboard.nextInt();*/
                // Tulis ke socket
                //keluaranBuff.write(nama+";"+klbb+";"+uv+";"+nit+";"+suhu);
                keluaranBuff.write(nama);
                keluaranBuff.write("\n");
                keluaranBuff.flush();

                // Baca dari Server
                masukan = new InputStreamReader(socket.getInputStream()); 
                masukanBuff = new BufferedReader(masukan);
                baris = masukanBuff.readLine();
                System.out.println(baris);
            }

            else if(perintah.equalsIgnoreCase("MINTA"))
            {
                String pohon, waktu;
                System.out.println("Masukkan NamaPohon dan Keterangan Pohon");
                System.out.printf("Masukkan Format Minta : ");
                pohon = keyboard.nextLine();

              
                //waktu = keyboard.nextLine();
                // Tulis ke socket

                keluaranBuff.write(pohon);
                keluaranBuff.write("\n");
                keluaranBuff.flush();

                // Baca dari Server
                //System.out.print("Data Pohon pada waktu tersebut yaitu");
                masukan = new InputStreamReader(socket.getInputStream()); 
                masukanBuff = new BufferedReader(masukan);
                baris = masukanBuff.readLine();
                if(baris.equals("")){
                    System.out.println("ID Pohon tidak ada");
                }else{
                    String [] brs = baris.split(";");
                    System.out.println(baris);
                    /*System.out.println("Nama Pohon       :  " +brs[0]);
                    System.out.println("Kelembaban Tanah :  " +brs[1]);
                    System.out.println("Intensitas UV    :  " +brs[2]);
                    System.out.println("Kadar Nitrogen   :  " +brs[3]);
                    System.out.println("Suhu             :  " +brs[4]);*/
                }                 

            }

            else
            {
                //Tulis ke Socket

                keluaranBuff.write(perintah);
                keluaranBuff.write("\n");
                keluaranBuff.flush();

                // Baca dari Server
                System.out.print("Server says : ");
                masukan = new InputStreamReader(socket.getInputStream()); 
                masukanBuff = new BufferedReader(masukan);
                baris = masukanBuff.readLine();
                System.out.println(""+baris);
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
