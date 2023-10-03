public class GameOfLifeModel {
    private int rader, kolonner;
    private Verden verden;
    private int antallLevende;

    public GameOfLifeModel(int rader, int kolonner){
        this.rader = rader;
        this.kolonner = kolonner;
        verden = new Verden(rader, kolonner);
    }

    public Verden hentVerden(){
        return verden;
    }

    public Rutenett hentRutenett(){
        return verden.rutenett;
    }

    public int antallLevende(){
        return verden.rutenett.antallLevende();
    }
}