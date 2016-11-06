package src.wilhelmsen.gkProg.eksamen.h11;/*  Eksamen høst 2011.BK
  En klasse Dekk som inneholder data om et sett med dekk
	En klasse Kunde som inneholder data om en kunde og deres dekk
	En klasse Dekkhotell som inneholder data om alle kunder
	Et testprogram som prøver ut alle metoder i klassen Dekkhotell*/

import static javax.swing.JOptionPane.DEFAULT_OPTION;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.JOptionPane.showOptionDialog;

/*	|		Dekk						|
*	|-----------------------------------|
*	| -String dekknavn					|
*	| -String dimensjon					|
*	| -String dekktype					|
*	| -int[] monsterdybde				|
*	|-----------------------------------|
*	| +String getDekkNavn()				|
*	| +String getDimensjon				|
*	| +String getTypeDekk()				|
*	| +int[] getMonsterdybde()			|
*   | +boolean equals(Object obj)		|
*   | +int compareTo(Dekk d)            |
*	| +void setMonsterdybde(int[] nyMD)	|
*	|-----------------------------------|
*/
class Dekk {
  private String dekknavn;
  private String dimensjon;
  private String dekkType;
  private int[] monsterdybde;

  public Dekk(String startDekknavn, String startDimensjon, String startDekkType, int[] MD) {
    dekknavn = startDekknavn;
    dimensjon = startDimensjon;
    dekkType = startDekkType;
    monsterdybde = MD;
  }

  // get metoder
  public String getDekknavn() {
    return dekknavn;
  }

  public String getDimensjon() {
    return dimensjon;
  }

  public String getDekkType() {
    return dekkType;
  }

  public int[] getMonsterdybde() {
    return monsterdybde;
  }

  // set metoder
  public void setMonsterdybde(int[] nymonsterdybde) {
    monsterdybde = nymonsterdybde;
  }

  // oppgave l
  // Sjekker om to dekkobjekter er like
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj instanceof Dekk) {
      Dekk tmp = (Dekk) obj;
      if (dekknavn.equals(tmp.getDekknavn()) && dimensjon.equals(tmp.getDimensjon()) && dekkType.equals(tmp.getDekkType()))
        return true;
    }
    return false;
  }


  public int compareTo(Dekk d) {
    if (this == d) return 0;
    if (dekknavn.equals(d)) return 0;
    return (dekknavn.compareTo(d.getDekknavn()));
  }

  public String toString() {
    String hjelp = "Navn på dekk: " + dekknavn
        + " Dimensjon: " + dimensjon
        + " Type dekk: " + dekkType;
    for (int i = 0; i < monsterdybde.length; i++) {
      hjelp += " " + monsterdybde[i];
    }
    hjelp += "\n";
    return hjelp;
  }

} // Avslutter klassen Dekk






/*  Oppgave 2 a)
*	|		Kunde								|
*	|-------------------------------------------|
*	| -String kundeNr							|
*	| -String kundenavn							|
*   | -int sisteSkifteDato						|
*	| -Dekk dekkInfo							|
*	|-------------------------------------------|
*	| +String getKundeNr()						|
*	| +String getKundeNavn()					|
*	| +int getSisteSkifteDato()					|
*	| +Dekk getDekkInfo()						|
*	| +void setKundeNr(String nyKundeNr)		|
*	| +void setKundeNavn(String nyttKundeNavn)	|
*   | +void setSisteSkifteDato(int nySkifteDato)|
*	| +void setDekkInfo(Dekk nyDekkInfo)		|
*	| +boolean compareTo(Kunde k)			|
*	| +boolean godkjentMonster(int monsterVerdi)|
*	|-------------------------------------------|
*/

class Kunde {
  private String kundeNr; //bilnummeret
  private String kundenavn;
  private int sisteSkifteDato; // på formen ÅÅÅÅMMDD
  private Dekk dekkInfo;

  public Kunde(String kundeNr, String kundenavn, int sisteSkifteDato, Dekk nydekkInfo) {
    this.kundeNr = kundeNr;
    this.kundenavn = kundenavn;
    this.sisteSkifteDato = sisteSkifteDato;
    this.dekkInfo = new Dekk(nydekkInfo.getDekknavn(), nydekkInfo.getDimensjon(), nydekkInfo.getDekkType(), nydekkInfo.getMonsterdybde()); //komposisjon
  }

  public String toString() {
    return "Kundenr: " + kundeNr
        + " Navn: " + kundenavn
        + " Siste skiftedato: " + sisteSkifteDato
        + " Informasjon om dekk: " + dekkInfo
        + "\n";
  }

  // get metoder
  public String getKundeNr() {
    return kundeNr;
  }

  public String getKundenavn() {
    return kundenavn;
  }

  public int getSisteSkiftedato() {
    return sisteSkifteDato;
  }

  public Dekk getDekkInfo() {
    return dekkInfo;
  }

  // set metoder
  public void setKundeNr(String nyKundeNr) {
    kundeNr = nyKundeNr;
  }

  public void setNavn(String nyttNavn) {
    kundenavn = nyttNavn;
  }

  public void setSisteSkifteDato(int nySkifteDato) {
    sisteSkifteDato = nySkifteDato;
  }

  public void setDekkInfo(Dekk nyDekkInfo) {
    if (nyDekkInfo != null)
      this.dekkInfo = new Dekk(nyDekkInfo.getDekknavn(), nyDekkInfo.getDimensjon(), nyDekkInfo.getDekkType(), nyDekkInfo.getMonsterdybde()); //komposisjon
    else dekkInfo = null;
  }


  // returnerer sann dersom alle fire dekkene er godkjent
  public boolean godkjentMonster(int monsterVerdi) {
    boolean godkjent = true;
    int[] hjelpetabell = getDekkInfo().getMonsterdybde();
    for (int i = 0; i < hjelpetabell.length; i++) {
      if (hjelpetabell[i] < monsterVerdi) godkjent = false;
    }
    return godkjent;
  }


  public int compareTo(Kunde k) {
    return dekkInfo.compareTo(k.getDekkInfo());
  }

} // Avslutter klassen Kunde




















/*
*	|		Dekkhotell											|
*	|-----------------------------------------------------------|
*	| -String navnDekkhotell									|
*	| -int kTeller												|
*	| -Kunde[] kundeTabell										|
*	|-----------------------------------------------------------|
*	| +String getHotellNavn()									|
*	| +String getkTeller()										|
*   | +Kunde[] getkundeTabell()									|
*	| +void setHotellNavn(String nyttNavn)						|
*   | +boolean regNyKunde(Kunde nyKunde)						|
*	| +String infoKunde(String sKundeNr)			 			|
*	| +String ikkeSkiftetDekk(int dato)							|
*   | +Kunde[] sorter()											|
*   | +String godkjentDekk(int monsterGrense)					|
*	|-----------------------------------------------------------|
*/

class Dekkhotell {
  private String navnDekkhotell;
  private Kunde[] kundeTabell;
  private int kTeller;

  public Dekkhotell(String hotellNavn) {
    this.navnDekkhotell = hotellNavn;
    kundeTabell = new Kunde[20];
    kTeller = 0;
  }

  // get metoder
  public String getHotellNavn() {
    return navnDekkhotell;
  }

  public int getkTeller() {
    return kTeller;
  }

  public Kunde[] getkundeTabell() {
    Kunde[] tabell = new Kunde[kundeTabell.length];
    if (kTeller > 0) {
      for (int i = 0; i < kundeTabell.length; i++) {
        tabell[i] = new Kunde(kundeTabell[i].getKundeNr(), kundeTabell[i].getKundenavn(), kundeTabell[i].getSisteSkiftedato(), kundeTabell[i].getDekkInfo());
      }
    }
    return tabell;
  }

  // set metode
  public void setHotellNavn(String nyttNavn) {
    navnDekkhotell = nyttNavn;
  }

  // En metode som registrerer en ny kunde.
  public boolean regNyKunde(Kunde nyKunde) {
    boolean ok = false;
    if (kTeller < kundeTabell.length && getKunde(nyKunde.getKundeNr()) <= 0) {
      kundeTabell[kTeller] = new Kunde(nyKunde.getKundeNr(), nyKunde.getKundenavn(), nyKunde.getSisteSkiftedato(), nyKunde.getDekkInfo());
      kTeller++;
      ok = true;
    }
    return ok;
  }

  private int getKunde(String knr) {
    for (int i = 0; i < kTeller; i++) {
      if (kundeTabell[i].getKundeNr().equals(knr)) return i;
    }
    return -1;
  }


  // Returnerer en tekststreng som inneholder all informasjon om en kunde
  public String infoKunde(String sKundeNr) {
    int indeks = 0;
    while (indeks < kTeller && !sKundeNr.equals(kundeTabell[indeks].getKundeNr())) indeks++;
    if (!(indeks < kTeller)) return "Fant ikke kunden";
    else return kundeTabell[indeks].toString();
  }

  // Returnerer en tekststreng som inneholder alle kundenr og navn som enda ikke har skiftet dekk
  public String ikkeSkiftetDekk(int dato) {
    String hjelp = "";
    if (kTeller != 0) {
      for (int i = 0; i < kTeller; i++) {
        if (kundeTabell[i].getSisteSkiftedato() < dato) hjelp += kundeTabell[i];
      }
      return hjelp;
    } else return "Kundetabellen er tom";
  }

  // Sorterer kundetabellen basert på om de har like dekk og navn
  public Kunde[] sorter() {
    Kunde[] sortTab = new Kunde[kTeller];
    for (int i = 0; i < kTeller; i++) sortTab[i] = kundeTabell[i];

    for (int start = 0; start < kTeller; start++) {
      int hittilMinst = start;
      for (int j = start + 1; j < kTeller; j++) {
        if (sortTab[j].compareTo(sortTab[start]) < 0) hittilMinst = j;
      }
      Kunde tmp = sortTab[hittilMinst];
      sortTab[hittilMinst] = sortTab[start];
      sortTab[start] = tmp;
    }
    return sortTab;
  }// sorter

  public String toString() {
    String hjelp = "Navn dekkhotell: " + navnDekkhotell
        + " Antall kunder: " + kTeller;
    for (int i = 0; i < kTeller; i++) hjelp += kundeTabell[i];
    hjelp += "\n";
    return hjelp;
  }


  // Returnerer ut en liste over de kundene som ikke har godkjente dekk
  public String godkjentDekk(int monsterGrense) {
    String hjelp = "Liste over kunder med underkjente dekk /n";
    int indeks = 0;
    for (int i = 0; i < kTeller; i++) {
      if (!kundeTabell[i].godkjentMonster(monsterGrense)) hjelp += kundeTabell[i] + "\n";
    }
    return hjelp;
  }

} // Avslutter klassen Dekkhotell


// Et klientprogram til demoformål slik at ekspeditører kan teste funksjonaliteten i programmet.
// Programmet skal gå i en løkke slik at ekspeditøren kan velge mellom de ulike funksjonene i programmet.
class Lf {
  public static void main(String[] args) {

    String objektnavn = showInputDialog("Registrer navnet på dekkhotellet");
    Dekkhotell dekkhotell1 = new Dekkhotell(objektnavn);
    int[] dim3 = {5, 3, 7, 8};
    int[] dim2 = {5, 6, 2, 8};
    int[] dim1 = {5, 6, 7, 8};
    Dekk d1 = new Dekk("Michelin", "17-100", "Sommer", dim3);
    Dekk d2 = new Dekk("Hakepelita", "16-100", "Vinter", dim2);
    Dekk d3 = new Dekk("Viking", "17-200", "Sommer", dim1);
    Dekk d4 = new Dekk("Michelin", "17-100", "Sommer", dim1);
    Dekk d5 = new Dekk("Michelin", "17-100", "Vinter", dim1);
    Dekk d6 = new Dekk("Michelin", "17-100", "Sommer", dim3);

    Kunde a = new Kunde("XR 12345", "Petter Hansen", 20102011, d1);
    Kunde b = new Kunde("XR 23456", "Olga Jensen", 20111010, d2);
    Kunde c = new Kunde("XR 34567", "Stian Jensen", 20111015, d3);
    Kunde d = new Kunde("XR 45678", "Venke Nielsen", 20111013, d4);
    Kunde e = new Kunde("XR 56789", "Gerda Aas", 20111101, d5);
    Kunde f = new Kunde("XR 67890", "Steinar Nielsen", 20111010, d6);

    dekkhotell1.regNyKunde(a);
    dekkhotell1.regNyKunde(b);
    dekkhotell1.regNyKunde(c);
    dekkhotell1.regNyKunde(d);
    dekkhotell1.regNyKunde(e);
    dekkhotell1.regNyKunde(f);
    showMessageDialog(null, "Dette er et program for å administrere kundene til " + objektnavn + ".  Programmet holder oversikten over kundene og hvilke dekk de lagrer på hotellet.");

    int tallsvar = 1;
    String[] muligheter = {"Registrer kunde", "Info om en kunde", "Kunder som må bytte dekk", "Kunder sortert etter like dekk", "Kunder med underkjente dekk", "Avslutt"};
    while (tallsvar >= 0) {
      tallsvar = showOptionDialog(null, "Velg", objektnavn, DEFAULT_OPTION, PLAIN_MESSAGE, null, muligheter, muligheter[0]);
      switch (tallsvar) {
        case 0:
          boolean ok1 = false;
          String kKundeNr = showInputDialog("Registrer kundenummeret (bilnr)");
          String kKundenavn = showInputDialog("Registrer navnet på kunden");
          int kSisteSkifteDato = Integer.parseInt(showInputDialog("Registrer dato for siste dekkskifte (ååååmmdd)"));
          String kDekknavn = showInputDialog("Registrer navn på dekk");
          String kDimensjon = showInputDialog("Registrer dimensjon på dekk");
          String kTypeDekk = showInputDialog("Registrer type dekk (sommer, vinter, piggfri)");
          int[] kMonsterdybde = new int[4];
          for (int i = 0; i < 4; i++) {
            kMonsterdybde[i] = Integer.parseInt(showInputDialog("Registrer målt mønsterdybde for dekk " + i + "(sett 0 hvis ikke målt)"));
          }
          Dekk sDekkInfo = new Dekk(kDekknavn, kDimensjon, kTypeDekk, kMonsterdybde);
          Kunde dKunde = new Kunde(kKundeNr, kKundenavn, kSisteSkifteDato, sDekkInfo);
          ok1 = dekkhotell1.regNyKunde(dKunde);
          if (ok1) showMessageDialog(null, "Registreringen av kunde gikk greit");
          break;
        case 1:
          kKundeNr = showInputDialog("Registrer kundenr for å skrive ut info om en kunde");
          showMessageDialog(null, dekkhotell1.infoKunde(kKundeNr));
          break;
        case 2:
          kSisteSkifteDato = Integer.parseInt(showInputDialog("Registrer dato du ønsker å sjekke mot (ååååmmdd)"));
          showMessageDialog(null, dekkhotell1.ikkeSkiftetDekk(kSisteSkifteDato));
          break;
        case 3:
          Kunde[] kTabell = dekkhotell1.sorter();
          String hjelpetekst = "";
          for (int i = 0; i < kTabell.length; i++) {
            hjelpetekst += kTabell[i];
          }
          showMessageDialog(null, hjelpetekst);
          break;
        case 4:
          int mGrense = Integer.parseInt(showInputDialog("Skriv inn minsteverdien for mønsterdybde"));
          showMessageDialog(null, dekkhotell1.godkjentDekk(mGrense));
          break;
        case 5:
          tallsvar = -1;
          break;
      }
    }
  }
} // Avslutter klassen Losning_EksJava11_V5