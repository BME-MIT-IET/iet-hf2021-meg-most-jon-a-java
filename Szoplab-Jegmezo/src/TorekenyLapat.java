/* Torekenylapat osztaly felelossegeit valositja meg. */
public class TorekenyLapat extends Lapat{
    /** Valtozok: */

    /** Hanyszor hasznalhato. */
    int hasznalhato = 3;

    /** Fuggvenyek: */

    /**
     * Torekenylapat hasznalata - asas.
     * @param k A karakter, aki a torekenylapatot hasznalja.
     * @param irany Melyik iranyban kivanja hasznalni a dolgot.
     * @return boolean Minden esetben false ertekkel ter vissza (lasd korabbi feladatokban leiras).
     */
    @Override
    public boolean hasznal(Karakter k, int irany) {
        /* Aktualis metodus nevenek kiirasa debug / tesztek celjabol! */
        log("Most a <" + getClass() + "> Osztaly <" + new Throwable().getStackTrace()[0].getMethodName() + "> fuggvenye fut! ");

        /* Lapattal ket egysegnyi havat lehet ellapatolni. */
        int i = 2;
        while(i > 0) {
            k.as();
            i--;
        }
        /* Hasznalhatosag csokkentese. */
        hasznalhato--;
        kiir("karakter_hasznalt_torekenylapat", "output.txt");
        if(hasznalhato < 1){
            k.getDolgok().remove(this);
            k.targyValtas();
        }
        return false;
    }
}