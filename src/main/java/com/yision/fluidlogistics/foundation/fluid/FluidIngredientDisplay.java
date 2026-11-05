package com.yision.fluidlogistics.foundation.fluid;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

/** Source and flowing tag members are one container fluid, not two recipe alternatives. */
public final class FluidIngredientDisplay {
    private FluidIngredientDisplay() { }

    public static FluidStack[] matchingSources(SizedFluidIngredient ingredient) {
        List<FluidStack> display = new ArrayList<>();
        for (FluidStack original : ingredient.getFluids()) {
            FluidStack candidate = original.copy();
            if (original.getFluid() instanceof FlowingFluid flowing) {
                var source = new FluidStack(net.minecraft.core.registries.BuiltInRegistries.FLUID.wrapAsHolder(flowing.getSource()), original.getAmount(), original.getComponentsPatch());
                // Keep an explicitly flowing-only ingredient intact; only collapse real matching aliases.
                if (ingredient.test(source))
                    candidate = source;
            }
            FluidStack resolved = candidate;
            if (!resolved.isEmpty() && display.stream().noneMatch(existing -> FluidStack.matches(existing, resolved)))
                display.add(resolved);
        }
        return display.toArray(FluidStack[]::new);
    }
}
