package quoridor;

public class MovePawnImpl extends AbstractMove implements MovePawn {

	Square destination;
	Square start;

	
	public MovePawnImpl(int col, int row, Player owner, Board setting) {
		super(owner, setting);
		destination = new SquareImpl(col, row);
    }
	
	@Override
	public Square getDestination() {
		return destination;
	}

	@Override
	public boolean isValid() {
		
		Square temp;
		Square temp2;
		boolean valid = true;
		int destination_col = destination.getCol();
		int destination_row = destination.getRow();
		int start_col = start.getCol();
		int start_row = start.getRow();
		
		//check whether square is on the board
		if(destination_col > 8 || destination_col < 0 ||
				   destination_row > 8 || destination_row < 0) {
			
            valid = false;

		//check the destination is not the start
		} else if(start == destination) {
			valid = false;
		
		//check if square is adjacent
		} else if (((destination_col - start_col < 2 || start_col - destination_col < 2)
						&& start_row == destination_row)||
					((destination_row - start_row < 2 || start_row - destination_row < 2) 
						&& start_col == destination_col)) {
			//check if the square has a pawn on it
			if(destination.hasPawn()) {
				valid = false;
			}
		}
		//check if the square is 2 away in 1 direction
		else if (destination_col - start_col < 3 && start_row == destination_row) {
			temp = new SquareImpl(destination_col - 1, destination_row);
			return temp.hasPawn();
		} else if (start_col - destination_col < 3 && start_row == destination_row) {
			temp = new SquareImpl(destination_col + 1, destination_row);
			return temp.hasPawn();
		} else if (destination_row - start_row < 3 && start_col == destination_col) {
			temp = new SquareImpl(destination_row - 1, destination_col);
			return temp.hasPawn();
		} else if (start_row - destination_row < 3 && start_col == destination_col) {
			temp = new SquareImpl(destination_row + 1, destination_col);
			return temp.hasPawn();
		}
		//check if the square is a diagonal
		else if(destination_col - start_col == 1 && destination_row - start_row == 1) {
			temp  = new SquareImpl(destination_col, destination_row - start_row);
			temp2 = new SquareImpl(destination_col - start_col, destination_col);
			if(!temp.hasPawn() && !temp2.hasPawn()) {
				valid = false;
			}
		} else if(destination_col + start_col == 1 && destination_row - start_row == 1) {
			temp  = new SquareImpl(destination_col, destination_row - start_row);
			temp2 = new SquareImpl(destination_col + start_col, destination_col);
			if(!temp.hasPawn() && !temp2.hasPawn()) {
				valid = false;
			}
		} else if(destination_col - start_col == 1 && destination_row + start_row == 1) {
			temp  = new SquareImpl(destination_col, destination_row + start_row);
			temp2 = new SquareImpl(destination_col - start_col, destination_col);
			if(!temp.hasPawn() && !temp2.hasPawn()) {
				valid = false;
			}
		} else if(destination_col + start_col == 1 && destination_row + start_row == 1) {
			temp  = new SquareImpl(destination_col, destination_row + start_row);
			temp2 = new SquareImpl(destination_col + start_col, destination_col);
			if(!temp.hasPawn() && !temp2.hasPawn()) {
				valid = false;
			}
		}
		return valid;
	}

	@Override
	public boolean type() {
		return PAWN;
	}


    @Override
    public void makeMove() {
        //Pair <Player> players = new PairImpl();
        /*if (players._1().getName().equalsIgnoreCase(owner.getName())) {
        	System.out.println("name of this player is " +players._1().getName());
        } else if (players._2().getName().equalsIgnoreCase(owner.getName())) {
        	System.out.println("name of the other player is: " +players._2().getName());
        }*/
    	
    	System.out.println("in pawn move" +setting);
    	System.out.println("the name of the player is: " +owner.getName());
       
    }

}
