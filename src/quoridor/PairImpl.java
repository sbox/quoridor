package quoridor;

import java.util.Iterator;

/**
 * 
 * A pair of objects of the same class
 * 
 * @author Stephen Sherratt
 *
 * @param <X>
 * 			The class of object to be stored in the pair
 */
public class PairImpl <X> implements Pair <X> {
    
	//the two objects
	protected X _1;
	protected X _2;

	/**
	 * Constructs a pair from the two items it will contain
	 * @param _1
	 * 			The first item in the pair
	 * @param _2
	 * 			The second item in the pair
	 */
    public PairImpl(X _1, X _2) {
    	this._1 = _1;
    	this._2 = _2;
    }
	
    /**
     * Returns the first item in the pair
     * @return the first item in the pair
     */
    public X _1() {
        return _1;
    }

    /**
     * Returns the second item in the pair
     * @return the second item in the pair
     */
    public X _2() {
        return _2;
    }

    /**
     * Given an item, this returns the other item in the pair.
     * @param item
     * 			The item other than the item to be returned
     * @return the item in the pair other than the argument
     */
    public X other(X item) {
        
    	assert(item.equals(_1) || item.equals(_2));
    	
    	X result;
        
        if (item.equals(_1)) {
        	result = _2;
        } else {
        	result = _1;
        }
    	
    	return result;
    }

    /**
     * Returns true iff the item is in the pair
     * @param item
     * 			the item we are checking for
     * @return true iff the item is in the pair
     */
    public boolean contains(X item) {
        return item.equals(_1) || item.equals(_2);
    }

    /**
     * Two pairs are equal if their items are in the same order
     * and are the same.
     * @param p
     * 			the pair with which we are comparing this
     * @return
     * 			true iff the two pairs are equal
     */
    public boolean equals(Pair <X> p) {
    	return _1().equals(p._1()) && _2().equals(p._2());
    }
    
    /**
     * Creates and returns an iterator for the pair
     * @return an iterator for the pair
     */
	@Override
	public Iterator <X> iterator() {
		return new PairIterator(this);
	}
	
	/**
	 * 
	 * An iterator for the pair. Although a pair only contains two
	 * items, it is convenient to be able to quickly access each
	 * item.
	 * 
	 * @author Stephen Sherratt
	 *
	 */
	private class PairIterator implements Iterator <X> {
		
		protected Pair <X> pair;
		protected X current;
		
		/**
		 * Constructs an iterator object for a pair
		 * @param p
		 * 			the pair for which we are creating an iterator
		 */
		private PairIterator(Pair <X> p) {
			pair = p;
			current = p._1();
		}
		
		/**
		 * Returns true iff there is at least one more item not yet
		 * visited by the iterator
		 * @return true iff there is at least one more item not yet
		 * visited by the iterator
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * Returns the next unvisited item in the list
		 * @return the next unvisited item in the list
		 */
		@Override
		public X next() {
			assert(hasNext());
			X result = current;
			
			if (current == pair._1()) {
				current = pair._2();
			} else {
				current = null;
			}
			
			return result;
		}
		
		/**
		 * (not implemented)
		 */
		@Override
		public void remove() {
		//we don't need this method
		}
		
	}

}
