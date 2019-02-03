package uk.co.netbans.supportbot.Commands.Admin;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import uk.co.netbans.supportbot.CommandFramework.Command;
import uk.co.netbans.supportbot.CommandFramework.CommandArgs;
import uk.co.netbans.supportbot.CommandFramework.CommandCategory;
import uk.co.netbans.supportbot.NetBansBot;
import uk.co.netbans.supportbot.CommandFramework.CommandResult;

import java.awt.*;

public class ConfigReload{

    @Command(name = "configreload", displayName = "configreload", aliases = "reloadconfig", permission = "supportbot.command.admin.configreload", usage = "configreload", category = CommandCategory.ADMIN)
    public CommandResult onConfigReload(CommandArgs args) {
        NetBansBot bot = args.getBot();
        TextChannel channel = (TextChannel) args.getChannel();
        if (args.getArgs().length > 0) return CommandResult.INVALIDARGS;
        try {
            bot.reloadConfig();
            bot.getMessenger().sendEmbed(channel, new EmbedBuilder().setDescription("Reloaded Config!").setColor(Color.GREEN).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommandResult.SUCCESS;
    }
}