/* Sator osztaly felelossegeit valositja meg. */
public class Sator extends Dolog implements Epitmeny {
    /** Valtozok: */

    /**
     * Az adott tabla, melyen a sator elhelyezkedik.
     */
    private Tabla tabla;

    /**
     * A sator fel van-e allitva vagy sem.
     */
    private boolean felallitva = false;

    /**
     * A sator megletenek meroszama.
     */
    private int  kor = 0;

    /** Fuggvenyek: */

    /**
     * Sator hasznalata.
     * @param k A karakter, aki a dolgot hasznalja.
     * @param irany Melyik iranyban kivanja felallitani a satrat.
     * @return boolean
     */
    public boolean hasznal(Karakter k, int irany){
        /* Aktualis metodus nevenek kiirasa debug / tesztek celjabol! */
        log("Most a <" + getClass() + "> Osztaly <" + new Throwable().getStackTrace()[0].getMethodName() + "> függvenye fut! ");

            /* A karakter dolgai kozul eltavolitjuk. */
            k.getDolgok().remove(this);
            /* "Elhelyezzuk" a tablan. */
            k.getTabla().setEpitmeny(this);
            felallitva = true;
            tabla = k.getTabla();

        return false;
    }

    /**
     * Ellenorzi, hogy a sator felallitasa ota eltelt-e egy teljes kor.
     * @param max a karakterek száma (ebbol kovetkeztet hogy meddig tart egy kor)
     */
    public void allapotFrissit(int max){
        if(tabla != null) {
            /* Ha a kor nagyobb, mint a parameterben megadott ertek (jatekosok szama), akkor a sátor eltunik. */
            if (kor >= max) {
                tabla.setEpitmeny(null);
                tabla = null;
                kiir("epitmeny_frissult_eltunik_sator", "output.txt");
            } else {
                kiir("epitmeny_frissult_marad_sator", "output.txt");
                kor++;
            }
        }
    }

    /**
     * Osszehasonlitja onmagat egy masik epitmennyel.
     * @param e Az epitmeny, melyet onmagaval hasonlit ossze.
     * @return boolean Amennyiben a ket epitmeny tipusa azonos a visszateresi ertek true, minden mas esetben false.
     */
    @Override
    public boolean hasonlit(Epitmeny e) {
        /* Aktualis metodus nevenek kiirasa debug / tesztek celjabol! */
        log("Most a <" + getClass() + "> Osztaly <" + new Throwable().getStackTrace()[0].getMethodName() + "> függvenye fut! ");

        return this.getClass() == e.getClass();
    }

    /** Getter-setter fuggvenyek. */

    /**
     * Visszaadja a sator egy tulajdonsagat.
     * @return boolean fel van e allitva vagy nincs.
     */
    public boolean isFelallitva() {
        return felallitva;
    }

    /**
     * Beallitja a sator egy tulajdonsagat.
     * @param felallitva ezt az erteket allitja be.
     */
    public void setFelallitva(boolean felallitva) {
        this.felallitva = felallitva;
    }

    /**
     * Visszaadja a tablat.
     * @return Tabla a visszaadott tabla.
     */
    @Override
    public Tabla getTaba() {
        return tabla;
    }

    /**
     * Beallitja a tablat.
     * @param t ezt az erteket allitja be.
     */
    @Override
    public void setTaba(Tabla t) {
        tabla = t;
    }
}
