package uk.co.netbans.discordbot.Music.Command;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import uk.co.netbans.discordbot.Message.Messenger;
import uk.co.netbans.discordbot.Support.Command.Command;
import uk.co.netbans.discordbot.Support.Command.CommandResult;
import uk.co.netbans.discordbot.NetBansBot;
import uk.co.netbans.discordbot.Util;

public class Play implements Command {
    private NetBansBot bot;

    @Override
    public CommandResult onExecute(NetBansBot bot, Member sender, TextChannel channel, String label, String[] args) {
        this.bot = bot;
        if (args.length == 0)
            return CommandResult.INVALIDARGS;
        if (sender.getVoiceState().getChannel() == null) {
            bot.getMessenger().sendEmbed(channel, Messenger.NOT_VOICE, 10);
            return CommandResult.TARGETNOTFOUND;
        }
        Member botMember = bot.getJDA().getGuildById(Long.valueOf((String)bot.getConf().get("guildID"))).getMemberById(bot.getJDA().asBot().getApplicationInfo().complete().getIdLong());
        if (botMember.getVoiceState().inVoiceChannel() && !sender.getVoiceState().getChannel().getMembers().contains(bot.getJDA().getGuildById(Long.valueOf((String)bot.getConf().get("guildID"))).getMemberById(bot.getJDA().asBot().getApplicationInfo().complete().getIdLong()))) {
            bot.getMessenger().sendEmbed(channel, Messenger.NOT_SAME_VOICE, 10);
            return CommandResult.TARGETNOTFOUND;
        }

        String input = args[0];
        bot.getMusicManager().loadTrack(input, sender, channel);
        return CommandResult.SUCCESS;
    }

    @Override
    public String name() {
        return "play";
    }

    @Override
    public String desc() {
        return "Play a song or playlist!";
    }

    @Override
    public String usage() {
        return bot.getCommandPrefix() + "play <url>";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
