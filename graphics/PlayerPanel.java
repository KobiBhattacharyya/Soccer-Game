package graphics;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;




/**Panel to display the position of the players*/

public class PlayerPanel extends JPanel{

  private int x;
  private int y;
  private Color c;
  private String lastname;

  /** Give the ball a location to be drawn
  * @param x x position of the player
  * @param y y position of the player
  * @param c color of the player
  * @param name the name of the player
  */
  public PlayerPanel(int x, int y, Color c, String name) {

    this.x = x;
    this.y = y;
    this.c = c;

    String[] fullname = name.split("\\s+");

    if (fullname.length == 2) {

      this.lastname = fullname[1];
    }

    else {

      this.lastname = fullname[0];
    }



  }

  /** Paints the JPanel with the players
  * @param g the graphics instance
  */

  protected void paintComponent(Graphics g)  {
    super.paintComponent(g);

    //painting the inside of the ball
    g.setColor(c);
    g.fillOval(x-13, y-13, 20, 20);
    //painting the outside of the ball
    g.setColor(Color.BLACK);
    g.drawOval(x-13, y-13, 20, 20);

    g.setColor(Color.WHITE);
    g.drawLine(x+5, y-15, x+10, y-25);
    g.drawString(lastname, x- lastname.length(), y-28);


  }
}
