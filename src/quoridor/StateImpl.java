package quoridor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



public class StateImpl implements State {

	private static final boolean MAX = true;
	private static final boolean MIN = false;
	private static final int ONE_FREQ = 16;
	
	protected Board setting;
	protected Player currentTurn;
	protected GenericMove nextMove;
	public int score;
	
	static List <Node> bestMin;
	static List <Node> bestMax;
	
	
	
	public void determineScore() {
		score = pathLengthMin() - pathLengthMax();
	}
	
	
	
	
	public int pathLengthMax() {
		
		Player player;
		
		if (playerType(currentTurn) == MAX) {
			player = currentTurn;
		} else {
			player = currentTurn.getOpponent();
		}
		
		if (bestMax == null) {
			Queue <Node> toVisit = new LinkedList <Node> ();
			
			Node current = new Node(setting.getPawn(player, setting).getSquare(), 0, player, null);
			
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
			
			bestMax = new LinkedList <Node>();
			
			while (current != null) {
				bestMax.add(current);
				current = current.getParent();
			}
		}
		
		return bestMax.size();
	}
	
	
	public int pathLengthMin() {
		
		Player player;
		
		if (playerType(currentTurn) == MIN) {
			player = currentTurn;
		} else {
			player = currentTurn.getOpponent();
		}
		
		if (bestMin == null) {
			Queue <Node> toVisit = new LinkedList <Node> ();
			
			Node current = new Node(setting.getPawn(player, setting).getSquare(), 0, player, null);
			
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
			
			bestMin = new LinkedList <Node>();
			
			while (current != null) {
				bestMin.add(current);
				current = current.getParent();
			}
		}
		
		return bestMin.size();
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
		bestMin = null;
		bestMax = null;
		StateImpl best = bestNextState();
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
	

	
	public StateImpl bestNextState() {
		
		StateImpl best = null;
		int tmp;
		int tracker = 0;
		//for each next state
		for (StateImpl s : this) {
			s.nextMove.makeMove(); //apply the move
			
			if (playerType(currentTurn) == MAX) {
				if (s.nextMove.type() == GenericMove.PAWN) {
					bestMax = null;
				} else {
					clearBest(s.setting);
				}
			} else {
				if (s.nextMove.type() == GenericMove.PAWN) {
					bestMin = null;
				} else {
					clearBest(s.setting);
				}
			}
			
			int depth;
			
			if (tracker % ONE_FREQ == 0) {
				depth = 1;
			} else {
				depth = 0;
			}
			tmp = s.alphabetaNum(Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
			
			
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
			
			tracker++;
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
	
	private void clearBestMin(Board b) {
		if (bestMin != null) {
			Iterator <Node> it = bestMin.iterator();
			if (it.hasNext()) {
				Node prev = it.next();
				Node current;
				
				boolean broken = false;
				
				while (it.hasNext() && !broken) {
					current = it.next();
					
					if (b.wallBetween(current.getSquare(), prev.getSquare())) {
						broken = true;
					}
					
					prev = current;
				}
				
				if (broken) {
					bestMin = null;
				}
			}
		}
	}
	
	private void clearBestMax(Board b) {
		if (bestMax != null) {
			Iterator <Node> it = bestMax.iterator();
			if (it.hasNext()) {
				Node prev = it.next();
				Node current;
				
				boolean broken = false;
				
				while (it.hasNext() && !broken) {
					current = it.next();
					
					if (b.wallBetween(current.getSquare(), prev.getSquare())) {
						broken = true;
					}
					
					prev = current;
				}
				
				if (broken) {
					bestMax = null;
				}
			}
		}
	}
	
	private void clearBest(Board b) {
		clearBestMax(b);
		clearBestMin(b);
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
				
				if (s.nextMove.type() == GenericMove.PAWN) {
					bestMin = null;
				} else {
					clearBestMin(s.setting);
				}
				
				tmp = s.alphabetaNum(alpha, beta, depth - 1);
				
				if (tmp > alpha) {
					alpha = tmp;
				}
				
				if (alpha >= beta) {
				
				}
			}
			
			return alpha;
		}
		
		
		if (playerType(currentTurn) == MAX) {
			
			while (nextMoves.hasNext() && alpha < beta) {
				
				s = nextMoves.next(); //get a new state
				s.nextMove.makeMove(); //apply the current move
				
				if (s.nextMove.type() == GenericMove.PAWN) {
					bestMax = null;
				} else {
					clearBestMax(s.setting);
				}
				
				tmp = s.alphabetaNum(alpha, beta, depth - 1);
				
				
				if (tmp < beta) {
					beta = tmp;
				}
				
				if (alpha >= beta) {
					
				}
			}
			
			return beta;
		}				
			

		return 0;
	}
	
	
	
	private class MoveGenerator implements Iterator <StateImpl> {

		List <StateImpl> wallBackerList = new LinkedList <StateImpl>();
		List <StateImpl> pawnBackerList;
		Iterator <StateImpl> pawnBacker;
		
		
		Iterator <StateImpl> wallBacker;
		
		Player mover;
		Board setting;
		
		public MoveGenerator(Player mover, Board setting) {
			pawnBackerList = new LinkedList <StateImpl>();
			
			this.mover = mover;
			this.setting = setting;
			
			populate();
			
			pawnBacker = pawnBackerList.iterator();
			wallBacker = null;
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
						
						
						
						pawnBackerList.add(new StateImpl(settingClone, moverClone, tentative));
					}
				}
			}
			
		
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8;j++) {
					Board settingClone = new BoardImpl((BoardImpl)setting);
					Player moverClone = new PlayerImpl(mover);
					
					PlaceWall vTentative = new PlaceWallImpl(i, j, Wall.VERTICAL, moverClone, settingClone);
					
					settingClone = new BoardImpl((BoardImpl)setting);
					moverClone = new PlayerImpl(mover);
					
					PlaceWall hTentative = new PlaceWallImpl(i, j, Wall.HORIZONTAL, moverClone, settingClone);
					
					if(vTentative.isValid()) {
						wallBackerList.add(new StateImpl(settingClone, moverClone, vTentative));
					}
					
					if(hTentative.isValid()) {
						wallBackerList.add(new StateImpl(settingClone, moverClone, hTentative));
					}
				}
			}
			
			
			
			
		}
		
		@Override
		public boolean hasNext() {
			
			boolean result;
			
			if (wallBacker == null) {
				result = true;
			} else {
				result = wallBacker.hasNext();
			}
			
			return result;
		}

		@Override
		public StateImpl next() {
			StateImpl result;
			
			if (pawnBacker.hasNext()) {
				result = pawnBacker.next();
			} else {
				if (wallBacker == null) {
					wallBacker = wallBackerList.iterator();
				}
				result = wallBacker.next();
			}
			return result;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
		}
		
		
		
		
		
	}
	
	private class Node implements Iterable<Node> {
		protected Square square;
		protected int cost;
		protected Player owner;
		
		protected Node parent;
		
		public Node(Square s, int n, Player p, Node parent) {
			square = s;
			cost = n;
			owner  = p;
			this.parent = parent;
		}
		
		public Node getParent() {
			return parent;
		}
		
		public Square getSquare() {
			return square;
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
							
							
						Board settingClone = new BoardImpl((BoardImpl)setting);
						Player moverClone = new PlayerImpl(owner);
						
						
						
						settingClone.getPawn(moverClone, settingClone).setSquare(square);
						
						MovePawn tentative = new MovePawnImpl(square.getCol() + j, square.getRow() + i, moverClone, settingClone );
						
						
						
						if (tentative.isValid()) {
							
							nextList.add(new Node(new SquareImpl(square.getCol()+j, square.getRow() + i), n + 1, moverClone, start));
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
