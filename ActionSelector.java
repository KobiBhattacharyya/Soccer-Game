import javax.swing.JOptionPane;
import javax.swing.JFrame;




/** provides the popup menu for player to select an action from*/

public class ActionSelector {


  private Object action_choice;

  /** creates a popup-menu of actions to chose from
  * @param title determines the title of the popup menu
  * @param prompt determines the prompt of the menu
  * @param playersList provides the list of actions to chose from
  * @return the selected action
  */

  public Object getChoice(String title, String prompt, Object[] action_options) {

    action_choice = JOptionPane.showInputDialog(null,
                                                prompt,
                                                title,
                                                JOptionPane.QUESTION_MESSAGE,
                                                null,
                                                action_options,
                                                null);
    return action_choice;
  }
}
