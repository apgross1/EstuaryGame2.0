package enums;

public enum Waves {
	WAVE_GAME1(0,0,0),
	WAVE_GAME3(2,0,0);
	
	private final int waveID;
	
	private Waves(int waveID, int minY, int maxY) {
    	this.waveID = waveID;
    }

    public int getWaveID() {
        return waveID;
    }
}
