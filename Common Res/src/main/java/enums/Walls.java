package enums;

public enum Walls {
	CONCRETE_GAME1(1),
	CONCRETE_GAME3(2),
	GABION_GAME1(3),
	GABION_GAME3(4);
	
	private final int value;
    private Walls(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
