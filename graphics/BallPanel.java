package graphics;

import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JPanel;


/**Ball instance of a JPanel*/

public class BallPanel extends JPanel{

  private int x;
  private int y;

  /** Give the ball a location to be drawn
  * @param x x position of the ball
  * @param y y position of the ball
  */
  public BallPanel(int x, int y) {

    this.x = x;
    this.y = y;
  }

  // this paintComponent method never gets called for some reason (?) it just works like that
  //same with the method variable Graphics g, somehow the super plugs it back into the Graphics class
  // and shit works

  //the repaint() method can get called later to update displays, like if we want to animate shit
  protected void paintComponent(Graphics g)  {
    super.paintComponent(g);
    //painting the inside of the ball
    g.setColor(Color.white);
    g.fillOval(x-10, y-10, 20, 20);
    //painting the outside of the ball
    g.setColor(Color.black);
    g.drawOval(x-10, y-10, 20, 20);
    g.dispose();


  }
}
