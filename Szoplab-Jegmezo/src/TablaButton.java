import javax.swing.*;
import java.awt.*;


/**
 * A TablaButton osztaly felelossegeit valositja meg.
 */
public class TablaButton extends JButton  {

    /**
     * A tabla, melynek a valtozasait kovetjuk.
     */
    private Tabla t;
    /**
     * A keret szinet tarolja.
     */
    private Color keretszin = Color.RED;
    /**
     * Rendelkezik-e kerettel a button.
     */
    private boolean keret = false;

    /**
     * A TablaButton osztaly egy parameteres konstruktora,
     * mely beallitja a tablat és listenereket rendel hozza.
     * @param t A tabla melyet beallitunk.
     */
    public TablaButton(Tabla t){
        this.t = t;
        enableInputMethods(true);
        addMouseListener(Main.getCont());
        addKeyListener(Main.getCont());
    }

    /**
     * Visszaadja a keret szinet.
     * @return Color A keret szine.
     */
    public Color getKeretszin() {
        return keretszin;
    }

    /**
     * Beallitja a keret szinet.
     * @param keretszin A keret szine.
     */
    public void setKeretszin(Color keretszin) {
        this.keretszin = keretszin;
    }

    /**
     * Visszaadja, hogy a button / tabla rendelkezik-e kerettel.
     * @return boolean Rendelkezik-e kerettel a button / tabla.
     */
    public boolean isKeret() {
        return keret;
    }

    /**
     * Visszaadja a tablat, melynek a valtozasait kovetjuk.
     * @return Tabla A tabla, melynek a valtozasait kovetjuk.
     */
    public Tabla getT() {
        return t;
    }

    /**
     * Beallitjuk a tablat, melynek a valtoztatasait kovetni akarjuk.
     * @param t A beallitando tabla.
     */
    public void setT(Tabla t) {
        this.t = t;
    }

    /**
     * Beallitjuk, hogy rendelkezik-e kerettel.
     * @param keret Maga a keret. (true / false)
     */
    public void setKeret(boolean keret) {
        this.keret = keret;
    }

    /**
     * Egy feluldefinialt fuggveny, mely megjeleniti a komponenst
     * a fentebb emlitett informaciokkal (keret ha van, es megfelo szinnel).
     * @param g Egy grafikus objektum.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image imageT;
        Image imageD = null;
        Image imageJ = null;
        Image imageE = null;

        if(t.getHoreteg() == 1) imageT = View.getImage(View.elem.horeteg1);
        else if(t.getHoreteg() == 2) imageT = View.getImage(View.elem.horeteg2);
        else{
            if(t.getClass() == new Viz().getClass()) imageT = View.getImage(View.elem.viz);
            else imageT = View.getImage(View.elem.jeg);
        }

        if(t.getDolog() != null) imageD = View.getImage(View.getDologNeveE(t.getDolog()));

        if(t.getNpck().size() > 0 && t.getNpck().get(0).getClass() == new Jegesmedve().getClass()) imageJ = View.getImage(View.elem.jegesmedve);

        if(t.getEpitmeny() != null){
            if(t.getEpitmeny().getClass() == new Iglu().getClass()) imageE = View.getImage(View.elem.iglu);
            else if(t.getEpitmeny().getClass() == new Sator().getClass()) imageE = View.getImage(View.elem.sator);
        }


        int ugras = getWidth()/5;
        g.drawImage(imageT, 5, 5, getWidth()-10, this.getHeight()-10,  this);
        if(t.getHoreteg() < 1) g.drawImage(imageD, ugras, getHeight()/3, ugras, getHeight()/3, this);
        if(imageJ != null) g.drawImage(imageJ, ugras*2, getHeight()/3, ugras, getHeight()/3, this);
        else if(t.getKarakterek().size() > 0) g.drawString(""+t.getKarakterek().size(), (int) (ugras*2.5), (int) (getHeight()*0.55));
        g.drawImage(imageE, ugras*3, getHeight()/3, ugras, getHeight()/3, this);

        if(View.getM().getAktualis_karakter().getTabla() == t){
            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(7));
            g2.drawRect(8, 8, getWidth()-17, getHeight()-17);
        }

        if(keret){
            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(keretszin);
            g2.setStroke(new BasicStroke(4));
            g2.drawRect(5, 5, getWidth()-11, getHeight()-11);
        }
    }
}
