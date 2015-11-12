
/**
 * Write a description of class Merge here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
   import java.io.FileInputStream;
   import java.io.FileOutputStream;
   import java.io.IOException;

public class Merge {
      public void merge (String file1, String file2, String file3, String sasaran)throws IOException {
        FileInputStream join1 = null;
        FileInputStream join2 = null;
        FileInputStream join3 = null;
        FileOutputStream keluaran = null;
         try {
            join1 = new FileInputStream(file1);
            join2 = new FileInputStream(file2);
            join3 = new FileInputStream(file3);
            
            keluaran = new FileOutputStream(sasaran);
            int karakter = join1.read();
            while (karakter != -1) {
                keluaran.write(karakter);
                karakter = join1.read();
            }

            keluaran = new FileOutputStream(sasaran,true);
            karakter = join2.read();
            while (karakter != -1) {
                keluaran.write(karakter);
                karakter = join2.read();
            }

            keluaran = new FileOutputStream(sasaran,true);
            karakter = join3.read();
            while (karakter != -1) {
                keluaran.write(karakter);
                karakter = join3.read();
            }

            keluaran.flush();
        } 
        finally {
             if (join1 !=null)
                join1.close();
             if (join2 != null)
                join2.close();
             if (join3 != null)
                join3.close(); 
             if (keluaran != null)
                keluaran.close();

        }
    }
}