/**
 * 
 */
package src.wilhelmsen.oving;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Harald Floor Wilhelmsen
 *
 */
public class Mandelbrot {
	
    public static void main(String[]v){
        
    	int n = 640;
        int t;
        int s;
        int i = n*n;
        
        final BufferedImage z = new BufferedImage(n, n, 1);
        
        Frame f = new Frame() {
            public void paint(Graphics g){
                g.drawImage(z, 0, 28, null);
            }
        };
        
        for(f.setSize(n, 668); --i > 0; z.setRGB(i/n, i%n, s*820)) {
            float c = 4f/n;
            float a = c*i/n - 2;
            float b = i%n*c - 2;
            float r = a;
            float e = b,p;
            for(
            	s = t = 99;
            	t-- > 0 && r*r + e*e < 4;
            	s = t, p = r*r - e*e + a, e = r*e*2 + b, r = p
            );
        }
        
        f.setVisible(true);
    }
}
