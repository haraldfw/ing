package src.wilhelmsen.vgProg.oving14;

/**
 * Created by Harald Wilhelmsen on 25. Feb 2015.
 */
public class Staa extends Tribune {
	private int antSolgteBilletter;

	public Staa(int kapasitet, int prisPerBillett) {
		super("Ståplass", kapasitet, prisPerBillett);
		antSolgteBilletter = 0;
	}

	@Override
	public double finnInntekt() {
		return super.finnInntekt();
	}

	@Override
	public Billett[] kjoepBilletter(int amount) {
		if(amount < getKapasitet() - finnAntallSolgteBilletter()) {
			Billett[] b = new Billett[amount];
			for(int i = 0; i < amount; i++) {
				b[i] = new StaaplassBillett(getTribunenavn(), getPris());
				antSolgteBilletter++;
			}
			return b;
		}
		return null;
	}

	@Override
	public Billett[] kjoepBilletter(String[] names) {
		return kjoepBilletter(names.length);
	}

	@Override
	public int finnAntallSolgteBilletter() {
		return antSolgteBilletter;
	}
}
