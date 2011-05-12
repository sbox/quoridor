package quoridor;

import java.util.Iterator;

public class PairImpl <X> implements Pair <X> {
    
	protected X _1;
	protected X _2;

    public PairImpl(X _1, X _2) {
    	this._1 = _1;
    	this._2 = _2;
    }
	
    public X _1() {
        return _1;
    }

    public X _2() {
        return _2;
    }

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

    public boolean contains(X item) {
        return item.equals(_1) || item.equals(_2);
    }

    public boolean equals(Pair <X> p) {
    	return _1().equals(p._1()) && _2().equals(p._2());
    }
    
	@Override
	public Iterator <X> iterator() {
		return new PairIterator(this);
	}
	
	private class PairIterator implements Iterator <X> {
		
		protected Pair <X> pair;
		protected X current;
		
		private PairIterator(Pair <X> p) {
			pair = p;
			current = p._1();
		}
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

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

		@Override
		public void remove() {
		//we don't need this method
		}
		
	}

}
