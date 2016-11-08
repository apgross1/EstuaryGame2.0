package enums;

public enum Waves {
	WAVE_GAME1(1,0,0),
	WAVE_GAME3(2,0,0),
	CLUSTER_ONE(3,0,77),
	CLUSTER_TWO(4,78,155),
	CLUSTER_THREE(5,156, 233),
	CLUSTER_FOUR(6,234, 310),
	CLUSTER_FIVE(7,311, 388),
	CLUSTER_SIX(8,389,466),
	CLUSTER_SEVEN(9,467, 544),
	CLUSTER_EIGHT(10,545,622);

	
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
