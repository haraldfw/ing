package src.wilhelmsen.vgProg.oving3;

/**
 * Created by Harald Wilhelmsen on 14. Jan 2015.
 */
class Reservation {
	private final AtTime fraTid;
	private final AtTime tilTid;
	private final Customer customer;

	/**
	 * Konstruktør:
	 * fraTid må være før tilTid.
	 * Ingen av argumentene kan være null.
	 */
	public Reservation(AtTime fraTid, AtTime tilTid, Customer kunde) {
		if (fraTid == null || tilTid == null) {
			throw new IllegalArgumentException("Fra-tid og/eller til-tid er null");
		}
		if (fraTid.compareTo(tilTid) >= 0) {
			throw new IllegalArgumentException("Fra-tid er lik eller etter til-tid");
		}
		if (kunde == null) {
			throw new IllegalArgumentException("Kunde er null");
		}
		this.fraTid = fraTid;
		this.tilTid = tilTid;
		this.customer = kunde;
	}

	public AtTime getFraTid() {
		return fraTid;
	}

	public AtTime getTilTid() {
		return tilTid;
	}

	/**
	 * Metoden returnerer true dersom tidsintervallet [sjekkFraTid, sjekkTilTid] overlapper
	 * med det tidsintervallet som er i det reservasjonsobjektet vi er inne i [fraTid, tilTid].
	 * Overlapp er ikke definert hvis sjekkFraTid eller sjekkTilTid er null.
	 * Da kaster metoden NullPointerException.
	 */
	public boolean overlapp(AtTime sjekkFraTid, AtTime sjekkTilTid) {
		return (sjekkTilTid.compareTo(fraTid) > 0 && sjekkFraTid.compareTo(tilTid) < 0);
	}

	public String toString() {
		return "\t\tKunde: " + customer.getNavn() + ", tlf: " + customer.getTlf() + ", fra " +
					   fraTid.toString() + ", til " + tilTid.toString();
	}

	/**
	 * Metode som prøver klassen Reservasjon.
	 */
	public static void main(String[] args) {
		Customer k = new Customer("Anne Hansen", "12345678");
		System.out.println("Totalt antall tester: ");
		Reservation r1 = new Reservation(new AtTime(200302011000L), new AtTime(200302011100L), k);
		Reservation r2 = new Reservation(new AtTime(200301211000L), new AtTime(200301211030L), k);
		Reservation r3 = new Reservation(new AtTime(200302011130L), new AtTime(200302011300L), k);
		Reservation r4 = new Reservation(new AtTime(200302010900L), new AtTime(200302011100L), k);
		if (r1.toString().equals("Kunde: Anne Hansen, tlf: 12345678, fra 01-02-2003 kl 1000, til 01-02-2003 kl 1100") &&
					r2.toString().equals("Kunde: Anne Hansen, tlf: 12345678, fra 21-01-2003 kl 1000, til 21-01-2003 kl 1030") &&
					r3.toString().equals("Kunde: Anne Hansen, tlf: 12345678, fra 01-02-2003 kl 1130, til 01-02-2003 kl 1300") &&
					r4.toString().equals("Kunde: Anne Hansen, tlf: 12345678, fra 01-02-2003 kl 0900, til 01-02-2003 kl 1100")) {
			System.out.println("Reservasjon: Test 1 vellykket.");
		}

		if (r1.overlapp(new AtTime(200302011000L), new AtTime(200302011100L)) &&
					!r1.overlapp(new AtTime(200302021000L), new AtTime(200302021100L)) &&
					r1.overlapp(new AtTime(200302011030L), new AtTime(200302011100L)) &&
					r1.overlapp(new AtTime(200302010930L), new AtTime(200302011030L))) {
			System.out.println("Reservasjon: Test 2 vellykket.");
		}
		// Flg. setning kaster exception (fra-tid lik til-tid)
		//Reservasjon r5 = new Reservasjon(new Tidspunkt(200302011100L), new Tidspunkt(200302011100L), k);
		// Flg. setning kaster exception (fra-tid > til-tid)
		//Reservasjon r5 = new Reservasjon(new Tidspunkt(200302011130L), new Tidspunkt(200302011100L), k);
	}
}