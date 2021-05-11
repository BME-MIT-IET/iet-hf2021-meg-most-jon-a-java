
import javax.swing.*;
import java.awt.*;

/**
 * A TablaView osztaly felelossegeit valositja meg.
 */
public class TablaView extends JPanel {

    /**
     * A megjelenitett tabla.
     */
    Tabla t = new Tabla();

    /**
     * Visszaadja a megjelenitett tablat.
     * @return Tabla A megjelenitett tabla.
     */
    public Tabla getT() {
        return t;
    }

    /**
     * Beallitja a megjelenitett tablat.
     * @param t A megjelenitett tabla.
     */
    public void setT(Tabla t) {
        this.t = t;
    }

    /**
     * Ez egy felul definialt fuggveny, mely megjeleniti
     * magat a tablat es a tablan levo dolgokat, karaktereket,
     * epitmenyeket, valamint, ha van, akkor a tablahoz tartozo keretet.
     * @param g Egy grafikus objektum.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("TimesRoman", Font.BOLD, 14));
        g.setColor(Color.RED);
        g.drawString("Kiválasztott", this.getWidth()/2+10, this.getHeight()/12);
        g.setColor(new Color(0, 200, 0));
        g.drawString("Amire léphet", this.getWidth()/2+10, this.getHeight()/12*2);
        g.setColor(Color.BLACK);

        if(t != null) {

            //------rajta levo karakterek kiirasa------------------
            String rajta = "Hányas játékosok vannak rajta: ";
            String rajta2="   ";
            for (int i = 0; i < t.getKarakterek().size(); i++) {
                rajta2 += View.getM().getKarakterek().indexOf(t.getKarakterek().get(i));
                if(i < t.getKarakterek().size() - 1) rajta2 += ", ";
            }
            g.drawString(rajta, getWidth()/20, this.getHeight()/3*2);
            g.drawString(rajta2, getWidth()/20, this.getHeight()/3*2+20);

            //-----rajta levo dolog, epitmeny kiirasa------------------------
            String dolog = "Dolog: nem látni";
            if(t.getHoreteg() < 1){
                if(t.getDolog() == null) dolog = "Dolog: semmi";
                else dolog = "Dolog: " + (String)View.getDologNeveS(t.getDolog());
            }
            g.drawString(dolog, this.getWidth()/2+10, this.getHeight()/12*4);
            g.drawString("Hóréteg: " + t.getHoreteg(), this.getWidth()/2+10, this.getHeight() /12 *5);
            String epit = "Epítmény: ";
            if(t.getEpitmeny() != null) {
                if (t.getEpitmeny().getClass() == new Iglu().getClass()) {
                    epit += "Iglu";
                }
                else if (t.getEpitmeny().getClass() == new Sator().getClass()) {
                    epit += "Sátor";
                }
            }
            g.drawString(epit, this.getWidth()/2+10, this.getHeight() /12 *6);

            //----------A tabla------------------------------------
            Image imgT;
            Image imgD = null;
            Image imgJ = null;
            Image imgE = null;

            if(t.getHoreteg() == 1) imgT = View.getImage(View.elem.horeteg1);
            else if(t.getHoreteg() == 2) imgT = View.getImage(View.elem.horeteg2);
            else{
                if(t.getStabilitas() == 0) imgT = View.getImage(View.elem.viz);
                else imgT = View.getImage(View.elem.jeg);
            }

            if(t.getDolog() != null) imgD = View.getImage(View.getDologNeveE(t.getDolog()));

            if(t.getNpck().size() > 0 && t.getNpck().get(0).getClass() == new Jegesmedve().getClass()) imgJ = View.getImage(View.elem.jegesmedve);

            if(t.getEpitmeny() != null){
                if(t.getEpitmeny().getClass() == new Iglu().getClass()) imgE = View.getImage(View.elem.iglu);
                else if(t.getEpitmeny().getClass() == new Sator().getClass()) imgE = View.getImage(View.elem.sator);
            }


            //--------------------------------kepek kirajzolasa----------------------------------------------
            int ugras = (getWidth()/2-2)/5;
            g.drawImage(imgT, 2, 2, getWidth()/2, getHeight()/2, this);
            if(t.getHoreteg() < 1) if(imgD != null) g.drawImage(imgD, ugras, getHeight()/2/3, ugras, getHeight()/2/3, this);
            if(imgJ != null) g.drawImage(imgJ, ugras*2, getHeight()/2/3, ugras, getHeight()/2/3, this);
            else if(t.getKarakterek().size() > 0) g.drawString(""+t.getKarakterek().size(), (int) (ugras*2.5), (int) (getHeight()*0.28));
            if(imgE != null) g.drawImage(imgE, ugras*3, getHeight()/2/3, ugras, getHeight()/2/3, this);

            //---------------------------------KERET KIRAJZOLASA---------------------------------------------
            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(3));
            g2.drawRect(2, 2, getWidth()/2, getHeight()/2);
        }
    }
}
