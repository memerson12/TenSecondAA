package com.memerson.tenSecondAA;

import net.fabricmc.api.ModInitializer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TenSecondAA implements ModInitializer {

    private static final int CREATIVE_TIME_SECS = 10;
    private static final int DELAY_TIME_SECS = 5;
    public static final Logger LOGGER = LogManager.getLogger("tsaa");
    private static ServerPlayerEntity player;
    private static long previousTicksInWorld = -1;

    @Override
    public void onInitialize() {

        LOGGER.info("Hello World");
    }

    public static void onJoin(ServerPlayerEntity player) {
        TenSecondAA.player = player;
        LOGGER.info("Player Joined!");
    }

    public static void onTick() {
        if(player == null) {
            LOGGER.info("Player is null");
            return;
        }
        long ticksInWorld = player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.PLAY_ONE_MINUTE));
//        LOGGER.info("Ticks in world: " + ticksInWorld);
        if(ticksInWorld < DELAY_TIME_SECS * 20) {
            player.teleport(player.prevX, player.prevY, player.prevZ);
            if(ticksInWorld % 20 == 0 && ticksInWorld != previousTicksInWorld) {
                player.sendMessage(Text.method_30163((DELAY_TIME_SECS - (ticksInWorld / 20)) + " seconds until creative"), false);
            }
        } else if(ticksInWorld < CREATIVE_TIME_SECS * 20 + DELAY_TIME_SECS * 20) {
            player.teleport(player.prevX, player.prevY, player.prevZ);
            if(player.interactionManager.isSurvivalLike()) {
                player.setGameMode(GameMode.CREATIVE);
            }
            if(ticksInWorld % 20 == 0 && ticksInWorld != previousTicksInWorld) {
                player.sendMessage(Text.method_30163((CREATIVE_TIME_SECS - ((ticksInWorld - DELAY_TIME_SECS * 20) / 20)) + " seconds until survival"), false);
            }
        } else if(!player.interactionManager.isSurvivalLike()) {
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(Text.method_30163("You are now in survival mode"), false);
        }
        previousTicksInWorld = ticksInWorld;
    }
}
