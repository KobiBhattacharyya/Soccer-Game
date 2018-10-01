import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PitchDriver{

public static void main(String[] args){

//DRIVER MUST GIVE HOME TEAM BALL TO START WITH
  char a = 'a';
  char h = 'h';
  char d ='d';
  char s = 's';
ArrayList<Player> conte = null;
ArrayList<Player> gerrard= null;
Team liverpool = null;
Team chelsea = null;
Pitch anfield = new Pitch();
anfield.displayField();
  String input_file = JOptionPane.showInputDialog("Input a British Premier League team followed by '.txt'");
  String input_file2 =JOptionPane.showInputDialog("Input a British Premier League team followed by '.txt'");
try{
  chelsea = new Team(input_file,true);
   conte = chelsea.getRoster();
}catch(FileNotFoundException c){
  System.out.println("file not found");
}
try{
anfield.setFormation(1,4,4,2,h,s,conte);
}catch(TooManyPlayersException e){
  System.out.println("too many players on the pitch!");
}
anfield.displayField();
try{
  liverpool = new Team(input_file2,false);
   gerrard = liverpool.getRoster();
  // System.out.println(liverpool.getRoster());
}catch(FileNotFoundException c){
  System.out.println("file not found");
}

try{
anfield.setFormation(1,4,3,3,a,s,gerrard);
}catch(TooManyPlayersException f){
  System.out.println("too many players on the pitch!");
}
anfield.displayField();

/*try
{
  Game newGame = new Game(chelsea, liverpool);
  newGame.passTo("Willian");
  newGame.shoot();
  newGame.shoot();
  //System.out.println("Thibaut Cortois: "+chelsea.getPlayer("Thibaut Cortois").hasBall());

}
catch(BadPlayerPassException e)
{
  System.out.println("That player isn't on the attacking team!");
}
catch(BadBallPosession ex)
{
  System.out.println("That player doesn't have the ball!");
}*/

Player x = liverpool.getPlayer("Sadio Mane");
System.out.println("Sadio Mane makes a great run down the line!");
anfield.move(x,2,5);
anfield.displayField();

try
{
  boolean shot;
  Game newGame = new Game(chelsea, liverpool);
  newGame.dribble(29,30);
/*  System.out.println("Prints true if shot is good: "+shot);
  newGame.passTo("Dejan Lovren");
  newGame.passTo("Roberto Firmino");
  newGame.passTo("Sadio Mane");
  shot = newGame.passTo("Loris Karius");
  System.out.println("Prints true if pass is good: "+shot);
  //System.out.println("Thibaut Cortois: "+chelsea.getPlayer("Thibaut Cortois").hasBall());*/

}
/*catch(BadPlayerPassException e)
{
  System.out.println("That player isn't on the attacking team!");
}*/
catch(BadBallPosession ex)
{
  System.out.println("That player doesn't have the ball!");
}
catch(MaxDistanceExceeded e)
{
  System.out.println("Enter a valid distance!");
}


}

//public void move(Player player, int x, int y){
  //player.get(postion);
  // field[poistionx][postiony] = " "
  // field[x][y]= player.getId();
  //player.setX(x);
  //player.setY(y);
}
