package com.memerson.tenSecondAA.mixin;

import com.memerson.tenSecondAA.TenSecondAA;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class onJoinMixin {
    @Inject(at = @At("TAIL"), method = "onPlayerConnect")
    private void onJoin(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
        TenSecondAA.onJoin(player);
    }

}

