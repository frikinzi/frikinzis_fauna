package com.creatures.afrikinzi.init;
import com.creatures.afrikinzi.objects.items.CreaturesFoodItem;
import com.creatures.afrikinzi.objects.items.CreaturesItem;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item DUCK_FEATHER = new CreaturesItem("duck_feather");
    public static final Item RAW_KOI = new CreaturesFoodItem("raw_koi", 1, false);
    public static final Item ROASTED_KOI = new CreaturesFoodItem("roasted_koi", 5, false);
    public static final Item RAW_SHRIMP = new CreaturesFoodItem("raw_shrimp", 1, false);
    public static final Item COOKED_SHRIMP = new CreaturesFoodItem("cooked_shrimp", 3, false);
    public static final Item RAW_PIKE = new CreaturesFoodItem("raw_pike", 2, false);
    public static final Item COOKED_PIKE = new CreaturesFoodItem("cooked_pike", 6, false);
    public static final Item RAW_AROWANA = new CreaturesFoodItem("raw_arowana", 2, false);
    public static final Item RAW_GOURAMI = new CreaturesFoodItem("raw_gourami", 1, false);
    public static final Item COOKED_AROWANA = new CreaturesFoodItem("cooked_arowana", 1, false);
    public static final Item RAW_LARGE_WILD_BIRD_MEAT = new CreaturesFoodItem("raw_large_wild_bird_meat", 2, true);
    public static final Item ROASTED_LARGE_WILD_BIRD_MEAT = new CreaturesFoodItem("roasted_large_wild_bird_meat", 7, true);
    public static final Item RAW_SMALL_WILD_BIRD_MEAT = new CreaturesFoodItem("raw_small_wild_bird_meat", 1, true);
    public static final Item ROASTED_SMALL_WILD_BIRD_MEAT = new CreaturesFoodItem("roasted_small_wild_bird_meat", 4, true);
    public static final Item GUPPY_TAIL = new CreaturesItem("guppy_tail");
    public static final Item BIRD_OF_PREY_FEATHER = new CreaturesItem("bird_of_prey_feather");
    public static final Item RAVEN_FEATHER = new CreaturesItem("raven_feather");
    public static final Item PARROT_FEATHER = new CreaturesItem("parrot_feather");
    public static final Item CRAB_PINCERS = new CreaturesItem("crab_pincers");
}
