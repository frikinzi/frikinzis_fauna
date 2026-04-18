package com.frikinzi.creatures.client.gui;

import java.util.List;
import java.util.Map;

public class CreaturesCategories {
    public static final Map<String, List<String>> CATEGORIES = Map.of(
        "raptors", List.of("goldeneagle", "stellersseaeagle", "redkite",
                           "gyrfalcon", "pygmyfalcon", "osprey", "barnowl", "eagleowl", "secretarybird"),
        "gulls", List.of("seagull"),
        "corvids", List.of("raven", "magpie"),
        "storks", List.of("stork", "marabou")
    );
}