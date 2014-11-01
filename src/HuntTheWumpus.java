import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HuntTheWumpus {
  Cell[][] board;
  int rows;
  int columns;
  //location of player
  int killed=0;
  int playX;
  int playY;
  //player starting location
  int startX;
  int startY;
  //player direction
  int playDir;
  int oldDir;
  int around;
  int ardcnt;
  //location of wumpous
  int wumpusX;
  int wumpusY;

  String bump="";
  String arrow="";
  String scream="";
  static Random generator;

  //creates WumpusWorld
  public void WumpusWorld() {
	  generator = new Random();
	  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.print("Enter path to Wumpus world layout input file: ");
      try {
			String pathToWumpusInputFile = br.readLine();
			File file = new File(pathToWumpusInputFile);
			BufferedReader reader = null;

			try {
			    reader = new BufferedReader(new FileReader(file));
			    String text = null;
			    int counter = 0;
			    while ((text = reader.readLine()) != null) {
			        if(counter == 0){
			        	readSize(text);
			        	board = new Cell[this.rows][this.columns];
			        	this.initializeCells();
			        	counter++;
			        } else {
			        	readCell(text);
			        	counter++;
			        }
			    }
			} catch (FileNotFoundException e) {
			    e.printStackTrace();
			} catch (IOException e) {
			    e.printStackTrace();
			} finally {
			    try {
			        if (reader != null) {
			            reader.close();
			        }
			    } catch (IOException e) {
			    }
			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
   //TODO: calculate the score for each cell here
   //calculate score after board is created, need to know the 
   //player starting position before you can get accurate score
   }
   
	private void initializeCells(){
		for(int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.columns; j++){
				board[i][j] = new Cell();
			}
		}
	}
	private void readSize(String text){
	      String pattern = "[S|s]ize\\s*=\\s*(\\d+)\\s*,\\s*(\\d+)";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(text);
	      if (m.find()) {
	    	 this.rows = Integer.parseInt(m.group(1));
	    	 this.columns = Integer.parseInt(m.group(2));
	      } else {
	         System.out.println("NO MATCH");
	      }
	}
	private void readCell(String text){
	      String splitBy = ",";
	      String[] content = text.split(splitBy);
	      int x = 0;
	      int y = 0;

	      for(int i = 0; i < content.length; i++){
	    	  if(i == 0){
	    		  x = Integer.parseInt(content[i]);
	    	  }
	    	  if(i == 1){
	    		  y = Integer.parseInt(content[i]);
	    	  } else {
	    		  if(content[i].equalsIgnoreCase("W")){
	    			  wumpusX = x;
	    			  wumpusY = y;
	    			 board[x][y].attributes.add("wumpus");
	    			 continue;
	    		  } else if (content[i].equalsIgnoreCase("B")){
	    			  board[x][y].attributes.add("breeze");
	    			 continue;
	    		  } else if (content[i].equalsIgnoreCase("S")){
    			  	 board[x][y].attributes.add("stench");
	    			 continue; 
	    		  } else if (content[i].equalsIgnoreCase("P")){
	    			  board[x][y].attributes.add("pit");
	    			  continue;
	    		  } else if (content[i].equalsIgnoreCase("E")){
	    			  startX = x;
	    			  startY = y;
	    			  board[x][y].attributes.add("start");
	    			  continue;	  
	    		  } else if (content[i].equalsIgnoreCase("G")){
	    			  board[x][y].attributes.add("glitter");
	    			  continue;
	    		  }
	    	  }
	      }
	}
  //input: players next action
  //return: new world state, cost of the action
  public int result(String action){
    scream="";
    String bumps="";
    Cell current = new Cell();
    int retval=-99;
    //take care of left turns
    if(action.equalsIgnoreCase("turnLeft")){
      turn(-1);
      retval=-1;}
    //take care of right turns
    if(action.equalsIgnoreCase("turnRight")){
      turn(1);
      retval=-1;}
    if(action.equalsIgnoreCase("forward")){
      bumps=move(playDir);
      retval=-1;
      //check for pit and wumpus
      current = board[playX][playY];
      for(int i=0;i<current.attributes.size();i++){
        if( ((String)current.attributes.get(i)).equalsIgnoreCase("pit")){
          retval = -1000;
          System.out.println("Fell in pit result at x: "+playX+", y: "+playY);
          killed++;} }
      if(playX==wumpusX && playY==wumpusY){
        retval = -1000;
        System.out.println("wumpus got me result");
        killed++;}
    }
    //get the gold
    if(action.equalsIgnoreCase("grab")){
      current = board[playX][playY];
      for(int i=0;i<current.attributes.size();i++){
        if( ((String)current.attributes.get(i)).equalsIgnoreCase("glitter")){
          retval = 1000;
          current.attributes.remove("glitter");
          System.out.println(current.attributes);}
      }//end of iterating through attributes
      if(retval!=1000){ retval=-1; }
    }
    //checks the fire arrow and sees if it hits wumpus
    if(action.equalsIgnoreCase("fire") && retval!=-1000) { retval = fire(); }
    
    bump=bumps;
    return retval;
  }
  
//shoots the arrow and returns 1000 for hit and -10 for miss
  public int fire() {
    int retval=-10;
    int x;
    int y;
    Cell current=new Cell();
    //player facing right
    if(playDir==0){
      x=playX+1;
      while(x<=3){
        current = board[x][playY];
        for(int i=0;i<current.attributes.size();i++){
          if( ((String)current.attributes.get(i)).equalsIgnoreCase("wumpus"))
            retval=-10;
            current.attributes.remove("wumpus");
            scream="yes";
        }//end for
        x++;
      }//end while
    }
    //player facing up
    if(playDir==1){
      y=playY+1;
      while(y<=3){
        current = board[playX][y];
        for(int i=0;i<current.attributes.size();i++){
          if( ((String)current.attributes.get(i)).equalsIgnoreCase("wumpus"))
            retval=-10;
            current.attributes.remove("wumpus");
            //removeAround("stench",playX,y);
            scream="yes";
        }//end for
        y++;
      }//end while
    }
    //player facing left
    if(playDir==2){
      x=playX-1;
      while(x>=0){
        current = board[x][playY];
        for(int i=0;i<current.attributes.size();i++){
          if( ((String)current.attributes.get(i)).equalsIgnoreCase("wumpus"))
            retval=-10;
            current.attributes.remove("wumpus");
            //removeAround("stench",x,playY);
            scream="yes";
        }//end for
         x--;
      }//end whiel
    }
    //player facing down
    if(playDir==3){
      y=playY-1;
     while(y>=0){
       current = board[playX][y];
       for(int i=0;i<current.attributes.size();i++){
         if( ((String)current.attributes.get(i)).equalsIgnoreCase("wumpus"))
           retval=-10;
           current.attributes.remove("wumpus");
           //removeAround("stench",playX,y);
           scream="yes";
       }//end for
       y--;
     }//end while
    }
    return retval;
  }
  
//turns the person either right or left
  public void turn(int dir){
    oldDir=playDir;
    
    if(dir==1){ playDir--; }//turn right
    else{ playDir++; }//turn left
    //set playDir within bounds
    if(playDir==4) { playDir=0; }
    if(playDir==-1) { playDir=3; }
  }
  
  public String move(int dir) {
    String retval="";
    if(dir==0){ playX++; }//right
    if(dir==1){ playY++; }//up
    if(dir==2){ playX--; } //left
    if(dir==3){ playY--; } //down
    
    if( playX > columns ){
      playX=4;
      retval="bump";
    }
    if( playY > rows ){
      playY=4;
      retval="bump";
    }
    if( playX < 0 ){
      playX=0;
      retval="bump";
    }
    if( playY < 0 ){
      playY=0;
      retval="bump";
    }
    return retval;
  }
  
  public String Agent(){
	  String retval="";
	  boolean stench=false;
	  boolean breeze=false;
	  
	  //percept current cell
	  //TODO: enter findings into player knowledge base
	  Cell current = board[playX][playY];
	    for(int i=0; i < current.attributes.size(); i++){
	    	
	      String temp=(String)current.attributes.get(i);
	      
	      if(temp.equalsIgnoreCase("stench")){ stench=true; }
	      if(temp.equalsIgnoreCase("glitter")){ 
	    	  retval = "grab";
	    	  return retval;
	      }
	      if(temp.equalsIgnoreCase("breeze")) { breeze = true; }
	    }

	    //Go forward
	    if(playX==startX && playY==startY && playDir==0) {
	      System.out.println("first move");
	      retval="forward";
	    }
	    //if wumpus killed, move forward
	    if(!scream.equalsIgnoreCase("") && retval.equalsIgnoreCase("")) {
	      if(breeze!=true){
	        retval = "forward";
	        return retval;
	      }
	      else {//it is dead but there might be a pit
	        retval="turnLeft";
	        around++;
	      }
	    }
	    if(breeze==true){
         if(around==0 || around==3){
           System.out.print("around 1 count is: "+ardcnt+": ");
           around=1;
           ardcnt++;
           if(ardcnt>11){
             around=0;
             retval= "forward";
           }
           else {
            retval = "turnLeft";
          }
        }
	   }
	   if(stench==true){
	     //try to go around wumpus
	     if (around == 0) {
	       around = 1;
	       ardcnt++;
	       if (ardcnt > 11) {
	         around = 0;
	         retval = "forward";
	       }
	       else {
	         retval = "turnLeft";
	        }
	      }
	    }
	    if(retval.equalsIgnoreCase("")){
	      if(around==1){
	        around=2;
	        System.out.print("around 2: ");
	        retval="turnLeft";
	       }
	      else if(around==2){
	        retval="forward";
	        System.out.print("around 3: ");
	        around=3;
	       }
	      else if(around==3){
	        around=0;
	        System.out.print("around 4: ");
	        retval="turnRight";
	      }
	      
	    //TODO: Implement knowledge base lookup here
	    //after KB lookup is complete, we can remove 
	    //the following random action code
	      if(retval.equalsIgnoreCase("")){
	    	  System.out.println("trying something random");
	          int pick = generator.nextInt(4);
	        if(breeze==false && stench==false){
	          retval = "forward";
	          pick=-1;
	        }

	        //turn left
	        if (pick == 0) {
	          retval = "turnRight";
	        }
	        //turn right
	        if (pick == 1) {
	          retval = "turnRight";
	        }
	        //move forward
	        if (pick >= 2) {
	          retval = "forward";
	        }
	      }
	    //end of choosing random action
	      
	    }

	    //following code handle the walls
	    if(playX==0 && playDir==2){
	      retval = "turnRight";
	    }
	    if(playY==0 && playDir==3){
	      retval = "turnRight";
	    }
	    if(playX==columns && playDir==0){
	      retval = "turnRight";
	    }
	    if(playY==rows && playDir==1){
	      retval = "turnRight";
	    }
	    return retval;
	}
 
  
	public static void main(String[] args) {
        HuntTheWumpus test;
        int score = 0, moves = 0, points = 0;
	    String doA="";
	    test = new HuntTheWumpus();
	    test.WumpusWorld();
	    while ( (points != -1000 && points != 1000) && moves < 1000) {
		      if (moves < 1000) {
		        doA = test.Agent();
		        points = test.result(doA);
		        score = score + points;
		       System.out.println("Action: "+doA+"   points: "+points+"    move count: " + moves+"  score: "+score);
		       System.out.println("cell["+test.playX+","+test.playY+"] direction: "+test.playDir+", Cell Attributes: "+(test.board[test.playX][test.playY]).attributes);
		      }
		      moves++;
		    }

	   
	   
	}

}
