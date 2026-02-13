package dev.tonimatas.config.primary;

import dev.tonimatas.config.JsonFile;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Role;

import java.util.Objects;

public class BotConfig extends JsonFile {
    public final String token;
    public final String allowedRole;
    
    @SuppressWarnings("unused")
    public BotConfig() {
        this("default-token", "test");
    }

    public BotConfig(String token, String allowedRole) {
        this.token = token;
        this.allowedRole = allowedRole;
    }

    @Override
    protected String getFilePath() {
        return "bot.json";
    }
    
    public Role getAllowedRole(JDA jda) {
        return Objects.requireNonNull(jda.getRoleById(allowedRole));
    }
}
