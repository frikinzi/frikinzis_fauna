package com.frikinzi.creatures.client.gui;

import com.frikinzi.creatures.entity.StingrayEntity;
import com.frikinzi.creatures.entity.TarantulaEntity;
import com.frikinzi.creatures.entity.base.AbstractCrabBase;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.player.AwardXPPacket;
import com.frikinzi.creatures.player.FieldGuideCapability;
import com.frikinzi.creatures.player.NetworkHandler;
import com.frikinzi.creatures.player.SpeciesEntry;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Quaternionf;

import java.util.*;

public class DailyQuizScreen extends Screen {
    private final Screen parent;
    private static final ResourceLocation BOOK_TEXTURE = new ResourceLocation("creatures:textures/gui/creatures/book.png");
    private int bankQuestionStartY = 0;
    // Quiz state
    private final boolean showBirdPickName; // true = show bird, pick name; false = show name, pick bird
    private final SpeciesEntry correctSpecies;
    private final int correctVariant;
    private final List<QuizOption> options; // 4 options total
    
    private Integer selectedOption = null;
    private boolean answered = false;
    private final Map<Integer, Integer> cachedGenders = new HashMap<>();
    private final Map<Integer, Integer> cachedSubVariants = new HashMap<>();
    private final BankQuestion bankQuestion;
    private final boolean isBankQuestion;
    private final String[] shuffledOptions;
    private final int shuffledCorrectIndex;
    private final SpeciesEntry correctSound;
    private final boolean isSoundQuestion;
    private final List<String> soundOptions;
    private boolean soundPlayed = false;
    private int soundOptionCorrectIndex = 0;

    private int questionNumber = 1;
    private static final int TOTAL_QUESTIONS = 5;
    private int correctCount = 0;
    public DailyQuizScreen(Screen parent, boolean showBirdPickName, SpeciesEntry correct,
                           int correctVariant, List<QuizOption> options,
                           int questionNumber, int correctCount) {
        super(Component.literal("Daily Quiz"));
        this.parent = parent;
        this.showBirdPickName = showBirdPickName;
        this.correctSpecies = correct;
        this.correctVariant = correctVariant;
        this.options = options;
        this.questionNumber = questionNumber;
        this.correctCount = correctCount;
        this.bankQuestion = null;
        this.isBankQuestion = false;
        this.shuffledOptions = null;
        this.shuffledCorrectIndex = 1;
        this.soundOptions = null;
        this.isSoundQuestion = false;
        this.correctSound = null;
    }

    public DailyQuizScreen(Screen parent, BankQuestion bankQuestion, int questionNumber, int correctCount) {
        super(Component.literal("Daily Quiz"));
        this.parent = parent;
        this.bankQuestion = bankQuestion;
        this.isBankQuestion = true;
        this.showBirdPickName = false; // unused
        this.correctSpecies = null;
        this.correctVariant = -1;
        this.options = List.of(); // unused
        this.questionNumber = questionNumber;
        this.correctCount = correctCount;
        Random random = new Random();
        this.shuffledOptions = bankQuestion.getShuffledOptions(random);
        this.shuffledCorrectIndex = bankQuestion.getShuffledCorrectIndex(shuffledOptions);
        this.soundOptions = null;
        this.isSoundQuestion = false;
        this.correctSound = null;
    }

    public DailyQuizScreen(Screen parent, SpeciesEntry correctSound, List<String> soundOptions,
                           int soundOptionCorrectIndex, int questionNumber, int correctCount) {
        super(Component.literal("Daily Quiz"));
        this.parent = parent;
        this.isSoundQuestion = true;
        this.correctSound = correctSound;  // ← change field type to SpeciesEntry
        this.soundOptions = soundOptions;
        this.soundOptionCorrectIndex = soundOptionCorrectIndex;
        this.questionNumber = questionNumber;
        this.correctCount = correctCount;
        this.isBankQuestion = false;
        this.showBirdPickName = false;
        this.correctSpecies = null;
        this.correctVariant = -1;
        this.options = List.of();
        this.bankQuestion = null;
        this.shuffledOptions = null;
        this.shuffledCorrectIndex = 0;
    }

    public static DailyQuizScreen create(Screen parent, FieldGuideCapability cap) {
        return create(parent, cap, 1, 0);
    }


    public static class QuizOption {
        public final SpeciesEntry species;
        public final int variant;
        public final String displayName; // species name for this variant

        
        public QuizOption(SpeciesEntry species, int variant, String displayName) {
            this.species = species;
            this.variant = variant;
            this.displayName = displayName;
            //this.options = options;
        }


    }

    public static DailyQuizScreen create(Screen parent, FieldGuideCapability cap, int questionNumber, int correctCount) {
        if (new Random().nextFloat() < 0.3f) {
            return new DailyQuizScreen(parent, BankQuestion.getRandom(), questionNumber, correctCount);
        }  else if (new Random().nextFloat() < 0.5f) {
            return createSoundQuestion(parent, questionNumber, correctCount);
        }
        List<QuizOption> pool = new ArrayList<>();
        for (SpeciesEntry s : FieldGuideGUI.ALL_SPECIES) {
            for (int v = 1; v <= s.totalVariants; v++) {
                String name = s.getSpeciesName(v);
                if (name.isEmpty() || name.equals("Unknown"))
                    name = s.displayName.getString();
                pool.add(new QuizOption(s, v, name));
            }
        }

        if (pool.size() < 4) return null;

        Collections.shuffle(pool);
        QuizOption correct = pool.get(0);

        List<QuizOption> wrongs = new ArrayList<>();

        for (QuizOption o : pool) {
            if (wrongs.size() >= 3) break;
            if (o != correct && o.species == correct.species && o.variant != correct.variant
                    && !o.displayName.equals(correct.displayName)
                    && wrongs.stream().noneMatch(w -> w.displayName.equals(o.displayName)))
                wrongs.add(o);
        }
        for (QuizOption o : pool) {
            if (wrongs.size() >= 3) break;
            if (o != correct && !wrongs.contains(o)
                    && !o.displayName.equals(correct.displayName)
                    && wrongs.stream().noneMatch(w -> w.displayName.equals(o.displayName)))
                wrongs.add(o);
        }

        List<QuizOption> allOptions = new ArrayList<>();
        allOptions.add(correct);
        allOptions.addAll(wrongs);
        Collections.shuffle(allOptions);

        boolean showBird = new Random().nextBoolean();
        return new DailyQuizScreen(parent, showBird, correct.species, correct.variant, allOptions, questionNumber, correctCount);
    }

//    public DailyQuizScreen(Screen parent, boolean showBirdPickName, SpeciesEntry correct,
//                           int correctVariant, List<QuizOption> options) {
//        super(Component.literal("Daily Quiz"));
//        this.parent = parent;
//        this.showBirdPickName = showBirdPickName;
//        this.correctSpecies = correct;
//        this.correctVariant = correctVariant;
//        this.options = options;
//    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);

        int bookW = 390, bookH = 245;
        int bookX = (this.width - bookW) / 2;
        int bookY = (this.height - bookH) / 2;

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        graphics.blit(BOOK_TEXTURE, bookX, bookY, 0, 0, bookW, bookH, bookW, bookW);

        int centerX = bookX + bookW / 2;
        int pageY = bookY + 50;

        String progress = "Question " + questionNumber + " / " + TOTAL_QUESTIONS
                + "   Score: " + correctCount;
        graphics.drawString(font, progress, bookX + 10, bookY + 10, 0xFFFFFF, false);

        // Question prompt
        String text = showBirdPickName ? "Identify this animal:" : "Select the animal that matches this species:";
        if (!isBankQuestion && !isSoundQuestion) {
            graphics.drawString(font, text, centerX - font.width(text) / 2, pageY, 0x3D2B1F, false);
        }
        if (isBankQuestion) {
            renderBankQuestion(graphics, bookX, bookY, bookW, bookH, centerX, mouseX, mouseY);
        }
        else if (isSoundQuestion) {
            renderSoundQuestion(graphics, bookX, bookY, bookW, centerX, mouseX, mouseY);
        }
        else if (showBirdPickName) {
            LivingEntity dummy = (LivingEntity) correctSpecies.entityType.get()
                    .create(Minecraft.getInstance().level);
            Quaternionf rot = new Quaternionf().rotateZ((float)Math.PI).rotateY((float)Math.toRadians(160));
            Random rand = new Random();
            if (dummy instanceof CreaturesBirdEntity bird) {
                bird.setVariant(correctVariant);
                bird.setGender(cachedGenders.computeIfAbsent(-1, k -> new Random().nextInt(2)));
                bird.setSubVariant(cachedSubVariants.computeIfAbsent(-1, k -> bird.getSubVariantBasedOnVariant(correctVariant)));                bird.setOnGround(true);
                rot = bird.getRotforGUI();
            }
            if (dummy instanceof AbstractCrabBase crab) {
                crab.setVariant(correctVariant);
                crab.setGender(cachedGenders.computeIfAbsent(-1, k -> new Random().nextInt(2)));
                crab.setOnGround(true);
                rot = crab.getRotforGUI();
            }
            if (dummy instanceof FishBase fish) {
                fish.setVariant(correctVariant);
                fish.setGender(rand.nextInt(2));
                fish.setSubVariant(cachedSubVariants.computeIfAbsent(-1, k -> fish.getSubVariantBasedOnVariant(correctVariant)));
                fish.setOnGround(true);
                fish.setGender(cachedGenders.computeIfAbsent(-1, k -> new Random().nextInt(2)));
                fish.setForcedInWater(true);
                rot = fish.getRotforGUI();
            }
            if (dummy != null) {
                float h = dummy.getBbHeight();
                int scale = (int)(30f / h);
                    InventoryScreen.renderEntityInInventory(graphics, centerX, pageY + 60, scale, rot, null, dummy);
            }

            renderNameOptions(graphics, bookX, bookY, bookW, mouseX, mouseY);

        } else {
            //String text2 = correctSpecies.getSpeciesName(correctVariant);
            String text2 = options.get(getCorrectIndex()).displayName;
            graphics.drawString(font, text2,
                    centerX - font.width(text2) / 2, pageY + 20, 0x5C4033, false);
            renderBirdOptions(graphics, bookX, bookY, bookW, mouseX, mouseY);
        }

        // Feedback
        if (answered) {
            boolean correct = isBankQuestion ? selectedOption == shuffledCorrectIndex
                    : isSoundQuestion ? selectedOption == soundOptionCorrectIndex
                    : selectedOption == getCorrectIndex();
            String text3 = correct ? "Nice job!" : "Better luck next time!";
            graphics.drawString(font,
                    text3,
                    centerX - font.width(text3) / 2, bookY + bookH - 30,
                    correct ? 0x00AA00 : 0xFF5555, false);

            if (questionNumber == TOTAL_QUESTIONS) {
                String comment = getScoreComment();
                graphics.drawString(font, comment,
                        centerX - font.width(comment) / 2, bookY + bookH - 20,
                        0xFFFFFF, false);
            }
        }

        this.children().stream()
                .filter(w -> w instanceof Button b && (b.getMessage().getString().contains("Next")
                        || b.getMessage().getString().equals("Finish")))
                .forEach(w -> ((Button) w).visible = answered);

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    private void renderNameOptions(GuiGraphics graphics, int bookX, int bookY, int bookW,
                                   int mouseX, int mouseY) {
        int btnW = 150, btnH = 18, gap = 4;
        int startX = bookX + bookW / 2 - btnW / 2;
        int startY = bookY + 120;

        for (int i = 0; i < options.size(); i++) {
            int x = startX;
            int y = startY + i * (btnH + gap);

            int color = 0xCCF5E6D3;
            if (answered) {
                if (i == getCorrectIndex()) color = 0xCC5C8A3C;
                else if (i == selectedOption) color = 0xCCAA3333;
            } else if (mouseX >= x && mouseX <= x + btnW && mouseY >= y && mouseY <= y + btnH) {
                color = 0xCCFFD580;
            }

            graphics.fill(x, y, x + btnW, y + btnH, color);
            graphics.fill(x, y, x + btnW, y + 1, 0x88000000);
            graphics.fill(x, y + btnH - 1, x + btnW, y + btnH, 0x88000000);
            String text = options.get(i).displayName;
            graphics.drawString(font, text, x + btnW / 2 - font.width(text) / 2, y + 5, 0x3D2B1F, false);
        }
    }

    private void renderBirdOptions(GuiGraphics graphics, int bookX, int bookY, int bookW,
                                   int mouseX, int mouseY) {
        int cellSize = 50;
        int totalW = options.size() * cellSize + (options.size() - 1) * 8;
        int startX = bookX + bookW / 2 - totalW / 2;
        int y = bookY + 80;

        for (int i = 0; i < options.size(); i++) {
            int x = startX + i * (cellSize + 8);
            int color = 0xCCF5E6D3;
            if (answered) {
                if (i == getCorrectIndex()) color = 0xCC5C8A3C;
                else if (i == selectedOption) color = 0xCCAA3333;
            } else if (mouseX >= x && mouseX <= x + cellSize && mouseY >= y && mouseY <= y + cellSize) {
                color = 0xCCFFD580;
            }
            graphics.fill(x, y, x + cellSize, y + cellSize, color);

            LivingEntity dummy = (LivingEntity) options.get(i).species.entityType.get()
                    .create(Minecraft.getInstance().level);
            Quaternionf rot = new Quaternionf().rotateZ((float)Math.PI).rotateY((float)Math.toRadians(160));
            Random random = new Random();
            float h = dummy.getBbHeight();
            int scale = (int)(20f / h);
            if (dummy instanceof CreaturesBirdEntity bird) {
                bird.setVariant(options.get(i).variant);
                bird.setGender(cachedGenders.computeIfAbsent(i, k -> new Random().nextInt(2)));
                int finalI1 = i;
                bird.setSubVariant(cachedSubVariants.computeIfAbsent(i, k -> bird.getSubVariantBasedOnVariant(options.get(finalI1).variant)));
                bird.setOnGround(true);
                rot = bird.getRotforGUI();
                scale = bird.getScaleforGUI();
            }
            if (dummy instanceof FishBase bird) {
                bird.setVariant(options.get(i).variant);
                //bird.setGender(random.nextInt(2));
                bird.setGender(cachedGenders.computeIfAbsent(i, k -> new Random().nextInt(2)));
                int finalI = i;
                bird.setSubVariant(cachedSubVariants.computeIfAbsent(i, k -> bird.getSubVariantBasedOnVariant(options.get(finalI).variant)));
                bird.setForcedInWater(true);
                rot = bird.getRotforGUI();
                scale = bird.getScaleforGUI();
            }
            if (dummy instanceof AbstractCrabBase crab) {
                crab.setVariant(options.get(i).variant);
                crab.setGender(cachedGenders.computeIfAbsent(i, k -> new Random().nextInt(2)));
                crab.setOnGround(true);
                rot = crab.getRotforGUI();
                scale = crab.getScaleforGUI();
            }
            if (dummy != null) {

                graphics.enableScissor(x, y, x + cellSize, y + cellSize);
                InventoryScreen.renderEntityInInventory(graphics, x + cellSize / 2,
                        y + cellSize - 5, scale, rot, null, dummy);
                graphics.disableScissor();
            }
        }
    }

    private int getCorrectIndex() {
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).species == correctSpecies && options.get(i).variant == correctVariant)
                return i;
        }
        return 0;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {

        if (answered) return super.mouseClicked(mouseX, mouseY, button);

        int bookW = 390, bookH = 245;
        int bookX = (this.width - bookW) / 2;
        int bookY = (this.height - bookH) / 2;
        int centerX = bookX + bookW / 2;
        if (isSoundQuestion) {
            bookW = 390; bookH = 245;
             bookX = (this.width - bookW) / 2;
            bookY = (this.height - bookH) / 2;
            centerX = bookX + bookW / 2;
            int pageY = bookY + 45;

            // Play button
            int btnW = 120, btnH = 20;
            int btnX = centerX - btnW / 2;
            int btnY = pageY + 20;
            if (mouseX >= btnX && mouseX <= btnX + btnW && mouseY >= btnY && mouseY <= btnY + btnH) {
                LivingEntity dummy = (LivingEntity) correctSound.entityType.get()
                        .create(Minecraft.getInstance().level);
                if (dummy != null && dummy instanceof CreaturesBirdEntity) {
                    if (((CreaturesBirdEntity) dummy).getAmbientSound() == null) return true; // no sound to play
                    Minecraft.getInstance().getSoundManager().play(
                            net.minecraft.client.resources.sounds.SimpleSoundInstance.forUI(
                                    ((CreaturesBirdEntity) dummy).getAmbientSound(), 1.0f));
                    soundPlayed = true;
                }
                return true;
            }

            // Answer buttons
            if (!answered) {
                int optW = 150, optH = 18, gap = 5;
                int optX = centerX - optW / 2;
                for (int i = 0; i < soundOptions.size(); i++) {
                    int x = optX;
                    int y = bankQuestionStartY + i * (optH + gap);
                    if (mouseX >= x && mouseX <= x + optW && mouseY >= y && mouseY <= y + optH) {
                        selectedOption = i;
                        answered = true;
                        if (i == soundOptionCorrectIndex) markCompleted();
                        return true;
                    }
                }
            }
            return super.mouseClicked(mouseX, mouseY, button);
        }
        if (isBankQuestion) {
            int btnW = 160, btnH = 18, gap = 5;
            int startX = centerX - btnW / 2;
            for (int i = 0; i < shuffledOptions.length; i++) {
                int x = startX;
                int y = bankQuestionStartY + i * (btnH + gap);
                if (mouseX >= x && mouseX <= x + btnW && mouseY >= y && mouseY <= y + btnH) {
                    selectedOption = i;
                    answered = true;
                    if (i == shuffledCorrectIndex) markCompleted();
                    return true;
                }
            }

        } else if (showBirdPickName) {
            int btnW = 150, btnH = 18, gap = 4;
            int startX = centerX - btnW / 2;
            int startY = bookY + 120;
            for (int i = 0; i < options.size(); i++) {
                int x = startX;
                int y = startY + i * (btnH + gap);
                if (mouseX >= x && mouseX <= x + btnW && mouseY >= y && mouseY <= y + btnH) {
                    selectedOption = i;
                    answered = true;
                    if (i == getCorrectIndex()) markCompleted();
                    return true;
                }
            }

        } else {
            int cellSize = 50;
            int totalW = options.size() * cellSize + (options.size() - 1) * 8;
            int startX = centerX - totalW / 2;
            int y = bookY + 80;
            for (int i = 0; i < options.size(); i++) {
                int x = startX + i * (cellSize + 8);
                if (mouseX >= x && mouseX <= x + cellSize && mouseY >= y && mouseY <= y + cellSize) {
                    selectedOption = i;
                    answered = true;
                    if (i == getCorrectIndex()) markCompleted();
                    return true;
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
    private void markCompleted() {
//        FieldGuideGUI.lastQuizDay = Minecraft.getInstance().level.getDayTime() / 24000L;
//        FieldGuideGUI.quizCompletedToday = true;
        correctCount++;
        NetworkHandler.CHANNEL.sendToServer(new AwardXPPacket(5));
        Minecraft mc = Minecraft.getInstance();
        mc.player.playSound(net.minecraft.sounds.SoundEvents.EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
    }

//    @Override
//    protected void init() {
//        super.init();
//        int bookW = 390, bookH = 245;
//        int bookX = (this.width - bookW) / 2;
//        int bookY = (this.height - bookH) / 2;
//        this.addRenderableWidget(Button.builder(Component.literal("✗ Close"),
//                b -> Minecraft.getInstance().setScreen(parent))
//                .pos(bookX + bookW - 50, bookY + bookH - 35).size(45, 20).build());
//    }

    @Override
    protected void init() {
        super.init();
        int bookW = 390, bookH = 245;
        int bookX = (this.width - bookW) / 2;
        int bookY = (this.height - bookH) / 2;

        this.addRenderableWidget(Button.builder(Component.literal("✗ Close"),
                        b -> Minecraft.getInstance().setScreen(parent))
                .pos(bookX + 10, bookY + bookH - 35).size(45, 20).build());

        this.addRenderableWidget(Button.builder(Component.literal(
                        questionNumber < TOTAL_QUESTIONS ? "Next ▶" : "Finish"), b -> {
                    if (questionNumber < TOTAL_QUESTIONS) {
                        // Build a new question, carry over score
                        FieldGuideCapability cap = Minecraft.getInstance().player
                                .getCapability(FieldGuideCapability.CAPABILITY).orElse(null);
                        DailyQuizScreen next = DailyQuizScreen.create(parent, cap, questionNumber + 1, correctCount);
                        if (next != null) Minecraft.getInstance().setScreen(next);
                    } else {
                        // Show final score — mark quiz day as complete
                        FieldGuideGUI.lastQuizDay = Minecraft.getInstance().level.getDayTime() / 24000L;
                        FieldGuideGUI.quizCompletedToday = true;
                        Minecraft.getInstance().setScreen(parent);
                    }
                }).pos(bookX + bookW - 60, bookY + bookH - 35).size(55, 20)
                .build()); // visibility handled in render
    }

    @Override
    public boolean isPauseScreen() { return false; }

    private String getScoreComment() {
        if (correctCount == 5) return "5/5. Wow! You're a 'natural'!";
        if (correctCount == 4) return "4/5. Nice!";
        if (correctCount == 3) return "3/5. Not bad! Keep exploring the wild!";
        if (correctCount == 2) return "You got unlucky... right?";
        if (correctCount == 1) return "Maybe study a bit more next time?";
        if (correctCount == 0) return "Maybe just guess next time?";
        return "Better luck next time — get out there and find some animals!";
    }

    private void renderBankQuestion(GuiGraphics graphics, int bookX, int bookY, int bookW, int bookH,
                                    int centerX, int mouseX, int mouseY) {
        int pageY = bookY + 40;

        // Question text — word wrapped and centered
        for (net.minecraft.util.FormattedCharSequence line :
                font.split(Component.literal(bankQuestion.questionText), bookW - 100)) {
            graphics.drawString(font, line, centerX - font.width(line) / 2, pageY, 0x3D2B1F, false);
            pageY += 11;
        }

        // Answer buttons
        int btnW = 160, btnH = 18, gap = 5;
        int startX = centerX - btnW / 2;
        int startY = pageY + 15;
        this.bankQuestionStartY = startY;

        for (int i = 0; i < shuffledOptions.length; i++) {
            int x = startX;
            int y = startY + i * (btnH + gap);

            int color = 0xCCF5E6D3;
            if (answered) {
                if (i == shuffledCorrectIndex) color = 0xCC5C8A3C;
                else if (i == selectedOption) color = 0xCCAA3333;
            } else if (mouseX >= x && mouseX <= x + btnW && mouseY >= y && mouseY <= y + btnH) {
                color = 0xCCFFD580;
            }

            graphics.fill(x, y, x + btnW, y + btnH, color);
            graphics.fill(x, y, x + btnW, y + 1, 0x88000000);
            graphics.fill(x, y + btnH - 1, x + btnW, y + btnH, 0x88000000);
            String opt = shuffledOptions[i];
            graphics.drawString(font, opt, x + btnW / 2 - font.width(opt) / 2, y + 5, 0x3D2B1F, false);
        }

        if (answered && bankQuestion.explanation != null) {
            int explY = startY + shuffledOptions.length * (btnH + gap) + 5;
            for (net.minecraft.util.FormattedCharSequence line :
                    font.split(Component.literal("Explanation: " + bankQuestion.explanation), bookW - 100)) {
                graphics.drawString(font, line, centerX - font.width(line) / 2, explY, 0x5C4033, false);
                explY += 10;
            }
        }
    }

    public static DailyQuizScreen createSoundQuestion(Screen parent, int questionNumber, int correctCount) {
        // Filter ALL_SPECIES to birds only that have an ambient sound
        List<SpeciesEntry> birdPool = FieldGuideGUI.ALL_SPECIES.stream()
                .filter(s -> {
                    LivingEntity dummy = (LivingEntity) s.entityType.get()
                            .create(Minecraft.getInstance().level);
                    return dummy instanceof CreaturesBirdEntity
                            && ((CreaturesBirdEntity) dummy).getAmbientSound() != null;
                })
                .collect(java.util.stream.Collectors.toList());

        if (birdPool.size() < 4) return null;

        Collections.shuffle(birdPool);
        SpeciesEntry correct = birdPool.get(0);

        List<String> wrongNames = new ArrayList<>();
        for (SpeciesEntry s : birdPool) {
            if (wrongNames.size() >= 3) break;
            if (s != correct && !wrongNames.contains(s.displayName.getString()))
                wrongNames.add(s.displayName.getString());
        }

        List<String> allOptions = new ArrayList<>();
        allOptions.add(correct.displayName.getString());
        allOptions.addAll(wrongNames);
        Collections.shuffle(allOptions);

        int correctIndex = allOptions.indexOf(correct.displayName.getString());
        return new DailyQuizScreen(parent, correct, allOptions, correctIndex, questionNumber, correctCount);
    }

    private void renderSoundQuestion(GuiGraphics graphics, int bookX, int bookY, int bookW,
                                     int centerX, int mouseX, int mouseY) {
        int pageY = bookY + 45;

        String prompt = "Which bird made this call?";
        graphics.drawString(font, prompt, centerX - font.width(prompt) / 2, pageY, 0x3D2B1F, false);

        // Play button
        int btnW = 120, btnH = 20;
        int btnX = centerX - btnW / 2;
        int btnY = pageY + 20;
        boolean hovering = mouseX >= btnX && mouseX <= btnX + btnW
                && mouseY >= btnY && mouseY <= btnY + btnH;
        graphics.fill(btnX, btnY, btnX + btnW, btnY + btnH,
                hovering ? 0xCCFFD580 : 0xCCF5E6D3);
        graphics.fill(btnX, btnY, btnX + btnW, btnY + 1, 0x88000000);
        graphics.fill(btnX, btnY + btnH - 1, btnX + btnW, btnY + btnH, 0x88000000);
        String playLabel = soundPlayed ? "▶ Play Again" : "▶ Play Sound";
        graphics.drawString(font, playLabel,
                btnX + btnW / 2 - font.width(playLabel) / 2, btnY + 6, 0x3D2B1F, false);

        // Name option buttons
        int optW = 150, optH = 18, gap = 5;
        int optX = centerX - optW / 2;
        int startY = btnY + btnH + 20;
        this.bankQuestionStartY = startY; // reuse field for click detection

        for (int i = 0; i < soundOptions.size(); i++) {
            int x = optX;
            int y = startY + i * (optH + gap);
            int color = 0xCCF5E6D3;
            if (answered) {
                if (i == soundOptionCorrectIndex) color = 0xCC5C8A3C;
                else if (i == selectedOption) color = 0xCCAA3333;
            } else if (mouseX >= x && mouseX <= x + optW && mouseY >= y && mouseY <= y + optH) {
                color = 0xCCFFD580;
            }
            graphics.fill(x, y, x + optW, y + optH, color);
            graphics.fill(x, y, x + optW, y + 1, 0x88000000);
            graphics.fill(x, y + optH - 1, x + optW, y + optH, 0x88000000);
            String name = soundOptions.get(i);
            graphics.drawString(font, name, x + optW / 2 - font.width(name) / 2, y + 5, 0x3D2B1F, false);
        }
    }

}