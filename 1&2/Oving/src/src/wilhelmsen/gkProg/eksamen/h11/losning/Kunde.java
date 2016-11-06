package src.wilhelmsen.gkProg.eksamen.h11.losning;

import java.util.Date;

/**
 * Created by Harald on 07.12.2015.
 */
public class Kunde {
  private String bilnr;
  private String navn;
  private Date sisteDekkskift;
  private Dekk dekk;

  public Kunde(Dekk dekk, String bilnr, String navn, Date sisteDekkskift) {
    this.dekk = dekk;
    this.bilnr = bilnr;
    this.navn = navn;
    this.sisteDekkskift = sisteDekkskift;
  }

  @Override
  public String toString() {
    return "bilnr = [" + bilnr + "], navn = [" + navn + "], sisteDekkskift = [" + sisteDekkskift + "]";
  }

  public Dekk getDekk() {
    return dekk;
  }

  public void setDekk(Dekk dekk) {
    this.dekk = dekk;
  }

  public String getBilnr() {
    return bilnr;
  }

  public void setBilnr(String bilnr) {
    this.bilnr = bilnr;
  }

  public String getNavn() {
    return navn;
  }

  public void setNavn(String navn) {
    this.navn = navn;
  }

  public Date getSisteDekkskift() {
    return sisteDekkskift;
  }

  public void setSisteDekkskift(Date sisteDekkskift) {
    this.sisteDekkskift = sisteDekkskift;
  }

  public boolean dekkAnbefaltDybde() {
    for (int dybde : dekk.getMonsterdybde()) {
      if (dybde < 5) return false;
    }
    return true;
  }
}
