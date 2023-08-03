package com.memerson.tenSecondAA;

import net.fabricmc.api.ModInitializer;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.GameMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TenSecondAA implements ModInitializer {

    private static final int CREATIVE_TIME_SECS = 10;
    public static final Logger LOGGER = LogManager.getLogger("tsaa");
    private static ServerPlayerEntity player;

    @Override
    public void onInitialize() {
        LOGGER.info("Hello World");
    }

    public void onJoin(ServerPlayerEntity player) {
        TenSecondAA.player = player;
        LOGGER.info("Player Joined!");
        long ticksInWorld = player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.PLAY_ONE_MINUTE));
        if(ticksInWorld < CREATIVE_TIME_SECS * 20) {
            player.setGameMode(GameMode.CREATIVE);
        }
    }

    public void onTick() {
        if(player == null) {
            LOGGER.info("Player is null");
            return;
        }
        long ticksInWorld = player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.PLAY_ONE_MINUTE));
        if(ticksInWorld >= CREATIVE_TIME_SECS * 20 && !player.interactionManager.isSurvivalLike()) {
            player.setGameMode(GameMode.SURVIVAL);
        } else if(ticksInWorld < CREATIVE_TIME_SECS * 20) {
            // Stop the player from being able to move
            player.teleport(player.prevX, player.prevY, player.prevZ);

        }
    }
}
