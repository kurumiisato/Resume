package fighters;

import framework.BattleField;
import framework.Random131;

/* This class can create more BasicSoldiers with the state of the grid, their
 row and column, their health status and which team they are on. */
public class BasicSoldier {

	//final static constants of the status of the soldier
	public final static int INITIAL_HEALTH = 10; 
	public final static int ARMOR = 20;   
	public final static int STRENGTH = 30;  
	public final static int SKILL = 40;  

	/*constants that CANNOT be touched. These name constants of directions
	 are represented by an integer */
	public final static int UP = 0;
	public final static int RIGHT = 1;
	public final static int DOWN = 2;
	public final static int LEFT = 3;
	public final static int UP_AND_RIGHT = 4;
	public final static int DOWN_AND_RIGHT = 5;
	public final static int DOWN_AND_LEFT = 6;
	public final static int UP_AND_LEFT = 7;
	public final static int NEUTRAL = -1;

	//Instance variables, variables used throughout the class
	public final BattleField grid;  
	public int row, col; 
	public int health; 
	public final int team;


	/* Constructor that creates a Basic Soldier using the parameters of grid,
	 the row the soldier is in, the team the soldier is in and the column 
	 the soldier is in */
	public BasicSoldier(BattleField gridIn, int teamIn, int rowIn, int colIn) {
		grid = gridIn;
		team = teamIn;
		row = rowIn;
		col = colIn;
		health = INITIAL_HEALTH;
	}



	//Instance methods


	/* This helps to see if the an enemy member is above the user. This will 
	   return a true or false about whether the enemy is above or not. */
	public boolean isEnemyAbove() {
		int enemyTeam;
		if (team == BattleField.RED_TEAM) {
			enemyTeam = BattleField.BLUE_TEAM;
		} else {
			enemyTeam = BattleField.RED_TEAM;
		}
		int above = grid.get(row - 1,  col);
		return above == enemyTeam;
	}

	/* This helps to see if the an enemy member is below the user. This will 
	   return a true or false about whether the enemy is below or not. */
	public boolean isEnemyBelow() {
		int enemyTeam;
		if (team == BattleField.RED_TEAM) {
			enemyTeam = BattleField.BLUE_TEAM;
		} else {
			enemyTeam = BattleField.RED_TEAM;
		}
		int below = grid.get(row + 1,  col);
		return below == enemyTeam;
	}

	/* This helps to see if the an enemy member is to the right of the user. 
	   This will return a true or false about whether the enemy is to the right
	   or not. */
	public boolean isEnemyRight() {
		int enemyTeam;
		if (team == BattleField.RED_TEAM) {
			enemyTeam = BattleField.BLUE_TEAM;
		} else {
			enemyTeam = BattleField.RED_TEAM;
		}
		int right = grid.get(row, col + 1);
		return right == enemyTeam;
	}

	/* This helps to see if the an enemy member is to the left of the user. 
	   This will return a true or false about whether the enemy is to the left
	   or not. */
	public boolean isEnemyLeft() {
		int enemyTeam;
		if (team == BattleField.RED_TEAM) {
			enemyTeam = BattleField.BLUE_TEAM;
		} else {
			enemyTeam = BattleField.RED_TEAM;
		}
		int left = grid.get(row, col - 1);
		return left == enemyTeam;
	}

	/* This method will see whether the user can move or not. If the surrounding 
	   area is empty the user will be able to move. They will return a true
	   or false statement */ 
	public boolean canMove() {
		int above = grid.get(row - 1,  col);
		int below = grid.get(row + 1, col);
		int right = grid.get(row, col + 1);
		int left = grid.get(row, col - 1);

		return above == BattleField.EMPTY || below == BattleField.EMPTY || 
				right == BattleField.EMPTY || left == BattleField.EMPTY;	
	}
	
	/* This method will allow the player to see if the user can move up using
	   a true or false statement */
	public boolean canMoveAbove() {
		int above = grid.get(row - 1,  col);
		return above == BattleField.EMPTY;
	}
	/* This method will allow the player to see if the user can move down using
	   a true or false statement */
	public boolean canMoveBelow() {
		int below = grid.get(row + 1, col);
		return below == BattleField.EMPTY;
	}

	/* This method will allow the player to see if the user can move right using
	   a true or false statement */
	public boolean canMoveRight() {
		int right = grid.get(row, col + 1);
		return right == BattleField.EMPTY;
	}

	/* This method will allow the player to see if the user can move left using
	   a true or false statement */
	public boolean canMoveLeft() {
		int left = grid.get(row, col - 1);
		return left == BattleField.EMPTY;
	}

	/* This method will return the number of enemies remaining that is alive
	 	on the battle field. */
	public int numberOfEnemiesRemaining()  {
		int numEnemiesRemain = 0; // we must initialize zero, since we increment

		// this portion of the method helps determine which color is the enemy
		int enemyTeam;
		if (team == BattleField.RED_TEAM) {
			enemyTeam = BattleField.BLUE_TEAM;
		} else {
			enemyTeam = BattleField.RED_TEAM;
		}
		
		
		for (int row = 0; row < grid.getRows(); row++) {
			for (int col = 0; col < grid.getCols(); col++) {
				if (grid.get(row, col) == enemyTeam) {
					numEnemiesRemain++;
				}
			}
		}
		return numEnemiesRemain;	
	}

	/* This method will provide the distance of how close a point on the field
	 	will be. For this method to work you need to set the parameter of where
	 	on the field you want to find the distance from to your player */
	public int getDistance(int destinationRow, int destinationCol) {
		int x = destinationRow - row;
		int y = destinationCol - col;

		return Math.abs(x) + Math.abs(y);
	}

	/* This method will provide a number that represents the direction that the
	   player must move towards in order to get the the position set in the 
	   parameter */
	public int getDirection(int destinationRow, int destinationCol) {
		
		/* If the set position on graph is the same place as the player, it will
		 return neutral */
		if (destinationRow == row && destinationCol == col) {
			return NEUTRAL;
		}
		
		if (destinationRow == row) {
			if (destinationCol > col) {
				return RIGHT;
			} else if (destinationCol < col) {
				return LEFT;
			}
		}

		if (destinationCol == col) {
			if (destinationRow > row) {
				return DOWN;
			} else if (destinationRow < row) {
				return UP;
			}
		}

		if (destinationRow > row) {
			if (destinationCol > col) {
				return DOWN_AND_RIGHT;
			} else {
				return DOWN_AND_LEFT;
			}
		} else {
			if (destinationCol > col) {
				return UP_AND_RIGHT;
			} else {
				return UP_AND_LEFT;
			}
		}
	}

	/* This method will return a number of team mates that are near you within
	 	the radius that is provided in the parameter */
	public int countNearbyFriends(int radius) {
		int numFriends = 0;
		for (int row = 0; row < grid.getRows(); row++) {
			for (int col = 0; col < grid.getCols(); col++) {
				if (grid.get(row, col) == team) {
					if (getDistance(row, col) <= radius) {
						numFriends++;
					}
				}
			}
		}
		return numFriends - 1;
	}

	/* This method will return an integer that represents a direction that the
	   player must move towards in order to get to the nearest friend */
	public int getDirectionOfNearestFriend() {
		int nearDistRow = row;
		int nearDistCol = col;
		int closeFriend = getDistance(-1000 , -1000); /*far away as possible
													    to able to compare */
		int numFriends = 0;

		for (int row = 0; row < grid.getRows(); row++) {
			for (int col = 0; col < grid.getCols(); col++) {
				if (grid.get(row, col) == team) {
					numFriends++;
					if (closeFriend >= getDistance(row,col) 
							
							// make sure the closeFriend is not the player
							&& (row == this.row && col == this.col) != true) {
						closeFriend = getDistance(row,col);

						nearDistRow = row;
						nearDistCol = col;
					}
				}
			}
		}
		if (numFriends == 1) { /* if the only team mate is you on the grid, the
							      method will return neutral */
			return NEUTRAL;
		}
		return getDirection(nearDistRow, nearDistCol);
	}

	/* This method will return an integer that represents the direction of the 
	   nearest enemy. If there aren't any more enemies on the field then the 
	   method will return neutral */
	public int getDirectionOfNearestEnemy(int radius) {
		int nearDistRow = row;
		int nearDistCol = col;
		int closeEnemy = getDistance(-1000 , -1000);
		int numEnemies = 0;

		// this portion of the method helps determine which color is the enemy 
		int enemyTeam;
		if (team == BattleField.RED_TEAM) {
			enemyTeam = BattleField.BLUE_TEAM;
		} else {
			enemyTeam = BattleField.RED_TEAM;
		}

		for (int gridRow = 0; gridRow < grid.getRows(); gridRow++) {
			for (int gridCol = 0; gridCol < grid.getCols(); gridCol++) {
				if (grid.get(gridRow, gridCol) == enemyTeam) {
					numEnemies++;

					if (getDistance(gridRow,gridCol) <= radius) {
						if (getDistance(gridRow,gridCol) <= closeEnemy) {
							closeEnemy = getDistance(gridRow,gridCol);

							nearDistRow = gridRow;
							nearDistCol = gridCol;
						}
					}
				}
			}
		} 

		if (numEnemies == 0) {
			return NEUTRAL;
		}

		return getDirection(nearDistRow, nearDistCol);
	}

	/* his method will perform it's turn by attacking if there is an enemy that
	   has the ability of getting attacked. If there is no enemy to attack but 
	   the player can move, it will move in the direction it can. If the player
	   is unable to move or attack, it will do nothing */
	public void performMyTurn()  {
		int above = grid.get(row - 1,  col);
		int below = grid.get(row + 1, col);
		int right = grid.get(row, col + 1);
		int left = grid.get(row, col - 1);

		// this portion of the method helps determine which color is the enemy
		int enemyTeam;
		if (team == BattleField.RED_TEAM) {
			enemyTeam = BattleField.BLUE_TEAM;
		} else {
			enemyTeam = BattleField.RED_TEAM;
		}

		if (isEnemyAbove() == true) {
			grid.attack(row - 1, col);

		} else if (isEnemyBelow() == true ) {
			grid.attack(row + 1, col);

		} else if (isEnemyRight() == true ) {
			grid.attack(row, col + 1);

		} else if (isEnemyLeft() == true ) {
			grid.attack(row, col - 1);

		} else if (canMoveAbove() == true) {
			row = row - 1;

		} else if (canMoveBelow() == true) {
			row = row + 1;

		} else if (canMoveRight() == true) {
			col = col + 1;

		} else if (canMoveLeft() == true) {
			col = col - 1;

		} else {
			/* there is no code in this else statement since the soldier should 
			do nothing */
		}
	}
}

// hey Sam, i just wanted to thank you for everything this week, you've truly made this really rough week into one that i can get through
