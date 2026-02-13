package dev.tonimatas.config;

import dev.tonimatas.config.primary.BotConfig;

public class BotFiles {
    public static final BotConfig CONFIG = JsonFile.loadOrCreate(BotConfig.class, "bot.json");

    private BotFiles() {
        // We don't need a constructor
    }
}
