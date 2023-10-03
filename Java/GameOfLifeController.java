public class GameOfLifeController {
    private GameOfLifeModel modell;
    private GameOfLifeView view;

    public boolean harStartet = false;

    public GameOfLifeController(int rader, int kolonner){
        modell = new GameOfLifeModel(rader, kolonner);
        view = new GameOfLifeView(this, rader, kolonner);
    }

    public Verden hentVerden() {
        return modell.hentVerden();
    }

    public Rutenett hentRutenett(){
        return modell.hentRutenett();
    }

    public int antallLevende() {
        return modell.antallLevende();
    }
}
