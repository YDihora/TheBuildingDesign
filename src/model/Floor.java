package model;

public class Floor {
	private Unit[] units;
	private int nou; // number of units
	private int maxCapacity; // in square feet
	private final int MAX_NUM_OF_UNITS =20;
	
	public Floor(int maxCapacity) {
		this.maxCapacity = maxCapacity;
		this.units = new Unit[MAX_NUM_OF_UNITS];
		this.nou = 0;
	}
	public Floor (Floor other) {
		this(other.maxCapacity);
		for(int i=0;i<other.nou;i++) {
			//Exercise: replace the assignment source by a call to the copy constructor of Unit.
			this.units[i] = other.units[i];
		}
		this.nou = other.nou;
		
	}
	public void addUnit(String function, int width, int length) throws InsufficientFloorSpaceException {
		
		
		if(this.maxCapacity-this.getUtilizedSpace()-(width*length)<0) {
			throw new InsufficientFloorSpaceException("Error.....");
		}
		
		else {
			Unit u =new Unit(function,width,length);
			this.units[this.nou] =u;
			this.nou++;
		}
		
	}
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		else if(obj ==null||this.getClass()!=obj.getClass()) {
			return false;
		}
		Floor other = (Floor) obj;
		boolean equal = this.maxCapacity == other.maxCapacity;
		if(equal) {
			for(int i=0;equal&&i<this.nou;i++) {
				Unit u = this.units[i];
				equal = this.numberOfEqualUnits(u) == other.numberOfEqualUnits(u);
			}
		}
		return equal;
	}
	
	
	public String toString() {
		// Floor's utilized space is 144 sq ft (356 sq ft remaining): [Master Bedroom: 144 sq ft (18' by 8')]
		String list = "[";
		for(int i =0; i<this.nou;i++) {
			Unit u = this.units[i];
			list+=String.format("%s: %d sq ft (%d' by %d')",
					u.getFunction(),
					u.getAreaInSquareFeet(),
					u.getWidth(),
					u.getLength()); 
			
			if(i<this.nou-1) {
				list+=", ";
			}
		}
		list+="]";
		
		int utilizedSpace = this.getUtilizedSpace();
		String result = String.format("Floor's utilized space is %d sq ft (%d sq ft remaining): %s",
				utilizedSpace,
				this.maxCapacity-utilizedSpace,
				list);
		
		return result;
	}
	public int getUtilizedSpace() {
		int total=0;
		for(int i=0;i<this.nou;i++) {
			total += this.units[i].getAreaInSquareFeet();
		}
		
		return total;
	}
	
	public int numberOfEqualUnits(Unit u) {
		int result=0;
		for (int i =0;i<this.nou;i++) {
			if(this.units[i].equals(u)) {
				result++;
			}
		}
		return result;
	}
	

}
