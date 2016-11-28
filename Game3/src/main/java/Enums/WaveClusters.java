package Enums;

public enum WaveClusters {
	CLUSTER_ONE(2,0,100),
	CLUSTER_TWO(3,101,200),
	CLUSTER_THREE(4,201, 300),
	CLUSTER_FOUR(5,301, 400),
	CLUSTER_FIVE(6,401, 500),
	CLUSTER_SIX(7,501,600),
	CLUSTER_SEVEN(8,601, 700);
	

	
	private final int waveID;
	private final int minY;
	private final int maxY;
	
	
	private WaveClusters(int waveID, int minY, int maxY) {
	    	this.waveID = waveID;
	    	this.minY = minY;
	    	this.maxY = maxY;
	    	
	}

    public int getWaveID() {
        return waveID;
    }
    public int getMinY() {
    	return minY;
    }
    public int getMaxY() {
    	return maxY;
    }
}
