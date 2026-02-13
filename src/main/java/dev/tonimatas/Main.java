package dev.tonimatas;

import dev.tonimatas.commands.PeakImpostor;
import dev.tonimatas.config.BotFiles;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import revxrsal.commands.Lamp;
import revxrsal.commands.jda.JDALamp;
import revxrsal.commands.jda.JDAVisitors;
import revxrsal.commands.jda.actor.SlashCommandActor;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        JDA jda = JDABuilder.createDefault(BotFiles.CONFIG.token)
                .enableIntents(List.of(GatewayIntent.values()))
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableCache(List.of(CacheFlag.values()))
                .setAutoReconnect(true)
                .build();

        Lamp<SlashCommandActor> lamp = JDALamp.builder().build();

        lamp.register(
                new PeakImpostor()
        );

        lamp.accept(JDAVisitors.slashCommands(jda));

        jda.getPresence().setActivity(Activity.of(Activity.ActivityType.WATCHING, "The Guild"));
        jda.awaitReady();

        addStopHook(jda);


        LOGGER.info("Done!");
    }

    private static void addStopHook(JDA jda) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info("Stopping...");

            jda.shutdown();

            try {
                if (!jda.awaitShutdown(10, TimeUnit.SECONDS)) {
                    jda.shutdownNow();
                }
            } catch (InterruptedException e) {
                LOGGER.error("Error stopping JDA: {}", e.getMessage());
                Thread.currentThread().interrupt();
            }

            LOGGER.info("Stopped!");
        }));
    }
}