import java.lang.Math;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Pitch{
String[][] field;
//Player[][] arena;
int n = 0;
char mode;
char side;
int position;
int numbermid;
int numberdef;
int numberfor;
int spacedef;
int spacemid;
int spacefor;
public Pitch(){
field = new String[40][30];
for(int i =0; i<40;i++){
  if (i==0 || i==19 || i==39){
    for(int j=0; j <30; j++){
      field[i][j]= "--";
    //  System.out.println("here13");
}
}else if(i==7 || i ==32){
      for(int j=0;j<1; j++){
        field[i][j]="| ";
      }
      for(int j=1; j<5;j++){
        field[i][j] ="  ";
    //    System.out.println("here18");
      }
      for(int j =5;j<26; j++){
        field[i][j]= "--";
        //System.out.println("here22");
      }
      for(int j = 26; j<29;j++){
        field[i][j] ="  ";
      //  System.out.println("here26");
      }
      for(int j= 29;j<30;j++){
        field[i][j]="| ";
      }
}else if(i==4 || i==35){
    for(int j=0;j<30; j++){
      if(j==0 || j==5 || j== 10|| j== 20 || j==25 || j==29){
    field[i][j]="| ";
    }else{
    field[i][j]="  ";
    }
    }
      for(int j = 10; j< 21; j++){
      field[i][j]="--";
    //  System.out.println("here35");
      }
      //for(int j =21;j<30;j++){
    //  field[i][j] = ' ';
    //  System.out.println("here39");
    //  }
  }else if(i<4 ||  i>35){
        for(int j = 0; j<30; j++){
          if(j==0 || j==5 || j== 10|| j== 20 || j==25 || j==29){
        field[i][j]="| ";
      }else{
        field[i][j]="  ";
      }
      //  System.out.println("here44");
        }
    }else if(i<7 || i>32){
        for(int j =0; j<30;j++){
        if(j==0 || j== 5 || j == 25 || j ==29 ){
          field[i][j]="| ";
        //  System.out.println("here54");
        }else{
          field[i][j]="  ";
        //  System.out.println("here57");
        }
      }
    }else if(i<32 && i>19 || i<19 && i>7){
      n=0;
        while(n !=30){
        if( n==0 || n ==29){
          field[i][n]="| ";
          n=n+1;
        //  System.out.println("here64");
        }else{
          field[i][n]="  ";
      //    System.out.println("here67");
          n=n+1;
        }

      }
    }

}

}
//sets all pitch values to spaces
//public void clearPitch(){

//}
public void setFormation(int gk, int d, int m, int f,char h,char s, ArrayList<Player> klopp) throws TooManyPlayersException{
  side = h;
  mode = s;
  numberdef =d;
  numbermid =m;
  numberfor=f;
  int fsa=0;
  int msa = 0;
  int dsa =0;
  int fsh = 0;
  int msh =0;
  int dsh = 0;
  if(mode == 's'){
    if(side =='a'){
      fsa=22;
      msa=25;
      dsa =30;
    }else if(side == 'h'){
      fsh =18;
     msh=14;
     dsh=8;
      System.out.println("");
    }
      System.out.println("");
  }else{
    System.out.println("");
  }
if(gk+numberdef+numberfor+numbermid==11){
  double dd = Math.ceil(30.0/(d+1));
  double mm = Math.ceil(30.0/(m+1));
  double ff = Math.ceil((30.0/(f+1)));
  System.out.println("dd = " + dd + ",  mm = " + mm + ", ff = " + ff);
  spacedef = (int)dd;
  spacemid=(int)mm;
  spacefor=(int)ff;
  position = 0;
  System.out.println("dd = " + spacedef + ",  mm = " + spacemid + ", ff = " + spacefor);

  if(side == 'a'){
    Player current1 = klopp.get(position);
    field[37][15]= current1.getId();
    current1.setX(15);
    current1.setY(37);
    position++;

      for(int xx = spacedef; xx<30; xx=spacedef+xx){
        Player current = klopp.get(position);
        field[dsa][xx]= current.getId();
        current.setX(xx);
        current.setY(dsa);
        position++;
      }

      //System.out.println(field[30][5]);
      //System.out.println(field[30][20]);
      //System.out.println(field[30][6] + "hhhhh");

    position=position;
    for(int yy = spacemid; yy<30; yy = spacemid+yy){
      Player current = klopp.get(position);
      field[msa][yy]= current.getId();
      current.setX(yy);
      current.setY(msa);
      position++;
    }

    position=position;
    for(int zz = spacefor; zz<30; zz = spacefor +zz){
      Player current = klopp.get(position);
      field[fsa][zz]= current.getId();
      current.setX(zz);
      current.setY(fsa);
      position++;
    }
  }else if(side == 'h'){
    position=0;
    Player current1 = klopp.get(position);
    field[2][15]= current1.getId();
    current1.setX(15);
    current1.setY(2);
    position++;
  for(int xx = spacedef; xx<30; xx=spacedef+xx){
    Player current = klopp.get(position);
    field[dsh][xx]= current.getId();
    current.setX(xx);
    current.setY(dsh);
    position++;
  }
  for(int yy = spacemid; yy<30; yy = spacemid+yy){
      Player current = klopp.get(position);
      field[msh][yy]= current.getId();
      current.setX(yy);
      current.setY(msh);
      position++;
  }
  for(int zz = spacefor; zz<30; zz = spacefor +zz){
    Player current = klopp.get(position);
    field[fsh][zz]= current.getId();
    current.setX(zz);
    current.setY(fsh);
    position++;
  }
}
}else{
  throw new TooManyPlayersException();
}
}

public void displayField(){
  for(int k= 0; k<40;k++){
    for(int l =0;l<30;l++){
      System.out.print(field[k][l]);
    }
    System.out.println(' ');
  }
}
public void move(Player player, int x, int y){
  int oldx=player.getPositionX();
  int oldy= player.getPositionY();
  field[oldy][oldx] = "  ";
  field[y][x]= player.getId();
  player.setX(x);
  player.setY(y);
}


}
