import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;


/** driver to play the game*/

public class GameDriver
{

  /** main method of the game
  * @param args unused
  */

  public static void main(String[] args)
  {

    //For starting new game:

    ArrayList<Player> hRoster = null;
    ArrayList<Player> aRoster= null;
    Team homeTeam = null;
    Team awayTeam = null;
    Pitch pitch = new Pitch();
    Game game;
    GUI gui;
    ActionSelector a = new ActionSelector();
    PlayerSelector p = new PlayerSelector();
    TeamSelector t = new TeamSelector();

    //getting the players team selections

    Object team_1 = t.getTeam("home", "irrelevant");
    Object team_2 = t.getTeam("away", team_1);

    Object home_formation;
    Object away_formation;



    try
    {
      homeTeam = new Team(team_1.toString(),true);
      hRoster = homeTeam.getRoster();
      awayTeam = new Team(team_2.toString(), false);
      aRoster = awayTeam.getRoster();

    }
    catch(FileNotFoundException e)
    {
      System.out.println("File not found");
    }

    gui = new GUI(hRoster, aRoster, homeTeam, awayTeam);

    //setting the players selected team formations

    Object[] formation_options = { "1-4-4-2", "1-4-3-3", "1-5-4-1" };
    home_formation = a.getChoice("Formation Menu",
                                "Select a Home Team formation:",
                                formation_options);


    away_formation = a.getChoice("Formation Menu",
                                "Select a Home Team formation:",
                                formation_options);


    try
    {

      if(home_formation.equals("1-4-4-2"))
      {
        pitch.setFormation(1,4,4,2,'h','s',hRoster);

      }
      if(home_formation.equals("1-4-3-3"))
      {
        pitch.setFormation(1,4,3,3,'h','s',hRoster);
      }
      if(home_formation.equals("1-5-4-1"))
      {
        pitch.setFormation(1,5,4,1,'h','s',hRoster);
      }
      if(away_formation.equals("1-4-4-2"))
      {
        pitch.setFormation(1,4,4,2,'a','s',aRoster);
      }
      if(away_formation.equals("1-4-3-3"))
      {
        pitch.setFormation(1,4,3,3,'a','s',aRoster);
      }
      if(away_formation.equals("1-5-4-1"))
      {
        pitch.setFormation(1,5,4,1,'a','s',aRoster);
      }

    }
    catch(TooManyPlayersException e)
    {
      System.out.println("There's too many players on the pitch!");
    }

    //create the game instance with the players in their formations

    game = new Game(homeTeam, awayTeam);
    gui.addTime(game);
    gui.displayGUI("Formations Set!");

    //for game operations and making plays

    while(game.getTime()<=45)
    {
      //select an action to perform:

      Object[] actions = { "MOVE", "PASS", "SHOOT", "DRIBBLE" };
      Object action = a.getChoice("Action Menu",
                                  "Select an action:",
                                  actions);


      if(action.equals("SHOOT"))
      {
        try
        {
          boolean shot = game.shoot();

          if(shot == true)
          {

            //resetting the formations after a goal

            try{
              if(away_formation.equals("1-4-4-2"))
              {
                pitch.setFormation(1,4,4,2,'a','s',aRoster);
              }
              if(away_formation.equals("1-4-3-3"))
              {
                pitch.setFormation(1,4,3,3,'a','s',aRoster);
              }
              if(away_formation.equals("1-5-4-1"))
              {
                pitch.setFormation(1,5,4,1,'a','s',aRoster);
              }
              if(home_formation.equals("1-4-4-2"))
              {
                pitch.setFormation(1,4,4,2,'h','s',hRoster);

              }
              if(home_formation.equals("1-4-3-3"))
              {
                pitch.setFormation(1,4,3,3,'h','s',hRoster);
              }
              if(home_formation.equals("1-5-4-1"))
              {
                pitch.setFormation(1,5,4,1,'h','s',hRoster);
              }
            }
            catch(TooManyPlayersException e)
            {
              System.out.println("There's too many players on the pitch!");
            }

            gui.displayGUI("GOOAALLLL!");
          }
        else if(shot == false) {
          gui.displayGUI("NO GOAL!");
        }

        }
        catch(BadBallPosession e)
        {
          System.out.println("Both teams have the ball or nobody has the ball...");
        }
      }



      if(action.equals("PASS"))
      {

        //determine the teamate to pass to

        try
        {
          Object teammate;

          if(awayTeam.hasBall() == true) {

            teammate = p.getPlayer("Player Menu",
                                    "Select a player:",
                                    aRoster);
            boolean pTrue = game.passTo(teammate.toString());
            System.out.println(teammate);

            if (pTrue == true) {
              gui.displayGUI("Nice Pass!");
            }
            else if(pTrue == false) {
                gui.displayGUI("Pass stolen!");
            }
          }

          else{

            teammate = p.getPlayer("Player Menu",
                                    "Select a player:",
                                    hRoster);

            boolean pTrue = game.passTo(teammate.toString());
            System.out.println(teammate);

            if (pTrue == true) {
              gui.displayGUI("Nice Pass!");
            }
            else if(pTrue == false) {
                gui.displayGUI("Pass stolen!");
            }
          }

        }
        catch(BadPlayerPassException e)
        {
          System.out.println("The player entered isn't on the attacking team!");
        }
        catch(BadBallPosession e)
        {
          System.out.println("That player/team doesn't have the ball");
        }
      }



      if(action.equals("MOVE"))
      {

        try
        {

          Object moving_player;

          if(awayTeam.hasBall() == true) {

            moving_player = p.getPlayer("Player Menu",
                                    "Select a player:",
                                    aRoster);
          }
          else{

            moving_player = p.getPlayer("Player Menu",
                                    "Select a player:",
                                    hRoster);
            }

          //pull coordinates to move the player to

          String xd = JOptionPane.showInputDialog("Enter the x-coordinate to move " +moving_player +" to: ");
          String yd = JOptionPane.showInputDialog("Enter the y-coordinate to move " +moving_player +" to: ");
          int x = Integer.parseInt(xd);
          int y = Integer.parseInt(yd);
          game.move(moving_player.toString(),x,y);
          gui.displayGUI("Nice Move!");
        }
        catch(MaxDistanceExceeded e)
        {
          System.out.println("That's not a valid distance");
        }
        catch(BadBallPosession e)
        {
          System.out.println("That player/team doesn't have the ball");
        }
      }



      if(action.equals("DRIBBLE")) {

        //determin the coordinates to dribble to

        String xd = JOptionPane.showInputDialog("Enter the x-coordinate to dribble to: ");
        String yd = JOptionPane.showInputDialog("Enter the y-coordinate to dribble to: ");
        int x = Integer.parseInt(xd);
        int y = Integer.parseInt(yd);


        try {
        boolean shot = game.dribble(x, y);

        if(shot == true)
          { gui.displayGUI("Nice dribble!");
          }
          else
          {
            gui.displayGUI("The ball was stolen!");
          }
        }
        catch(MaxDistanceExceeded e)
        {
          System.out.println("That's not a valid distance");
          gui.displayGUI("Too risky of a move!");

        }
        catch(BadBallPosession e)
        {
          System.out.println("That player/team doesn't have the ball");
        }

      }
    }
  }
}
