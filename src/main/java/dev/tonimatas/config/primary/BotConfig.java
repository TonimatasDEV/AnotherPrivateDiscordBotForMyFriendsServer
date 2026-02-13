package dev.tonimatas.config.primary;

import dev.tonimatas.config.JsonFile;

public class BotConfig extends JsonFile {
    public final String token;
    
    @SuppressWarnings("unused")
    public BotConfig() {
        this("default-token");
    }

    public BotConfig(String token) {
        this.token = token;
    }

    @Override
    protected String getFilePath() {
        return "bot.json";
    }
}
