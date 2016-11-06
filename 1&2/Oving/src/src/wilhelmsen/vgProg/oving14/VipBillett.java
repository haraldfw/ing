package src.wilhelmsen.vgProg.oving14;

/**
 * Created by Harald Wilhelmsen on 25. Feb 2015.
 */
public class VipBillett extends SitteplassBillett {

	private final String navn;

	public VipBillett(String tribunenavn, int pris, int rad, int plass, String navn) {
		super(tribunenavn, pris, rad, plass);
		this.navn = navn;
	}

	@Override
	public String toString() {
		return super.toString() + ". Navn: " + navn;
	}
}
