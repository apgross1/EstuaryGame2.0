package enums;

public enum Waves {
	WAVE_GAME1(1,0,0),
	WAVE_GAME3(2,0,0),
	CLUSTER_ONE(3,0,100),
	CLUSTER_TWO(4,101,200),
	CLUSTER_THREE(5,201, 300),
	CLUSTER_FOUR(6,301, 400),
	CLUSTER_FIVE(7,401, 500),
	CLUSTER_SIX(8,501,600),
	CLUSTER_SEVEN(9,601, 700);
	

	
	private final int waveID;
	private final int minY;
	private final int maxY;
	
	
private Waves(int waveID, int minY, int maxY) {
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
