package bioast.mods.gt6m.mixin.gregtech;

import bioast.mods.gt6m.Config;
import gregapi.data.TD;
import gregapi.item.IItemEnergy;
import gregapi.item.multiitem.MultiItemTool;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static gregapi.data.CS.T;

@Mixin(value = MultiItemTool.class, remap = false)
public abstract class GT_MultiItemTool_Mixin {

    @Shadow
    public abstract IItemEnergy getEnergyStats(ItemStack aStack);

    /*
     * If a tool uses Energy do not use Duribility anymore!
     */
    @Inject(
        method = "doDamage(Lnet/minecraft/item/ItemStack;JLnet/minecraft/entity/EntityLivingBase;Z)Z",
        at = @At(
            value = "INVOKE",
            target = "Lgregapi/item/multiitem/MultiItemTool;getEnergyStats(Lnet/minecraft/item/ItemStack;)Lgregapi/item/IItemEnergy;",
            shift = At.Shift.BEFORE),
        cancellable = true)
    public void doDamage(ItemStack aStack, long aAmount, EntityLivingBase aPlayer, boolean aAllowBreaking,
                         CallbackInfoReturnable<Boolean> cir) {
        IItemEnergy tElectric = getEnergyStats(aStack);
        if (tElectric != null && Config.ELECTRIC_DURIBILITY_NO.getBoolean()) {
            cir.setReturnValue(
                ((MultiItemTool) (Object) this)
                    .useEnergy(TD.Energy.EU, aStack, aAmount, aPlayer, null, null, 0, 0, 0, T));
        }
    }
}
