package uk.co.netbans.discordbot.Message;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Messenger {
    private final ScheduledExecutorService executor;

    public Messenger() {
        this.executor = Executors.newScheduledThreadPool(2);
    }

    public void sendMessage(TextChannel channel, String message) {
        this.sendMessage(channel, new MessageBuilder().append(message).build(), 0);
    }

    public void sendMessage(TextChannel channel, String message, int lifetime) {
        this.sendMessage(channel, new MessageBuilder().append(message).build(), lifetime);
    }

    public void sendEmbed(TextChannel channel, EmbedBuilder embed) {
        this.sendMessage(channel, new MessageBuilder().setEmbed(embed.build()).build(), 0);
    }

    public void sendEmbed(TextChannel channel, EmbedBuilder embed, int lifetime) {
        this.sendMessage(channel, new MessageBuilder().setEmbed(embed.build()).build(), lifetime);
    }

    public void sendMessage(TextChannel channel, Message message, int lifetime) {
        final Message completedMessage = channel.sendMessage(message).complete();

        if (lifetime != 0)
            this.executor.schedule(() -> message.delete().reason("netbans_message_autoremoval").complete(), lifetime, TimeUnit.SECONDS);
    }
}
