package maximal.matching.graph.algorithms.model;

public enum Layer {
	
	INNER, OUTER;

	public boolean isInner() {
		return this == INNER;
	}
	
	// It's redundant, but its ok !
	public boolean isOuter() {
		return this == OUTER;
	}
	
	public Layer other() {
		if (this == INNER)
			return OUTER;
		else 
			return INNER;
	}
	
}
