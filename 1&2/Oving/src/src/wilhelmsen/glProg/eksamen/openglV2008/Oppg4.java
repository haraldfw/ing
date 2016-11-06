package src.wilhelmsen.glProg.eksamen.openglV2008;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Oppg4 extends JFrame implements GLEventListener {

  private GLU glu = new GLU();
  double[][] firkant = {
      {-2, 0, 0}, {-1, 0, 0}, {-1, 1, 0}, {-2, 1, 0}
  };
  double[][] pyramid = {
      {-2, 1.5, 3}, {-2, 0, 0}, {-1, 0, 0}, {-1, 1, 0}, {-2, 1, 0}
  };
  double[][] colors = {
      {0, 1, 0},
      {1, 0, 0},
      {1, 0, 1},
      {1, 1, 0},
      {0, 1, 1},
  };

  public Oppg4() {
    GLCanvas canvas = new GLCanvas();
    canvas.addGLEventListener(this);
    this.getContentPane().add(canvas);

    this.setSize(1000, 750);
    this.setVisible(true);
    final FPSAnimator animator = new FPSAnimator(canvas, 180);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        animator.stop();
        System.exit(0);
      }
    });
    animator.start();
  }

  @Override
  public void init(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClearColor(0f, 0f, 0f, 1f); // Sets the background colour to white
    gl.glMatrixMode(GL2.GL_PROJECTION); // Select The Projection Matrix
    gl.glLoadIdentity(); // Reset the view matrix to the identity matrix
    glu.gluPerspective(45, 1.25, 0.25f, 250);

    gl.glMatrixMode(GL2.GL_MODELVIEW);
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); //Clear The Screen
    // and The Depth Buffer
    gl.glLoadIdentity(); // Reset The View matrix to the identity matrix
    drawScene(gl);
    gl.glFlush();
  }

  private void drawScene(GL2 gl) {
    glu.gluLookAt(.25, .25, 8, 0, 2, 0, 0, 1, 0);
    drawCoords(gl);
    tegnFirkant(gl);
    tegnMidtFirkant(gl);
  }

  public void tegnFirkant(GL2 gl) {
    // Bruker GL2 gl som argument da man ikke trenger GLAutoDrawable-objektet for å tegne firkanten
    gl.glBegin(GL2.GL_LINE_LOOP);
    gl.glColor3f(0, 1, 0);
    for (double[] points : firkant) {
      gl.glVertex3d(points[0], points[1], points[2]);
    }
    gl.glEnd();
  }

  private void tegnMidtFirkant(GL2 gl) {
    gl.glTranslated(5, 1, 0);
    gl.glScaled(2, 1, 1);
    tegnFirkant(gl);
    tegnPyramide(gl);
  }

  private void tegnPyramide(GL2 gl) {
    drawSquare(gl, pyramid[1], pyramid[2], pyramid[3], pyramid[4], colors[0]);
    for (int i = 1; i < pyramid.length; i++) {
      int c2 = i + 1;
      if (c2 >= pyramid.length) {
        c2 = 1;
      }
      drawTriangle(gl, pyramid[0], pyramid[i], pyramid[c2], colors[i]);
    }
  }

  // i opgg står det: Hjørnepunktene og fargen på trekanten skal gis på vektorform.
  // Jeg tolker dette på den best designrettede måten, som gjør metode så generell som mulig,
  // altså at man gir metoden argumenter i form a vektorer. Den andre tolkningen vil produsere
  // såkalt "spaghettikode", da man gir array-indexer som argumenter,
  // dette ville vært ekstremt rart å gjøre designmessig.
  private void drawTriangle(GL2 gl, double[] apex, double[] c1, double[] c2, double[] color) {
    gl.glBegin(GL2.GL_TRIANGLES);
    gl.glColor3d(color[0], color[1], color[2]);
    gl.glVertex3d(apex[0], apex[1], apex[2]);
    gl.glVertex3d(c1[0], c1[1], c1[2]);
    gl.glVertex3d(c2[0], c2[1], c2[2]);
    gl.glEnd();
  }

  private void drawSquare(GL2 gl, double[] a, double[] b, double[] c, double[] d, double[] color) {
    gl.glBegin(GL2.GL_QUADS);
    gl.glColor3d(color[0], color[1], color[2]);
    gl.glVertex3d(a[0], a[1], a[2]);
    gl.glVertex3d(b[0], b[1], b[2]);
    gl.glVertex3d(c[0], c[1], c[2]);
    gl.glVertex3d(d[0], d[1], d[2]);
    gl.glEnd();
  }

  private void drawCoords(GL2 gl) {
    gl.glBegin(GL2.GL_LINES);
    gl.glColor3f(0, 0, 1);
    gl.glVertex3f(0, 0, 0);
    gl.glVertex3f(0, 0, 1);

    gl.glColor3f(0, 1, 0);
    gl.glVertex3f(0, 0, 0);
    gl.glVertex3f(0, 1, 0);

    gl.glColor3f(1, 0, 0);
    gl.glVertex3f(0, 0, 0);
    gl.glVertex3f(1, 0, 0);

    gl.glEnd();
  }

  @Override
  public void dispose(GLAutoDrawable drawable) {

  }

  @Override
  public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

  }
}
