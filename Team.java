import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Team{
  ArrayList<Player> teamList = new ArrayList<Player>();
  String teamName;
  boolean home, ball;
  File file;
  Scanner scan;
  String line1;
  int score = 0;

  public Team(String newFile, boolean home) throws FileNotFoundException{
    try{
      file = new File(newFile);
      scan = new Scanner(file);
    }catch(FileNotFoundException e){
      throw new FileNotFoundException("error");
    }

    //this part scans through the given file and adds the player information to teamList
    line1 = scan.nextLine();
    teamName = line1;//first line is the name of the team; next lines are all players
    while(scan.hasNextLine())
    {
      // String nameHolder = "";
      // String strengthHolder = "";
      line1 = scan.nextLine();
      String[] line_chunks = line1.split(",");
      //System.out.println("line chunks size = " + line_chunks.length);
      String player_name = line_chunks[0];
      String player_id = line_chunks[1];
      String playerStrength = line_chunks[2];
      int pStrength = Integer.parseInt(playerStrength);
      Player p = new Player(player_name,teamName,player_id,pStrength);
      teamList.add(p);
    }

    this.home = home;
  }
  //
  //     while(letter!=',')
  //     {
  //       letter = line.charAt(i);
  //       String l1 = String.valueOf(letter);
  //       nameHolder = nameHolder+l1;
  //       i++;
  //     }
  //     nameHolder = nameHolder.substring(0,i-1);
  //
  //     while(i<line.length())
  //     {
  //       letter = line.charAt(i);
  //       String l2 = String.valueOf(letter);
  //       strengthHolder = strengthHolder+l2;
  //       i++;
  //       System.out.println(strengtholder);
  //     }
  //   //  int strHolder = Integer.parseInt(strengthHolder);
  //     Player p = new Player(nameHolder,teamName,strengthHolder);
  //     teamList.add(p);
  //   }
  //
  //   this.home = home;
  // }

  // public void placePlayers(int d, int m, int f) throws BadPlayerNumberException
  // {
  //   if((d+m+f+1)!=11)
  //   {
  //     throw new BadPlayerNumberException();
  //   }
  //   else
  //   {
  //     if(home==true)
  //     {
  //       teamList.get(0).setPosition(14,2);
  //       int dSpacing = 30/(d+1);
  //       int mSpacing = 30/(m+1);
  //       int fSpacing = 30/(f+1);
  //
  //       int index=1;
  //       while(index<(d+1))
  //       {
  //         System.out.println(index);
  //         teamList.get(index).setPosition((index)*dSpacing, 7);
  //         index++;
  //       }
  //       while(index<(m+d+1))
  //       {
  //         teamList.get(index).setPosition((index-(d))*mSpacing, 17);
  //         index++;
  //       }
  //       while(index<11)
  //       {
  //         teamList.get(index).setPosition((index-(d+m))*fSpacing, 27);
  //         index++;
  //       }
  //     }
  //
  //     else //if team is away
  //     {
  //       teamList.get(0).setPosition(14,37);
  //       int dSpacing = 30/(d+1);
  //       int mSpacing = 30/(m+1);
  //       int fSpacing = 30/(f+1);
  //
  //       // int index=1;
  //       // while(index<(d+1))
  //       // {
  //       //   teamList.get(index).setPosition((index)*dSpacing, 32);
  //       //   index++;
  //       // }
  //       // while(index<(m+d+1))
  //       // {
  //       //   for(int i=1;i<=m;i++)
  //       //   {
  //       //     teamList.get(index).setPosition((index-(d))*mSpacing, 22);
  //       //   }
  //       //   index++;
  //       // }
  //       // while(index<11)
  //       // {
  //       //   teamList.get(index).setPosition((index-(d+m))*fSpacing, 12);
  //       //   index++;
  //       // }
  //     }
  //   }
  // }

  public Player getPlayer(String player_name){
    Player desired_player = null;
    for (Player player : teamList){
      if (player.getName().equals(player_name)){
        desired_player = player;
      }
    }
    return desired_player;
  }

  public ArrayList<Player> getRoster()
  {
    return teamList;
  }

  public boolean isHome()
  {
    return home;
  }

  public boolean hasBall()
  {
    return ball;
  }

  public void setBall(Boolean torf)
  {
    ball = torf;
  }

  public String getTeamName()
  {
    return teamName;
  }

  public void keepScore()
  {
    score = score+1;
  }

  public int getScore()
  {
    return score;
  }
}
