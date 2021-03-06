//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Dolog.java
//  @ Date : 2020. 03. 19.
//  @ Author : 
//
//


import java.io.Serializable;

/**
 * Absztrakt osztaly, a Dolog osztaly felelossegeit valositja meg.
 */
public abstract class Dolog implements Serializable, JatekVezerlo {

	/**
	 * Osszehasonlitja onmagat egy parameterben kapott dologgal.
	 * @param d A dolog, melyet onmagaval hasonlit ossze.
	 * @return boolean Amennyiben a ket dolog tipusa azonos a visszateresi ertek true, minden mas esetben false.
	 */
	public boolean hasonlit(Dolog d){

		/* Aktualis metodus nevenek kiirasa debug / tesztek celjabol! */
		log("Most a <" + getClass() + "> Osztaly <" + new Throwable().getStackTrace()[0].getMethodName() + "> fuggvenye fut! ");

		return this.getClass() == d.getClass();
	}

	/**
	 * Dolog hasznalata, absztrakt fuggveny.
	 * @param k A karakter, aki a dolgot hasznalja.
	 * @param irany Melyik iranyban kivanja hasznalni a dolgot.
	 * @return boolean Pisztolyalkatresz hasznalata eseten, amikor az osszeszereles, illetve a fegyver elsutese
	 * sikeresen megtortent (=jatek vege, gyozelem) tér vissza true ertekkel, ezzel egy jelzest biztositva a
	 * tobbi fuggvenynek. Minden mas esetben false ertekkel ter vissza.
	 */
	public abstract boolean hasznal(Karakter k, int irany);
}
