package dev.naurzera.arenas.objects;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class MessageDelivery
{
    public void sendMessage(CommandSender player, List<String> type)
    {
        for (String line : type)
        {
            sendMessage(player, line);
        }
    }
    public void sendMessage(List<CommandSender> players, String type)
    {
        for (CommandSender player : players)
        {
            sendMessage(player, type);
        }
    }
    public void sendMessage(List<CommandSender> players, List<String> types)
    {
        for (CommandSender player : players)
        {
            for (String type : types)
            {
                sendMessage(player, type);
            }
        }
    }
    public void sendMessage(CommandSender player, String line)
    {
        player.sendMessage(line);
    }

    /**
     *
     * @NullAble player = The name that will replace %player%
     * @NullAble arena = The name that will replace %arena%
     *
     */
    public List<String> formatMessage(List<String> message, String player, String arena)
    {
        List<String> messages = new ArrayList<>();
        for (String line : message)
        {
            messages.add(formatMessage(line, player, arena));
        }
        return messages;
    }

    public String formatMessage(String message, String player, String arena)
    {
        String player1,arena1;

        if (player==null) player1="";
        else player1 = player;

        if (arena==null) arena1="";
        else arena1 = arena;

        return message.replaceAll("%player%",player1).replaceAll("%arena%", arena1);
    }
}
