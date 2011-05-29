package quoridor;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class RandomAI implements State {

	protected Board setting;
	protected Player currentTurn;
	protected GenericMove nextMove;
	
	public RandomAI(Board setting, Player currentTurn) {
		this.setting = setting;
		this.currentTurn = currentTurn;
		nextMove = null;
	}
	
	
	public RandomAI(Board setting, Player currentTurn, GenericMove nextMove) {
		this.setting = setting;
		this.currentTurn = currentTurn;
		this.nextMove = nextMove;
		
	}
	

	@Override
	public Iterator<StateImpl> iterator() {
		return null;
		//return new MoveGenerator(currentTurn, setting);
	}

	@Override
	public GenericMove nextBestMove() {
		randomMove();
		return nextMove;
	}


	
	List <PlaceWall> wallBackerList = new LinkedList <PlaceWall>();
	List <MovePawn> pawnBackerList = new LinkedList <MovePawn>();
	private void wall() {
		for (int i = 0; i <= 8; i++) {
			for (int j = 0; j <= 8;j++) {
				Board settingClone = new BoardImpl((BoardImpl)setting);
				Player moverClone = new PlayerImpl(currentTurn);
				PlaceWallImpl vTentative = new PlaceWallImpl(i, j, Wall.VERTICAL, moverClone, settingClone);
				settingClone = new BoardImpl((BoardImpl)setting);
				moverClone = new PlayerImpl(currentTurn);
				PlaceWallImpl hTentative = new PlaceWallImpl(i, j, Wall.HORIZONTAL, moverClone, settingClone);
				
				if(vTentative.isValid()) {
					wallBackerList.add(vTentative);
				}
				if(hTentative.isValid()) {
					wallBackerList.add(hTentative);
				}
			}
		}
	}
	
	private void pawn() {
		for (int i = -2; i <= 2 ; i ++) {
			for (int j = -2;j<=2;j++) {
				Square tmp = setting.getPawn(currentTurn, setting).getSquare();
				Board settingClone = new BoardImpl((BoardImpl)setting);
				Player moverClone = new PlayerImpl(currentTurn);
				MovePawnImpl tentative = new MovePawnImpl(tmp.getCol() + j, tmp.getRow() + i, moverClone, settingClone );
				if (tentative.isValid()) {
					pawnBackerList.add(tentative);
				}
			}
		}
	}
	
	
	public void randomMove() {
		Random generator = new Random();
		float f = generator.nextFloat() * 100;
		if ((int) f < 50 && currentTurn.wallCount() > 0) {
			wall();
			int wall = (int) generator.nextFloat() *wallBackerList.size();
			PlaceWall tmp = wallBackerList.get(wall);
			nextMove = tmp;
		} else {
			pawn();
			int move = (int) generator.nextFloat()*pawnBackerList.size();
			MovePawn tmp = pawnBackerList.get(move);
			nextMove = tmp;
		}
	}

}
