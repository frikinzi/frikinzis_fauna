package com.frikinzi.creatures.client.gui;

import net.minecraft.network.chat.Component;

import java.util.Random;
public enum BankQuestion {
    FISH_EVOL(
            Component.translatable("quiz.creatures.fish_evol.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.fish_evol.option.0"),
                    Component.translatable("quiz.creatures.fish_evol.option.1"),
                    Component.translatable("quiz.creatures.fish_evol.option.2"),
                    Component.translatable("quiz.creatures.fish_evol.option.3")},
            Component.translatable("quiz.creatures.fish_evol.option.0"),
            Component.translatable("quiz.creatures.fish_evol.explanation")),
    SPOONBILL_PINK(
            Component.translatable("quiz.creatures.spoonbill_pink.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.spoonbill_pink.option.0"),
                    Component.translatable("quiz.creatures.spoonbill_pink.option.1"),
                    Component.translatable("quiz.creatures.spoonbill_pink.option.2"),
                    Component.translatable("quiz.creatures.spoonbill_pink.option.3")},
            Component.translatable("quiz.creatures.spoonbill_pink.option.0"),
            Component.translatable("quiz.creatures.spoonbill_pink.explanation")),
    DINOSAUR(
            Component.translatable("quiz.creatures.dinosaur.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.dinosaur.option.0"),
                    Component.translatable("quiz.creatures.dinosaur.option.1")},
            Component.translatable("quiz.creatures.dinosaur.option.0"),
            Component.translatable("quiz.creatures.dinosaur.explanation")),
    PARROT_TOX(
            Component.translatable("quiz.creatures.parrot_tox.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.parrot_tox.option.0"),
                    Component.translatable("quiz.creatures.parrot_tox.option.1"),
                    Component.translatable("quiz.creatures.parrot_tox.option.2"),
                    Component.translatable("quiz.creatures.parrot_tox.option.3")},
            Component.translatable("quiz.creatures.parrot_tox.option.0"),
            Component.translatable("quiz.creatures.parrot_tox.explanation")),
    RELATIVE(
            Component.translatable("quiz.creatures.relative.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.relative.option.0"),
                    Component.translatable("quiz.creatures.relative.option.1"),
                    Component.translatable("quiz.creatures.relative.option.2"),
                    Component.translatable("quiz.creatures.relative.option.3")},
            Component.translatable("quiz.creatures.relative.option.0"),
            Component.translatable("quiz.creatures.relative.explanation")),
    WINDOW_DEATHS(
            Component.translatable("quiz.creatures.window_deaths.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.window_deaths.option.0"),
                    Component.translatable("quiz.creatures.window_deaths.option.1"),
                    Component.translatable("quiz.creatures.window_deaths.option.2"),
                    Component.translatable("quiz.creatures.window_deaths.option.3")},
            Component.translatable("quiz.creatures.window_deaths.option.3"),
            Component.translatable("quiz.creatures.window_deaths.explanation")),
    CAT_DEATHS(
            Component.translatable("quiz.creatures.cat_deaths.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.cat_deaths.option.0"),
                    Component.translatable("quiz.creatures.cat_deaths.option.1"),
                    Component.translatable("quiz.creatures.cat_deaths.option.2"),
                    Component.translatable("quiz.creatures.cat_deaths.option.3")},
            Component.translatable("quiz.creatures.cat_deaths.option.3"),
            Component.translatable("quiz.creatures.cat_deaths.explanation")),
    VULTURE_BALD(
            Component.translatable("quiz.creatures.vulture_bald.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.vulture_bald.option.0"),
                    Component.translatable("quiz.creatures.vulture_bald.option.1"),
                    Component.translatable("quiz.creatures.vulture_bald.option.2"),
                    Component.translatable("quiz.creatures.vulture_bald.option.3")},
            Component.translatable("quiz.creatures.vulture_bald.option.0"),
            Component.translatable("quiz.creatures.vulture_bald.explanation")),
    TRAIN_INSPIRATION(
            Component.translatable("quiz.creatures.train_inspiration.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.train_inspiration.option.0"),
                    Component.translatable("quiz.creatures.train_inspiration.option.1"),
                    Component.translatable("quiz.creatures.train_inspiration.option.2"),
                    Component.translatable("quiz.creatures.train_inspiration.option.3")},
            Component.translatable("quiz.creatures.train_inspiration.option.0"),
            Component.translatable("quiz.creatures.train_inspiration.explanation")),
    DICKIN_MEDAL(
            Component.translatable("quiz.creatures.dickin_medal.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.dickin_medal.option.0"),
                    Component.translatable("quiz.creatures.dickin_medal.option.1"),
                    Component.translatable("quiz.creatures.dickin_medal.option.2"),
                    Component.translatable("quiz.creatures.dickin_medal.option.3")},
            Component.translatable("quiz.creatures.dickin_medal.option.0"),
            Component.translatable("quiz.creatures.dickin_medal.explanation")),
    DODO(
            Component.translatable("quiz.creatures.dodo.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.dodo.option.0"),
                    Component.translatable("quiz.creatures.dodo.option.1"),
                    Component.translatable("quiz.creatures.dodo.option.2"),
                    Component.translatable("quiz.creatures.dodo.option.3")},
            Component.translatable("quiz.creatures.dodo.option.0"),
            Component.translatable("quiz.creatures.dodo.explanation")),
    DANGEROUS(
            Component.translatable("quiz.creatures.dangerous.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.dangerous.option.0"),
                    Component.translatable("quiz.creatures.dangerous.option.1"),
                    Component.translatable("quiz.creatures.dangerous.option.2"),
                    Component.translatable("quiz.creatures.dangerous.option.3")},
            Component.translatable("quiz.creatures.dangerous.option.0"),
            Component.translatable("quiz.creatures.dangerous.explanation")),
    MOURNING_DOVE(
            Component.translatable("quiz.creatures.mourning_dove.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.mourning_dove.option.0"),
                    Component.translatable("quiz.creatures.mourning_dove.option.1"),
                    Component.translatable("quiz.creatures.mourning_dove.option.2"),
                    Component.translatable("quiz.creatures.mourning_dove.option.3")},
            Component.translatable("quiz.creatures.mourning_dove.option.1"),
            Component.translatable("quiz.creatures.mourning_dove.explanation")),
    CRAB(
            Component.translatable("quiz.creatures.crab.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.crab.option.0"),
                    Component.translatable("quiz.creatures.crab.option.1"),
                    Component.translatable("quiz.creatures.crab.option.2"),
                    Component.translatable("quiz.creatures.crab.option.3")},
            Component.translatable("quiz.creatures.crab.option.0"),
            Component.translatable("quiz.creatures.crab.explanation")),
    MANTIS_SHRIMP(
            Component.translatable("quiz.creatures.mantis_shrimp.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.mantis_shrimp.option.0"),
                    Component.translatable("quiz.creatures.mantis_shrimp.option.1")},
            Component.translatable("quiz.creatures.mantis_shrimp.option.0"),
            Component.translatable("quiz.creatures.mantis_shrimp.explanation")),
    MANTIS_SHRIMP2(
            Component.translatable("quiz.creatures.mantis_shrimp2.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.mantis_shrimp2.option.0"),
                    Component.translatable("quiz.creatures.mantis_shrimp2.option.1")},
            Component.translatable("quiz.creatures.mantis_shrimp2.option.1"),
            Component.translatable("quiz.creatures.mantis_shrimp2.explanation")),
    TARANTULA_DEATHS(
            Component.translatable("quiz.creatures.tarantula_deaths.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.tarantula_deaths.option.0"),
                    Component.translatable("quiz.creatures.tarantula_deaths.option.1"),
                    Component.translatable("quiz.creatures.tarantula_deaths.option.2"),
                    Component.translatable("quiz.creatures.tarantula_deaths.option.3")},
            Component.translatable("quiz.creatures.tarantula_deaths.option.0"),
            Component.translatable("quiz.creatures.tarantula_deaths.explanation")),
    OLD_WORLD(
            Component.translatable("quiz.creatures.old_world.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.old_world.option.0"),
                    Component.translatable("quiz.creatures.old_world.option.1"),
                    Component.translatable("quiz.creatures.old_world.option.2"),
                    Component.translatable("quiz.creatures.old_world.option.3")},
            Component.translatable("quiz.creatures.old_world.option.0"),
            Component.translatable("quiz.creatures.old_world.explanation")),
    VENOMOUS(
            Component.translatable("quiz.creatures.venomous.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.venomous.option.0"),
                    Component.translatable("quiz.creatures.venomous.option.1")},
            Component.translatable("quiz.creatures.venomous.option.0"),
            Component.translatable("quiz.creatures.venomous.explanation")),
    PEAFOWL_ENDANGERED(
            Component.translatable("quiz.creatures.peafowl_endangered.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.peafowl_endangered.option.0"),
                    Component.translatable("quiz.creatures.peafowl_endangered.option.1"),
                    Component.translatable("quiz.creatures.peafowl_endangered.option.2"),
                    Component.translatable("quiz.creatures.peafowl_endangered.option.3")},
            Component.translatable("quiz.creatures.peafowl_endangered.option.1"),
            Component.translatable("quiz.creatures.peafowl_endangered.explanation")),
    KAKAPO_GROUND(
            Component.translatable("quiz.creatures.kakapo_ground.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.kakapo_ground.option.0"),
                    Component.translatable("quiz.creatures.kakapo_ground.option.1")},
            Component.translatable("quiz.creatures.kakapo_ground.option.0"),
            Component.translatable("quiz.creatures.kakapo_ground.explanation")),
    GUPPY(
            Component.translatable("quiz.creatures.guppy.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.guppy.option.0"),
                    Component.translatable("quiz.creatures.guppy.option.1")},
            Component.translatable("quiz.creatures.guppy.option.1"),
            Component.translatable("quiz.creatures.guppy.explanation")),
    INVASIVE_SPECIES(
            Component.translatable("quiz.creatures.invasive_species.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.invasive_species.option.0"),
                    Component.translatable("quiz.creatures.invasive_species.option.1"),
                    Component.translatable("quiz.creatures.invasive_species.option.2"),
                    Component.translatable("quiz.creatures.invasive_species.option.3")},
            Component.translatable("quiz.creatures.invasive_species.option.0"),
            Component.translatable("quiz.creatures.invasive_species.explanation")),
    MANDARIN_DUCK(
            Component.translatable("quiz.creatures.mandarin_duck.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.mandarin_duck.option.0"),
                    Component.translatable("quiz.creatures.mandarin_duck.option.1")},
            Component.translatable("quiz.creatures.mandarin_duck.option.1"),
            Component.translatable("quiz.creatures.mandarin_duck.explanation")),
    SYSTEMATICS(
            Component.translatable("quiz.creatures.systematics.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.systematics.option.0"),
                    Component.translatable("quiz.creatures.systematics.option.1"),
                    Component.translatable("quiz.creatures.systematics.option.2"),
                    Component.translatable("quiz.creatures.systematics.option.3")},
            Component.translatable("quiz.creatures.systematics.option.0"),
            Component.translatable("quiz.creatures.systematics.explanation")),
    SYSTEMATICS2(
            Component.translatable("quiz.creatures.systematics2.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.systematics2.option.0"),
                    Component.translatable("quiz.creatures.systematics2.option.1"),
                    Component.translatable("quiz.creatures.systematics2.option.2"),
                    Component.translatable("quiz.creatures.systematics2.option.3")},
            Component.translatable("quiz.creatures.systematics2.option.0"),
            Component.translatable("quiz.creatures.systematics2.explanation")),
    ISOLATION(
            Component.translatable("quiz.creatures.isolation.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.isolation.option.0"),
                    Component.translatable("quiz.creatures.isolation.option.1"),
                    Component.translatable("quiz.creatures.isolation.option.2"),
                    Component.translatable("quiz.creatures.isolation.option.3")},
            Component.translatable("quiz.creatures.isolation.option.0"),
            Component.translatable("quiz.creatures.isolation.explanation")),
    GEOGRAPHICAL(
            Component.translatable("quiz.creatures.geographical.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.geographical.option.0"),
                    Component.translatable("quiz.creatures.geographical.option.1"),
                    Component.translatable("quiz.creatures.geographical.option.2"),
                    Component.translatable("quiz.creatures.geographical.option.3")},
            Component.translatable("quiz.creatures.geographical.option.1"),
            Component.translatable("quiz.creatures.geographical.explanation")),
    TEMPERATURE(
            Component.translatable("quiz.creatures.temperature.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.temperature.option.0"),
                    Component.translatable("quiz.creatures.temperature.option.1"),
                    Component.translatable("quiz.creatures.temperature.option.2"),
                    Component.translatable("quiz.creatures.temperature.option.3")},
            Component.translatable("quiz.creatures.temperature.option.0"),
            Component.translatable("quiz.creatures.temperature.explanation")),
    BODY_SIZE(
            Component.translatable("quiz.creatures.body_size.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.body_size.option.0"),
                    Component.translatable("quiz.creatures.body_size.option.1"),
                    Component.translatable("quiz.creatures.body_size.option.2"),
                    Component.translatable("quiz.creatures.body_size.option.3")},
            Component.translatable("quiz.creatures.body_size.option.1"),
            Component.translatable("quiz.creatures.body_size.explanation")),
    CHICKADEE(
            Component.translatable("quiz.creatures.chickadee.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.chickadee.option.0"),
                    Component.translatable("quiz.creatures.chickadee.option.1"),
                    Component.translatable("quiz.creatures.chickadee.option.2"),
                    Component.translatable("quiz.creatures.chickadee.option.3")},
            Component.translatable("quiz.creatures.chickadee.option.0"),
            Component.translatable("quiz.creatures.chickadee.explanation")),
    LEAVES(
            Component.translatable("quiz.creatures.leaves.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.leaves.option.0"),
                    Component.translatable("quiz.creatures.leaves.option.1"),
                    Component.translatable("quiz.creatures.leaves.option.2"),
                    Component.translatable("quiz.creatures.leaves.option.3")},
            Component.translatable("quiz.creatures.leaves.option.0"),
            Component.translatable("quiz.creatures.leaves.explanation")),
    HEART(
            Component.translatable("quiz.creatures.heart.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.heart.option.0"),
                    Component.translatable("quiz.creatures.heart.option.1"),
                    Component.translatable("quiz.creatures.heart.option.2"),
                    Component.translatable("quiz.creatures.heart.option.3")},
            Component.translatable("quiz.creatures.heart.option.0"),
            Component.translatable("quiz.creatures.heart.explanation")),
    BROOD_PARASITE(
            Component.translatable("quiz.creatures.brood_parasite.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.brood_parasite.option.0"),
                    Component.translatable("quiz.creatures.brood_parasite.option.1")},
            Component.translatable("quiz.creatures.brood_parasite.option.1"),
            Component.translatable("quiz.creatures.brood_parasite.explanation")),
    KLEPTO(
            Component.translatable("quiz.creatures.klepto.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.klepto.option.0"),
                    Component.translatable("quiz.creatures.klepto.option.1"),
                    Component.translatable("quiz.creatures.klepto.option.2"),
                    Component.translatable("quiz.creatures.klepto.option.3")},
            Component.translatable("quiz.creatures.klepto.option.0"),
            Component.translatable("quiz.creatures.klepto.explanation")),
    DENATURE(
            Component.translatable("quiz.creatures.denature.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.denature.option.0"),
                    Component.translatable("quiz.creatures.denature.option.1"),
                    Component.translatable("quiz.creatures.denature.option.2"),
                    Component.translatable("quiz.creatures.denature.option.3")},
            Component.translatable("quiz.creatures.denature.option.0"),
            Component.translatable("quiz.creatures.denature.explanation")),
    KAKAPO2(
            Component.translatable("quiz.creatures.kakapo2.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.kakapo2.option.0"),
                    Component.translatable("quiz.creatures.kakapo2.option.1"),
                    Component.translatable("quiz.creatures.kakapo2.option.2"),
                    Component.translatable("quiz.creatures.kakapo2.option.3")},
            Component.translatable("quiz.creatures.kakapo2.option.2"),
            Component.translatable("quiz.creatures.kakapo2.explanation")),
    GENE(
            Component.translatable("quiz.creatures.gene.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.gene.option.0"),
                    Component.translatable("quiz.creatures.gene.option.1"),
                    Component.translatable("quiz.creatures.gene.option.2"),
                    Component.translatable("quiz.creatures.gene.option.3")},
            Component.translatable("quiz.creatures.gene.option.0"),
            Component.translatable("quiz.creatures.gene.explanation")),
    GENOME(
            Component.translatable("quiz.creatures.genome.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.genome.option.0"),
                    Component.translatable("quiz.creatures.genome.option.1")},
            Component.translatable("quiz.creatures.genome.option.0"),
            Component.translatable("quiz.creatures.genome.explanation")),
    BIRD_GUT(
            Component.translatable("quiz.creatures.bird_gut.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.bird_gut.option.0"),
                    Component.translatable("quiz.creatures.bird_gut.option.1"),
                    Component.translatable("quiz.creatures.bird_gut.option.2"),
                    Component.translatable("quiz.creatures.bird_gut.option.3")},
            Component.translatable("quiz.creatures.bird_gut.option.0"),
            Component.translatable("quiz.creatures.bird_gut.explanation")),
    KOI_GOLDFISH(
            Component.translatable("quiz.creatures.koi_goldfish.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.koi_goldfish.option.0"),
                    Component.translatable("quiz.creatures.koi_goldfish.option.1")},
            Component.translatable("quiz.creatures.koi_goldfish.option.1"),
            Component.translatable("quiz.creatures.koi_goldfish.explanation")),
    EMU_EGG(
            Component.translatable("quiz.creatures.emu_egg.question"),
            new Component[]{
                    Component.translatable("quiz.creatures.emu_egg.option.0"),
                    Component.translatable("quiz.creatures.emu_egg.option.1"),
                    Component.translatable("quiz.creatures.emu_egg.option.2"),
                    Component.translatable("quiz.creatures.emu_egg.option.3")},
            Component.translatable("quiz.creatures.emu_egg.option.0"),
            Component.translatable("quiz.creatures.emu_egg.explanation"));
    public final Component questionText;
    public final Component[] options;
    public final Component correctString;
    public final Component explanation;

    BankQuestion(Component questionText, Component[] options, Component correctString, Component explanation) {
        this.questionText = questionText;
        this.options = options;
        this.correctString = correctString;
        this.explanation = explanation;
    }

    public String[] getShuffledOptions(Random random) {
        String[] strings = new String[options.length];
        for (int i = 0; i < options.length; i++) {
            strings[i] = options[i].getString();
        }
        for (int i = strings.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String tmp = strings[i];
            strings[i] = strings[j];
            strings[j] = tmp;
        }
        return strings;
    }

    public int getShuffledCorrectIndex(String[] shuffled) {
        String correct = correctString.getString();
        for (int i = 0; i < shuffled.length; i++) {
            if (shuffled[i].equals(correct)) return i;
        }
        return 0;
    }

    public static BankQuestion getRandom() {
        BankQuestion[] values = values();
        return values[new Random().nextInt(values.length)];
    }
    }