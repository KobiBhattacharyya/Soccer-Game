
public class Player{
  Player[][] playerArray = new Player[40][30];
  String name, team;
  private int positionX, positionY, strength;
  String id;
  boolean ball;

  // public Player(String name, String team, int strength, String id)
  // {
  //   this.name = name;
  //   this.team = team;
  //   this.id = id;
  //   //boolean for home/away team???
  //   this.strength = strength;
  // }

  public Player (String name, String team, String id, int strength){
    this.name = name;
    this.team = team;
    this.strength = strength;
    this.id = id;
    ball = false;
  }

  public boolean hasBall()
  {
    return ball;
  }

  public void setX(int x_location){
    positionX = x_location;
  }

  public void setY(int y_location){
    positionY = y_location;
  }

  public String getId(){
    return id;
  }

  public void setBall(boolean torf)
  {
    ball = torf;
  }

  public void setPosition(int x, int y)
  {
    positionX = x;
    positionY = y;
  }

  public int getPositionX(){
    return positionX;
  }
  public int getPositionY(){
    return positionY;
  }

  public String getName()
  {
    return name;
  }

  public String getTeam()
  {
    return team;
  }

  public int getStrength()
  {
    return strength;
  }
/*
  public boolean passTo(String name) throws BadBallPosession
  {
    if(ball!=true)
    {
      //MUST MAKE NEW EXCEPTION
      throw new BadBallPosession();
      //"that player doesn't have the ball!"
    }
    else
    {
      double maxD = Math.sqrt(900+1600);
      int dx = positionX-teammate.getPositionX();
      int dy = positionY-teammate.getPositionY();
      double d = Math.sqrt(dx*dx+dy*dy);

      double composite = strength*(maxD-d);
      double maxComposite = 100*maxD; //assuming that strength has a max value of 100!!
      double randNum = ThreadLocalRandom.current().nextInt(1, maxComposite+1);
      //0<randNum<maxComposite, composite = strength(maxD-d)

      if(composite>randNum)
      {
        ball=false;
        teammate.giveBall();
        return true;
      }
      else
      {

        return false;
      }
    }
  }*/
}
