package com.frikinzi.creatures.client.gui;

import java.util.Random;
public enum BankQuestion {
    FISH_EVOL(
            "Fish are a ___ group.",
                    new String[]{"Paraphyletic", "Monophyletic", "Polyphyletic", "Divergent"},
            "Paraphyletic", "Fish are a paraphyletic group because they include the common ancestor of all fish but not all of its descendants (e.g. tetrapods)."),
    SPOONBILL_PINK(
            "Why are spoonbills pink?",
            new String[]{"Because of carotenoids in their diet", "Because of iron oxide in their diet", "Because of symbiotic bacteria", "None of the above"},
            "Because of carotenoids in their diet", "Spoonbills are pink because of carotenoids in their diet, which come from the crustaceans and other aquatic organisms they eat."),
    DINOSAUR(
            "Birds are dinosaurs.",
            new String[]{"True", "False"},
            "True", "Birds are considered dinosaurs because they evolved from feathered dinosaurs called theropods."),
    PARROT_TOX(
            "Which of these fruits is toxic to parrots (excluding the seeds)?",
            new String[]{"Avocado", "Apple", "Jalapeno", "Peach"},
            "Avocado", "Never give avocados to your parrots."),
    RELATIVE(
            "What bird lays the largest egg relative to its size?",
            new String[]{"Kiwi", "Chicken", "Ostrich", "Emu"},
            "Kiwi", "A kiwi egg is roughly 20% of the mother's weight."),
    WINDOW_DEATHS(
            "Each year, window collisions kill approximately how many birds in the United States?",
            new String[]{"15 million", "100 million", "500 million", "1 billion"},
"1 billion", "Window collisions are a major cause of bird mortality, with estimates around 1 billion birds killed annually in the US alone."),
    CAT_DEATHS(
            "How many birds are killed by cats each year in the United States?",
            new String[]{"A negligible amount", "10-30 million", "100-300 million", "1-3 billion"},
            "1-3 billion", "Keep your cats indoors!"),
    VULTURE_BALD(
            "What is the purpose of a vulture's bald head?",
            new String[]{"To stay clean while feeding on carrion","To regulate metabolism", "To attract mates", "It is the result of genetic drift (no purpose)"},
            "To stay clean while feeding on carrion", ""),
    TRAIN_INSPIRATION(
            "The Shinkansen bullet train in Japan was inspired by which bird?",
            new String[]{"Kingfisher","Peregrine Falcon", "Golden Eagle", "It wasn't inspired by a bird"},
            "Kingfisher", "The Shinkansen's design was inspired by the kingfisher's shape, which allows it to dive into water with minimal resistance."),
    DICKIN_MEDAL(
            "Which bird has won a Dickin Medal for bravery during wartime?",
            new String[]{"Pigeon","Chicken", "Parrot", "Crow"},
            "Pigeon", "Several pigeons have been awarded the Dickin Medal for their service during wartime, including carrying messages and locating survivors."),
    DODO(
            "Which bird is the most closely related to the dodo?",
            new String[]{"Nicobar Pigeon","Cassowary", "Victoria Crowned Pigeon", "Ostrich"},
            "Nicobar Pigeon", "The dodo's closest living relative is the Nicobar pigeon, which is found in Southeast Asia."),
    DANGEROUS(
            "What is the most dangerous bird in the world to humans?",
            new String[]{"Cassowary","Ostrich", "Golden Eagle", "Emu"},
            "Cassowary", "2 people have been killed by Cassowaries since 1900."),
    MOURNING_DOVE(
            "How do mourning doves cool down?",
            new String[]{"They sweat","They pant", "They roll in mud", "They find shelter"},
            "They pant", "All birds cannot sweat. Mourning doves cool down by panting like a dog."),
    CRAB(
            "What is the shell of the crab called?",
            new String[]{"Exoskeleton","Armor", "Test", "Mollusk Shell"},
            "Exoskeleton", "All arthropods (insects, crustaceans, arachnids, etc.) have an exoskeleton."),
    MANTIS_SHRIMP(
            "When a mantis shrimp strikes, it creates a bubble that can reach temperatures of the sun.",
            new String[]{"True","False"},
            "True", ""),
    MANTIS_SHRIMP2(
            "A mantis shrimp is a true shrimp.",
            new String[]{"True","False"},
            "False", "Mantis shrimps are in the order Stomatopoda while true shrimps are in the order Decapoda."),
    TARANTULA_DEATHS(
            "Tarantulas have killed an estimate of __ people since 1900.",
            new String[]{"0","1-2", "10-20", "100-200"},
            "0", "Although tarantula bites hurt, their venom is not strong enough to kill a human. There are no confirmed deaths from tarantula bites in history."),
    OLD_WORLD(
            "What is meant by 'Old World' tarantulas?",
            new String[]{"Tarantulas from Europe, Asia, and Africa","Tarantulas from the Americas", "Tarantulas that have been around for a long time", "Tarantulas that are extinct"},
            "Tarantulas from Europe, Asia, and Africa", "Old World - Europe, Asia, Africa. New World - Americas."),
    VENOMOUS(
            "Tarantulas are venomous or poisonous?",
            new String[]{"Venomous","Poisonous"},
            "Venomous", "Venomous - if it bites you you'll suffer'. Poisonous - if you eat it you'll suffer."),
    PEAFOWL_ENDANGERED(
            "Which of these peafowl species is endangered?",
            new String[]{"Indian Peafowl","Green Peafowl", "Congo Peafowl", "All of them"},
            "Green Peafowl", ""),
    KAKAPO_GROUND(
            "The kakapo is the only flightless parrot in the world.",
            new String[]{"True","False"},
            "True", ""),
    GUPPY(
            "Guppies lay eggs.",
            new String[]{"True","False"},
            "False", "Guppies give live birth."),
    INVASIVE_SPECIES(
            "Which one of these birds is an invasive species in the United States?",
            new String[]{"House sparrow","Blue jay", "Canada goose", "Snow goose"},
            "House sparrow", "House sparrows were introduced to the US in the 19th century and have since become widespread, often outcompeting native bird species."),
    MANDARIN_DUCK(
            "Mandarin ducks mate for life.",
            new String[]{"True","False"},
            "False", "Mandarin ducks are not monogamous. They form seasonal pair bonds but do not mate for life."),
    SYSTEMATICS(
            "Birds are part of the class ___, and phylum ___.",
            new String[]{"Aves, Chordata","Chordata, Passeriformes", "Reptilia, Chordata", "Passeriformes, Chordata"},
            "Aves, Chordata", "Birds comprise the class Aves, which is part of the phylum Chordata (animals with a notochord)."),
    SYSTEMATICS2(
            "Which of the following is the most diverse bird order?",
            new String[]{"Passeriformes","Accipitriformes", "Anseriformes", "Strigiformes"},
            "Passeriformes", "Passeriformes comprise 60% of all avian diversity."),
    ISOLATION(
            "Some birds recognize members of their own species through a unique song or call. This is an example of ___ isolation.",
            new String[]{"Behavioral","Geographical", "Mechanical", "Temporal"},
            "Behavioral", "Behavioral isolation is when unique songs/mating rituals prevent interbreeding between species."),
    GEOGRAPHICAL(
            "Magnificent Bird-of-Paradise and Wilson’s Bird-of-Paradise are separated by the 2 mile wide Sagewin Strait. This is an example of ___ isolation.",
            new String[]{"Behavioral","Geographical", "Mechanical", "Temporal"},
            "Geographical", ""),
    TEMPERATURE(
            "What is the average temperature of a bird's body?",
            new String[]{"40-42°C","37-39°C", "45-47°C", "None of the above"},
            "40-42°C", "Birds have a higher body temperature than mammals, typically around 40-42°C, which supports their high metabolism and flight."),
    BODY_SIZE(
            "What is the association between body size and temperature in birds?",
            new String[]{"Positive association","Inverse association", "No association", "Linear relationship"},
            "Inverse association", "Smaller birds tend to have higher body temperatures than larger birds, likely due to their higher metabolism rate and high surface-area-to-volume ratio."),
    CHICKADEE(
            "How do chickadees remember where they hide their food?",
            new String[]{"Their hippocampus expands in volume by 30%","They hide it in the same place every year", "They hibernate", "They have a special organ for storing food"},
            "Their hippocampus expands in volume by 30%", "Chickadees' hippocampus (the brain region associated with memory) expands in volume by about 30% during the fall when they are caching food for the winter."),
    LEAVES(
            "Why do so few birds eat leaves?",
            new String[]{"Because leaves take a long time to digest","Because birds do not have teeth", "Because birds cannot handle the toxins in leaves", "This statement is false, many birds eat leaves"},
            "Because leaves take a long time to digest", "Birds have a high metabolism and need to digest food quickly. Leaves are low in nutrients and take a long time to break down, making them an inefficient food source for most birds."),
    HEART(
            "How many chambers does a bird's heart have?",
            new String[]{"4","2", "3", "5"},
            "4", "Birds have a four-chambered heart, which allows for efficient separation of oxygenated and deoxygenated blood, supporting their high metabolism and flight."),
    BROOD_PARASITE(
            "After laying eggs in another bird's nest, obligate brood parasites return to feed their own young. The host parents only provide shelter for the parasite's eggs and chicks.",
            new String[]{"True","False"},
            "False", "Brood parasites leave feeding and care to the host parents, which conserve the parasite's energy."),
    KLEPTO(
            "Skuas are kleptoparasites. What does this mean?",
            new String[]{"They steal catches from other birds","They lay eggs in other birds nests", "They mimic other bird calls", "They prey on other birds' young"},
            "They steal catches from other birds", "Skuas are known for stealing food from other birds, often chasing them until they drop their catch."),
    DENATURE(
            "What is the starting temperature for bird proteins to denature?",
            new String[]{"Above 42°C","Above 36°C", "Below 10°C", "Above 60°C"},
            "Above 42°C", "Birds have a high body temperature (40-42°C), so their proteins are adapted to function at these temperatures. If the temperature rises above this range, their proteins can denature, leading to cellular damage."),
    KAKAPO2(
            "The endangered kakapos are facing low fertility rates and reduced hatching success. What is the main reason for this?",
            new String[]{"Because of habitat loss","Because of high presence of predators in the area", "Because of low heterozygosity", "Because of climate change"},
            "Because of low heterozygosity", "Kakapos have lost ~70% of their genetic diversity since the 1800s, which has led to inbreeding and associated fertility issues."),
    GENE(
            "Humans share approximately what percentage of functional genes with birds?",
            new String[]{"60%","50%", "90%", "99%"},
            "60%", "Although birds and mammals diverged around 300 million years ago, they are still share a vertebrate common ancestor."),
    GENOME(
            "Birds have the smallest genomes of all amniotes.",
            new String[]{"True", "False"},
            "True", "Birds have the smallest genomes of all amniotes (vast majority of living vertebrates), ranging from 0.91 to 1.3 Gb. The human genome is 3.2 Gb."),
    BIRD_GUT(
            "Why are bird gut microbiomes less diverse than those of mammals?",
            new String[]{"Because birds have a shorter gastrointestinal tract","Because birds have a strong immune system", "Because birds have high metabolism", "Because birds have antimicrobials in their gut"},
            "Because birds have a shorter gastrointestinal tract", "Unlike mammals, birds have a relatively short gastrointestinal tract, which limits the diversity of microbes that can colonize it. This is thought to be an adaptation to reduce weight for flight."),
    KOI_GOLDFISH(
            "Koi and goldfish were both domesticated from the same species of carp.",
            new String[]{"True","False"},
            "False", "Genetic evidence points to Goldfish being domesticated from C. auratus, and koi being domesticated from C. rubrofuscus. They can interbreed but their offspring are sterile."),
    EMU_EGG(
            "Why are emu eggs blue-green?",
                    new String[]{"Because of biliverdin", "It is structural color", "Because of anthocyanins", "None of the options are correct"},
            "Because of biliverdin", "Emu eggs are blue-green because of biliverdin, a pigment that is deposited in the eggshell during formation."),;

    public final String questionText;
    public final String[] options;
    public final String correctString;
    public final String explanation;

    BankQuestion(String questionText, String[] options, String correctString, String explanation) {
        this.questionText = questionText;
        this.options = options;
        this.correctString = correctString;
        this.explanation = explanation;
    }

    public String[] getShuffledOptions(Random random) {
        String[] shuffled = options.clone();
        for (int i = shuffled.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String tmp = shuffled[i];
            shuffled[i] = shuffled[j];
            shuffled[j] = tmp;
        }
        return shuffled;
    }

    public int getShuffledCorrectIndex(String[] shuffled) {
        for (int i = 0; i < shuffled.length; i++) {
            if (shuffled[i].equals(correctString)) return i;
        }
        return 0;
    }

    public static BankQuestion getRandom() {
        BankQuestion[] values = values();
        return values[new Random().nextInt(values.length)];
    }
    }