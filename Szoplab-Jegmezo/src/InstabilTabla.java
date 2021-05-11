//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : InstabilTabla.java
//  @ Date : 2020. 03. 19.
//  @ Author : 
//
//



/**
 * Az InstabilTabla osztaly felelossegeit valositja meg.
 */
public class InstabilTabla extends Tabla {

	/**
	 * Ellenozni a tablan levok szamat,
	 * ha tul sokan vannak rajta,
	 * akkor a rajta levok eletuket vesztik.
	 */
	@Override
	public void allapotfrissites() {

		/* Aktualis metodus nevenek kiirasa debug / tesztek celjabol! */
		log("Most a <" + getClass() + "> Osztaly <" + new Throwable().getStackTrace()[0].getMethodName() + "> fuggvenye fut! ");

		if(karakterek.size() > getStabilitas()){
			for (Karakter k : karakterek) {
				k.hpNullazas();
			}
		}
	}

	/**
	 * A karaktert atveszi a tabla,
	 * majd lefutatja az allapotfrissitest.
	 * @param k A karakter, melyet a tabla atvett.
	 */
	@Override
	public void elfogad(Karakter k) {

		/* Aktualis metodus nevenek kiirasa debug / tesztek celjabol! */
		log("Most a <" + getClass() + "> Osztaly <" + new Throwable().getStackTrace()[0].getMethodName() + "> fuggvenye fut! ");

		karakterek.add(k);
		allapotfrissites();
	}
}
