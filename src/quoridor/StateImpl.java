package quoridor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



public class StateImpl implements State {

	private static final boolean MAX = true;
	private static final boolean MIN = false;
	private static final int DEPTH = 3;
	
	protected Board setting;
	protected Player currentTurn;
	protected GenericMove nextMove;
	public int score;
	
	private Player getMax() {
		if (playerType(currentTurn) == MAX) {
			return currentTurn;
		} else {
			return currentTurn.getOpponent();
		}
	}
	
	public void determineScore() {
		score = pathLength(getMax().getOpponent()) - pathLength(getMax());
	}
	
	public int pathLength(Player player) {
		
		Queue <Node> toVisit = new LinkedList <Node> ();
		
		Node current = new Node(setting.getPawn(player, setting).getSquare(), 0);
		
		toVisit.add(current);
		
		HashSet <Node> seen = new HashSet<Node>();
		
		while (current.getSquare().getRow() != destRow(player.goalEnd()) ) {
			current = toVisit.remove();
			
			seen.add(current);
			
			for (Node n : current) {
				if (!seen.contains(n)) {
					
					toVisit.add(n);
				}
			}
		}
		
		return current.getCost();
	}
	

	public StateImpl(Board setting, Player currentTurn) {
		this.setting = setting;
		this.currentTurn = currentTurn;
		nextMove = null;
	}
	
	
	public StateImpl(Board setting, Player currentTurn, GenericMove nextMove) {
		this.setting = setting;
		this.currentTurn = currentTurn;
		this.nextMove = nextMove;
		
	}
	
	
	@Override
	public Iterator<StateImpl> iterator() {
		return new MoveGenerator(currentTurn, setting);
	}
	
	public Iterator<StateImpl> swapIterator() {
		return new MoveGenerator(currentTurn.getOpponent(), setting);
	}

	@Override
	public GenericMove nextBestMove() {
		
		StateImpl best = bestNextState();
		//System.out.println(best.nextMove);
		return best.nextMove;
	}
	
	private boolean playerType(Player p) {
		boolean result;
		
		if (p.goalEnd() == Player.BOTTOM) {
			result = MAX;
		} else {
			result = MIN;
		}
		
		return result;
	}
	
	
	public StateImpl alphabeta(StateImpl current, StateImpl alpha, StateImpl beta, int depth) {
		StateImpl result = null;
		
		if (current.nextMove != null) {
			current.nextMove.makeMove();
		}
		
		//System.out.println(current.setting);
		//System.out.println(alpha.score + ", " + beta.score + "\n\n");
		if (depth == 0){ 
			current.determineScore();
			result = current;
		} else {
			StateImpl tmp = null;
			
			Iterator <StateImpl> nextMoves = iterator();
			StateImpl s = null;
			
			if (current.playerType(current.currentTurn) == MAX) {
				
				while (nextMoves.hasNext() && alpha.score < beta.score) {
					s = nextMoves.next();
					
					tmp = s.alphabeta(s, alpha, beta, depth - 1);
					
					if (tmp.score > alpha.score) {
						alpha = tmp;
					}
				}
				result = alpha;
				
				
			} else {
				
				
				while (nextMoves.hasNext() && alpha.score < beta.score) {
					
					s = nextMoves.next();

					
					
					tmp = s.alphabeta(s, alpha, beta, depth - 1);
					
					if (tmp.score < beta.score) {
						beta = tmp;
					}
				}
				result = beta;
				
			}
			
			
		}
		
		
		
		
		
		return result;
	}

	
	public StateImpl bestNextState() {
		
		StateImpl best = null;
		int tmp;
		
		//for each next state
		for (StateImpl s : this) {
			s.nextMove.makeMove(); //apply the move
			
			tmp = s.alphabetaNum(Integer.MIN_VALUE, Integer.MAX_VALUE, DEPTH);
			//System.out.println(s.setting + ":" + tmp);
			
			
			if (best == null) {
				best = s;
				best.score = tmp;
			} else {
				
				if (playerType(currentTurn) == MAX) {
					if (tmp > best.score) {
						best = s;
					}
				} else {
					if (tmp < best.score) {
						best = s;
					}
				}
			}
		}
		
		return best;
	}
	
	
	private int destRow(boolean dest) {
		if (dest == Player.TOP){
			return 0;
		} else {
			return 8;
		}
	}
	
	public int alphabetaNum(int alpha, int beta, int depth) {
		
		
		if (depth == 0 || setting.getPawn(currentTurn, setting).getSquare().getRow() == destRow(currentTurn.goalEnd())) {
			determineScore();
			return score;
		}
		
		int tmp;
		
		Iterator <StateImpl> nextMoves = swapIterator();
		StateImpl s = null;
		
		if (playerType(currentTurn) == MIN) {
			
			while (nextMoves.hasNext() && alpha < beta) {
				
				s = nextMoves.next(); //get a new state
				s.nextMove.makeMove(); //apply the current move
				
				tmp = s.alphabetaNum(alpha, beta, depth - 1);
				//System.out.println(s.setting + ":" + tmp);
				if (tmp > alpha) {
					alpha = tmp;;
				}
			}
			
			return alpha;
		}
		
		
		if (playerType(currentTurn) == MAX) {
			
			while (nextMoves.hasNext() && alpha < beta) {
				
				s = nextMoves.next(); //get a new state
				s.nextMove.makeMove(); //apply the current move
				
				tmp = s.alphabetaNum(alpha, beta, depth - 1);
				//System.out.println(s.setting + ":" + tmp);
				if (tmp < beta) {
					beta = tmp;;
				}
			}
			
			return beta;
		}				
			

		return 0;
	}
	
	
	private class MoveGenerator implements Iterator <StateImpl> {

		List <StateImpl> backerList;
		Iterator <StateImpl> backer;
		Player mover;
		Board setting;
		
		public MoveGenerator(Player mover, Board setting) {
			backerList = new LinkedList <StateImpl>();
			this.mover = mover;
			this.setting = setting;
			populate();
			backer = backerList.iterator();
		}
		
		private void populate() {
			//populate with MovePawns
			for (int i = -2; i <= 2 ; i ++) {
				for (int j = -2;j<=2;j++) {
					Square tmp = setting.getPawn(mover, setting).getSquare();
					Board settingClone = new BoardImpl((BoardImpl)setting);
					Player moverClone = new PlayerImpl(mover);
					MovePawn tentative = new MovePawnImpl(tmp.getCol() + j, tmp.getRow() + i, moverClone, settingClone );
					
					
					
					if (tentative.isValid()) {
						
						//System.out.println(tentative);
						
						backerList.add(new StateImpl(settingClone, moverClone, tentative));
					}
				}
			}
			
			//populate with PlaceWalls
			/*
			for (int i = 0; i <= 8; i++) {
				for (int j = 0; j <= 8;j++) {
					Board settingClone = new BoardImpl((BoardImpl)setting);
					Player moverClone = new PlayerImpl(mover);
					
					PlaceWall vTentative = new PlaceWallImpl(i, j, Wall.VERTICAL, moverClone, settingClone);
					
					settingClone = new BoardImpl((BoardImpl)setting);
					moverClone = new PlayerImpl(mover);
					
					PlaceWall hTentative = new PlaceWallImpl(i, j, Wall.HORIZONTAL, moverClone, settingClone);
					
					if(vTentative.isValid()) {
						backerList.add(new StateImpl(settingClone, moverClone, vTentative));
					}
					
					if(hTentative.isValid()) {
						backerList.add(new StateImpl(settingClone, moverClone, hTentative));
					}
				}
			}
			*/
			
		}
		
		@Override
		public boolean hasNext() {
			return backer.hasNext();
		}

		@Override
		public StateImpl next() {
			return backer.next();
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
		}
		
		
		
		
		
	}
	
	private class Node implements Comparable<Node>, Iterable<Node> {
		protected Square square;
		protected int cost;
		
		public Node(Square s, int n) {
			square = s;
			cost = n;
		}
		
		public Square getSquare() {
			return square;
		}
		
		public int getCost() {
			return cost;
		}
		
		public void setCost(int cost) {
			this.cost  = cost;
		}

		@Override
		public int compareTo(Node o) {
			return o.cost - cost;
		}
		
		@Override
		public boolean equals(Object o) {
			return ((Node)o).square.equals(square);
		}
		
		@Override
		public int hashCode() {
			return square.hashCode();
		}

		@Override
		public Iterator<Node> iterator() {
			return new NodeIterator(this, cost);
		}
		
		private class NodeIterator implements Iterator<Node> {
			List <Node> nextList = new LinkedList <Node>();
			Iterator <Node> backer;
			
			public NodeIterator(Node start, int n) {
				
				for (int i = -2; i <= 2 ; i ++) {
					for (int j = -2;j<=2;j++) {
						if (i != 0 || j != 0) {
							
							
							Board settingClone = new BoardImpl((BoardImpl)setting);
							Player moverClone = new PlayerImpl(currentTurn);
							
							settingClone.getPawn(moverClone, settingClone).setSquare(square);
							
							MovePawn tentative = new MovePawnImpl(square.getCol() + j, square.getRow() + i, moverClone, settingClone );
							
							
							if (tentative.isValid()) {
								
								nextList.add(new Node(new SquareImpl(square.getCol()+j, square.getRow() + i), n + 1));
							}
						}
					}
				}
				
				backer = nextList.iterator();
				
			}
			
			@Override
			public boolean hasNext() {
				return backer.hasNext();
			}

			@Override
			public Node next() {
				return backer.next();
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
			
		}
		
	}
	
	
}
