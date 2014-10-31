import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HuntTheWumpus {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter path to Wumpus world layout input file: ");
        try {
			String pathToWumpusInputFile = br.readLine();
			Board board = new Board(pathToWumpusInputFile);
			board.print();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
