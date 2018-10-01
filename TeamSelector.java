import javax.swing.JOptionPane;
import javax.swing.JFrame;

import java.io.File;


/**provides the popup menu for the player to select teams*/
public class TeamSelector {


  private Object team_choice;
  private Object[] team_file_options = new File("teams").listFiles();



  /** creates a popup-menu of teams to chose from
  * @param prompt determines the prompt of the menu
  * @return the name of the selected team
  */

  public Object getTeam(String prompt, Object first_team) {


    Object[] team_options = new Object[team_file_options.length];

    for(int i=0; i<team_file_options.length; i++) {



      if (!first_team.toString().equals(team_file_options[i].toString())) {

        team_options[i] = team_file_options[i].toString().substring(6, team_file_options[i].toString().length()-4);
        }
      }




    team_choice = JOptionPane.showInputDialog(null,
                                                "Select " +prompt +" team:",
                                                "Team Selector",
                                                JOptionPane.QUESTION_MESSAGE,
                                                null,
                                                team_options,
                                                null);

    team_choice = "teams/" +team_choice.toString() +".txt";

    return team_choice;
  }
}
