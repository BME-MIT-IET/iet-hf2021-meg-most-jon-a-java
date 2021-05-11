
/**
 * Az Iglu osztaly felelossegeit valositja meg, valamint az Epitmeny interfeszt.
 */
public class Iglu implements Epitmeny {
    /**
     *  A tabla, melyen az iglu elhelyezkedik.
     */
    private Tabla tabla;

    /**
     * Frissiti az adott epitmeny allapotat.
     * @param max Jatekban levo jatekosok szama,
     * ez altal kepes szamon tartani mikor telik
     * le egy kor, es tud koronkent valtozni (oregedni)
     * (jelen esetben nem hasznaljuk).*/
    public void allapotFrissit(int max) {
        kiir("epitmeny_frissult_marad_Iglu", "output.txt");
    }

    /**
     * Osszehasonlitja onmagat egy masik epitmennyel.
     * @param e Az epitmeny, melyet onmagaval hasonlit ossze.
     * @return boolean Amennyiben a ket epitmeny tipusa azonos a
     * visszateresi ertek true, minden mas esetben false.
     */
    @Override
    public boolean hasonlit(Epitmeny e) {
        /* Aktualis metodus nevenek kiirasa debug / tesztek celjabol! */
        log("Most a <" + getClass() + "> Osztaly <" + new Throwable().getStackTrace()[0].getMethodName() + "> fuggvenye fut! ");

        return this.getClass() == e.getClass();
    }

    /**
     * Visszaadja a tablat, melyen az epitmeny all.
     * return@ Tabla A tabla, melyen az epitmeny all.
     */
    @Override
    public Tabla getTaba() {
        return tabla;
    }

    /**
     * Beallitja a tablat, melyen az epitmeny all.
     * @param t A tabla, melyet az epitmeny helyenek allit be.
     */
    @Override
    public void setTaba(Tabla t) {
        tabla=t;
    }
}
