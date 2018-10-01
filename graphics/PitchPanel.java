package graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;


import javax.swing.JPanel;


/**Pitch instance of a JPanel*/

public class PitchPanel extends JPanel{

  /** Paints the JPanel with the pitch and coordinates
  * @param g the graphics instance
  */
  
  protected void paintComponent(Graphics g)  {

    super.paintComponent(g);
    //painting the pitch
    Image pitch = Toolkit.getDefaultToolkit().getImage("graphics/soccer_pitch.jpg");
    g.drawImage(pitch, 0, 0, this);

    g.setColor(Color.WHITE);

    g.drawString("5", 20, 80);
    g.drawString("10", 20, 195);
    g.drawString("15", 20, 310);
    g.drawString("20", 20, 420);
    g.drawString("25", 20, 540);
    g.drawString("30", 20, 655);
    g.drawString("35", 20, 770);

    g.drawString("5", 60, 830);
    g.drawString("10", 145, 830);
    g.drawString("15", 230, 830);
    g.drawString("20", 317, 830);
    g.drawString("25", 405, 830);
  }
}
