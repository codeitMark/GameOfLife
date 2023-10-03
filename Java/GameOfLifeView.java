import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameOfLifeView {
    private GameOfLifeController kontroller;
    private int rader, kolonner;
    private JFrame vindu;
    private JPanel panelInfo, panelVerden;
    private JLabel tekstLevende;

    public GameOfLifeView(GameOfLifeController kontroller, int rader, int kolonner){
        try {
            UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName()
            );
        } catch (Exception e) {
            System.exit(1);
        }
        
        this.rader = rader;
        this.kolonner = kolonner;
        this.kontroller = kontroller;
    
        vindu = new JFrame("Game Of Life"); 
        vindu.setLayout(new BorderLayout());
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        leggTilKnapper();
        leggTilInfo();
    
        vindu.pack();
        vindu.setLocationRelativeTo(null);
        vindu.setVisible(true);
    }
    
    public void antLevende(){
        int antLev = kontroller.antallLevende();
        tekstLevende = new JLabel("Antall Levende: " + antLev);
    }

    public void leggTilInfo(){
        panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(1, 3));

        antLevende();
        panelInfo.add(tekstLevende);

        JButton startKnapp = new JButton("Start");
        class Start implements ActionListener{
            @Override
            public void actionPerformed (ActionEvent e){
                if (kontroller.harStartet == false){
                    kontroller.harStartet = true;
                    startKnapp.setName("Stopp"); 
                    while (kontroller.harStartet == true){
                        //while må et annet sted. kan hende knappen blir non-responsiv mens den er under while-løkken.
                        //koden med thread sleep eller Timer delay kan brukes
                        //du kan lage en kode som kaller på seg selv hele tiden. 
                        break;
                    }
                } else{
                    kontroller.harStartet = false;
                    startKnapp.setName("Start"); //må et annet sted med while, samme med "Stopp" for å oppdatere real time.
                }
                //harStartet = true;. Kode med thread.sleep eller noe som venter med at game of life går.
            }
        }
        startKnapp.addActionListener(new Start());
        panelInfo.add(startKnapp);

        JButton avsluttKnapp = new JButton("Avslutt");
        class Avslutt implements ActionListener{
            @Override
            public void actionPerformed (ActionEvent e){
                System.exit(0);
            }
        }
        avsluttKnapp.addActionListener(new Avslutt());
        panelInfo.add(avsluttKnapp);

        vindu.add(panelInfo, BorderLayout.NORTH);
    }

    public void leggTilKnapper(){
        panelVerden = new JPanel();
        panelVerden.setLayout(new GridLayout(rader, kolonner));

        Rutenett rutenettet = kontroller.hentRutenett();

        class CelleKnapp extends JButton{
            private Celle celle;

            public CelleKnapp(Celle celle){
                super("");
                this.celle = celle;
                if (celle.erLevende()) {
                    setText(String.valueOf(celle.hentStatusTegn()));
                } else {
                    setText(String.valueOf(celle.hentStatusTegn()));
                }

                super.addActionListener(new celleLiv());
            }

            class celleLiv implements ActionListener{
                @Override
                public void actionPerformed(ActionEvent e){
                    if (celle.erLevende()){
                        celle.settDoed();
                        setText(String.valueOf(celle.hentStatusTegn()));
                        antLevende();
                    } else{
                        celle.settLevende();
                        setText(String.valueOf(celle.hentStatusTegn()));
                        antLevende();
                    }
                }
            }
        }
        for (int rad = 0; rad < rader; rad++){
            for (int kol = 0; kol < kolonner; kol++){
                rutenettet.lagCelle(rad, kol);
                Celle cellen = rutenettet.hentCelle(rad, kol);
                CelleKnapp celleKnappen = new CelleKnapp(cellen);
                panelVerden.add(celleKnappen);
            }
        }

        vindu.add(panelVerden, BorderLayout.CENTER);
    }
}
