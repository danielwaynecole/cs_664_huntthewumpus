import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Board {
	final String WUMPUS = "W";
	final String BREEZY = "B";
	final String STENCH = "S";
	final String GOLD = "G";
	final String PIT = "P";
	final String ENTER = "E";


	private int rows;
	private int columns;
	public Cell[][] cell;
	
	public Board(String pathToBoardFile){
		File file = new File("file.txt");
		BufferedReader reader = null;

		try {
		    reader = new BufferedReader(new FileReader(file));
		    String text = null;
		    int counter = 0;
		    while ((text = reader.readLine()) != null) {
		        if(counter == 0){
		        	readSize(text);
		        	this.cell = new Cell[this.rows][this.columns];
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
	}
	
	private void readSize(String text){
	      String pattern = "[S|s]ize\\s*=\\s*(\\d+)\\s*,\\s*(\\d+)";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(text);
	      if (m.find( )) {
	         //System.out.println("Found value: " + m.group(0) );
	         //System.out.println("Found value: " + m.group(1) );
	    	 this.rows = Integer.parseInt(m.group(0));
	    	 this.columns = Integer.parseInt(m.group(1));
	      } else {
	         System.out.println("NO MATCH");
	      }
	}
	
	private void readCell(String text){
	      String splitBy = ",";
	      String[] cellStuff = text.split(splitBy);
	      int rowNumber = 0;
	      int columnNumber = 0;
	      boolean start = false;
	      boolean stench = false; 
		  boolean breeze = false;
		  boolean pit = false;
		  boolean wumpus = false;
		  boolean glitter = false;
	      for(int i = 0; i < cellStuff.length; i++){
	    	  if(i == 0){
	    		  rowNumber = Integer.parseInt(cellStuff[i]);
	    	  }
	    	  if(i == 1){
	    		  columnNumber = Integer.parseInt(cellStuff[i]);
	    	  } else {
	    		  if(cellStuff[i].equalsIgnoreCase(WUMPUS)){
	    			  wumpus = true;
	    			  continue;
	    		  } else if (cellStuff[i].equalsIgnoreCase(BREEZY)){
	    			 breeze = true;
	    			 continue;
	    		  } else if (cellStuff[i].equalsIgnoreCase(STENCH)){
	    			 stench = true;
	    			 continue; 
	    		  } else if (cellStuff[i].equalsIgnoreCase(PIT)){
	    			  pit = true;
	    			  continue;
	    		  } else if (cellStuff[i].equalsIgnoreCase(ENTER)){
	    			  start = true;
	    			  continue;	  
	    		  } else if (cellStuff[i].equalsIgnoreCase(GOLD)){
	    			  glitter = true;
	    			  continue;
	    		  }
	    	  }
	    	  
	      }
	      cell[rowNumber][columnNumber] = new Cell( 
	    		    start,
					stench, 
					breeze, 
					pit, 
					wumpus,
					glitter);
	}
	
	public void boardInit (String pathToBoardFile){
		
	}
	public void print(){
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				
			}
		}
	}
}
