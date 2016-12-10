package Enums;

import java.io.Serializable;

/**
 * @author Andrew
 * Enum class that refers to the wave clusters in which a wave particle could be placed. 
 */
public enum WaveClusters implements Serializable {
	CLUSTER_ONE(0,0,100),
	CLUSTER_TWO(1,101,200),
	CLUSTER_THREE(2,201, 300),
	CLUSTER_FOUR(3,301, 400),
	CLUSTER_FIVE(4,401, 500);
	

	
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
