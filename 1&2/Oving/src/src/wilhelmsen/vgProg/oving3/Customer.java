package src.wilhelmsen.vgProg.oving3;

/**
 * Created by Harald Wilhelmsen on 14. Jan 2015.
 */
/**
 * Kunde.java
 * Inneholder kundedata.
 */
class Customer {
	private final String navn;
	private final String tlf;

	/**
	 * Konstruktør:
	 * Både navn og telefon må oppgis, de kan ikke være verken null eller tomme strenger.
	 */
	public Customer(String navn, String tlf) {
		if (navn == null || navn.trim().equals("")) {
			throw new IllegalArgumentException("Navn må oppgis.");
		}
		if (tlf == null || tlf.trim().equals("")) {
			throw new IllegalArgumentException("Tlf må oppgis.");
		}
		this.navn = navn.trim();
		this.tlf = tlf.trim();
	}

	public String getNavn() {
		return navn;
	}

	public String getTlf() {
		return tlf;
	}

	public String toString() {
		return navn + ", tlf " + tlf;
	}
}
