# Soccer-Game
A text-based soccer game utilizing the Swing toolkit in Java. Thanks to Gabe Poehls and Seneca Griffin.

**Use GameDriver.java to run the game**

This program simulates a soccer match, featuring teams (with lineups from June 2018) from the Barclay's Premier League! When the game initially loads, the user is asked for the home and away team through a drop-down menu. Next, the user is asked to select a formation for each of the home and away team. After the formations are selected, the players are placed on the field and the home team's striker receives the ball first. The attacking team (the team with the ball) has 4 options to choose from: pass, shoot, move, or dribble. Moving a player has a 100% success rate; Dribbling is only accessible to the player with the ball. Choosing to pass, dribble, or shoot could result in either a success or failure, depending on various factors discussed below. Let the game begin!

**Fun/cool aspects of the program:**

**Ball posession:** Ball posession for both teams and players was designed as a simple boolean. Either the player has the ball or the player does not have the ball. Only one player (no more no less) may have posession of the ball at a time.

**Strength factors:** Each player is assigned a "strength" attribute, which is a number between 1-100. This strength value is part of what determines the outcome of the shoot, dribble, and pass actions. The higher a value for strength, the more likely an action is to succeed. 

**Distance factors:** Outcome of the shoot and pass actions also depend on how far away the player wishes to accurately place the ball. Players are more likely to have a successful action if they are closer to their target. The maximum distance (corner to opposite corner) has a 0% success rate.

**RNG:** With each failable action (shoot, pass, dribble) is an associated threshold number and a randomly generated number. In order for an action to be successful, its randomly generated number must be higher than its respective threshold number, otherwise the action fails. Player strength has a positive linear relationship with the action's random number, while distance has a positive linear relationship with the action's threshold number. 

**Actions:**

  - Shoot: the initial action is dependent upon both strength and distance factors. If the shot is successful, the attacking team's score is incremented. If the shot is unsuccessful, the goalkeeper either catches the ball or the ball is deflected to the nearest player to the goalkeeper (attacker or defender). The success of the goalkeeper's action is dependent upon their strength factor. 
  
  - Pass: this action is dependent upon both strength and distance factors. If the pass is successful, the receiving teammate gains control of the ball. If the shot is unsuccessful, the ball goes to player (attacker or defender) closest to the receiving player.  
  
  - Dribble: this action's success is dependent upon the strength of the attacker and the relative position of the closest defender. If the dribble is successful, the player with the ball moves to the desired location. If the dribble is unsuccessful, the closest defender gains control of the ball. 
  
  - Move: action has a 100% success rate and is only used to move players without the ball. 
  
**Future improvements:**

  - Alter equation for distance factor (too easy to score... maybe vary by a factor of 1/r<sup>2</sup>?)
  - Update move and dribble to be clickable on the graphics panel, not the numerical input
  - Update cancel button so it loops back to previous input, instead of ending the game
