package org.group62.Model;

import java.util.HashMap;

public enum CastlesEnum {
    KEEP("Keep", 0, new HashMap<Resource, Integer>(), 0, new HashMap<Constant, Integer>()),
    SMALL_STONE_GATEHOUSE("Small stone gatehouse", 0, new HashMap<Resource, Integer>(), 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 3000);
    }}),
    BIG_STONE_GATEHOUSE("Big stone gatehouse", 0, new HashMap<Resource, Integer>() {{
        put(Resource.STONE, 20);
    }}, 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 4000);
    }}),
    DRAW_BRIDGE("Draw bridge", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 10);
    }}, 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 3000);
    }}),
    LOOKOUT_TOWER("Lookout tower", 0, new HashMap<Resource, Integer>() {{
        put(Resource.STONE, 10);
    }}, 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 3000);
        put(Constant.FIRE_RANGE, 15);
        put(Constant.DEFEND_RANGE, 15);
    }}),
    PERIMETER_TOWER("Perimeter tower", 0, new HashMap<Resource, Integer>() {{
        put(Resource.STONE, 10);
    }}, 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 3000);
        put(Constant.FIRE_RANGE, 10);
        put(Constant.DEFEND_RANGE, 10);
    }}),
    DEFENSIVE_TURRET("Defensive turret", 0, new HashMap<Resource, Integer>() {{
        put(Resource.STONE, 15);
    }}, 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 3500);
        put(Constant.FIRE_RANGE, 10);
        put(Constant.DEFEND_RANGE, 10);
    }}),
    SQUARE_TOWER("Square tower", 0, new HashMap<Resource, Integer>() {{
        put(Resource.STONE, 35);
    }}, 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 3500);
        put(Constant.FIRE_RANGE, 10);
        put(Constant.DEFEND_RANGE, 10);
    }}),
    CIRCULAR_TOWER("Circular tower", 0, new HashMap<Resource, Integer>() {{
        put(Resource.STONE, 40);
    }}, 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 5000);
        put(Constant.FIRE_RANGE, 10);
        put(Constant.DEFEND_RANGE, 10);
    }}),
    ARMOURY("Armoury", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 5);
    }}, 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 5000);
        put(Constant.CAPACITY, 50);
    }}),
    BARRACK("Barrack", 0, new HashMap<Resource, Integer>() {{
        put(Resource.STONE, 15);
    }}, 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
    }}),
    MERCENARY_POST("Mercenary post", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 10);
    }}, 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
    }}),
    ENGINEER_GUILD("Engineer guild", 100, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 10);
    }}, 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
    }}),
    KILLING_PIT("Killing pit", 0, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 6);
    }}, 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 100);
        put(Constant.DAMAGE, 1200);
    }}),
    OIL_SMELTER("Oil smelter", 100, new HashMap<Resource, Integer>() {{
        put(Resource.IRON, 10);
    }}, 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 1000);
        put(Constant.RATE, 4);
    }}),
    PITCH_DITCH("Pitch ditch", 0, new HashMap<Resource, Integer>() {{
        put(Resource.PITCH, 1);
    }}, 0, new HashMap<Constant, Integer>()),
    CAGED_WAR_DOGS("Caged war dogs", 100, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 10);
    }}, 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 600);
    }}),
    SIEGE_TENT("Siege tent", 0, new HashMap<Resource, Integer>()
            , 1, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 100);
    }}),
    STABLE("Stable", 400, new HashMap<Resource, Integer>() {{
        put(Resource.WOOD, 20);
    }}, 0, new HashMap<Constant, Integer>() {{
        put(Constant.HP, 2000);
        put(Constant.RATE, 4);
    }});
    private final String name;
    private final int goldCost;
    private HashMap<Resource, Integer> resourcesCost = new HashMap<>();
    private int workersNumber;
    private HashMap<Constant, Integer> constants = new HashMap<>();

    private CastlesEnum(String name, int goldCost, HashMap<Resource, Integer> resourcesCost, int workersNumber, HashMap<Constant, Integer> constants) {
        this.name = name;
        this.goldCost = goldCost;
        this.resourcesCost = resourcesCost;
        this.workersNumber = workersNumber;
        this.constants = constants;
    }

    public static Castles getCastles(CastlesEnum castlesEnum) {
        return new Castles(Play.getCurrentUser(), castlesEnum.name, castlesEnum.goldCost, castlesEnum.resourcesCost, castlesEnum.workersNumber, castlesEnum.constants);
    }
}
