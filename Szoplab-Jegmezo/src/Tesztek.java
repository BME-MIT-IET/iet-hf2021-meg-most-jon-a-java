import java.util.Scanner;

/* A Tesztek osztaly felelossegeit valositja meg */
public class Tesztek implements JatekVezerlo {
    /** Fuggvenyek: */

    /**
     * Teszt kivatasztasat bonyolitja le.
     */
    public void tesztvalaszt() {

        /* Ez lesz a fajlnak a neve a valasztas utan */
        String name;
        /* While ciklus eddig megy */
        boolean mehet = true;

        /* A dolgok amiket ki fog irni egyszerre. */
        String[] kiirasok = {"\nTesztesetek:","0. Kilepes -> Vissza menube", "1. kepessegEszkimo", "2. kepessegSarkkutato",
                "3. lepViz", "4. lepStabil", "5. lepInstabil", "6. dologHasznalTorekenyLapat", "7. dologHasznalKotel",
                "8. dologHasznalEtel", "9. dologHasznalLapat", "10. dologValtas", "11. as", "12. jatekVegeVeszit",
                "13. jatekVegeNyer", "14. jatekMentes", "15. jatekBetolt", "16. hovihar", "17. satorFrissit",
                "18. jegesMedveLep", "19. Medve-teszt", "20. Felborulas-teszt", "21. Iglu-teszt" , "100. osszesTeszt"};

        /* Tesztek file nevei, ebbol valaszt. */
        String[] tesztFileok = {}; /*{"beKepessegEszkimo.txt", "beKepessegSarkkutato.txt", "beLepViz.txt", "beLepStabil.txt",
                "beLepInstabil.txt", "beTorekenyLapat.txt", "beKotel.txt", "beEtel.txt", "beLapat.txt", "beDologValt.txt",
                "beAs.txt", "beJatekVegeVeszit.txt", "beJatekVegeNyer.txt", "beJatekMentes.txt", "beJatekBetolt.txt",
                "beHovihar.txt", "beSatorFrissit.txt", "beJegesMedveLep.txt", "bePluszTeszt1Medve.txt",
                "bePluszTeszt2Felborul.txt", "bePluszTeszt3Iglu.txt", "osszesTeszt.txt" };*/

        while(mehet) {
            name = null;
            /* kiirja a valasztasi lehetosegeket */
            for (String s : kiirasok) {
                System.out.println(s);
            }

            int tesztValasztas = 0;
            /* beolvassa a valasztast, hibas bemenet eseten jelzi es leall a program */
            try {
                Scanner myInput = new Scanner(System.in);
                tesztValasztas = myInput.nextInt();
            } catch (Exception e) {
                System.out.println("Beolvasas sikertelen: ervenytelen bemenet: " + e);
            }

            /* A korabban megirt tesztek bemeneteit olvassa be egy-egy .txt fajlbol.
            * A valasztas szerinti tesztet valasztja ki
            * */
            if (tesztValasztas < 22 && tesztValasztas > 0) {
                name = tesztFileok[tesztValasztas - 1];
                /* ha a valasztas 0, kilep*/
            } else if (tesztValasztas == 0) {
                mehet = false;
                System.out.println("Kilepes a tesztekbol");
                return;
            } else if (tesztValasztas == 100){
                name = tesztFileok[tesztFileok.length-1];
            }
            else System.out.println("Ilyen valasztasi lehetoseg nincs.");

            if (name != null) {
                /* Torli az kimeneti fileok mostani tartalmat - uresek lesznek. */
                JatekVezerlo.freset();
                /* beolvasssa a parancsokat a fajlbol mindig, egy uj jatekba a prototipus interface
                * utána vegre hajtja ezeket a parancsokat, amennyiben nem tapasztal hibat
                * */
                fparancsokbeolvas(name, new Jatek());
            }
        }
    }
}

