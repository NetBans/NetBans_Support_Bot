package uk.co.netbans.supportbot.Commands.Admin;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import uk.co.netbans.supportbot.CommandFramework.Command;
import uk.co.netbans.supportbot.CommandFramework.CommandArgs;
import uk.co.netbans.supportbot.CommandFramework.CommandCategory;
import uk.co.netbans.supportbot.NetBansBot;
import uk.co.netbans.supportbot.CommandFramework.CommandResult;

import java.awt.*;

public class Embedify {


    @Command(name = "embed", displayName = "embed", permission = "supportbot.command.admin.embed", usage = "embed <title> <link|token> <username|token> [<password>]", category = CommandCategory.ADMIN)
    public CommandResult onEmbedify(CommandArgs commandArgs) {
        NetBansBot bot = commandArgs.getBot();
        String[] args = commandArgs.getArgs();
        TextChannel channel = (TextChannel) commandArgs.getChannel();
        if (args.length > 2) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(new Color(247, 207, 13));
            embed.setThumbnail("https://i.imgur.com/sBIoRP4.png");

            for (String str : args) {
                System.out.println(str);
            }

            if (!args[1].startsWith("`http")) {
                embed.setTitle(args[0].replaceAll("\"", ""));
            } else {
                embed.setTitle(args[0].replaceAll("\"", ""), args[1].replaceAll("`", ""));
            }
            if (args.length == 4) {
                embed.addField("**Username:**", args[2].replaceAll("`", ""), true);
                embed.addField("**Password:**", args[3].replaceAll("`", ""), true);
            } else {
                if (!args[1].contains("Token") || !args[1].contains("token")) {
                    embed.addField("**Username:**", args[1].replaceAll("`", ""), true);
                    embed.addField("**Password:**", args[2].replaceAll("`", ""), true);
                } else {
                    embed.addField("**" + args[1].replaceAll("`", "") + "**:", args[2].replaceAll("`", ""), false);
                }
            }
            channel.sendMessage(embed.build()).complete();
        }
        return CommandResult.INVALIDARGS;
    }
}