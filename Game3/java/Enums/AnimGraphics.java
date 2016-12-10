package Enums;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Andrew
 *Enum class that refers to the graphics used in the game
 */
public enum AnimGraphics implements Serializable {
	BLUECRAB_0(0),
	BLUECRAB_1(1),
	BLUECRAB_2(2),
	CONCRETE_WALL(3),
	CONC_PU(4),
	GABION_WALL(5),
	GAB_PU(6),
	TILE_SAND_CENTER(7),
	SUN(8),
	HURRICANE_ANGRY(9),
	HURRICANE_SCARED(10),
	KEY_PIC_0(11),
	KEY_PIC_1(12),
	KEY_PIC_2(13),
	KEY_PIC_3(14),
	KEY_PIC_4(15),
	BIG_X(16),
	ARROW(17),
	DIALOGUE(18),
	SAND_WITH_WATER(19),
	SAND_WITH_WATER_CENTER(20),
	SHORE(21),
	EXIT_GAME_0(22),
	EXIT_GAME_1(23),
	RETURN_MAIN_0(24),
	RETURN_MAIN_1(25);
	
   
    private int graphicNum;

    private static Map<Integer, AnimGraphics> map = new HashMap<Integer, AnimGraphics>();

    static {
        for (AnimGraphics anim : AnimGraphics.values()) {
            map.put(anim.graphicNum, anim);
        }
    }

    private AnimGraphics(final int num) { 
    	graphicNum = num; 
	}

    public static AnimGraphics valueOf(int anim) {
        return map.get(anim);
    }
    
    public int getVal() {
    	return graphicNum;
    }
}