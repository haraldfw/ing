package src.wilhelmsen.vgProg.oving14;

import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Created by Harald Wilhelmsen on 25. Feb 2015.
 */
public class Sitte extends Tribune {

	private int [] antOpptatt;  // tabellstørrelse: antall rader
	private int radBredde;

	public Sitte(int rader, int radBredde, int prisPerBillett) {
		super("Sitteplass", rader*radBredde, prisPerBillett);
		antOpptatt = new int[rader];
		this.radBredde = radBredde;
	}

	@Override
	public int finnAntallSolgteBilletter() {
		int b = 0;
		for(int i = 0; i < antOpptatt.length; i++) {
			b += antOpptatt[i];
		}
		return b;
	}

	@Override
	public Billett[] kjoepBilletter(int amount) {
		// hvis det i det hele tatt er nok plasser igjen
		if(amount < getKapasitet() - finnAntallSolgteBilletter()) {
			int rad = -1;
			int plass = -1;

			for(int i = 0; i < antOpptatt.length; i++) {
				// hvis det er plass på denne raden
				if(radBredde - antOpptatt[i] >= amount) {
					// sett rad og plass variabler til disse
					rad = i;
					plass = antOpptatt[i];
					break;
				}
			}

			// hvis loopen ikke fant plass nok, returner null
			if(rad == -1 && plass == -1) return null;

			antOpptatt[rad] += amount;

			Billett[] b = new Billett[amount];
			for(int i = 0; i < amount; i++) {
				b[i] = new SitteplassBillett(getTribunenavn(), getPris(), rad, plass++);

			}
			return b;
		}
		return null;
	}

	@Override
	public Billett[] kjoepBilletter(String[] names) {
		return kjoepBilletter(names.length);
	}
}
