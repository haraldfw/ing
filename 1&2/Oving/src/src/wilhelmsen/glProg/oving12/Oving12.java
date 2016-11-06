package src.wilhelmsen.glProg.oving12;

import com.jogamp.opengl.GL;
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
public class Oving12 extends JFrame implements GLEventListener {

  private static final long serialVersionUID = 1L;

  private final int width = 2000;
  private final int height = 1500;

  private GLU glu = new GLU();

  public Oving12(String title) throws HeadlessException {
    super("Minimal OpenGL");
    GLProfile profile = GLProfile.get(GLProfile.GL2);
    GLCapabilities capabilities = new GLCapabilities(profile);

    GLCanvas canvas = new GLCanvas(capabilities);
    canvas.addGLEventListener(this);

    this.setName("Minimal OpenGL");
    this.getContentPane().add(canvas);

    this.setSize(width, height);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setResizable(false);
    canvas.requestFocusInWindow();
  }

  @Override
  public void init(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // Sets the background colour to white
    gl.glMatrixMode(GL2.GL_PROJECTION); // Select The Projection Matrix
    gl.glLoadIdentity(); // Reset the view matrix to the identity matrix
    glu.gluPerspective(45.0, 1.25, 2.0,
                       9.0); // Specify the projection matrix (fov, w/h, near plane, far plane)

    gl.glMatrixMode(GL2.GL_MODELVIEW);
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();

    drawGLScene(drawable);
    drawGLScene2(drawable);

    gl.glFlush();
  }

  public void drawGLScene(GLAutoDrawable glDrawable) {
    GL2 gl = glDrawable.getGL().getGL2();
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); //Clear The Screen
    // and The Depth Buffer
    gl.glLoadIdentity(); // Reset The View matrix to the identity matrix
    gl.glColor3f(1, 0.2f, 0.2f); // Set the Colour to Red
    gl.glTranslatef(0, 0.0f, -8.0f); // Move Left 1.5 Units and into The Screen 8 units

    gl.glBegin(GL.GL_TRIANGLES);
    // Top
    gl.glVertex3d(-2, 2, 0);
    // Bottom Left
    gl.glVertex3d(-3, 0, 0);
    // Bottom Right
    gl.glVertex3d(-1, 0, 0);
    // End Drawing the Triangle
    gl.glEnd();

    // Move Right 2 Units
    gl.glTranslatef(2, 0, 0);
    // Set the Colour to Green
    gl.glColor3f(0.33f, 1f, 0.33f);

    // Start drawing a Quad
    gl.glBegin(GL2.GL_QUADS);
    // Top Left
    gl.glVertex3d(0, 1, 0);
    // Top Right
    gl.glVertex3d(1, 1, 0);
    // Bottom Right
    gl.glVertex3d(1, 0, 0);
    // Bottom Left
    gl.glVertex3d(0, 0, 0);
    // End drawing the Quad
    gl.glEnd();

    // Start drawing triangle 1
    gl.glBegin(GL.GL_TRIANGLES);
    // Top
    gl.glVertex3d(0, -2, 0);
    // Bottom Left
    gl.glVertex3d(-1, 0, 0);
    // Bottom Right
    gl.glVertex3d(1, 0, 0);
    // End Drawing the Triangle
    gl.glEnd();

  }

  public void drawGLScene2(GLAutoDrawable glDrawable) {
    GL2 gl = glDrawable.getGL().getGL2();
    gl.glLoadIdentity(); // Reset The View matrix
    gl.glTranslatef(-0.1f, -1.0f,
                    -7.0f); // Move Left 0.1, down 1.0 units and into the screen 7 units
    final double PI = 3.1415926535898; // Initiate constant PI
    int circle_points =
        100; // Initiate circle_points ( number of points to construct/draw the circle)
    gl.glColor3f(0, 0.4f, 1); // Set Colour to Blue
    gl.glBegin(GL2.GL_POLYGON); // Draw a lines between circle points using
    double angle = 0.0; // Initiate angle
    for (int i = 0; i < circle_points; i++) { // for loop
      angle = 2 * PI * i / circle_points; // calculate new angle
      gl.glVertex2f((float) Math.cos(angle),
                    (float) Math.sin(angle));
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
