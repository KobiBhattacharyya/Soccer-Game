import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Game
{
  Team attackTeam;
  Team defendTeam;
  Team hTeam, aTeam;
  ArrayList<Player> aRoster = new ArrayList<Player>();
  ArrayList<Player> dRoster = new ArrayList<Player>();
  Player attackPlayer, defender;
  Player teammate=null;
  Player defendPlayer;
  Player movePlayer;
  Pitch pitch;
  double time=0;
  //Team[] game = new Team[2];

//Game constructor moves the two teams to this class AND gives the ball to the last player in
//the home team's roster
  public Game(Team t1, Team t2)
  {
    //THIS ASSUMES THAT THE FIRST TEAM IN CONSTRUCTOR PARAMETER IS HOME TEAM!!!
    //REMEMBER TO PUT HOME TEAM AS FIRST PARAMETER IN GAME INSTANCE IN MAIN METHOD
    t1.setBall(true);
    attackTeam = t1;
    attackPlayer = t1.getRoster().get(10);
    attackPlayer.setBall(true);
    //(t1.getRoster().get(10))
    t2.setBall(false);
    defendTeam = t2;

    aRoster = t1.getRoster();
    dRoster = t2.getRoster();
  }

  public Team getAttackingTeam()
  {
    return attackTeam;
  }

  public boolean passTo(String name) throws BadPlayerPassException, BadBallPosession
  {
    Team placeholderTeam;
    boolean pSuccess;
    /* finds which team has the ball and establishes the index of each attacking/defending team
    and pulls the roster for each team */

    if(!attackTeam.hasBall())
    {
      if(defendTeam.hasBall())
      {
        placeholderTeam = attackTeam;
        attackTeam = defendTeam;
        defendTeam = placeholderTeam;
      }
    }

    /*if(!attackPlayer.hasBall())
    {
      for(int i =0;i<11;i++)
      {
        Player ap = attackTeam.getRoster().get(i);
        if(ap.hasBall())
        {
          attackPlayer = ap;
        }
      }
    }*/

    System.out.println("Attacking team: "+attackTeam.getTeamName());
    System.out.println("Defending team: "+defendTeam.getTeamName());
    aRoster = attackTeam.getRoster();
    dRoster = defendTeam.getRoster();

    //finds teammate for string name entered and records the teammmate's position in Team arraylist
    for(int i=0;i<aRoster.size();i++)
    {
      if(name.equals(aRoster.get(i).getName()))
      {
        teammate = aRoster.get(i);
        System.out.println("Teammate's name: "+teammate.getName());
        System.out.println("Attacking player's name: "+attackPlayer.getName());
        break;
      }
    }

    double maxD = Math.sqrt(900+1600);
    double dx = attackPlayer.getPositionX()-teammate.getPositionX();
    double dy = attackPlayer.getPositionY()-teammate.getPositionY();
    double d = Math.sqrt(dx*dx+dy*dy);

    double composite = attackPlayer.getStrength()*(maxD-d);
    System.out.println(composite);
    double maxComposite = 100*maxD; //assuming that strength has a max value of 100!!
    double randNum = ThreadLocalRandom.current().nextDouble(1, maxComposite+1);
    System.out.println(randNum);
    //0<randNum<maxComposite, composite = strength(maxD-d)

    if(teammate==null)
    {
      pSuccess = false;
      throw new BadPlayerPassException();
    }
    else if((!attackTeam.hasBall()&&!defendTeam.hasBall()) || (attackTeam.hasBall()&&defendTeam.hasBall()) || !attackPlayer.hasBall())
    {
      pSuccess = false;
      throw new BadBallPosession();
    }
    else
    {
      if(composite>=randNum)
      {
        attackPlayer.setBall(false);
        teammate.setBall(true);
        String namedisplay = teammate.getName();
        attackPlayer = teammate;
        System.out.println("The ball was passed to "+namedisplay);
        System.out.println("The attacking player is now "+attackPlayer.getName());

        System.out.println("Attacking Team: ");
        for(int i =0;i<11;i++)
        {
          System.out.println(aRoster.get(i).getName());
        }
        System.out.println("");
        dRoster = defendTeam.getRoster();
        System.out.println("Defending Team: ");
        for(int i =0;i<11;i++)
        {
          System.out.println(dRoster.get(i).getName());
        }
        System.out.println("");
        time++;

        pSuccess = true;
      }
      else
      {
        /* FOR BASIC PASS METHOD
        String namedisplay = teammate.getName();
        System.out.println("The pass to "+namedisplay+" failed");
        System.out.println("The ball was returned to "+attackPlayer.getName());
        return false;
        */
        System.out.println("The pass to "+name+" from "+attackPlayer.getName()+" failed");

        attackPlayer.setBall(false);

        int lowDistIndex=0;
        ArrayList<Double> distances = new ArrayList<Double>();
        for(int i=0;i<dRoster.size();i++)
        {
          Player test = dRoster.get(i);
          int deltaX = test.getPositionX()-attackPlayer.getPositionX();
          int deltaY = test.getPositionY()-attackPlayer.getPositionY();
          double dist = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
          distances.add(dist);
        }
        double lowDist=distances.get(0);
        for(int j=1;j<distances.size();j++)
        {
          if(distances.get(j)<lowDist)
          {
            lowDist = distances.get(j);
            lowDistIndex = j;
          }
        }
        defendPlayer = dRoster.get(lowDistIndex);
        System.out.println("Ball goes to "+defendPlayer.getName());

        defendPlayer.setBall(true);
        attackPlayer = defendPlayer;
        System.out.println("New attacker is "+attackPlayer.getName());
        System.out.println("New Attacker has ball: "+attackPlayer.hasBall());

        placeholderTeam = attackTeam;
        attackTeam = defendTeam;
        defendTeam = placeholderTeam;

        attackTeam.setBall(!attackTeam.hasBall());
        defendTeam.setBall(!defendTeam.hasBall());
        System.out.println("Does attackTeam have ball: "+attackTeam.hasBall());
        System.out.println("Does defendTeam not have ball: "+!defendTeam.hasBall());

        aRoster = attackTeam.getRoster();
        System.out.println("Attacking Team: ");
        for(int i =0;i<11;i++)
        {
          System.out.println(aRoster.get(i).getName());
        }
        System.out.println("");
        dRoster = defendTeam.getRoster();
        System.out.println("Defending Team: ");
        for(int i =0;i<11;i++)
        {
          System.out.println(dRoster.get(i).getName());
        }
        System.out.println("");

        time++;
        pSuccess = false;
      }
    }
    return pSuccess;
  }

  public boolean shoot() throws BadBallPosession
  {
    Team placeholderTeam;
    boolean sSuccess;

    if(attackTeam.isHome())
    {
      System.out.println("Home team: "+attackTeam.getTeamName());
      System.out.println("Away team: "+defendTeam.getTeamName());
    }
    else
    {
      System.out.println("Home team: "+defendTeam.getTeamName());
      System.out.println("Away team: "+attackTeam.getTeamName());
    }
    System.out.println("Attacking team: "+attackTeam.getTeamName());
    System.out.println("Defending team: "+defendTeam.getTeamName());
    //gets rosters for attacking/defending teams
    aRoster = attackTeam.getRoster();
    dRoster = defendTeam.getRoster();

    /* if the attack player doesn't have the ball, loop through attacking team to find
    attacking player*/
    if(!attackPlayer.hasBall())
    {
      for(int i =0;i<11;i++)
      {
        Player ap = attackTeam.getRoster().get(i);
        if(ap.hasBall())
        {
          attackPlayer = ap;
          break;
        }
      }
    }

//Method for shoot if the attacking team is the home team
    if((!attackTeam.hasBall()&&!defendTeam.hasBall()) || (attackTeam.hasBall()&&defendTeam.hasBall()) || !attackPlayer.hasBall())
    {
      sSuccess = false;
      throw new BadBallPosession();
    }
    else
    {
      if(attackTeam.isHome())
      {
        /* RNG aspect of shot */
        double maxD = Math.sqrt(225+1600);
        double dx = 14-attackPlayer.getPositionX();
        double dy = 40-attackPlayer.getPositionY();
        double d = Math.sqrt(dx*dx+dy*dy);

        double composite = attackPlayer.getStrength()*(maxD-d);
        System.out.println(composite);
        double maxComposite = 100*maxD; //assuming that strength has a max value of 100!!
        double randNum = ThreadLocalRandom.current().nextDouble(1, maxComposite+1);
        System.out.println(randNum);

        if(composite>randNum)
        {
          //changes the attacking players and sets ball
          System.out.println("This line prints if the attacking team is the Home team");
          attackPlayer.setBall(false);
          System.out.println(attackPlayer.getName()+" shoots and scores... Does "+ attackPlayer.getName()+" have the ball: "+attackPlayer.hasBall());
          attackPlayer = defendTeam.getRoster().get(10);
          System.out.println("New attacker: "+attackPlayer.getName());
          attackPlayer.setBall(true);
          System.out.println("Does "+attackPlayer.getName()+" have the ball: "+attackPlayer.hasBall());

          //changes the attacking team and sets ball
          attackTeam.keepScore();
          attackTeam.setBall(false);
          defendTeam.setBall(true);
          //switches attackTeam and defendTeam
          placeholderTeam = attackTeam;
          attackTeam = defendTeam;
          defendTeam = placeholderTeam;

          System.out.println("Does the attacking team ("+attackTeam.getTeamName()+") have the ball: "+attackTeam.hasBall());
          System.out.println("Is the attacking team home: "+attackTeam.isHome());
          System.out.println("Does the defending team ("+defendTeam.getTeamName()+") have the ball: "+defendTeam.hasBall());
          System.out.println("Is the defending team home: "+defendTeam.isHome());

          if(attackTeam.isHome())
          {
            System.out.println("Home Score: "+attackTeam.getTeamName()+" "+attackTeam.getScore());
            System.out.println("Away Score: "+defendTeam.getTeamName()+" "+defendTeam.getScore());
          }
          else
          {
            System.out.println("Home Score: "+defendTeam.getTeamName()+" "+defendTeam.getScore());
            System.out.println("Away Score: "+attackTeam.getTeamName()+" "+attackTeam.getScore());
          }

          time++;
          sSuccess = true;
        }
        else //if the shot misses, new conditional branch that determines if the gk rebounds the ball or holds onto it
        {
          int gkRandom = ThreadLocalRandom.current().nextInt(1, 101);

          if(dRoster.get(0).getStrength()>gkRandom)//for goalkeeper number beats new random number
          {
            System.out.println("This line prints if the attacking team is the Home team");
            attackPlayer.setBall(false);
            System.out.println(attackPlayer.getName()+" shoots but misses... Does "+ attackPlayer.getName()+" have the ball: "+attackPlayer.hasBall());
            attackPlayer= defendTeam.getRoster().get(0);
            System.out.println("New attacker: "+attackPlayer.getName());
            attackPlayer.setBall(true);
            System.out.println("Does "+attackPlayer.getName()+" have the ball: "+attackPlayer.hasBall());

            attackTeam.setBall(false);
            defendTeam.setBall(true);

            placeholderTeam = attackTeam;
            attackTeam = defendTeam;
            defendTeam = placeholderTeam;

            System.out.println("Does the attacking team ("+attackTeam.getTeamName()+") have the ball: "+attackTeam.hasBall());
            System.out.println("Is the attacking team home: "+attackTeam.isHome());
            System.out.println("Does the defending team ("+defendTeam.getTeamName()+") have the ball: "+defendTeam.hasBall());
            System.out.println("Is the defending team home: "+defendTeam.isHome());

            if(attackTeam.isHome())
            {
              System.out.println("Home Score: "+attackTeam.getTeamName()+" "+attackTeam.getScore());
              System.out.println("Away Score: "+defendTeam.getTeamName()+" "+defendTeam.getScore());
            }
            else
            {
              System.out.println("Home Score: "+defendTeam.getTeamName()+" "+defendTeam.getScore());
              System.out.println("Away Score: "+attackTeam.getTeamName()+" "+attackTeam.getScore());
            }

            time++;
            sSuccess = false;
          }
          else //if goalkeeper does not save the ball:
          {
            int lowDistIndexA=0;
            int lowDistIndexD=1;
            ArrayList<Double> distancesA = new ArrayList<Double>();
            ArrayList<Double> distancesD = new ArrayList<Double>();
            Player gk = dRoster.get(0);

            for(int i=1;i<dRoster.size();i++)
            {
              Player test = dRoster.get(i);
              int deltaX = test.getPositionX()-gk.getPositionX();
              int deltaY = test.getPositionY()-gk.getPositionY();
              double dist = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
              distancesD.add(dist);
            }
            for(int i=0;i<aRoster.size();i++)
            {
              Player test = aRoster.get(i);
              int deltaX = test.getPositionX()-gk.getPositionX();
              int deltaY = test.getPositionY()-gk.getPositionY();
              double dist = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
              distancesA.add(dist);
            }
            System.out.println(distancesD.size());
            System.out.println(distancesA.size());

            double lowDistD=distancesD.get(0);
            for(int j=1;j<distancesD.size();j++)
            {
              if(distancesD.get(j)<lowDistD)
              {
                lowDistD = distancesD.get(j);
                lowDistIndexD = j+1;
              }
            }

            double lowDistA = distancesA.get(0);
            for(int j=1;j<distancesA.size();j++)
            {
              if(distancesA.get(j)<lowDistA)
              {
                lowDistA = distancesA.get(j);
                lowDistIndexA = j;
              }
            }

            if(lowDistD<lowDistA)
            {
              attackPlayer.setBall(false);
              System.out.println(attackPlayer.getName()+" shoots but misses... Does "+ attackPlayer.getName()+" have the ball: "+attackPlayer.hasBall());
              attackPlayer= defendTeam.getRoster().get(lowDistIndexD);
              System.out.println("New attacker: "+attackPlayer.getName());
              attackPlayer.setBall(true);
              System.out.println("Does "+attackPlayer.getName()+" have the ball: "+attackPlayer.hasBall());

              attackTeam.setBall(false);
              defendTeam.setBall(true);

              placeholderTeam = attackTeam;
              attackTeam = defendTeam;
              defendTeam = placeholderTeam;
            }
            else
            {
              attackPlayer.setBall(false);
              System.out.println(attackPlayer.getName()+" shoots but misses... Does "+ attackPlayer.getName()+" have the ball: "+attackPlayer.hasBall());
              attackPlayer = attackTeam.getRoster().get(lowDistIndexA);
              System.out.println("New attacker: "+attackPlayer.getName());
              attackPlayer.setBall(true);
              System.out.println("Does "+attackPlayer.getName()+" have the ball: "+attackPlayer.hasBall());
            }

            time++;
            sSuccess = false;
          }
        }
      }
  //method for shoot if the attacking team is away team
      else
      {
        /* RNG aspect of shot*/
        double maxD = Math.sqrt(225+1600);
        double dx = 14-attackPlayer.getPositionX();
        double dy = attackPlayer.getPositionY();
        double d = Math.sqrt(dx*dx+dy*dy);

        double composite = attackPlayer.getStrength()*(maxD-d);
        System.out.println(composite);
        double maxComposite = 100*maxD; //assuming that strength has a max value of 100!!
        double randNum = ThreadLocalRandom.current().nextDouble(1, maxComposite+1);
        System.out.println(randNum);

        if(composite>randNum)
        {
          System.out.println("This line prints if the attacking team is the Away team");
          attackPlayer.setBall(false);
          System.out.println(attackPlayer.getName()+" shoots and scores... Does "+ attackPlayer.getName()+" have the ball: "+attackPlayer.hasBall());
          attackPlayer = defendTeam.getRoster().get(10);
          System.out.println("New attacker: "+attackPlayer.getName());
          attackPlayer.setBall(true);
          System.out.println("Does "+attackPlayer.getName()+" have the ball: "+attackPlayer.hasBall());
          //changes the attacking team and sets ball
          attackTeam.keepScore();
          attackTeam.setBall(false);
          defendTeam.setBall(true);
          //switches attackTeam and defendTeam
          placeholderTeam = attackTeam;
          attackTeam = defendTeam;
          defendTeam = placeholderTeam;

          System.out.println("Does the attacking team ("+attackTeam.getTeamName()+") have the ball: "+attackTeam.hasBall());
          System.out.println("Is the attacking team home: "+attackTeam.isHome());
          System.out.println("Does the defending team ("+defendTeam.getTeamName()+") have the ball: "+defendTeam.hasBall());
          System.out.println("Is the defending team home: "+defendTeam.isHome());

          if(attackTeam.isHome())
          {
            System.out.println("Home Score: "+attackTeam.getTeamName()+" "+attackTeam.getScore());
            System.out.println("Away Score: "+defendTeam.getTeamName()+" "+defendTeam.getScore());
          }
          else
          {
            System.out.println("Home Score: "+defendTeam.getTeamName()+" "+defendTeam.getScore());
            System.out.println("Away Score: "+attackTeam.getTeamName()+" "+attackTeam.getScore());
          }

          time++;
          sSuccess = true;
        }
        else
        {
          int gkRandom = ThreadLocalRandom.current().nextInt(1, 101);

          if(dRoster.get(0).getStrength()>gkRandom)//for goalkeeper number beats new random number
          {
            System.out.println("This line prints if the attacking team is the Home team");
            attackPlayer.setBall(false);
            System.out.println(attackPlayer.getName()+" shoots but misses... Does "+ attackPlayer.getName()+" have the ball: "+attackPlayer.hasBall());
            attackPlayer= defendTeam.getRoster().get(0);
            System.out.println("New attacker: "+attackPlayer.getName());
            attackPlayer.setBall(true);
            System.out.println("Does "+attackPlayer.getName()+" have the ball: "+attackPlayer.hasBall());

            attackTeam.setBall(false);
            defendTeam.setBall(true);

            placeholderTeam = attackTeam;
            attackTeam = defendTeam;
            defendTeam = placeholderTeam;

            System.out.println("Does the attacking team ("+attackTeam.getTeamName()+") have the ball: "+attackTeam.hasBall());
            System.out.println("Is the attacking team home: "+attackTeam.isHome());
            System.out.println("Does the defending team ("+defendTeam.getTeamName()+") have the ball: "+defendTeam.hasBall());
            System.out.println("Is the defending team home: "+defendTeam.isHome());

            if(attackTeam.isHome())
            {
              System.out.println("Home Score: "+attackTeam.getTeamName()+" "+attackTeam.getScore());
              System.out.println("Away Score: "+defendTeam.getTeamName()+" "+defendTeam.getScore());
            }
            else
            {
              System.out.println("Home Score: "+defendTeam.getTeamName()+" "+defendTeam.getScore());
              System.out.println("Away Score: "+attackTeam.getTeamName()+" "+attackTeam.getScore());
            }

            time++;
            sSuccess = false;
          }
          else //if goalkeeper does not save the ball:
          {
            int lowDistIndexA=0;
            int lowDistIndexD=1;
            ArrayList<Double> distancesA = new ArrayList<Double>();
            ArrayList<Double> distancesD = new ArrayList<Double>();
            Player gk = dRoster.get(0);

            for(int i=1;i<dRoster.size();i++)
            {
              Player test = dRoster.get(i);
              int deltaX = test.getPositionX()-gk.getPositionX();
              int deltaY = test.getPositionY()-gk.getPositionY();
              double dist = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
              distancesD.add(dist);
            }
            for(int i=0;i<aRoster.size();i++)
            {
              Player test = aRoster.get(i);
              int deltaX = test.getPositionX()-gk.getPositionX();
              int deltaY = test.getPositionY()-gk.getPositionY();
              double dist = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
              distancesA.add(dist);
            }
            System.out.println(distancesD.size());
            System.out.println(distancesA.size());

            double lowDistD=distancesD.get(0);
            for(int j=1;j<distancesD.size();j++)
            {
              if(distancesD.get(j)<lowDistD)
              {
                lowDistD = distancesD.get(j);
                lowDistIndexD = j+1;
              }
            }

            double lowDistA = distancesA.get(0);
            for(int j=1;j<distancesA.size();j++)
            {
              if(distancesA.get(j)<lowDistA)
              {
                lowDistA = distancesA.get(j);
                lowDistIndexA = j;
              }
            }

            if(lowDistD<lowDistA)
            {
              attackPlayer.setBall(false);
              System.out.println(attackPlayer.getName()+" shoots but misses... Does "+ attackPlayer.getName()+" have the ball: "+attackPlayer.hasBall());
              attackPlayer= defendTeam.getRoster().get(lowDistIndexD);
              System.out.println("New attacker: "+attackPlayer.getName());
              attackPlayer.setBall(true);
              System.out.println("Does "+attackPlayer.getName()+" have the ball: "+attackPlayer.hasBall());

              attackTeam.setBall(false);
              defendTeam.setBall(true);

              placeholderTeam = attackTeam;
              attackTeam = defendTeam;
              defendTeam = placeholderTeam;
            }
            else
            {
              attackPlayer.setBall(false);
              System.out.println(attackPlayer.getName()+" shoots but misses... Does "+ attackPlayer.getName()+" have the ball: "+attackPlayer.hasBall());
              attackPlayer = attackTeam.getRoster().get(lowDistIndexA);
              System.out.println("New attacker: "+attackPlayer.getName());
              attackPlayer.setBall(true);
              System.out.println("Does "+attackPlayer.getName()+" have the ball: "+attackPlayer.hasBall());
            }

            time++;
            sSuccess = false;
          }
        }
      }
    }
    return sSuccess;
  }

  public void move(String name, int x, int y) throws MaxDistanceExceeded, BadBallPosession
  {
    //MAX MOVE NUMBER SHOULD BE 10 UNITS IN X AND Y DIRECTION
    //SHOULD NOT ELAPSE TIME
    aRoster = attackTeam.getRoster();
    dRoster = defendTeam.getRoster();

    for(int i =0;i<11;i++)
    {
      if(name.equals(aRoster.get(i).getName()))
      {
        if(!name.equals(attackPlayer))
        {
          movePlayer = aRoster.get(i);
        }
        else
        {
          throw new BadBallPosession();
        }
      }
      if(name.equals(dRoster.get(i).getName()))
      {
        movePlayer = dRoster.get(i);
      }
    }
        int absDist = Math.abs(x-movePlayer.getPositionX())+Math.abs(y-movePlayer.getPositionY());
        if(absDist>=0 && absDist<=20)
        {
          movePlayer.setX(x);
          movePlayer.setY(y);
          System.out.println(movePlayer.getName()+"'s new X-position is now: "+movePlayer.getPositionX());
          time = time+(1/3.0);
          System.out.println("Time: "+time);
        }
        else
        {
          throw new MaxDistanceExceeded();
        }
/*
        if(absDist>=0 && absDist<=20)
        {
          movePlayer.setX(x);
          movePlayer.setY(y);
          System.out.println(movePlayer.getName()+"'s new Y-position is now: "+movePlayer.getPositionY());
          time=time+1/3;
          System.out.println("Time: "+time);
        }
        else
        {
          throw new MaxDistanceExceeded();
        }*/
  }

  public boolean dribble(int x, int y) throws MaxDistanceExceeded, BadBallPosession
  {
    //dribble random number generator
    //MAX MOVE NUMBER SHOULD BE 10 UNITS IN X AND Y DIRECTION
    //SHOULD ELAPSE TIME (MOVE SHOULD NOT ELAPSE TIME)
    int absDist = Math.abs(x-attackPlayer.getPositionX())+Math.abs(y-attackPlayer.getPositionY());
    Team placeholderTeam;
    boolean dTrue;//returns true if dribble succeeds
    aRoster = attackTeam.getRoster();
    dRoster = defendTeam.getRoster();

    if(absDist>=0 && absDist<=10)
    {
      if(attackPlayer.hasBall())
      {
        int lowDistIndex=0;
        ArrayList<Double> distances = new ArrayList<Double>();
        for(int i=0;i<dRoster.size();i++)
        {
          Player test = dRoster.get(i);
          int deltaX = test.getPositionX()-attackPlayer.getPositionX();
          int deltaY = test.getPositionY()-attackPlayer.getPositionY();
          double dist = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
          distances.add(dist);
        }
        double lowDist=distances.get(0);
        for(int j=1;j<distances.size();j++)
        {
          if(distances.get(j)<lowDist)
          {
            lowDist = distances.get(j);
            lowDistIndex = j;
          }
        }
        defender = dRoster.get(lowDistIndex);

        double maxD = 10; //ASSUMING WE USE 10 AS MAX PLAYER TRAVEL LENGTH
        double composite = attackPlayer.getStrength()*(lowDist/10); //likelihood of success decreases when defender is near
        System.out.println(composite);
        double randNum = ThreadLocalRandom.current().nextDouble(1, 101);
        System.out.println(randNum);

        if(composite>randNum)
        {
          attackPlayer.setX(x);
          attackPlayer.setY(y);
          System.out.println(attackPlayer.getName()+"'s new coordinates are: ("+attackPlayer.getPositionX()+","+attackPlayer.getPositionY()+")");

          time=time+1/3.0;
          System.out.println("Time: "+time);

          dTrue = true;
        }
        else
        {
          attackPlayer.setBall(false);
          defender.setBall(true);
          attackPlayer = defender;
          System.out.println("New attacker is "+attackPlayer.getName());
          System.out.println("New Attacker has ball: "+attackPlayer.hasBall());

          placeholderTeam = attackTeam;
          attackTeam = defendTeam;
          defendTeam = placeholderTeam;

          attackTeam.setBall(!attackTeam.hasBall());
          defendTeam.setBall(!defendTeam.hasBall());
          System.out.println("Does attackTeam have ball: "+attackTeam.hasBall());
          System.out.println("Does defendTeam not have ball: "+!defendTeam.hasBall());

          time=time+1/3.0;
          System.out.println("Time: "+time);

          dTrue=false;
          //defendPlayer = dRoster.get(lowDistIndex);
          //System.out.println("Ball goes to "+defendPlayer.getName());
        }
      }
      else
      {
        dTrue=false;
        throw new BadBallPosession();
      }
    }
    else
    {
      dTrue=false;
      throw new MaxDistanceExceeded();
    }
    return dTrue;
  }

  public double getTime()
  {
    return time;
  }
}
