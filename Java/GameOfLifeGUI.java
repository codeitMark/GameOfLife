import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameOfLifeGUI {
    private boolean harStartet = false;
    private Verden verden;
    private int rader, kolonner;
    private JFrame vindu;
    private JPanel panelInfo, panelVerden;
    private JLabel tekstLevende, tekstGen;
    private JButton startKnapp;

    public GameOfLifeGUI(int rader, int kolonner){
        try {
            UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName()
            );
        } catch (Exception e) {
            System.exit(1);
        }

        this.rader = rader;
        this.kolonner = kolonner;
        verden = new Verden(rader, kolonner);

        vindu = new JFrame("Game Of Life"); 
        vindu.setLayout(new BorderLayout());
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        leggTilKnapper();
        leggTilInfo();
    
        vindu.pack();
        vindu.setLocationRelativeTo(null);
        vindu.setVisible(true);

    }

    public void oppdaterKnapp(){
        tekstLevende.setText("Antall Levende: " + verden.rutenett.antallLevende());
        tekstGen.setText("Generasjonsnummer: " + verden.genNr);
        if (harStartet == false){
            startKnapp.setText("Start");
        } else{
            startKnapp.setText("Stopp");
        }

    }
    
    public void leggTilInfo(){
        panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(1, 4));
        
        tekstLevende = new JLabel("Antall Levende: " + verden.rutenett.antallLevende());
        tekstGen = new JLabel("Generasjonsnummer: " + verden.genNr);
        tekstLevende.setFont(new Font("Arial", Font.BOLD, 18));
        tekstGen.setFont(new Font("Arial", Font.BOLD, 18));
        panelInfo.add(tekstLevende);
        panelInfo.add(tekstGen);

        startKnapp = new JButton("Start");
        class Start implements ActionListener{
            Timer timer = new Timer(2000, new OppdaterendeStart());
            @Override
            public void actionPerformed (ActionEvent e){
                if (harStartet == false){
                    harStartet = true;
                    timer.start();
                } else{
                    timer.stop();
                    harStartet = false;
                    oppdaterKnapp();
                }
            }
            class OppdaterendeStart implements ActionListener{
                @Override
                public void actionPerformed(ActionEvent e) {
                    verden.oppdatering();
                    oppdaterVerden();
                    oppdaterKnapp();
                }
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
                        oppdaterKnapp();
                    } else{
                        celle.settLevende();
                        setText(String.valueOf(celle.hentStatusTegn()));
                        oppdaterKnapp();
                    }
                }
            }
        }
        for (int rad = 0; rad < rader; rad++){
            for (int kol = 0; kol < kolonner; kol++){
                Celle cellen = verden.rutenett.hentCelle(rad, kol);
                CelleKnapp celleKnappen = new CelleKnapp(cellen);
                panelVerden.add(celleKnappen);
            }
        }
        vindu.add(panelVerden, BorderLayout.CENTER);
    }
    public void oppdaterVerden(){
        vindu.remove(panelVerden);
        leggTilKnapper();
        vindu.revalidate();
        vindu.repaint();
    }
}

