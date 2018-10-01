import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import java.util.ArrayList;
import java.awt.Color;
import java.lang.Math;
import graphics.*;



/** JLayeredPane to comile all of the other graphics panels*/

public class MainPanel extends JLayeredPane {


  /**sets the rosters and teams playing in the game
  * @param home roster of the home team
  * @param away roster of the away team
  * @param hTeam the home team
  * @param aTeam the away team
  * @param time the current time in the game
  */

  public MainPanel(ArrayList<Player> home, ArrayList<Player> away, Team hTeam, Team aTeam, double time) {


    //create the pitch panel

    JPanel pitch = new PitchPanel();
    pitch.setBounds(0, 0, 480, 852);
    addImpl(pitch, new Integer(1), 0);


    //go through the player arrays and place players

    for(int i=0;i<11;i++)

    //first the home team
    {
      Player this_player = home.get(i);

      int x = (int) Math.round(this_player.getPositionX() * 16);
      int y = (int) Math.round(this_player.getPositionY() * 21.3);
      String name = this_player.getName();

      JPanel player = new PlayerPanel(x, y, Color.RED, name);
      player.setBounds(0, 0, 480, 852);
      player.setOpaque(false);
      addImpl(player, new Integer(2), 0);


      //see if the player has the ball, if so draw it

      if (this_player.hasBall() == true) {
        JPanel ball = new BallPanel(x, y+10);
        ball.setBounds(0, 0, 480, 852);
        ball.setOpaque(false);
        addImpl(ball, new Integer(3), 0);
      }

      //draw away team players

      this_player = away.get(i);

      x = (int) Math.round(this_player.getPositionX() * 16);
      y = (int) Math.round(this_player.getPositionY() * 21.3);
      name = this_player.getName();

      player = new PlayerPanel(x, y, Color.BLUE, name);
      player.setBounds(0, 0, 480, 852);
      player.setOpaque(false);
      addImpl(player, new Integer(2), 0);

      //again, see if a player has the ball, if so draw that

      if (this_player.hasBall() == true) {
        JPanel ball = new BallPanel(x, y-10);
        ball.setBounds(0, 0, 480, 852);
        ball.setOpaque(false);
        addImpl(ball, new Integer(3), 0);
      }
    }

    // draw the time and score
    
    JPanel score = new ScorePanel(hTeam.getScore(), aTeam.getScore(), time);
    score.setBounds(0, 0, 480, 852);
    score.setOpaque(false);
    addImpl(score, new Integer(4), 0);
  }
}
