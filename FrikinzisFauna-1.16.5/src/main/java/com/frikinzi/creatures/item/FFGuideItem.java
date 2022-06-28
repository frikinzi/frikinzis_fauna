package com.frikinzi.creatures.item;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.client.gui.GUICreatures;
import com.frikinzi.creatures.entity.base.*;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FFGuideItem extends Item {
    public FFGuideItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
            if (!worldIn.isClientSide()) {
                openCreaturesGUI(itemstack);
            }
            return ActionResult.success(itemstack);
    }


    @Override
    public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
        if (!(target instanceof AbstractCrabBase) || !(target instanceof FishBase) || !(target instanceof GroupFishBase) || !(target instanceof NonTameableFlyingBirdBase)  || !(target instanceof NonTameableBirdBase)  || !(target instanceof TameableBirdBase)  || !(target instanceof TameableWalkingBirdBase)) {
            // Most likely someone summoned this item by command without data
            return ActionResultType.FAIL;
        }
        else {
            Creatures.PROXY.setReferencedMob(target);
            if (!player.level.isClientSide()) {
            openCreaturesGUI(stack);
            return ActionResultType.PASS;
            }
        }
        return ActionResultType.PASS;
        //return ActionResultType.sidedSuccess(player.level.isClientSide);
    }

    @OnlyIn(Dist.CLIENT)
    public void openCreaturesGUI(ItemStack book) {
        Minecraft mc = Minecraft.getInstance();
        mc.setScreen(new GUICreatures());
    }
}
