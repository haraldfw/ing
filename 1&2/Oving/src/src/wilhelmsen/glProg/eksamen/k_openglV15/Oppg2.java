package src.wilhelmsen.glProg.eksamen.k_openglV15;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 * Created by haraldfw on 10/30/15.
 */
public class Oppg2 extends JFrame implements GLEventListener {

  private GLU glu = new GLU();
  float angle = 0;

  public Oppg2(String title) throws HeadlessException {
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
    glu.gluPerspective(105.0, 1.25, 0.25f, 250);

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
    //gl.glTranslatef(-1, -1, -3);
    glu.gluLookAt(.25, .25, 3, 0, 0, 0, 0, 1, 0);
    drawCoords(gl);
    gl.glRotatef(angle++, 0, 0, 1);
    gl.glTranslatef(1, 2, 0);
    drawSmiley(gl);
    gl.glTranslatef(-2, -4, 0);
    gl.glRotatef(45, 0, 0, 1);
    drawSmiley(gl);
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

  private void drawSmiley(GL2 gl) {
    gl.glColor3f(1, 1, 0);
    drawcircle(gl, 0, 0, 0, 1, 128, false);
    gl.glColor3f(0, 0, 1);
    drawcircle(gl, 0.35f, 0.35f, 0, 0.2f, 20, true);
    drawcircle(gl, -0.35f, 0.35f, 0, 0.2f, 20, true);
    gl.glColor3f(1, 0, 0);
    drawArc(gl, 0, 0, 0, 0.5f, (float) Math.PI + 0.5f, (float) Math.PI - 1, 20);
  }

  private void drawcircle(GL2 gl, float x, float y, float z, float r, int segments, boolean fill) {
    gl.glBegin(fill ? GL2.GL_TRIANGLE_FAN : GL2.GL_LINE_LOOP);
    for (int i = 0; i < segments; i++) {
      double rad = (i / (float) segments) * Math.PI * 2;
      gl.glVertex3f(x + (float) Math.cos(rad) * r, y + (float) Math.sin(rad) * r, z);
    }
    gl.glEnd();
  }

  private void drawArc(GL2 gl, float x, float y, float z, float r, float startrad, float rads,
                       int segments) {
    gl.glBegin(GL2.GL_LINE_STRIP);
    for (int i = 0; i < segments; i++) {
      double rad = (i / (float) segments) * rads + startrad;
      gl.glVertex3f(x + (float) Math.cos(rad) * r, y + (float) Math.sin(rad) * r, z);
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
