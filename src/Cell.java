public class Cell {
	private boolean start;
	private boolean stench;
	private boolean breeze;
	private boolean pit;
	private boolean wumpus;
	private boolean visited;
	private boolean glitter; 
	private boolean maybePit;
	private boolean maybeWumpus;
	private int score;	

	public Cell(
				boolean start,
				boolean stench, 
				boolean breeze, 
				boolean pit, 
				boolean wumpus,
				boolean glitter){
		this.setStart(start);
		this.stench = stench;
		this.breeze = breeze;
		this.pit = pit;
		this.wumpus = wumpus;
		this.glitter = glitter;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the glitter
	 */
	public boolean isGlitter() {
		return glitter;
	}

	/**
	 * @param glitter the glitter to set
	 */
	public void setGlitter(boolean glitter) {
		this.glitter = glitter;
	}

	/**
	 * @return the clear
	 */
	public boolean isClear() {
		return (!stench && !breeze && !wumpus && !pit);
	}
	/**
	 * @return the stench
	 */
	public boolean isStench() {
		return stench;
	}
	/**
	 * @param stench the stench to set
	 */
	public void setStench(boolean stench) {
		this.stench = stench;
	}
	/**
	 * @return the breeze
	 */
	public boolean isBreeze() {
		return breeze;
	}
	/**
	 * @param breeze the breeze to set
	 */
	public void setBreeze(boolean breeze) {
		this.breeze = breeze;
	}
	/**
	 * @return the pit
	 */
	public boolean isPit() {
		return pit;
	}
	/**
	 * @param pit the pit to set
	 */
	public void setPit(boolean pit) {
		this.pit = pit;
	}
	/**
	 * @return the wumpus
	 */
	public boolean isWumpus() {
		return wumpus;
	}
	/**
	 * @param wumpus the wumpus to set
	 */
	public void setWumpus(boolean wumpus) {
		this.wumpus = wumpus;
	}
	/**
	 * @return the visited
	 */
	public boolean isVisited() {
		return visited;
	}
	/**
	 * @param visited the visited to set
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	/**
	 * @return the maybePit
	 */
	public boolean isMaybePit() {
		return maybePit;
	}
	/**
	 * @param maybePit the maybePit to set
	 */
	public void setMaybePit(boolean maybePit) {
		this.maybePit = maybePit;
	}
	/**
	 * @return the maybeWumpus
	 */
	public boolean isMaybeWumpus() {
		return maybeWumpus;
	}
	/**
	 * @param maybeWumpus the maybeWumpus to set
	 */
	public void setMaybeWumpus(boolean maybeWumpus) {
		this.maybeWumpus = maybeWumpus;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isStart() {
		return start;
	}

	public void print() {
		String values = "";
		if(this.isBreeze()){
			values += "B ";
		}
		if(this.isStench()){
			values += "S ";
		}
		if(this.isPit()){
			values += "P ";
		}
		if(this.isGlitter()){
			values += "G ";
		}
		if(this.isWumpus()){
			values += "W ";
		}
		if(this.isStart()){
			values += "START ";
		}
		if(values.equalsIgnoreCase("")){
			values = "  ";
		}
		System.out.print(values);
	}
}
