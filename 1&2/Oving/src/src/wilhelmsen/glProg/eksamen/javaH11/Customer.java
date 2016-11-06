package src.wilhelmsen.glProg.eksamen.javaH11;

import java.util.Date;

/**
 * Created by Harald Wilhelmsen on 23/11/2015.
 */
public class Customer {

  private long uid;
  private String name;
  private Date lastTireChange;
  private TireSet tires;

  public Customer(long uid, String name, Date lastTireChange) {
    this.uid = uid;
    this.name = name;
    this.lastTireChange = lastTireChange;
  }

  public boolean allTiresValid() {
    for (int depth : tires.getTreadDepth()) {
      if (depth < 5) {
        return false;
      }
    }
    return true;
  }

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getLastTireChange() {
    return lastTireChange;
  }

  public void setLastTireChange(Date lastTireChange) {
    this.lastTireChange = lastTireChange;
  }

  public TireSet getTires() {
    return tires;
  }

  public void setTires(TireSet tires) {
    this.tires = tires;
  }

  @Override
  public String toString() {
    String str = "Costumer:"
                 + "\nName: " + name
                 + "\nId: " + uid
                 + "\nLast tire change: " + lastTireChange.toString()
                 + "\nTires: " + tires;
    return str;
  }
}
