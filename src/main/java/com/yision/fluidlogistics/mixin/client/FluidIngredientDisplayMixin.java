package com.yision.fluidlogistics.mixin.client;

import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import com.yision.fluidlogistics.foundation.fluid.FluidIngredientDisplay;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CreateRecipeCategory.class)
public abstract class FluidIngredientDisplayMixin {
    @Redirect(method = "addFluidSlot(Lmezz/jei/api/gui/builder/IRecipeLayoutBuilder;IILnet/neoforged/neoforge/fluids/crafting/SizedFluidIngredient;)Lmezz/jei/api/gui/builder/IRecipeSlotBuilder;",
        at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/fluids/crafting/SizedFluidIngredient;getFluids()[Lnet/neoforged/neoforge/fluids/FluidStack;"), remap = false)
    private static FluidStack[] fluidlogistics$displayMatchingSources(SizedFluidIngredient ingredient) {
        return FluidIngredientDisplay.matchingSources(ingredient);
    }
}
