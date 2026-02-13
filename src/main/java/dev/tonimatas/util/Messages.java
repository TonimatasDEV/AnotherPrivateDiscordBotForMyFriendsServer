package dev.tonimatas.util;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.interactions.InteractionHook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Messages {
    private static final Logger LOGGER = LoggerFactory.getLogger(Messages.class);

    private Messages() {
        // We don't need a constructor
    }

    public static <T> Consumer<T> deleteBeforeX(long seconds) {
        return thing -> {
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            switch (thing) {
                case InteractionHook hook -> hook.deleteOriginal().queue(null, null);
                case Message msg -> msg.delete().queue(null, null);
                default -> LOGGER.warn("Messages#deleteBeforeX is not compatible with: {}", thing.getClass().getName());
            }
        };
    }
}
