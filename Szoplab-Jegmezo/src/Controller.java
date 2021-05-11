
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * A Controller osztaly felelossegeit valositja meg.
 */
public class Controller implements MouseListener, ActionListener, KeyListener{

    /**
     * A jatekot szimbolizalo Jatek.
     */
    private Jatek j;
    /**
     * A view-ot (nezetet / megjelenitest) szimbolizalo View.
     */
    private View v;
    /**
     * Tortent-e tabla valasztas a lepeshez.
     */
    private boolean lepesValaszt = false;
    /**
     * Tortent-e kepesseg valasztas.
     */
    private boolean kepessegValaszt = false;
    /**
     * Tortent-e targyhasznalat.
     */
    private boolean hasznalatValaszt = false;
    /**
     * A palya merete.
     */
    int palyameret;
    /**
     * Az eszkimok szama a palyan.
     */
    int eszkimok;
    /**
     * A sarkkutatok szama a palyan.
     */
    int sarkkutatok;
    /**
     * A jegesmedvek szama a palyan.
     */
    int jegesmedvek;

    /**
     * A html altal nyujtott segitseg.
     */
    private String helpHtml = "<html> " +
            "<br> Menürendszer: " +
            "<div style=\"margin:0px 10px\"> " +
            "<br>    Beállítások: </b> Mentése után az Új gombra kell kattintani" +
            "<br>    Mentés: </b> Aktuális játékállás elmentése" +
            "<br>    Betöltés: </b> Előzőleg elmentett játék betöltése " +
            "<br>    Új: </b> Új játék létrehozása:  </div>" +
            "<div style=\"margin:0px 15px\"> " +
            "        Generált: Beállítasok alapján generált pálya." +
            "<br>    Egyedi: Saját egyedi pálya létrehozása. </div>" +
            "<div style=\"margin:0px 20px\"> " +
            "       (Egy txt fájl nevét fogja kérni, amiben a parancsok vannak.) </div>" +
            "<br>Irányítás: " +
            "<div style=\"margin:0px 10px\"> " +
            "<br>   Q:</br> tárgyváltás " +
            "<br>   W + kattintás:</br> lépni lehet a sárgával kijelölt mezőkre " +
            "<br>   F: </br>tárgyfelvétel" +
            "<br>   E + kattintás: </br>tárgyhasználat a kiválasztott táblán" +
            "<br>   R + kattintás:</br> képesség használat a kiválasztott táblán" +
            "<br>   Space: </br>eltűntet minden kijelölést" +  "<br> </div>"+

            "<br>Táblák színei:</br>" +
            "<div style=\"margin:0px 10px\"> " +
            "<br>   fekete:</br> az aktuális tábla, ahol állunk" + "<br>" +
            "<b     style=\"color:green\">zöld:</b> szomszédos táblák" + "<br>" +
            "<b     style=\"color:#FCD12A\">sárga:</b> választható táblák " + "<br>" +
            "<b     style=\"color:red\">piros:</b> kijelölt tábla" +  "<br> </div>" +
            " <br>   Bővebb információt a <u> jegmezo.html </u> fájlban lehet találni. " +
            "</html>";

    /**
     * A kontroller default konstruktora.
     */
    public Controller(){}

    /**
     * A jatek inicializalasa.
     * @param j A jatek maga.
     * @param v A view maga.
     */
    public void init(Jatek j, View v){
        this.v = v;
        this.j = j;
        //----------------Grafikai elemek betoltese----------------------
        if(!v.grafikaInit()) {
            j.error("Hiba a grafikus elemek betoltesekor (pl.: fajl nem elerheto)");
            System.exit(-1);
        }
        View.setM(j.getMezo());
        v.setVisible(true);
        palyameret = (int)v.getPalyaSpinner().getValue();
        eszkimok = (int)v.getEszkimoJSpinner().getValue();
        sarkkutatok = (int)v.getSarkkutatoJSpinner().getValue();
        jegesmedvek = (int)v.getNpcSpinner().getValue();
    }

    /**
     * Az uzenetek megjeleniteset vegzi.
     * Az egyes tipus az a jatekkal kapcsolatos informacios uzeneteket tartalmazza.
     * A masodik tipus az a jatekkal kapcsolatos warning uzeneteket tartalmazza.
     * A harmadik tipus az a jatekkal kapcsolatos error uzeneteket tartalmazza.
     * @param s Az uzenet szovege.
     * @param i Az uzenet tipusa.
     */
    public void uzenet(String s, int i){

        v.getSzoveg().setSelectionValues(null);
        v.getSzoveg().setWantsInput(false);

        if(i == 1) v.getSzoveg().setMessageType(JOptionPane.INFORMATION_MESSAGE);
        else if(i == 2){
            v.getSzoveg().setMessageType(JOptionPane.WARNING_MESSAGE);
        }
        else if(i == 3) v.getSzoveg().setMessageType(JOptionPane.ERROR_MESSAGE);
        else if(i ==  4){
            v.getSzoveg().setMessageType(JOptionPane.QUESTION_MESSAGE);
            v.getSzoveg().setWantsInput(true);
        }
        else if(i == 5){
            v.getSzoveg().setMessageType(JOptionPane.QUESTION_MESSAGE);
            String[] options = {"Generált", "Egyedi"};
            v.getSzoveg().setSelectionValues(options);
        }
        v.getSzoveg().setMessage(s);
        v.getUzenet().setVisible(true);
        v.getUzenet().setSize(300, 200);
    }

    /**
     * Ellenorzi, hogy a jatek folyamata veget ert-e.
     * Kezeli a hovihar mukodeset.
     */
    public void jatekVegeCheck(){

        int kar = j.getMezo().getKarakterek().indexOf(j.getMezo().getAktualis_karakter());
        if(j.jatekLep()){
            j.jatekVege();
            if(j.getMezo().isNyerte()){
                uzenet("JÁTÉK VÉGE : NYERTÉL", 1);
                //valami jatekvege kep, a jatek helyett stb;
            }
            else{
                uzenet("JÁTÉK VÉGE : VESZTETTÉL", 1);
                //valami jatekvege kep, a jatek helyett stb;
            }
        }
        else if(kar != j.getMezo().getKarakterek().indexOf(j.getMezo().getAktualis_karakter())){

            if(new Random().nextInt(5) == 1) {
                j.parancsfeldolgozas("hovihar 1", j);
                uzenet("HÓVIHAR!", 2);
                jatekVegeCheck();
                v.getJatekalatt().repaint();
            }
            uzenet("Következő játékos!", 2);
        }
    }

    /**
     * Az egerrel valo kattintasokra lekezelt folyamatokert felel.
     * Ilyen folyamat a lepes, kepesseghasznalat, dolog hasznalat,
     * tovabba, ha olyan tablara kapcsolunk, melynek csak informaciojara
     * vagyunk kivancsiak.
     * @param e Az eger esemenye.
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        //----------------TABLA VALASZTAS MEGVALOSITASA ---&& csak ha meg megy a jatek--------
        if( (lepesValaszt || kepessegValaszt || hasznalatValaszt) && j.getMezo().isNyerte() == null){

            TablaButton b = (TablaButton) e.getComponent();
            ((TablaView) v.getpTabla()).setT(b.getT());
            String parancs = "";

            if(b.getKeretszin() == Color.YELLOW && b.isKeret()) {
                int karakterS = j.getMezo().getKarakterek().indexOf(j.getMezo().getAktualis_karakter());
                int tablaS = j.getMezo().getAktualis_karakter().getTabla().getSzomszedok().indexOf(b.getT());

                if(lepesValaszt) {
                    parancs = "lep" + " " + karakterS + " " + tablaS;
                    if(b.getT().getClass() == new Viz().getClass())
                        uzenet("Vízbe estél!", 2);

                }
                else if(kepessegValaszt){
                    parancs = "kepesseghasznal" + " " + karakterS + " " + tablaS;
                    if(j.getMezo().getAktualis_karakter().getClass() == new Sarkkutato().getClass()){
                        if(b.getT().getClass() == new Tabla().getClass())
                            uzenet("Tábla felderítve: Stabilitás = stabil", 1);
                        else
                            uzenet("Tábla felderítve: Stabilitás = " + b.getT().getStabilitas(), 1);
                    }
                    else if(b.getT().getClass() == new Viz().getClass()) uzenet("Vízre nem építhet!", 2);
                }
                else if(hasznalatValaszt){
                    parancs = "hasznal" + " " + karakterS + " " + tablaS;
                }
                j.parancsfeldolgozas(parancs, j);

            }
            else {
                uzenet("Az nem választható! \n (nem szomszédos tábla)", 2);
                v.getpMezo().setEnabled(false);
            }
            v.border(b, new Color(0, 200, 0));
            v.getJatekalatt().repaint();
            lepesValaszt = kepessegValaszt = hasznalatValaszt = false;
            if(b.getT().getClass() == new InstabilTabla().getClass() && b.getT().getStabilitas() < b.getT().getKarakterek().size())
                uzenet("Tábla felfordult!", 2);
            jatekVegeCheck();
        }

        //-----------sima tablara kattintas megvalositasa----------------------
        else {
            TablaButton b = (TablaButton) e.getComponent();
            ((TablaView) v.getpTabla()).setT(b.getT());
            v.border(b, new Color(0, 200, 0));
            v.getpTabla().repaint();
        }
    }

    /**
     * Az egerrel valo kattintas lekezeleseert felel.
     * @param e Az eger esemenye.
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Az egerrel valo kattintas felengedesenek lekezelese.
     * @param e Az eger esemenye.
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Az egerrel valo belepes lekezelese egy adott komponensre.
     * @param e Az eger esemenye.
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Az egerrel valo kilepes lekezelese egy adott komponensről.
     * @param e Az eger esemenye.
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * A tortent esemenyeket kezeli le.
     * @param e Maga az esemeny.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand() == "Új"){
            uzenet("Generált vagy Egyedi?", 5);
            String mit = (String)v.getSzoveg().getInputValue();
            if(mit == "Generált") {
                JatekVezerlo.freset();
                beallitasokMentese();
                if(!j.jatekLetrehoz("defaultGame.txt")) {
                    uzenet("Létrehozas sikertelen!", 3);
                    return;
                }
            }
            else if(mit == "Egyedi"){
                uzenet("File neve (pl.: palya.txt)", 4);
                String file = (String) v.getSzoveg().getInputValue();
                JatekVezerlo.freset();
                beallitasokMentese();
                if(!j.jatekLetrehoz(file)){
                    uzenet("Létrehozas sikertelen!", 3);
                    return;
                }

            }
            else return;
            View.setM(j.getMezo());
            v.getContentPane().removeAll();
            v.mezoUjraTolt();
            v.getContentPane().add(v.getJatekalatt(), BorderLayout.CENTER);
            v.pack();
            if (palyameret <= 5) v.setSize(1200, 800);
            else if (palyameret > 5) v.setSize(1600, 1000);
            v.repaint();

        }
        else if(e.getActionCommand() == "Betöltés"){

            String mentes = "aktualismentes.xml";
            String parancs = "betolt " + mentes;
            if(j.parancsfeldolgozas(parancs, j)) {
                View.setM(j.getMezo());
                v.getContentPane().removeAll();
                v.mezoUjraTolt();
                v.getContentPane().add(v.getJatekalatt(), BorderLayout.CENTER);
                v.pack();
                v.setSize(800, 600);
                v.repaint();
            }
            else uzenet("Betöltés sikertelen", 3);
        }
        else if(e.getActionCommand() == "Mentés"){
            if(j.getMezo() == null){
                v.getUzenet().setSize(400, 300);
                uzenet("Mentes sikertelen: Nincs játék betöltve!", 3);
                return;
            }
            if(j.parancsfeldolgozas("mentes aktualismentes.xml", j))
                uzenet("Játék mentve -> aktualismentes.xml", 1);
            else uzenet("Mentés sikertelen!", 3);
        }
        else if(e.getActionCommand() == "Beállitások"){
            //uzenet("még nincs megvalostiva, drága Dávid bácsira vár");

            v.getpBeallitasok().setVisible(true);

        }
        else if(e.getActionCommand() == "Kilépés"){
            System.exit(0);
        }
        else if(e.getActionCommand() == "Segítség"){
            v.getUzenet().setSize(500,600);
            uzenet(helpHtml, 1);

        }
        else if(e.getActionCommand() == "OK"){
            v.getUzenet().setVisible(false);
            v.getJatekalatt().setVisible(true);
        }
        else if(e.getActionCommand() == "Beállítások Mentése"){
            int eszkimokSzama = (Integer)v.getEszkimoJSpinner().getValue();
            int sarkkutatokSzama = (Integer)v.getSarkkutatoJSpinner().getValue();
            if(eszkimokSzama + sarkkutatokSzama < 3){
                uzenet("Minimum 3 karakter kell!", 2);
                v.getSarkkutatoJSpinner().setValue(sarkkutatok);
                v.getEszkimoJSpinner().setValue(eszkimok);
                v.getPalyaSpinner().setValue(palyameret);
                v.getNpcSpinner().setValue(jegesmedvek);
            }
            else {
                palyameret = (int)v.getPalyaSpinner().getValue();
                eszkimok = (int)v.getEszkimoJSpinner().getValue();
                sarkkutatok = (int)v.getSarkkutatoJSpinner().getValue();
                jegesmedvek = (int)v.getNpcSpinner().getValue();
                beallitasokMentese();
                v.getpBeallitasok().setVisible(false);
            }
        }
        else if(e.getActionCommand() == "Vissza"){
            v.getPalyaSpinner().setValue(palyameret);
            v.getEszkimoJSpinner().setValue(eszkimok);
            v.getSarkkutatoJSpinner().setValue(sarkkutatok);
            v.getNpcSpinner().setValue(jegesmedvek);
            v.getpBeallitasok().setVisible(false);
        }
    }

    /**
     * A billentyu leuteseket kezeli.
     * @param e Maga az esemeny.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * A billentyu leuteseket kezeli.
     * @param e Maga az esemeny.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        lepesValaszt = kepessegValaszt = hasznalatValaszt = false;

        if(j.getMezo().isNyerte() == null) {

            //-----------------------TARGYVALTAS---------------------------------------
            if (e.getKeyCode() == KeyEvent.VK_Q) {
                j.getMezo().getAktualis_karakter().targyValtas();
                v.getpKarakter().repaint();
            }

            //--------------------------LEPES_MOZGAS-------------------------------------
            else if (e.getKeyCode() == KeyEvent.VK_W) {
                lepesValaszt = true;
            }

            //___________DOLOGHASZNALATA-----------------------------------
            else if (e.getKeyCode() == KeyEvent.VK_E) {
                Karakter k = j.getMezo().getAktualis_karakter();

                //-------------------KEZ HASZNALAT_ASAS---------------
                if (k.getAktivdolog() == k.getDolgok().size()) {
                    String parancs = "as " + j.getMezo().getKarakterek().indexOf(k);
                    j.parancsfeldolgozas(parancs, j);
                    jatekVegeCheck();
                    v.getJatekalatt().repaint();
                }

                //--------KOTEL HASZNALAT KIVETELES--------------
                else if (View.getDologNeveE(k.getDolgok().get(k.getAktivdolog())) == View.elem.kotel) {
                    hasznalatValaszt = true;
                }
                //----------NORMAL TARGYHASZNALAT------------------
                else {
                    String parancs = "hasznal " + j.getMezo().getKarakterek().indexOf(k);
                    parancs += " " + 0;
                    j.parancsfeldolgozas(parancs, j);
                    jatekVegeCheck();
                    v.getJatekalatt().repaint();
                }
            }

            //---------------------------------KEPESSEG--------------------------------
            else if (e.getKeyCode() == KeyEvent.VK_R) {
                kepessegValaszt = true;
            }

            //------------------------------TARGYFELVETEL-----------------------------------
            else if (e.getKeyCode() == KeyEvent.VK_F) {
                String parancs = "felvesz " + j.getMezo().getKarakterek().indexOf(j.getMezo().getAktualis_karakter());
                j.parancsfeldolgozas(parancs, j);
                jatekVegeCheck();
                v.getJatekalatt().repaint();

            }

            //----------------------------NEZET_RESET-----------------------------------------
            else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                for (int i = 0; i < View.getButtonok().size(); i++)
                    View.getButtonok().get(i).setKeret(false);
                TablaView tv = (TablaView) v.getpTabla();
                tv.setT(j.getMezo().getAktualis_karakter().getTabla());
                v.getJatekalatt().repaint();
            }

            //------------------_VALASZTASHOZ_TABLAK_KIJELOLESE--------------------------------------
            if (lepesValaszt || kepessegValaszt || hasznalatValaszt) {
                TablaButton b = View.getButtonok().get(j.getMezo().getTablak().indexOf(j.getMezo().getAktualis_karakter().getTabla()));
                v.border(b, Color.YELLOW);
                v.getpMezo().repaint();
                TablaView tv = (TablaView) v.getpTabla();
                tv.setT(b.getT());
                tv.repaint();
            }
        }
    }

    /**
     * Kezeli mikor a lenyomott billentyu felengedesre kerul.
     * @param e Maga az esemeny.
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * A beallitott adatok menteset vegzi.
     */
    private void beallitasokMentese(){
        JatekVezerlo.palyagen(palyameret);
        int eszkimokSzama = eszkimok;
        int sarkkutatokSzama = sarkkutatok;
        int jegesmacikSzama = jegesmedvek;
        int tablakSzama = 0;

        LinkedList<String> parancslista = new LinkedList<>();

        File file = new File("defaultGame.txt");
        Scanner sc;
        FileWriter fw;
        PrintWriter pw;

        /* File megnyitasa, scannelese. */
        try {
                sc = new Scanner(file);
        } catch (FileNotFoundException e) {
                j.error("File megnyitasa sikertelen: " + e);
                return;
        }

        while (sc.hasNext()) {
            String line = sc.nextLine();
            parancslista.add(line);
        }
        sc.close();

        /* eddigi karakterek torlese, tablak megszamolasa */
        int k=0;
        while (k < parancslista.size()) {
            String[] ps = parancslista.get(k).split(" ");
            if(ps.length > 0 && ps[0].equals("hozzaadKarakter")){
                parancslista.remove(k);
            }else{
                k++;
            }
            if(ps.length > 0 && ps[0].equals("hozzaadTabla")){
                tablakSzama++;
            }
        }

        for (int i = 0; i < eszkimokSzama; i++){
            String s = "hozzaadKarakter 1 0";
            parancslista.add(s);
        }

        for (int i = 0; i < sarkkutatokSzama; i++){
            String s = "hozzaadKarakter 2 " + (tablakSzama-1);
            parancslista.add(s);
        }
        for (int i = 0; i < jegesmacikSzama; i++){
            String s = "hozzaadNpc 1 " + (new Random().nextInt(tablakSzama-5) + 2);
            parancslista.add(s);
        }

        try {
            fw = new FileWriter("defaultGame.txt",false);
            pw = new PrintWriter(fw);

            for (String s: parancslista) {
                pw.println(s);
            }
            pw.close();
        } catch (IOException e) {
            j.error("File megnyitasa sikertelen: " + e);
        }
    }
}

