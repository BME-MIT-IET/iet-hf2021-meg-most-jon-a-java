import java.io.Serializable;

/**
 * Npc osztaly felelossegeit valositja meg.
 * Npc az egy olyan szereplo a jatekban, amit a gep iranyit.
 */
public abstract class Npc implements Serializable, JatekVezerlo {

    /**
     * Ebben tartja nyilvan melyik tablan helyezkedik el.
     */
    protected Tabla tabla;

    /**
     * Ez a metodus hivodik meg koronkent az Npc-ken.
     * Ezt valositja meg az osszes npc, es ez alapjan teszi a dolgat.
     * @param irany Melyik iranyba lepjen az npc.
     */
    abstract void cselekszik(int irany);

    /**
     * Beallitja az npc tablajat.
     * @param tabla A tabla, amin az npc elhelyezkedik.
     */
    public void setTabla(Tabla tabla) {
        this.tabla = tabla;
    }

    /**
     * Visszaadja az npc tablajat.
     * @return Tabla A tabla, amin az npc elhelyezkedik.
     */
    public Tabla getTabla(){
        return tabla;
    }
}
