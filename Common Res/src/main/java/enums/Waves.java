package enums;

public enum Waves {
	WAVE_GAME1(1),
	WAVE_GAME3(2);
	
	private final int value;
    private Waves(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
