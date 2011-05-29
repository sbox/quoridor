package quoridor;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;



public class StateImpl implements State {

	private static final boolean MAX = true;
	private static final boolean MIN = false;
	private static final int DEPTH =0;
	
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
	
	
	public int bfs(Player player) {
		
		Queue <Node> toVisit = new LinkedList <Node> ();
		
		Node current = new Node(setting.getPawn(player, setting).getSquare(), 0, player);
		
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
	
	
	public int bfsPri(Player player) {
		
		PriorityQueue <Node> toVisit = new PriorityQueue <Node> ();
		
		Node current = new Node(setting.getPawn(player, setting).getSquare(), 0, player);
		
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
	
	public int pathLength(Player player) {
		
		PriorityQueue <Node> unVisited = new PriorityQueue <Node> ();
		Hashtable <Integer, Node> lookup = new Hashtable <Integer, Node>();
		
		Node current = new Node(setting.getPawn(player, setting).getSquare(), 0, player);
		
		
		unVisited.add(current);
		lookup.put(new Integer(current.hashCode()), current);
		
		HashSet <Node> visited = new HashSet<Node>();
		
		while (current.getSquare().getRow() != destRow(player.goalEnd()) ) {
			current = unVisited.remove();
			
			visited.add(current);
			
			for (Node n : current) {
				
				if (!visited.contains(n)) {
					Node tent = lookup.get(new Integer(n.hashCode()));
					
					if (tent == null) {
						lookup.put(new Integer(n.hashCode()), n);
						unVisited.add(n);
						
					} else {
						if (tent.getHeuristic() > n.getHeuristic()) {
							tent.setCost(n.getCost());
						}
					}
					
					
				}
				
			}
			
			visited.add(current);
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
	

	
	public StateImpl bestNextState() {
		
		StateImpl best = null;
		int tmp;
		
		//for each next state
		for (StateImpl s : this) {
			s.nextMove.makeMove(); //apply the move
			
			System.out.println("start:");
			tmp = s.alphabetaNum(Integer.MIN_VALUE, Integer.MAX_VALUE, DEPTH);
			//System.out.println(s.setting + ":" + tmp);
			System.out.println("\nend.\n\n");
			
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
			System.out.print("+");
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
				
				
				tmp = s.alphabetaNum(alpha, beta, depth - 1);
				
				//System.out.println(s.setting + ":" + tmp);
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
	
	static List <StateImpl> wallBackerList = new LinkedList <StateImpl>();
	
	private class MoveGenerator implements Iterator <StateImpl> {

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
						
						//System.out.println(tentative);
						
						pawnBackerList.add(new StateImpl(settingClone, moverClone, tentative));
					}
				}
			}
			
			//populate with PlaceWalls
			if (wallBackerList.isEmpty()) {
				for (int i = 0; i <= 8; i++) {
					for (int j = 0; j <= 8;j++) {
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
	
	private class Node implements Comparable<Node>, Iterable<Node> {
		protected Square square;
		protected int cost;
		protected int heuristic;
		
		public Node(Square s, int n, Player p) {
			square = s;
			cost = n;
			
			heuristic = Math.abs(destRow(p.goalEnd()) - s.getRow());
		}
		
		public Square getSquare() {
			return square;
		}
		
		public int getHeuristic() {
			return heuristic + cost;
		}
		
		public int getCost() {
			return cost;
		}
		
		public void setCost(int cost) {
			this.cost  = cost;
		}

		@Override
		public int compareTo(Node o) {
			return getHeuristic() - o.getHeuristic();
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
							
							//System.out.println(settingClone);
							
							settingClone.getPawn(moverClone, settingClone).setSquare(square);
							
							MovePawn tentative = new MovePawnImpl(square.getCol() + j, square.getRow() + i, moverClone, settingClone );
							
							
							if (tentative.isValid()) {
								nextList.add(new Node(new SquareImpl(square.getCol()+j, square.getRow() + i), n + 1, moverClone));
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
