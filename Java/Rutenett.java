public class Rutenett {
    int antRader = 0;
    int antKolonner = 0;
    Celle[][] rutene;

    public Rutenett(int antRader, int antKolonner){
        this.antRader = antRader;
        this.antKolonner = antKolonner;
        rutene = new Celle[antRader][antKolonner];
    }

    public void lagCelle(int rad, int kolonne){
        Celle cell = new Celle();
        rutene[rad][kolonne] = cell;
        if (Math.random() <= 0.3333){
            cell.settLevende();
        }
    }

    public void fyllMedTilfeldigeCeller(){
        for (int i = 0; i < antRader; i++){
            for (int x = 0; x < antKolonner; x++){
                lagCelle(i, x);
            }
        }
    }

    public Celle hentCelle(int rad, int kol){
        if (rad >= 0 && rad < antRader && kol >= 0 && kol < antKolonner){
            return rutene[rad][kol];
            }
        else{
            return null;
        }
    }

    public void tegnRutenett(){
        System.out.println("");
        for (int i = 0; i < antRader; i++){
            System.out.print(" +---");
            System.out.println("+---".repeat(antKolonner-1) + "+");
            for (int x = 0; x < antKolonner; x++){
                System.out.print(" | ");
                System.out.print(rutene[i][x].hentStatusTegn() + "");
            }
            System.out.print(" |");
            System.out.println("");
        }
        System.out.print(" +---");
        System.out.print("+---".repeat(antKolonner-1) + "+");
        System.out.println("");
        System.out.println("");
    }

    public void settNaboer(int rad, int kol){
        Celle celle = hentCelle(rad, kol);
        if (celle != null){
            for (int i = rad-1; i <= rad+1 && i < antRader; i++){
                for (int x = kol-1; x <= kol+1 && x < antKolonner; x++){
                    if (i == rad && x == kol){
                        ;
                    }
                    else{
                        if (i != -1 && x != -1){
                            Celle nabo = hentCelle(i, x);
                            celle.leggTilNabo(nabo);
                        }
                        else{
                            ;
                        }
                    }
                }
            }
        }
    }

    public void kobleAlleCeller(){
        for (int i = 0; i < antRader; i++){
            for (int x = 0; x < antKolonner; x++){
                settNaboer(i, x);
            }
        }
    }

    public int antallLevende(){
        int levende = 0;
        for (int i = 0; i < antRader; i++){
            for (int x = 0; x < antKolonner; x++){
                boolean status = rutene[i][x].erLevende();
                if (status == true){
                    levende++;
                }
            }
        }
        return levende;
    }
}
