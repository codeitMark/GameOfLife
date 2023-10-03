import java.util.ArrayList;

public class Celle {
    boolean levende;
    ArrayList<Celle> naboer = new ArrayList<Celle>();
    int antNaboer = 0;
    int antLevendeNaboer = 0;

    public Celle(){
        levende = false;
    }
    
    public void settDoed(){
        levende = false;
    }

    public void settLevende(){
        levende = true;
    }

    public boolean erLevende(){
        return levende;
    }

    public char hentStatusTegn(){
        if (levende == true){
            return 'O';
        }
        else{
            return '.';
        }
    }

    public void leggTilNabo(Celle nabo){
        naboer.add(nabo);
        antNaboer ++;
    }

    public int tellLevendeNaboer(){
        antLevendeNaboer = 0;
        for (Celle nabo:naboer){
            if (nabo.levende == true){
                antLevendeNaboer++;
            }
        }
        return antLevendeNaboer;
    }

    public void oppdaterStatus(){
        if (levende == true){
            if (antLevendeNaboer < 2){
                levende = false;
            }
            else if (antLevendeNaboer > 3){
                levende = false;
            }
        }
        else if (levende == false){
            if (antLevendeNaboer == 3){
                levende = true;
            }
        }
    }
}
