package uk.co.netbans.discordbot.Command.MusicCommands;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import uk.co.netbans.discordbot.Command.Command;
import uk.co.netbans.discordbot.Command.CommandResult;
import uk.co.netbans.discordbot.NetBansBot;

public class Volume implements Command {
    @Override
    public CommandResult onExecute(NetBansBot bot, Member sender, TextChannel channel, String label, String[] args) {
        if (args.length == 0)
            return CommandResult.INVALIDARGS;
        try {
            int vol = Integer.valueOf(args[0]);
            bot.getMusicManager().setVolume(channel.getGuild(), channel, vol);
            return CommandResult.SUCCESS;
        } catch (NumberFormatException e) {
            return CommandResult.INVALIDARGS;
        }
    }

    @Override
    public String name() {
        return "volume";
    }

    @Override
    public String desc() {
        return "Change the volume of the audio player!";
    }

    @Override
    public String usage() {
        return "!volume 1";
    }

    @Override
    public String[] aliases() {
        return new String[]{"vol"};
    }
}
