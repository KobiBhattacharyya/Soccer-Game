import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.ArrayList;

import graphics.*;



/** creates a GUI for the player to interact with the game through */

public class GUI {


  private ArrayList<Player> home;
  private ArrayList<Player> away;
  private Team homeTeam;
  private Team awayTeam;
  private JFrame frame;
  private Game game;



  /** defines the GUI and the variables it takes in
  * @param home roster of the home team
  * @param away roster of the awya team
  * @param hTeam home team
  * @param aTeam away team
  */

  public GUI(ArrayList<Player> home, ArrayList<Player> away, Team hTeam, Team aTeam) {

    this.home = home;
    this.away = away;
    this.homeTeam = hTeam;
    this.awayTeam = aTeam;


    //build and set up the frame

    frame = new JFrame("Fifa 2019");

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(488, 887);

    //add the initial empty pitch

    frame.add(new PitchPanel());

    //make the frame visible

    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    //adding the "Welcome to Fifa" popup

    JOptionPane j = new JOptionPane();
    j.showMessageDialog(null, "Welcome to Fifa!");

    }

  /** adds the time into the GUI
  *@param game the game instance
  */

  public void addTime(Game game) {

    this.game=game;

  }

  /** displays the updated GUI
  * @param play the message that pops up displaying what just happened in the game
  */

  public void displayGUI(Object play) {


    frame.setContentPane(new MainPanel(home, away, homeTeam, awayTeam, game.getTime()));

    // Display the window.
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    //Display what just happened in the game
    JOptionPane.showMessageDialog(null, play);
  }
}
