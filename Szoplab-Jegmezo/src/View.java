import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import static java.lang.Math.sqrt;

/**
 * A View osztaly felelossegeit valositja meg.
 * A grafikus megjelenitest vegzi.
 */
public class View extends JFrame {

    /**
     * Hogy ne kelljen szamontantani,
     * melyik kepnek mi a sorszama a tombben (egyszerusites)
     */
   enum elem{
       horeteg1,
       horeteg2,
       viz,
       jeg,
       buvarruha,
       csukottsator,
       etel,
       pisztoly,
       torekenylapat,
       lapat,
       kotel,
       jegesmedve,
       sarkkutato,
       eszkimo,
       sator,
       iglu,
       kez,
       betoltokep,
       beallitasok,
       ikon
   }

    /**
     * Az aktualis mezo.
     */
    private static Mezo m;

    /**
     * Gombok listaja.
     */
    private static ArrayList<TablaButton> buttonok = new ArrayList<>();

    /**
     * A grafikai elemeket tarolja.
     */
    private static ArrayList<Image> grafikaElemek = new ArrayList<>();

    /**
     * A grafikai elemek neveit tarolja, melyek szuksegesek a megjeleniteshez.
     */
    private static String[] elemek = {"horeteg1.png", "horeteg2.png", "viz.png", "jeg.png",
            "buvarruha.png", "csukottsator.png", "etel.png", "pisztoly.png", "torekenylapat.png", "lapat.png",
            "kotel.png", "jegesmedve.png","sarkkutato.png", "eszkimo.png", "sator.png", "iglu.png", "kez.png",
            "betoltokep2.png", "workinmaci.png", "ikon.png"};

    /**
     * A View konstruktora, mely inicializalja a kezdeli allapotot.
     */
    public View(){
        init();
    }

    /**
     * A menubar.
     */
    private JMenuBar menuBar;

    /**
     * A jatek menuje.
     */
    private JMenu mJatek;

    /**
     * A jatek menuben levo valasztasi opcio,
     * hasznalataval uj jatek kerul letrehozasra.
     */
    private JMenuItem miUj;

    /**
     * A jatek menuben levo valasztasi opcio,
     * hasznalata soran betoltesre kerul
     * egy regebben elmentett jatekallapot.
     */
    private JMenuItem miBetolt;

    /**
     * A jatek menuben levo valasztasi opcio,
     * hasznalata soran kimentesre kerul
     * az aktualis jatekallapot.
     */
    private JMenuItem miMentes;

    /**
     * A jatek menuben levo valasztasi opcio,
     * hasznalata soran a jatek kezdeti allapotat
     * van lehetosegunk szemelyre szabni / valtoztatni.
     */
    private JMenuItem miBeallitasok;

    /**
     * A jatek menuben levo valasztasi opcio,
     * hasznalata soran kilepunk a programbol.
     */
    private JMenuItem miKilep;

    /**
     * A segitseg menuje.
     */
    private JMenu mHelp;

    /**
     * A segitseg menuben levo valasztasi opcio,
     * hasznalata soran segitseget kapunk
     * a jatekkal kapcsolatban.
     */
    private JMenuItem miHelp;

    /**
     * Az alap panel.
     */
    private JPanel alap;

    // ----------------JatekMenetekozben--------
    /**
     * A mezo panelt vlasztja el a karakter, illetve tabla paneltol.
     */
    private JSplitPane jatekalatt;

    /**
     * A mezo panel, mely a tablakat tartalmazza.
     */
    private JPanel pMezo;

    /**
     * A karakter panelt es a tabla panelt valasztja el.
     */
    private JSplitPane pReszletek;

    /**
     * A tabla adatait megjelenito panel.
     */
    private JPanel pTabla;

    /**
     * A karakter adatait megjelenito panel.
     */
    private JPanel pKarakter;

    //----------------------UzenetDialog-----------
    /**
     * Az uzenet dialog.
     */
    private JDialog uzenet;

    /**
     * Az uzenet panel.
     */
    private JOptionPane szoveg;

    //----------------------Beallitasok-----------
    /**
     * A beallitasi lehetosegeket magaba foglalo ablak / dialog.
     */
    private JDialog jdBeallitasok;

    /**
     * Az eszkimo szamanak beallitasara szolgalo spinner.
     */
    private JSpinner eszkimoJSpinner;

    /**
     * A sarkkutatok szamanak beallitasara szolgalo spinner.
     */
    private JSpinner sarkkutatoJSpinner;

    /**
     * A palya meretenek beallitasara szolgalo spinner.
     */
    private JSpinner palyaSpinner;

    /**
     * Az npc-k (jelen esetben jegesmedve) szamanak
     * beallitasara szolgalo spinner.
     */
    private JSpinner npcSpinner;

    /**
     * Visszaadja az eszkimok szamanak beallitasara szolgalo spinnert.
     * @return JSpinner Maga a spinner.
     */
    public JSpinner getEszkimoJSpinner() {
        return eszkimoJSpinner;
    }

    /**
     * Beallitja az eszkimok szamanak beallitsara szolgalo spinnert.
     * @param eszkimoJSpinner A beallitando spinner.
     */
    public void setEszkimoJSpinner(JSpinner eszkimoJSpinner) {
        this.eszkimoJSpinner = eszkimoJSpinner;
    }

    /**
     * Visszaadja a sarkkutatok szamanak beallitasara szolgalo spinnert.
     * @return Maga a spinner.
     */
    public JSpinner getSarkkutatoJSpinner() {
        return sarkkutatoJSpinner;
    }

    /**
     * Beallitja a sarkkutato szamanak beallitsara szolgalo spinnert.
     * @param sarkkutatoJSpinner A beallitando spinner.
     */
    public void setSarkkutatoJSpinner(JSpinner sarkkutatoJSpinner) {
        this.sarkkutatoJSpinner = sarkkutatoJSpinner;
    }

    /**
     * Visszaadja a Szoveg JOptionPane-jet.
     * @return JOptionPane Maga a JOptionPane.
     */
    public JOptionPane getSzoveg() {
        return szoveg;
    }

    /**
     * Beallitja a Szoveg JOptionPane-jet.
     * @param szoveg A beallitando JOptionPane.
     */
    public void setSzoveg(JOptionPane szoveg) {
        this.szoveg = szoveg;
    }

    /**
     * Visszaadja a mezot.
     * @return Mezo Maga a mezo.
     */
    public static Mezo getM() {
        return m;
    }

    /**
     * Beallitja a mezot.
     * @param m A beallitando mezo.
     */
    public static void setM(Mezo m) {
        View.m = m;
    }

    /**
     * Visszaadja a tablakat szimbolizalo buttonokat / gombokat.
     * @return ArrayList<TablaButton> A tablakat szimbolizalo buttonok / gombok. </TablaButton>
     */
    public static ArrayList<TablaButton> getButtonok() {
        return buttonok;
    }

    /**
     * Beallitja a tablakat szimbolizalo buttonokat / gombokat.
     * @param buttonok A beallitando buttonok / gombok.
     */
    public static void setButtonok(ArrayList<TablaButton> buttonok) {
        View.buttonok = buttonok;
    }

    /**
     * Beallitja a MenuBar-t.
     * @param menuBar A beallitando MenuBar.
     */
    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    /**
     * Visszaadja a jatek menujet.
     * @return JMenu A jatek menuje.
     */
    public JMenu getmJatek() {
        return mJatek;
    }

    /**
     * Beallitja a jatek menujet.
     * @param mJatek A beallitando menu.
     */
    public void setmJatek(JMenu mJatek) {
        this.mJatek = mJatek;
    }

    /**
     * Visszaadja a jatek menuben talalhato Uj JMenuItem-et.
     * @return JMenuItem Maga a JMenuItem.
     */
    public JMenuItem getMiUj() {
        return miUj;
    }

    /**
     * Beallitja a jatek menuben talalhato Uj JMenuItem-et.
     * @param miUj A beallitando JMenuItem.
     */
    public void setMiUj(JMenuItem miUj) {
        this.miUj = miUj;
    }

    /**
     * Visszaadja a jatek menuben talalhato Betolt JMenuItem-et.
     * @return JMenuItem Maga a JMenuItem.
     */
    public JMenuItem getMiBetolt() {
        return miBetolt;
    }

    /**
     * Beallitja a jatek menuben talalhato Betolt JMenuItem-et.
     * @param miBetolt A beallitando JMenuItem.
     */
    public void setMiBetolt(JMenuItem miBetolt) {
        this.miBetolt = miBetolt;
    }

    /**
     * Visszaadja a jatek menuben talalhato Mentes JMenuItem-et.
     * @return JMenuItem Maga a JMenuItem.
     */
    public JMenuItem getMiMentes() {
        return miMentes;
    }

    /**
     * Beallitja a jatek menuben talalhato Mentes JMenuItem-et.
     * @param miMentes A beallitando JMenuItem.
     */
    public void setMiMentes(JMenuItem miMentes) {
        this.miMentes = miMentes;
    }

    /**
     * Visszaadja a jatek menuben talalhato Kilep JMenuItem-et.
     * @return JMenuItem Maga a JMenuItem.
     */
    public JMenuItem getMiKilep() {
        return miKilep;
    }

    /**
     * Beallitja a jatek menuben talalhato Kilep JMenuItem-et.
     * @param miKilep A beallitando JMenuItem.
     */
    public void setMiKilep(JMenuItem miKilep) {
        this.miKilep = miKilep;
    }

    /**
     * Visszaadja a help menujet.
     * @return JMenu A help menuje.
     */
    public JMenu getmHelp() {
        return mHelp;
    }

    /**
     * Beallitja a Help menujet.
     * @param mHelp A beallitando menu.
     */
    public void setmHelp(JMenu mHelp) {
        this.mHelp = mHelp;
    }

    /**
     * Visszaadja a help menuben talalhato Help JMenuItem-et.
     * @return JMenuItem Maga a JMenuItem.
     */
    public JMenuItem getMiHelp() {
        return miHelp;
    }

    /**
     * Beallitja a help menuben talalhato Help JMenuItem-et.
     * @param miHelp A beallitando JMenuItem.
     */
    public void setMiHelp(JMenuItem miHelp) {
        this.miHelp = miHelp;
    }

    /**
     * Visszaadja az alap panelt.
     * @return JPanel Maga az alap panel.
     */
    public JPanel getAlap() {
        return alap;
    }

    /**
     * Beallitja az alap panelt.
     * @param alap A beallitando panelt.
     */
    public void setAlap(JPanel alap) {
        this.alap = alap;
    }

    /**
     * Visszadja a JSplitPane-t,
     * ami a mezo panelt valasztja el a karakter,
     * illetve tabla paneltol.
     * @return JSplitPane Maga a JSplitPane.
     */
    public JSplitPane getJatekalatt() {
        return jatekalatt;
    }

    /**
     * Beallitja a JSplitPane-t,
     * ami a mezo panelt valasztja el a karakter,
     * illetve a tabla paneltol.
     * @param jatekalatt A beallitando JSplitPane.
     */
    public void setJatekalatt(JSplitPane jatekalatt) {
        this.jatekalatt = jatekalatt;
    }

    /**
     * Visszaadja a mezo panelt, mely a tablakat tartalmazza.
     * @return JPanel Maga a JPanel.
     */
    public JPanel getpMezo() {
        return pMezo;
    }

    /**
     * Beallitja a mezo panelt, mely a tablakat tartalmazza.
     * @param pMezo A beallitando JPanel.
     */
    public void setpMezo(JPanel pMezo) {
        this.pMezo = pMezo;
    }

    /**
     * Visszaadja a karakter, illetve a tabla panelt elvalaszto JSplitPane-t.
     * @return JSplitPane Maga a JSplitPane.
     */
    public JSplitPane getpReszletek() {
        return pReszletek;
    }

    /**
     * Beallitja a karakter, illetve a tabla panelt elvalaszto JSplitPane-t.
     * @param pReszletek A beallitando JSplitPane.
     */
    public void setpReszletek(JSplitPane pReszletek) {
        this.pReszletek = pReszletek;
    }

    /**
     * Visszaadja a tabla adatait megjelenito JPanel-t.
     * @return JPanel Maga a JPanel.
     */
    public JPanel getpTabla() {
        return pTabla;
    }

    /**
     * Beallitja a tabla adatait megjelenito JPanelt.
     * @param pTabla A beallitando JPanel.
     */
    public void setpTabla(JPanel pTabla) {
        this.pTabla = pTabla;
    }

    /**
     * Visszaadja a karakter adatait megjelenito JPanel-t.
     * @return JPanel Maga a JPanel.
     */
    public JPanel getpKarakter() {
        return pKarakter;
    }

    /**
     * Beallitja a karakter adatait megjelenito JPanel-t.
     * @param pKarakter A beallitando JPanel.
     */
    public void setpKarakter(JPanel pKarakter) {
        this.pKarakter = pKarakter;
    }

    /**
     * Visszaadja az uzenet JDialogot-jat.
     * @return JDialog Maga a JDialog.
     */
    public JDialog getUzenet() {
        return uzenet;
    }

    /**
     * Beallitja az uzenet JDialog-jat.
     * @param uzenet A beallitando JDialog.
     */
    public void setUzenet(JDialog uzenet) {
        this.uzenet = uzenet;
    }

    /**
     * Visszaadja a beallitasi lehetosegeket tartalmazo JDialog-ot.
     * @return JDialog Maga a JDialog.
     */
    public JDialog getpBeallitasok() {
        return jdBeallitasok;
    }

    /**
     * Beallitja a beallitasi lehetosegeket tartalmazo JDialog-ot.
     * @param pBeallitasok A beallitando JDialog.
     */
    public void setpBeallitasok(JDialog pBeallitasok) {
        this.jdBeallitasok = pBeallitasok;
    }

    /**
     * Visszaadja a palya meretenek beallitasara szolgalo JSpinner-t.
     * @return JSpinner Maga a JSpinner.
     */
    public JSpinner getPalyaSpinner() {
        return palyaSpinner;
    }

    /**
     * Beallitja a palya meretenek beallitasara szolgalo JSpinner-t.
     * @param palyaSpinner A beallitando JSpinner.
     */
    public void setPalyaSpinner(JSpinner palyaSpinner) {
        this.palyaSpinner = palyaSpinner;
    }
    /**
     * Visszaadja a jegesmacik szamanak beallitasara szolgalo JSpinner-t.
     * @return JSpinner Maga a JSpinner.
     */
    public JSpinner getNpcSpinner() {
        return npcSpinner;
    }

    /**
     * Beallitja a jegesmacik szamanak beallitasara szolgalo JSpinner-t.
     * @param npcSpinner A beallitando JSpinner.
     */
    public void setNpcSpinner(JSpinner npcSpinner) {
        this.npcSpinner = npcSpinner;
    }

    /**
     * Az View inicializalasa.
     */
    public void init() {


        // ----------------MENUBAR----------------
        menuBar = new JMenuBar();
        mJatek = new JMenu();
        miUj = new JMenuItem();
        miBetolt = new JMenuItem();
        miMentes = new JMenuItem();
        miBeallitasok = new JMenuItem();
        miKilep = new JMenuItem();
        mHelp = new JMenu();
        miHelp = new JMenuItem();

        mJatek.setText("Játék");

        miUj.setText("Új");
        miUj.addActionListener(Main.getCont());
        mJatek.add(miUj);


        miBetolt.setText("Betöltés");
        miBetolt.addActionListener(Main.getCont());
        mJatek.add(miBetolt);

        miMentes.setText("Mentés");
        miMentes.addActionListener(Main.getCont());
        mJatek.add(miMentes);

        miBeallitasok.setText("Beállitások");
        miBeallitasok.addActionListener(Main.getCont());
        mJatek.add(miBeallitasok);

        miKilep.setText("Kilépés");
        miKilep.addActionListener(Main.getCont());
        mJatek.add(miKilep);

        menuBar.add(mJatek);

        mHelp.setText("Segítség");

        miHelp.setText("Segítség");
        miHelp.addActionListener(Main.getCont());
        mHelp.add(miHelp);

        menuBar.add(mHelp);
        setJMenuBar(menuBar);


        //--------------------JatekKepernyo---------------------
        alap = new JPanel(){
            public void paintComponent(Graphics g) {
                g.drawImage(getImage(elem.betoltokep), 0, 0, alap.getWidth(), alap.getHeight(), this);
            }
        };
        alap.setLayout(new BorderLayout());
        alap.setVisible(true);


        //-------------------------JatekSplitPanel-------------------------
        jatekalatt = new JSplitPane();
        jatekalatt.setOrientation(JSplitPane.HORIZONTAL_SPLIT);

        pReszletek = new JSplitPane();
        pReszletek.setOrientation(JSplitPane.VERTICAL_SPLIT);

        pTabla = new TablaView();
        pKarakter = new KarakterView();
        pReszletek.setBottomComponent(pTabla);
        pReszletek.setTopComponent(pKarakter);
        pReszletek.setResizeWeight(0.5);

        pMezo = new JPanel(){
            public void paintComponent(Graphics g) {
                g.drawImage(getImage(elem.viz), 0, 0, pMezo.getWidth(), pMezo.getHeight(), this);
            }
        };
        pMezo.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        pMezo.setLayout(new GridLayout(8, 8));
        jatekalatt.setRightComponent(pReszletek);
        jatekalatt.setLeftComponent(pMezo);
        jatekalatt.setResizeWeight(0.6);
        jatekalatt.setSize(800, 600);
        jatekalatt.setVisible(true);


        // ----------------------UzenetDialog-----------

        JPanel pUzenet = new JPanel();
        SpringLayout sl1 = new SpringLayout();
        pUzenet.setLayout(sl1);
        szoveg = new JOptionPane();

        //----- Szoveg -------
        szoveg.setMessage("uzenet");
        szoveg.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        szoveg.setFont(new Font("TimesRoman", Font.BOLD, 14));
        szoveg.setFocusable(true);
        JScrollPane js = new JScrollPane();
        js.setViewportView(szoveg);

        //---- okButton ----
        JButton okButton = new JButton();
        okButton.setText("OK");
        okButton.addActionListener(Main.getCont());

        //---------- ikon --------------
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("graphics/ikon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        uzenet = szoveg.createDialog(null, "Üzenet");
        uzenet.setIconImage(image);
        uzenet.setVisible(false);
        uzenet.setAlwaysOnTop(true);
        uzenet.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        uzenet.pack();
        uzenet.setSize(300,200);

        //----------------------Beallitasok-----------

        jdBeallitasok = new JDialog();
        jdBeallitasok.setTitle("Beállitások");
        jdBeallitasok.setAlwaysOnTop(true);
        jdBeallitasok.setLocationRelativeTo(getOwner());
        jdBeallitasok.setLayout(new BorderLayout());
        jdBeallitasok.setDefaultCloseOperation(HIDE_ON_CLOSE);

        JPanel beallitasokPanel = new JPanel(){
            public void paintComponent(Graphics g) {
                g.drawImage(getImage(elem.beallitasok), 5, 5, this.getWidth()/5, this.getHeight()-10, this);
            }
        };
        SpringLayout sl = new SpringLayout();

        beallitasokPanel.setLayout(sl);

        JLabel beallitasokLabel = new JLabel("Beállítások");


        JLabel eszkimoLabel = new JLabel("Eszkimók száma:");
        JLabel sarkkutatoLabel = new JLabel("Sarkkutatók száma:");
        JLabel palyaLabel = new JLabel("Pálya mérete:");
        JLabel npcLabel = new JLabel("Jegesmedvék száma:");

        eszkimoJSpinner = new JSpinner();
        eszkimoJSpinner.setModel(new SpinnerNumberModel(2, 0, 10, 1));
        JFormattedTextField tf1 = ((JSpinner.DefaultEditor) eszkimoJSpinner.getEditor()).getTextField();
        tf1.setEditable(false);
        tf1.setBackground(Color.white);

        sarkkutatoJSpinner = new JSpinner();
        sarkkutatoJSpinner.setModel(new SpinnerNumberModel(1, 0, 10, 1));
        JFormattedTextField tf2 = ((JSpinner.DefaultEditor) sarkkutatoJSpinner.getEditor()).getTextField();
        tf2.setEditable(false);
        tf2.setBackground(Color.white);

        palyaSpinner = new JSpinner();
        palyaSpinner.setModel(new SpinnerNumberModel(5, 3, 10, 1));
        JFormattedTextField tf3 = ((JSpinner.DefaultEditor) eszkimoJSpinner.getEditor()).getTextField();
        tf3.setEditable(false);
        tf3.setBackground(Color.white);

        npcSpinner = new JSpinner();
        npcSpinner.setModel(new SpinnerNumberModel(1, 0, 5, 1));
        JFormattedTextField tf4 = ((JSpinner.DefaultEditor) npcSpinner.getEditor()).getTextField();
        tf4.setEditable(false);
        tf4.setBackground(Color.white);

        JButton mentesButton = new JButton("Beállítások Mentése");
        JButton visszaButton = new JButton("Vissza");
        mentesButton.addActionListener(Main.getCont());
        visszaButton.addActionListener(Main.getCont());

        beallitasokPanel.add(beallitasokLabel);
        beallitasokPanel.add(eszkimoLabel);
        beallitasokPanel.add(sarkkutatoLabel);
        beallitasokPanel.add(palyaLabel);
        beallitasokPanel.add(npcLabel);
        beallitasokPanel.add(eszkimoJSpinner);
        beallitasokPanel.add(sarkkutatoJSpinner);
        beallitasokPanel.add(palyaSpinner);
        beallitasokPanel.add(npcSpinner);
        beallitasokPanel.add(mentesButton);
        beallitasokPanel.add(visszaButton);


        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, beallitasokLabel,0,SpringLayout.HORIZONTAL_CENTER, beallitasokPanel);
        sl.putConstraint(SpringLayout.NORTH, beallitasokLabel,20,SpringLayout.NORTH, beallitasokPanel);

        sl.putConstraint(SpringLayout.EAST, eszkimoLabel,-10,SpringLayout.HORIZONTAL_CENTER, beallitasokPanel);
        sl.putConstraint(SpringLayout.NORTH, eszkimoLabel,20,SpringLayout.SOUTH, beallitasokLabel);

        sl.putConstraint(SpringLayout.EAST, sarkkutatoLabel,-10,SpringLayout.HORIZONTAL_CENTER, beallitasokPanel);
        sl.putConstraint(SpringLayout.NORTH, sarkkutatoLabel,20,SpringLayout.SOUTH, eszkimoLabel);

        sl.putConstraint(SpringLayout.EAST, palyaLabel,-10,SpringLayout.HORIZONTAL_CENTER, beallitasokPanel);
        sl.putConstraint(SpringLayout.NORTH, palyaLabel,20,SpringLayout.SOUTH, sarkkutatoLabel);

        sl.putConstraint(SpringLayout.EAST, npcLabel,-10,SpringLayout.HORIZONTAL_CENTER, beallitasokPanel);
        sl.putConstraint(SpringLayout.NORTH, npcLabel,20,SpringLayout.SOUTH, palyaLabel);

        sl.putConstraint(SpringLayout.WEST, eszkimoJSpinner,10, SpringLayout.HORIZONTAL_CENTER, beallitasokPanel);
        sl.putConstraint(SpringLayout.NORTH, eszkimoJSpinner,0,SpringLayout.NORTH, eszkimoLabel);
        sl.putConstraint(SpringLayout.EAST, eszkimoJSpinner,80,SpringLayout.WEST, eszkimoJSpinner);

        sl.putConstraint(SpringLayout.WEST, sarkkutatoJSpinner,10, SpringLayout.HORIZONTAL_CENTER, beallitasokPanel);
        sl.putConstraint(SpringLayout.NORTH, sarkkutatoJSpinner,0,SpringLayout.NORTH, sarkkutatoLabel);
        sl.putConstraint(SpringLayout.EAST, sarkkutatoJSpinner,80,SpringLayout.WEST, sarkkutatoJSpinner);

        sl.putConstraint(SpringLayout.WEST, palyaSpinner,10, SpringLayout.HORIZONTAL_CENTER, beallitasokPanel);
        sl.putConstraint(SpringLayout.NORTH, palyaSpinner,0,SpringLayout.NORTH, palyaLabel);
        sl.putConstraint(SpringLayout.EAST, palyaSpinner,80,SpringLayout.WEST, palyaSpinner);

        sl.putConstraint(SpringLayout.WEST, npcSpinner,10, SpringLayout.HORIZONTAL_CENTER, beallitasokPanel);
        sl.putConstraint(SpringLayout.NORTH, npcSpinner,0,SpringLayout.NORTH, npcLabel);
        sl.putConstraint(SpringLayout.EAST, npcSpinner,80,SpringLayout.WEST, npcSpinner);

        sl.putConstraint(SpringLayout.EAST, visszaButton,-10,SpringLayout.HORIZONTAL_CENTER, beallitasokPanel);
        sl.putConstraint(SpringLayout.NORTH, visszaButton,20,SpringLayout.SOUTH, npcSpinner);

        sl.putConstraint(SpringLayout.WEST, mentesButton,10,SpringLayout.HORIZONTAL_CENTER, beallitasokPanel);
        sl.putConstraint(SpringLayout.NORTH, mentesButton,20,SpringLayout.SOUTH, npcSpinner);

        beallitasokPanel.setVisible(true);

        jdBeallitasok.add(beallitasokPanel,BorderLayout.CENTER);
        jdBeallitasok.setIconImage(image);
        jdBeallitasok.setVisible(false);
        jdBeallitasok.pack();
        jdBeallitasok.setSize(600,300);
        jdBeallitasok.setResizable(false);


        //----------------------------------FRAME--------------------
        this.getContentPane().setLayout(new BorderLayout());
        setJMenuBar(menuBar);
        this.getContentPane().add(alap, BorderLayout.CENTER);
        setTitle("Jégmező");
        pack();
        setSize(800, 630);
        setLocationRelativeTo(getOwner());
        setIconImage(image);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    /**
     * Inicializalja a grafikai elemeket a programban.
     * @return boolean Sikeres volt-e.
     */
    public boolean grafikaInit(){

        BufferedImage image = null;
        try {
            for (int i = 0; i < elemek.length; i++) {
                image = ImageIO.read(new File("graphics/" + elemek[i]));
                grafikaElemek.add(image);
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Visszaadja az adott elem megjeleniteset.
     * @param e Maga az elem.
     * @return Image Az elem megjelenitese / kepe.
     */
    public static Image getImage(elem e){
        return grafikaElemek.get(e.ordinal());
    }

    /**
     * Visszaadja a dolog nevet.
     * @param d Maga a dolog.
     * @return String A dolog neve.
     */
    public static String getDologNeveS(Dolog d) {

        if (d.hasonlit(new Lapat())) return "Lapát";
        else if (d.hasonlit(new Kotel())) return "Kötél";
        else if (d.hasonlit(new TorekenyLapat())) return "TörékenyLapát";
        else if (d.hasonlit(new Buvarruha())) return "Buvárruha";
        else if (d.hasonlit(new Etel())) return "Étel";
        else if (d.hasonlit(new PisztolyAlkatresz())) return "Pisztoly";
        else return "Sátor";
    }

    /**
     * Visszaadja a dolog enumjat.
     * @param d Maga a dolog
     * @return elem Az enumja a dolognak.
     */
    public static elem getDologNeveE(Dolog d) {

        if (d.hasonlit(new Lapat())) return elem.lapat;
        else if (d.hasonlit(new Kotel())) return elem.kotel;
        else if (d.hasonlit(new TorekenyLapat())) return elem.torekenylapat;
        else if (d.hasonlit(new Buvarruha())) return elem.buvarruha;
        else if (d.hasonlit(new Etel())) return elem.etel;
        else if (d.hasonlit(new PisztolyAlkatresz())) return elem.pisztoly;
        else return elem.csukottsator;
    }

    /**
     * Beallitja a kivalasztott tabla keretet es ennek megfeleloen a szomszedai is ellatja egy kerettel,
     * ezzel szimbolizalva a koztuk levo szomszedsagi viszonyt.
     * @param b A kivalasztott TablaButton.
     * @param c A szomszedok keretszine.
     */
    public void border(TablaButton b, Color c){

        for (int i = 0; i < buttonok.size(); i++){
            buttonok.get(i).setKeret(false);
        }
        for (Tabla t: b.getT().getSzomszedok()) {
            buttonok.get( View.getM().getTablak().indexOf(t)).setKeret(true);
            buttonok.get( View.getM().getTablak().indexOf(t)).setKeretszin(c);
        }
        b.setKeret(true);
        b.setKeretszin(Color.RED);
        pMezo.repaint();
    }

    /**
     * A mezo ujra toltese.
     */
    public void mezoUjraTolt(){
        if(m != null){
            buttonok.clear();
            pMezo.removeAll();
            int q = (int) sqrt(m.getTablak().size());
            pMezo.setLayout(new GridLayout(q, q));
            for (int i = 0; i < m.getTablak().size(); i++) {
                buttonok.add(new TablaButton(m.getTablak().get(i)));
                pMezo.add(buttonok.get(i));
            }
        }
    }
}
