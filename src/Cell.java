public class Cell {
	private boolean start;
	private boolean clear;
	private boolean stench;
	private boolean breeze;
	private boolean pit;
	private boolean wumpus;
	private boolean visited;
	private boolean glitter; 
	private boolean maybePit;
	private boolean maybeWumpus;
	private int score;	

	public Cell(boolean start, 
				boolean clear, 
				boolean stench, 
				boolean breeze, 
				boolean pit, 
				boolean wumpus,
				boolean glitter)
	{
		this.start = start;
		this.clear = clear;
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
	 * @return the start
	 */
	public boolean isStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(boolean start) {
		this.start = start;
	}
	/**
	 * @return the clear
	 */
	public boolean isClear() {
		return clear;
	}
	/**
	 * @param clear the clear to set
	 */
	public void setClear(boolean clear) {
		this.clear = clear;
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
}
