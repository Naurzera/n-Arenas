package dev.naurzera.arenas.command;

import dev.naurzera.arenas.Main;
import dev.naurzera.arenas.objects.Arena;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.scheduler.BukkitRunnable;

public class ArenaListCommand
    extends BukkitCommand
{
    public ArenaListCommand()
    {
        super("arenas");
    }

    @Override
    public boolean execute(CommandSender sender, String arg, String[] args)
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                sender.sendMessage("§e=-----------= Arenas =------------=");
                String arenas = "";
                for (Arena arena : Main.getInstance().getArenas().getAll())
                {
                    if (arenas.equals("")) arenas = arena.getName();
                    else arenas = arenas+" ,"+arena.getName();
                }
                sender.sendMessage(ChatColor.YELLOW+" "+arenas);
            }
        }.runTaskAsynchronously(Main.getInstance());
        return false;
    }
}
