package graphics;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JPanel;


/** Panel to display the time and score of the game*/

public class ScorePanel extends JPanel{


  private int homeScore;
  private int awayScore;
  private double time;
  DecimalFormat df = new DecimalFormat("#");



  /** defines the values for the score and time
  * @param homeScore score of the home team
  * @param awayScore score of the away team
  * @param time current time in the match
  */

  public ScorePanel(int homeScore, int awayScore, double time) {

    this.homeScore = homeScore;
    this.awayScore = awayScore;
    this.time = 2* time;
  }

  /** Paints the JPanel with the score and time box
  * @param g the graphics instance
  */

  protected void paintComponent(Graphics g)  {

    super.paintComponent(g);
    //painting the pitch
    g.setColor(Color.WHITE);
    g.fillRect(340, 13, 120, 40);

    g.setColor(Color.BLACK);
    g.drawRect(340, 13, 120, 40);

    g.drawString("HOME: " +homeScore +"  AWAY: " +awayScore, 343, 33);
    g.drawString("TIME: " +df.format(time) +"'", 343, 47);
  }
}
