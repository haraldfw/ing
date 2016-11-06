package src.wilhelmsen.glProg.eksamen.javaH11;

/**
 * Created by Harald Wilhelmsen on 23/11/2015.
 */
public class TireSet {

  private String name;
  private String dimension;
  private String type;
  private int[] treadDepth;

  public TireSet(String name, String dimension, String type, int[] treadDepth) {
    this.name = name;
    this.dimension = dimension;
    this.type = type;
    this.treadDepth = treadDepth;
  }

  public void setTreadDepth(int[] treadDepth) {
    this.treadDepth = treadDepth;
  }

  public String getName() {
    return name;
  }

  public String getDimension() {
    return dimension;
  }

  public String getType() {
    return type;
  }

  public int[] getTreadDepth() {
    return treadDepth;
  }
}
