package src.wilhelmsen.glProg.oving13.oppg2_1;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import java.awt.*;

import javax.swing.*;

/**
 * Created by haraldfw on 10/30/15.
 */
public class Oving13 extends JFrame implements GLEventListener {

  private static final long serialVersionUID = 1L;

  private double[][] points;

  private GLU glu = new GLU();

  public Oving13(String title) throws HeadlessException {
    super("Minimal OpenGL");
    GLProfile profile = GLProfile.get(GLProfile.GL2);
    GLCapabilities capabilities = new GLCapabilities(profile);

    GLCanvas canvas = new GLCanvas(capabilities);
    canvas.addGLEventListener(this);

    this.setName("Minimal OpenGL");
    this.getContentPane().add(canvas);

    this.setSize(1000, 750);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setResizable(false);
    canvas.requestFocusInWindow();

    points = new double[8][3];
    points[0] = new double[]{0.0, 2.0, 0.0};
    points[1] = new double[]{1.5, 1.5, 0.0};
    points[2] = new double[]{2.0, 0.0, 0.0};
    points[3] = new double[]{1.5, -1.5, 0.0};
    points[4] = new double[]{0.0, -2.0, 0.0};
    points[5] = new double[]{-1.5, -1.5, 0.0};
    points[6] = new double[]{-2.0, 0.0, 0.0};
    points[7] = new double[]{-1.5, 1.5, 0.0};
  }

  @Override
  public void init(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // Sets the background colour to white
    gl.glMatrixMode(GL2.GL_PROJECTION); // Select The Projection Matrix
    gl.glLoadIdentity(); // Reset the view matrix to the identity matrix
    glu.gluPerspective(45.0, 1.25, 2.0, 250);

    gl.glMatrixMode(GL2.GL_MODELVIEW);
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();

    drawGLScene(drawable);

    gl.glFlush();
  }

  public void drawGLScene(GLAutoDrawable glDrawable) {
    GL2 gl = glDrawable.getGL().getGL2();
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); //Clear The Screen
    // and The Depth Buffer
    gl.glLoadIdentity(); // Reset The View matrix to the identity matrix
    gl.glTranslatef(-30, 0f, -60.0f);

    draw(gl, GL2.GL_POINTS);
    draw(gl, GL2.GL_LINES);
    draw(gl, GL2.GL_LINE_STRIP);
    draw(gl, GL2.GL_LINE_LOOP);
    draw(gl, GL2.GL_TRIANGLES);
    draw(gl, GL2.GL_TRIANGLE_STRIP);
    draw(gl, GL2.GL_TRIANGLE_FAN);
    draw(gl, GL2.GL_QUADS);
    draw(gl, GL2.GL_QUAD_STRIP);
    draw(gl, GL2.GL_POLYGON);
  }

  private void draw(GL2 gl, int mode) {
    gl.glTranslatef(5, 0, 0); // Move Left 1.5 Units and into The Screen 8 units
    gl.glColor3f((float) Math.random(), (float) Math.random(), (float) Math.random());
    gl.glBegin(mode);
    for(double[] p : points) {
      gl.glVertex3d(p[0], p[1], p[2]);
    }
    gl.glEnd();
  }

  @Override
  public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

  }

  @Override
  public void dispose(GLAutoDrawable drawable) {

  }
}
