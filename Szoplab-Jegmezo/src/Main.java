

/**
 * Main osztaly.
 */
public class Main {

    /**
     * Maga a jatekot reprezentalo tagvaltozo.
     */
    private static Jatek jatek;
    /**
     * A játék iranyitasat vegzo controller.
     */
    private static Controller cont;


    /**
     * A jatek iranyitasat vegzo controllert adja vissza.
     * @return A controller
     */
    public static Controller getCont() {
        return cont;
    }

    /**
     * Main program.
     * @param args
     */
    public static void main(String[] args) {

        //output, log, error, defaultGame txt tartalmainak torlese
        JatekVezerlo.freset();

        jatek = new Jatek();
        cont = new Controller();
        cont.init(jatek, new View());
    }
}
