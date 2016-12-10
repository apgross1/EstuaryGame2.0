package enums;

/**
 * @author Andrew
 * Enum class for gabion/concrete walls to be used in each game
 */
public enum Walls {
	CONCRETE_GAME1(0),
	CONCRETE_GAME3(1),
	GABION_GAME1(2),
	GABION_GAME3(3);
	
	private final int value;
    private Walls(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
