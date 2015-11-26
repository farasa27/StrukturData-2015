import java.io.FileWriter;
import java.io.IOException;

public class CariPrima {
    public static void main() throws IOException {
        // Buat berkas untuk menulis hasil. Pakai WRITER karena yang ditulis 
        // berkas text.
        FileWriter berkas = new FileWriter(NAMA_BERKAS);
        
        // Buat array dari thread
        BenarPrima[] benarPrima = new BenarPrima[JUMLAH_THREAD];
        // Mulai hitung dari angka 2, karena 1 otomatis bukan prima
        int angka = 2;
        // Loop sampai batas atas yang diminta
        while (angka<=ANGKA_TERBESAR) {
           for(int i=0; i<JUMLAH_THREAD;i++){
            
            if(benarPrima[i]==null){
             benarPrima[i] = new BenarPrima(angka);
             Thread thread = new Thread(benarPrima[i]);
             thread.start();
             
             while (benarPrima[i].selesai() == false) { }
             
            }
            

            
            if(benarPrima[i].selesai()){
                if(benarPrima[i].prima()){
                    
                  synchronized(berkas) {
                     try {
                          berkas.write(benarPrima[i].angka()+"\n");
                       }
                       catch (IOException kesalahan) {
                          System.out.printf("Terjadi kesalahan: %s", kesalahan);
                     }
                  }
                  
                 
                }
            }   
            angka+=1;
        }
            
            
            
                       
        }
        
        
        for (int counterThread=0; counterThread<JUMLAH_THREAD; ++counterThread)
            while (benarPrima[counterThread].selesai() == false) { }        
        
        
        berkas.close();
    }
    
    private final static String NAMA_BERKAS = "prima.log";
    private final static int JUMLAH_THREAD = 10;
    private final static int ANGKA_TERBESAR = 100000;
}