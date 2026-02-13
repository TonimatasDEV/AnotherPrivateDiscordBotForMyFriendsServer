package dev.tonimatas.commands;

import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Description;
import revxrsal.commands.annotation.Named;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.jda.actor.SlashCommandActor;
import revxrsal.commands.jda.annotation.GuildOnly;

public class PeakImpostor {
    
    @Command("peak")
    @Description("This command send a private DM to every member who reacted to the specified message. You are a climber... or not.")
    @GuildOnly
    @Subcommand("impostor")
    public void execute(SlashCommandActor actor, @Named("message-id") @Description("The message id of the party") String messageId) {
        
    }
}
