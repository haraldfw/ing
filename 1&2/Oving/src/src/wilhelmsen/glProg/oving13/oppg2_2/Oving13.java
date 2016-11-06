package src.wilhelmsen.glProg.oving13.oppg2_2;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by haraldfw on 10/30/15.
 */
public class Oving13 extends GLJPanel implements GLEventListener, KeyListener {

  private static final long serialVersionUID = 1L;

  private GLU glu = new GLU();

  GLUT glut = new GLUT();

  private float rotateX = 15;
  private float rotateY = 15;
  private float rotateZ = 0;
  private float scale = 1;
  private float cx = 0;
  private float cy = 0;
  private float cz = 0;
  private float ex = 1;
  private float ey = 0;
  private float ez = 0;
  private float ux = 0;
  private float uy = 0;
  private float uz = 1;

  public Oving13(GLCapabilities caps) {
    super(caps);
    setPreferredSize(new Dimension(2000, 1500));
    addGLEventListener(this);
    addKeyListener(this);
    rotateX = 15;
    rotateY = 15;
    rotateZ = 0;
  }

  @Override
  public void init(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // Sets the background colour to white
    gl.glMatrixMode(GL2.GL_PROJECTION); // Select The Projection Matrix
    gl.glLoadIdentity(); // Reset the view matrix to the identity matrix
    glu.gluPerspective(45.0, 1.25, 1.0, 20);

    gl.glMatrixMode(GL2.GL_MODELVIEW);
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClearColor(0, 0, 0, 0);
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

    gl.glMatrixMode(GL2.GL_PROJECTION);  // Set up the projection.
    gl.glLoadIdentity();
    gl.glOrtho(-1, 1, -1, 1, -2, 2);
    gl.glMatrixMode(GL2.GL_MODELVIEW);

    gl.glLoadIdentity();             // Set up modelview transform.
    gl.glRotatef(rotateZ, 0, 0, 1);
    gl.glRotatef(rotateY, 0, 1, 0);
    gl.glRotatef(rotateX, 1, 0, 0);
    gl.glScalef(scale, scale, scale);
    glu.gluLookAt(ex, ey ,ez, cx, cy ,cz, ux, uy, uz);

    glut.glutWireCube(scale);
    cube(gl);
  }

  private void cube(GL2 gl) {
    gl.glPushMatrix();
    square(gl, 1, 0, 0);        // front face is red
    gl.glPopMatrix();

    gl.glPushMatrix();
    gl.glRotatef(180, 0, 1, 0); // rotate square to back face
    square(gl, 0, 1, 1);        // back face is cyan
    gl.glPopMatrix();

    gl.glPushMatrix();
    gl.glRotatef(-90, 0, 1, 0); // rotate square to left face
    square(gl, 0, 1, 0);        // left face is green
    gl.glPopMatrix();

    gl.glPushMatrix();
    gl.glRotatef(90, 0, 1, 0); // rotate square to right face
    square(gl, 1, 0, 1);       // right face is magenta
    gl.glPopMatrix();

    gl.glPushMatrix();
    gl.glRotatef(-90, 1, 0, 0); // rotate square to top face
    square(gl, 0, 0, 1);        // top face is blue
    gl.glPopMatrix();

    gl.glPushMatrix();
    gl.glRotatef(90, 1, 0, 0); // rotate square to bottom face
    square(gl, 1, 1, 0);        // bottom face is yellow
    gl.glPopMatrix();
  }

  private void square(GL2 gl, float r, float g, float b) {
    gl.glColor3f(r, g, b);         // The color for the square.
    gl.glTranslatef(0, 0, 0.5f);    // Move square 0.5 units forward.
    gl.glNormal3f(0, 0, 1);        // Normal vector to square (this is actually the default).
    gl.glBegin(GL2.GL_LINE_LOOP);
    gl.glVertex2f(-0.5f, -0.5f);    // Draw the square (before the
    gl.glVertex2f(0.5f, -0.5f);     //   the translation is applied)
    gl.glVertex2f(0.5f, 0.5f);      //   on the xy-plane, with its
    gl.glVertex2f(-0.5f, 0.5f);     //   at (0,0,0).
    gl.glEnd();
  }

  @Override
  public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

  }

  @Override
  public void dispose(GLAutoDrawable drawable) {

  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    if (key == KeyEvent.VK_LEFT) {
      rotateY -= 15;
    } else if (key == KeyEvent.VK_RIGHT) {
      rotateY += 15;
    } else if (key == KeyEvent.VK_DOWN) {
      rotateX += 15;
    } else if (key == KeyEvent.VK_UP) {
      rotateX -= 15;
    } else if (key == KeyEvent.VK_PAGE_UP) {
      rotateZ += 15;
    } else if (key == KeyEvent.VK_PAGE_DOWN) {
      rotateZ -= 15;
    } else if (key == KeyEvent.VK_HOME) {
      rotateX = rotateY = rotateZ = 0;
    } else if (key == KeyEvent.VK_CONTROL) {
      scale += 0.1f;
    } else if (key == KeyEvent.VK_SHIFT) {
      scale -= 0.1f;
    } else if (key == KeyEvent.VK_A) {
      ex = 1;
      ey = -1;
      ez = -1;

      ux = 1;
      uy = 1;
      uz = 1;
    } else if (key == KeyEvent.VK_S) {
      ex = -1;
      ey = 1;
      ez = -1;

      ux = 1;
      uy = 1;
      uz = 1;
    } else if (key == KeyEvent.VK_D) {
      ex = 1;
      ey = -1;
      ez = 1;

      ux = 1;
      uy = 1;
      uz = 1;
    }
    repaint();
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
