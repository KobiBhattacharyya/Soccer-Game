import javax.swing.JOptionPane;
import javax.swing.JFrame;


import java.util.ArrayList;

/**provides the popup menu for the player to select a player on the field to pass to or move*/

public class PlayerSelector {


  private Object player_choice;
  private Object[] player_options;

  /** creates a popup-menu of players to chose from
  * @param title determines the title of the popup menu
  * @param prompt determines the prompt of the menu
  * @param playersList provides the list of players to chose from
  * @return the name of the selected player
  */

  public Object getPlayer(String title, String prompt, ArrayList<Player> playersList) {

    player_options = new Object[11];

    for(int i=0; i<11; i++) {
      if (playersList.get(i).hasBall() == false) {
        player_options[i] = playersList.get(i).getName();
      }
    }

    player_choice = JOptionPane.showInputDialog(null,
                                                prompt,
                                                title,
                                                JOptionPane.QUESTION_MESSAGE,
                                                null,
                                                player_options,
                                                null);
    return player_choice;
  }
}
