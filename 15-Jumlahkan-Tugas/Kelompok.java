
/**
 * Write a description of class JumlahkanTugas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kelompok implements Runnable
{
    private int awal;
    private int akhir;
    private double[] data;
    private double nilaiHasil=0;

    public Kelompok(int awal, int akhir, double[] data)
    {
        this.awal=awal;
        this.akhir=akhir;
        this.data=data;
    }

    public double hasil()
    {
        return nilaiHasil;
    }

    public void hitung()
    {
        for( int counter = awal ; counter<=akhir;counter++)
            nilaiHasil+=data[counter];
    }

    public void run() {
        hitung();
    }
}
