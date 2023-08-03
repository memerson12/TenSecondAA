package com.memerson.tenSecondAA.mixin;

import com.memerson.tenSecondAA.TenSecondAA;
import net.minecraft.advancement.Advancement;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.StatType;
import net.minecraft.stat.Stats;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(PlayerManager.class)
public class onJoinMixin {
    @Unique
    private static final TenSecondAA tsaa = new TenSecondAA();

    @Inject(at = @At("TAIL"), method = "onPlayerConnect")
    private void onJoin(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
        tsaa.onJoin(player);
    }

}

