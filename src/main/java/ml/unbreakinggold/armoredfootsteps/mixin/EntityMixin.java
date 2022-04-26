package ml.unbreakinggold.armoredfootsteps.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
    @Shadow public World world;

    @Shadow private Vec3d pos;

    @Inject(at = @At("HEAD"), method = "playStepSound(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V", cancellable = true)
    public void playStepSound(BlockPos pos, BlockState state, CallbackInfo info) {
        ArmorItem armorItem = null;
        for (ItemStack itemStack : ((Entity)(Object)this).getArmorItems()) {
            Item item = itemStack.getItem();

            if (item instanceof ArmorItem && ((ArmorItem)item).getSlotType().equals(EquipmentSlot.FEET)) {
                armorItem = (ArmorItem)item;
                break;
            }
        }

        if (armorItem == null) {
            return;
        }

        if (!state.getMaterial().isLiquid()) {
            ((Entity)(Object)this).playSound(armorItem.getEquipSound(), 1.0F, 1.0F);
        }
    }
}
