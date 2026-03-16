package com.frikinzi.creatures.item;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.*;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeSpawnEggItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class ModSpawnEggVariants extends ForgeSpawnEggItem {
    private Supplier<? extends EntityType<?>> typeGetter;
    private int currentSpecies;
    private final Supplier<? extends EntityType<? extends Mob>> entityType;

    public ModSpawnEggVariants(Supplier<? extends EntityType<? extends Mob>> typeIn, final int i, final int j) {
        super(typeIn, i, j, new Item.Properties().stacksTo(64));
        typeGetter = typeIn;
        this.entityType = typeIn;

        this.currentSpecies = 0;
        DispenserBlock.registerBehavior(this, new DefaultDispenseItemBehavior() {
            public ItemStack execute(BlockSource source, ItemStack stack) {
                Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
                EntityType<?> entitytype = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());
                entitytype.spawn(source.getLevel(), stack, null, source.getPos().relative(direction),
                        MobSpawnType.DISPENSER, direction != Direction.UP, false);
                stack.shrink(1);
                return stack;
            }
        });
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("item.creatures.spawn_egg_current", this.getCurrentSpeciesName(stack)).withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
    }


    public String getCurrentSpeciesName(ItemStack stack) {
        if (stack.hasTag()) {
            if (stack.getTag().contains("EntityTag")) {
                this.currentSpecies = stack.getTag().getCompound("EntityTag").getInt("Variant");
            }
        }
        if (this.currentSpecies == 0) {
            return "Random";
        }
        EntityType<?> entitytype = this.getType(stack.getTag());
        if (entitytype == CreaturesEntities.LOVEBIRD.get()) {
            if (this.currentSpecies > 0) {
                if (LovebirdEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                    return LovebirdEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                }if (entitytype == CreaturesEntities.SPOONBILL.get()) {
                    if (this.currentSpecies > 0) {
                        if (SpoonbillEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return SpoonbillEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                }
//                if (entitytype == CreaturesEntities.DOTTYBACK.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (DottybackEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return DottybackEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                }
                if (entitytype == CreaturesEntities.BUNTING.get()) {
                    if (this.currentSpecies > 0) {
                        if (BuntingEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return BuntingEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                }
                if (entitytype == CreaturesEntities.BUSHTIT.get()) {
                    if (this.currentSpecies > 0) {
                        if (BushtitEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return BushtitEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                }
                if (entitytype == CreaturesEntities.CHICKADEE.get()) {
                    if (this.currentSpecies > 0) {
                        if (ChickadeeEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return ChickadeeEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                }
                if (entitytype == CreaturesEntities.CONURE.get()) {
                    if (this.currentSpecies > 0) {
                        if (ConureEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return ConureEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                }
                if (entitytype == CreaturesEntities.DOVE.get()) {
                    if (this.currentSpecies > 0) {
                        if (DoveEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return DoveEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                }
                if (entitytype == CreaturesEntities.EAGLEOWL.get()) {
                    if (this.currentSpecies > 0) {
                        if (EagleOwlEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return EagleOwlEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                }
                if (entitytype == CreaturesEntities.FAIRYWREN.get()) {
                    if (this.currentSpecies > 0) {
                        if (FairywrenEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return FairywrenEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                }if (entitytype == CreaturesEntities.MONAL.get()) {
                    if (this.currentSpecies > 0) {
                        if (MonalEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return MonalEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                }
                if (entitytype == CreaturesEntities.TANAGER.get()) {
                    if (this.currentSpecies > 0) {
                        if (TanagerEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return TanagerEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                }
                if (entitytype == CreaturesEntities.FINCH.get()) {
                    if (this.currentSpecies > 0) {
                        if (FinchEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return FinchEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                }
//                if (entitytype == CreaturesEntities.PHEASANT.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (PheasantEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return PheasantEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                }
                if (entitytype == CreaturesEntities.LORIKEET.get()) {
                    if (this.currentSpecies > 0) {
                        if (LorikeetEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return LorikeetEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                } if (entitytype == CreaturesEntities.FINCH.get()) {
                    if (this.currentSpecies > 0) {
                        if (FinchEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return FinchEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                } if (entitytype == CreaturesEntities.GOOSE.get()) {
                    if (this.currentSpecies > 0) {
                        if (GooseEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return GooseEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                } if (entitytype == CreaturesEntities.IBIS.get()) {
                    if (this.currentSpecies > 0) {
                        if (IbisEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return IbisEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                } if (entitytype == CreaturesEntities.KINGFISHER.get()) {
                    if (this.currentSpecies > 0) {
                        if (KingfisherEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return KingfisherEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                } if (entitytype == CreaturesEntities.LAPWING.get()) {
                    if (this.currentSpecies > 0) {
                        if (LapwingEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return LapwingEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                } if (entitytype == CreaturesEntities.LAUGHINGTHRUSH.get()) {
                    if (this.currentSpecies > 0) {
                        if (LaughingthrushEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return LaughingthrushEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                } if (entitytype == CreaturesEntities.MAGPIE.get()) {
                    if (this.currentSpecies > 0) {
                        if (MagpieEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return MagpieEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                } if (entitytype == CreaturesEntities.PEAFOWL.get()) {
                    if (this.currentSpecies > 0) {
                        if (PeafowlEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return PeafowlEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                } if (entitytype == CreaturesEntities.PELICAN.get()) {
                    if (this.currentSpecies > 0) {
                        if (PelicanEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return PelicanEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                } if (entitytype == CreaturesEntities.PYGMY_GOOSE.get()) {
                    if (this.currentSpecies > 0) {
                        if (PygmyGooseEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return PygmyGooseEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                } if (entitytype == CreaturesEntities.ROBIN.get()) {
                    if (this.currentSpecies > 0) {
                        if (RobinEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return RobinEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                } if (entitytype == CreaturesEntities.ROLLER.get()) {
                    if (this.currentSpecies > 0) {
                        if (RollerEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return RollerEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                } if (entitytype == CreaturesEntities.SKUA.get()) {
                    if (this.currentSpecies > 0) {
                        if (SkuaEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return SkuaEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                }
//                if (entitytype == CreaturesEntities.GUPPY.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (GuppyEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return GuppyEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                }
                if (entitytype == CreaturesEntities.SPARROW.get()) {
                    if (this.currentSpecies > 0) {
                        if (SparrowEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return SparrowEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                } if (entitytype == CreaturesEntities.SWALLOW.get()) {
                    if (this.currentSpecies > 0) {
                        if (SwallowEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return SwallowEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                } if (entitytype == CreaturesEntities.WILD_DUCK.get()) {
                    if (this.currentSpecies > 0) {
                        if (WildDuckEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
                            return WildDuckEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
                        }
                    }
                }
//                 if (entitytype == CreaturesEntities.STORK.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (StorkEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return StorkEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.WHISTLINGDUCK.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (WhistlingDuckEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return WhistlingDuckEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.GROUND_HORNBILL.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (GroundHornbillEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return GroundHornbillEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.STARLING.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (StarlingEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return StarlingEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.CORMORANT.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (CormorantEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return CormorantEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                }
//                if (entitytype == CreaturesEntities.PUFFIN.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (PuffinEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return PuffinEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                }
//                if (entitytype == CreaturesEntities.SEAGULL.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (SeagullEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return SeagullEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                }
//                if (entitytype == CreaturesEntities.RED_SNAPPER.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (RedSnapperEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return RedSnapperEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.FIDDLER_CRAB.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (FiddlerCrabEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return FiddlerCrabEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.TARANTULA.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (TarantulaEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return TarantulaEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.RAVEN.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (RavenEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return RavenEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.TROUT.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (TroutEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return TroutEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.GOURAMI.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (GouramiEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return GouramiEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.PIRANHA.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (PiranhaEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return PiranhaEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.SHRIMP.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (ShrimpEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return ShrimpEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.TIGERBARB.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (TigerBarbEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return TigerBarbEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.STINGRAY.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (StingrayEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return StingrayEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.SWORDFISH.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (SwordfishEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return SwordfishEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.BOOBY.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (BoobyEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return BoobyEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.SQUID.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (SquidEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return SquidEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.BANDED_PENGUIN.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (BandedPenguinEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return BandedPenguinEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.BARRACUDA.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (BarracudaEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return BarracudaEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.AVOCET.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (AvocetEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return AvocetEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.SEADRAGON.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (SeaDragonEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return SeaDragonEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.TRUMPETFISH.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (TrumpetfishEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return TrumpetfishEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.CRESTED_PENGUIN.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (CrestedPenguinEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return CrestedPenguinEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.PARROTFISH.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (ParrotfishEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return ParrotfishEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.BRUSH_TAILED_PENGUIN.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (BrushTailedPenguinEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return BrushTailedPenguinEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.LARGE_PENGUIN.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (LargePenguinEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return LargePenguinEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.FRIGATE.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (FrigateBirdEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return FrigateBirdEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.CLOWNFISH.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (ClownfishEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return ClownfishEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.STILT.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (StiltEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return StiltEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.LUNGFISH.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (LungfishEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return LungfishEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.LITTLE_PENGUIN.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (LittlePenguinEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return LittlePenguinEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.BLUE_TANG.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (BlueTangEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return BlueTangEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.SEA_EAGLE.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (SeaEagleEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return SeaEagleEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.MANTIS_SHRIMP.get()) {
//                    if (this.currentSpecies > 0) {
//                        int no = 1;
//                        if (this.currentSpecies == 6) {
//                            no = 2;
//                        }
//                        if (MantisShrimpEntity.SPECIES_NAMES.get(no) != null) {
//                            return MantisShrimpEntity.SPECIES_NAMES.get(no).getString() + " " + this.currentSpecies;
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.RAIL.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (RailEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return RailEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.SAWFISH.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (SawfishEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return SawfishEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.GHOST_CRAB.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (GhostCrabEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return GhostCrabEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                } if (entitytype == CreaturesEntities.EDIBLE_CRAB.get()) {
//                    if (this.currentSpecies > 0) {
//                        if (EdibleCrabEntity.SPECIES_NAMES.get(this.currentSpecies) != null) {
//                            return EdibleCrabEntity.SPECIES_NAMES.get(this.currentSpecies).getString();
//                        }
//                    }
//                }
            }
        }
        int i = this.currentSpecies;
        return Integer.toString(i);

    }

    public void increaseSpeciesCount(Level world) {
        CreaturesBirdEntity bird = (CreaturesBirdEntity) this.entityType.get().create(world);
        this.currentSpecies += 1;
        if (this.currentSpecies > bird.numVariants()) {
            this.currentSpecies = 0;
        }

    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!(worldIn instanceof ServerLevel)) {
            return InteractionResultHolder.success(itemstack);
        }
        if (playerIn.isSteppingCarefully()) {
            this.increaseSpeciesCount(worldIn);
            itemstack.setTag(new CompoundTag());
            if (!(this.currentSpecies <= 0)) {
                CompoundTag entityNBT = new CompoundTag();
                entityNBT.putInt("Variant", this.currentSpecies);
                itemstack.getTag().put("EntityTag", entityNBT);
            }
            playerIn.displayClientMessage(Component.translatable("item.creatures.spawn_egg_change", this.getCurrentSpeciesName(itemstack)), true);
            return InteractionResultHolder.pass(itemstack);
        }

        BlockHitResult raytraceresult = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.SOURCE_ONLY);
        if (raytraceresult.getType() != BlockHitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            BlockPos blockpos = raytraceresult.getBlockPos();
            if (!(worldIn.getBlockState(blockpos).getBlock() instanceof LiquidBlock)) {
                return InteractionResultHolder.pass(itemstack);
            } else if (worldIn.mayInteract(playerIn, blockpos) && playerIn.mayUseItemAt(blockpos, raytraceresult.getDirection(), itemstack)) {
                EntityType<?> entitytype = this.getType(itemstack.getTag());
                CreaturesBirdEntity bird = (CreaturesBirdEntity) entitytype.spawn((ServerLevel) worldIn, itemstack, playerIn, blockpos, MobSpawnType.SPAWN_EGG, false, false);
                if (bird == null) {
                    return InteractionResultHolder.pass(itemstack);
                } else {
                    if (!playerIn.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }

                    if (this.currentSpecies == 0) {
                        bird.setVariant(bird.methodOfDeterminingVariant());
                    } else if (this.currentSpecies > 0) {
                        bird.setVariant(this.currentSpecies);
                    }
                    ((ServerLevel) worldIn).addFreshEntityWithPassengers(bird);
                    playerIn.awardStat(Stats.ITEM_USED.get(this));
                    worldIn.gameEvent(playerIn, GameEvent.ENTITY_PLACE, blockpos);
                    return InteractionResultHolder.consume(itemstack);
                }

            }
            }  return InteractionResultHolder.fail(itemstack);

    }

}





//    @Override
//    public EntityType<?> getType(@Nullable CompoundTag p_208076_1_) {
//        return typeGetter.get();
//    }

