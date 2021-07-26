package dev.naurzera.arenas.manager;

import dev.naurzera.arenas.Main;
import dev.naurzera.arenas.objects.Arena;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ArenaManager
{
    public boolean createArena(CommandSender sender, String arena)
    {
        if (!(sender instanceof Player)) return false;
        if (Main.getInstance().getArenas().contains(arena)) return false;
        Player player = (Player) sender;
        try
        {
            ItemStack[] contents = player.getInventory().getContents().clone();
            ItemStack[] armor = player.getInventory().getArmorContents();
            Location location = player.getLocation().clone();
            Arena arena1 = new Arena(arena, contents, armor, location);
            Main.getInstance().getBuffer().saveArena(arena1);
            Arena arena2 = Main.getInstance().getBuffer().getArena(arena1.getName());
            Main.getInstance().getArenas().add(arena2);
            Main.getInstance().registerCommand(arena2);
            msg(sender, arena, Main.getInstance().getMessages().STAFF_CREATED);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean removeArena(CommandSender sender, String arena)
    {
        if (!(sender instanceof Player)) return false;
        if (!Main.getInstance().getArenas().contains(arena)) return false;
        try
        {
            Arena arena1 = Main.getInstance().getArenas().get(arena);
            Main.getInstance().getBuffer().removeArena(Main.getInstance().getArenas().get(arena));
            Main.getInstance().getArenas().remove(arena1);
            msg(sender, arena, Main.getInstance().getMessages().STAFF_REMOVE);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean setKit(CommandSender sender, String arena)
    {
        if (!(sender instanceof Player)) return false;
        if (!Main.getInstance().getArenas().contains(arena)) return false;
        Player player = (Player) sender;
        ItemStack[] contents = player.getInventory().getContents().clone();
        ItemStack[] armor = player.getInventory().getArmorContents();
        Main.getInstance().getArenas().get(arena).setContents(contents);
        Main.getInstance().getArenas().get(arena).setArmor(armor);
        Main.getInstance().getBuffer().saveArena(Main.getInstance().getArenas().get(arena));
        msg(sender, arena, Main.getInstance().getMessages().STAFF_KIT_SETTED);
        return true;
    }
    public boolean setArenaLocation(CommandSender sender, String arena)
    {
        if (!(sender instanceof Player)) return false;
        if (!Main.getInstance().getArenas().contains(arena)) return false;
        Player player = (Player) sender;
        Location location = player.getLocation().clone();
        Main.getInstance().getArenas().get(arena).setLocation(location);
        Main.getInstance().getBuffer().saveArena(Main.getInstance().getArenas().get(arena));
        msg(sender, "", Main.getInstance().getMessages().STAFF_ENRTY_SETTED);
        return true;
    }
    public boolean setSpawn(CommandSender sender)
    {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        Location spawn = player.getLocation();
        Main.getInstance().setSpawn(spawn);
        Main.getInstance().getBuffer().setSpawn(spawn);
        msg(sender, "", Main.getInstance().getMessages().STAFF_SPAWN_SETTED);
        return true;
    }

    void msg(CommandSender sender, String arena, List<String> type)
    {
        List<String> message = Main.getInstance().getMessages()
                .formatMessage(type,sender.getName(),arena);
        Main.getInstance().getMessages().sendMessage(sender, message);
    }
}
