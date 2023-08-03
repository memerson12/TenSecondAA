package com.memerson.tenSecondAA.mixin;

import com.memerson.tenSecondAA.TenSecondAA;
import net.minecraft.server.world.ServerWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(ServerWorld.class)
public  class onTickMixin {
    @Inject(at = @At("TAIL"), method = "tick")
    private void onTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        TenSecondAA.onTick();
    }

}
