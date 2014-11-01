import java.io.BufferedReader;
import java.util.Vector;
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
		File file = new File(pathToBoardFile);
		BufferedReader reader = null;

		try {
		    reader = new BufferedReader(new FileReader(file));
		    String text = null;
		    int counter = 0;
		    while ((text = reader.readLine()) != null) {
		        if(counter == 0){
		        	readSize(text);
		        	this.cell = new Cell[this.rows][this.columns];
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
	}
	
	private void initializeCells(){
		for(int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.columns; j++){
				this.cell[i][j] = new Cell();
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
	    		  if(content[i].equalsIgnoreCase(WUMPUS)){
	    			 cell[x][y].attributes.add("wumpus");
	    			 continue;
	    		  } else if (content[i].equalsIgnoreCase(BREEZY)){
	    			  cell[x][y].attributes.add("breeze");
	    			 continue;
	    		  } else if (content[i].equalsIgnoreCase(STENCH)){
	    			  cell[x][y].attributes.add("stench");
	    			 continue; 
	    		  } else if (content[i].equalsIgnoreCase(PIT)){
	    			  cell[x][y].attributes.add("pit");
	    			  continue;
	    		  } else if (content[i].equalsIgnoreCase(ENTER)){
	    			  cell[x][y].attributes.add("start");
	    			  continue;	  
	    		  } else if (content[i].equalsIgnoreCase(GOLD)){
	    			  cell[x][y].attributes.add("glitter");
	    			  continue;
	    		  }
	    	  }
	      }
	}
	
	public void print(){
		for(int i = 0; i < rows; i++){
			System.out.print("|");
			for(int j = 0; j < columns; j++){
				cell[i][j].print();
				System.out.print("|");
			}
			System.out.println();
		}
	}
}
