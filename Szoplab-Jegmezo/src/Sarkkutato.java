//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Sarkkutato.java
//  @ Date : 2020. 03. 19.
//  @ Author : 
//
//

/* A Sarkkutato osztaly felelossegeit valositja meg */
public class Sarkkutato extends Karakter {

	/** Fuggvenyek: */

	/**
	 * Sarkkutato kepessegenek hasznalata,  vagyis megnezi egy adott tablarol, hogy stabil-e.
	 * @param irany Melyik iranyban hasznalja a kepesseget.
	 */
	@Override
	public void kepesseg(int irany) {

		/* Aktualis metodus nevenek kiirasa debug / tesztek celjabol! */
		log("Most a <" + getClass() + "> Osztaly <" + new Throwable().getStackTrace()[0].getMethodName() + "> fuggvenye fut! ");

		/* Leker egy szomszedos tablat, valasztani is szeretne. */
		Tabla tabla_valasztott = tabla.szomszedLeker(true, irany);

		if( tabla_valasztott == null){
			kiir("kepesseghasznal_sikertelen","output.txt");

			return;
		}

		/*Hasznalja a kepesseget: Megnezi, hogy amit valaszott tabla az stabil-e, ezaltal csokken egyel az elvegezheto munkaja az adott korben.  */
		tabla_valasztott.stabilitastMutat();
		setMunka(getMunka()-1);
	}
}
