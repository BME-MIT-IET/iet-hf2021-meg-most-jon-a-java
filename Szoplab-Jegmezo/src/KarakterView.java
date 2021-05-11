import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A KarakterView osztaly felelossegeit valositja meg.
 */
public class KarakterView extends JPanel {

    /**
     * Az aktualis karakter.
     */
    private Karakter k;

    /**
     * Visszaadja az aktualis karaktert.
     * @return Karakter Az aktualis karakter.
     */
    public Karakter getK() {
        return k;
    }

    /**
     * Beallitja az aktualis karaktert.
     * @param k Az aktualis karakter.
     */
    public void setK(Karakter k) {
        this.k = k;
    }

    /**
     * Tarolja a dolgok megjeleniteset / nezeteit.
     */
    private ArrayList<Image> dolgokI = new ArrayList<>();

    /**
     * Egy feluldefinialt fuggveny, mely megjeleniti
     * az aktualis kakarterrol a lenyegesebb informaciokat
     * (HP, munka, dolgok amivel rendelkezik...stb.)
     * @param g Egy grafikus objektum.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        k = View.getM().getAktualis_karakter();
        Image imageK = null;
        dolgokI.clear();

        if(k.getClass() == new Eszkimo().getClass()) imageK = View.getImage(View.elem.eszkimo);
        else if(k.getClass() == new Sarkkutato().getClass()) imageK = View.getImage(View.elem.sarkkutato);

        for (int i = 0; i < k.getDolgok().size(); i++){
            dolgokI.add(View.getImage(View.getDologNeveE(k.getDolgok().get(i))));
        }
        dolgokI.add(View.getImage(View.elem.kez));

        g.drawImage(imageK, 0, 0, this.getWidth() / 2, this.getHeight() * 2 / 3, this);

        g.setFont(new Font("TimesRoman", Font.BOLD, 14));
        g.drawString("Sorszám: ", this.getWidth() / 2, this.getHeight() / 5 * 2 / 3);
        g.drawString("Hp: ", this.getWidth() / 2, this.getHeight() / 5 * 2 * 2 / 3);
        g.drawString("Munka: ", this.getWidth() / 2, this.getHeight() / 5 * 3 * 2 / 3);
        g.drawString("Tábla: ", this.getWidth() / 2, this.getHeight() / 5 * 4 * 2 / 3);

        g.setColor(Color.RED);
        String tablaTipus = "Jég";
        if(k.getTabla().getStabilitas() == 0) tablaTipus = "Víz";
        g.drawString("" + View.getM().getKarakterek().indexOf(k), (int) (this.getWidth() /2 + 80), this.getHeight() / 5 * 2 / 3);
        g.drawString("" + k.getHp() + "/" + k.getMaxHp(), (int) (this.getWidth() /2 + 80), this.getHeight() / 5 * 2 * 2 / 3);
        g.drawString("" + k.getMunka(), (int) (this.getWidth() /2  + 80), this.getHeight() / 5 * 3 * 2 / 3);
        g.drawString(tablaTipus, (int) (this.getWidth() /2 + 80), this.getHeight() / 5 * 4 * 2 / 3);
        //g.drawString("" + k.getTabla().getHoreteg(), (int) (this.getWidth() /2 + 80), this.getHeight() / 5 * 5 * 2 / 3);

        g.setColor(Color.BLACK);
        int ugras = getWidth()/10;
        g.drawRect(ugras, (int) (this.getHeight() * 0.7), ugras * 8, this.getHeight()/9 *2);
        for(int i = 0; i < k.getDolgok().size(); i++){
            g.drawImage(dolgokI.get(i), ugras * (i+1), (int) (this.getHeight() * 0.7), this.getWidth() /10, this.getHeight() /9 *2 -1, this);
        }
        g.drawImage(dolgokI.get(dolgokI.size()-1), ugras * (8), (int) (this.getHeight() * 0.7), this.getWidth() /10, this.getHeight() /9 *2 -1, this);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        int valasztott = k.getAktivdolog(); if(k.getDolgok().size() == k.getAktivdolog()) valasztott = 7;
        g2.drawRect(ugras * (8), (int) (this.getHeight() * 0.7)+1, this.getWidth() /10, this.getHeight()/9 *2 -1);
        g.setColor(Color.RED);
        g2.drawRect(ugras * (valasztott+1), (int) (this.getHeight() * 0.7)+1, this.getWidth() /10, this.getHeight()/9 *2 -1);

       }
}
