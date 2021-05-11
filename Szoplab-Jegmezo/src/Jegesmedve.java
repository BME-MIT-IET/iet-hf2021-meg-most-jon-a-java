import java.io.Serializable;

/**
 * A Jegesmedve osztaly felelossegeit valositja meg.
 */
public class Jegesmedve extends Npc implements Serializable{

    /**
     * Az npc lepteteset vegzi egy veletlenszeru szomszedos tablara.
     * @param irany Melyik iranyba tortenjen a lepes (jelen esetben nem hasznaljuk).
     */
    @Override
    public void cselekszik(int irany) {

        /* Aktualis metodus nevenek kiirasa debug / tesztek celjabol! */
        log("Most a <" + getClass() + "> Osztaly <" + new Throwable().getStackTrace()[0].getMethodName() + "> fuggvenye fut! ");

        /* Veletlenszeruen leker egy tablat, nem o akar valasztani, hanem valaszt helyette a metodus, null-t nem kaphat vissza. */
        boolean valaszt = true;
        if(irany == -1) valaszt = false;
        Tabla tabla_ahovalep = tabla.szomszedLeker(valaszt, irany);

        /* Lepteti magat a kapott tablara, es eltavolitja magat jelenlegirol. */
        tabla.eltavolitNpc(this);
        tabla_ahovalep.hozzaadNpc(this);
        /* megjegyzi az indexet a tablanak ahova lepni fog, hogy kesobb kiirhassa */
        int index = getTabla().getSzomszedok().indexOf(tabla_ahovalep);
        tabla = tabla_ahovalep;

        boolean evett = true;

        /* Ha az erkezett tablan, nincs iglu, csak sator vagy az se, akkor a rajta levo karaktereket "megeszi", hpNullazas. */
        if (tabla.getEpitmeny() != null) {
            if (tabla.getEpitmeny().hasonlit(new Iglu())) {
                evett = false;
            }
        }
        /*Ha nincs epitmeny a tablan*/
        if (evett) {
            evett = false;
            for (Karakter k : tabla.getKarakterek()) {
                k.hpNullazas();
                evett = true;
            }
        }
        /* vegen kiirja, hogy hova lepett, es hogy evett -e*/
        if(evett) kiir("npc_lepett_"+index+"_evett", "output.txt");
        else kiir("npc_lepett_"+index+"_nemevett", "output.txt");
    }
}
