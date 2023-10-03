public class Verden {
    Rutenett rutenett;
    int genNr;
    int antRader;
    int antKolonner;

    public Verden(int antRader, int antKolonner){
        this.antRader = antRader;
        this.antKolonner = antKolonner;
        rutenett = new Rutenett(antRader, antKolonner);
        rutenett.fyllMedTilfeldigeCeller();
        rutenett.kobleAlleCeller();
        genNr = 0;
    }

    public void tegn(){
        rutenett.tegnRutenett();
        System.out.println("Det er " + genNr + ". generasjon og har " + rutenett.antallLevende() + " antall levende celler.");
    }

    public void oppdatering(){
        for (int i = 0; i < antRader; i++){
            for (int x = 0; x < antKolonner; x++){
                rutenett.hentCelle(i, x).tellLevendeNaboer();
            }
        }
        for (int i = 0; i < antRader; i++) {
            for (int x = 0; x < antKolonner; x++) {
                rutenett.hentCelle(i, x).oppdaterStatus();
            }
        }
        genNr++;
    }
}
