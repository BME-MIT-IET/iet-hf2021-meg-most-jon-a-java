import java.io.Serializable;

/**
 *  Epitmeny interface felelossegeit tartja nyilvan.
 */
public interface Epitmeny extends Serializable, JatekVezerlo {

    /**
     * Frissiti az adott epitmeny allapotat.
     * Kovetkezo jatekosonkent van meghivva.
     * @param max Jatekban levo jatekosok szama,
     * ez altal kepes szamon tartani mikor telik
     * le egy kor, es tud koronkent valtozni (oregedni).
     */
    void allapotFrissit(int max);

    /**
     * Osszehasonlitja onmagat egy masik epitmennyel.
     * @param e Az epitmeny, melyet onmagaval hasonlit ossze.
     * @return boolean Amennyiben a ket epitmeny tipusa azonos a
     * visszateresi ertek true, minden mas esetben false.
     */
    boolean hasonlit(Epitmeny e);

    /**
     * Visszaadja a tablat, melyen az epitmeny all.
     * return@ Tabla A tabla, melyen az epitmeny all.
     */
    Tabla getTaba();

    /**
     * Beallitja a tablat, melyen az epitmeny all.
     * @param t A tabla, melyet az epitmeny helyenek allit be.
     */
    void setTaba(Tabla t);
}
