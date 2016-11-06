package src.wilhelmsen.gkProg.eksamen.h11.losning;

/**
 * Created by Harald on 07.12.2015.
 */
public class Dekk {
  private String dekknavn;
  private String dimensjon;
  private String dekktype;
  private int[] monsterdybde;

  public Dekk(String dekknavn, String dimensjon, String dekktype, int[] monsterdybde) {
    this.dekknavn = dekknavn;
    this.dimensjon = dimensjon;
    this.dekktype = dekktype;
    this.monsterdybde = monsterdybde;
  }

  public void setMonsterdybde(int[] monsterdybde) {
    this.monsterdybde = monsterdybde;
  }

  public String getDekknavn() {
    return dekknavn;
  }

  public String getDimensjon() {
    return dimensjon;
  }

  public String getDekktype() {
    return dekktype;
  }

  public int[] getMonsterdybde() {
    return monsterdybde;
  }
}
