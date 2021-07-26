package dev.naurzera.arenas.objects;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Arena
    extends Messages
    implements ConfigurationSerializable
{
    final String name;
    ItemStack[] contents;
    ItemStack[] armor;
    Location location;
    final List<Player> activePlayers = new ArrayList<>();
    boolean open;

    public Arena(){name="null";}

    public Arena(String name,ItemStack[] contents, ItemStack[] armor, Location location)
    {
        this.name = name;
        if (contents!=null) this.contents = contents.clone();
        else this.contents = null;
        if (armor!=null) this.armor = armor.clone();
        else this.armor = null;
        this.location = location;
        this.open = true;
    }

    public Arena(Arena arena)
    {
        this.name = arena.getName();
        if (arena.getContents()!=null) this.contents = arena.getContents().clone();
        else this.contents = null;
        if (arena.getArmor()!=null) this.armor = arena.getArmor().clone();
        else this.armor = null;
        this.location = arena.getLocation();
        this.activePlayers.clear();
        this.open = arena.isOpen();
    }

    public String getName()
    {
        return name;
    }

    public ItemStack[] getContents()
    {
        return contents;
    }
    public void setContents(ItemStack[] contents)
    {
        this.contents = contents;
    }

    public ItemStack[] getArmor()
    {
        return this.armor;
    }
    public void setArmor(ItemStack[] armor)
    {
        this.armor = armor;
    }

    public Location getLocation()
    {
        return this.location;
    }
    public void setLocation(Location location)
    {
        this.location = location;
    }

    public List<Player> getPlayers()
    {
        return this.activePlayers;
    }
    public void addPlayer(Player player)
    {
        this.activePlayers.add(player);
    }
    public void removePlayer(Player player)
    {
        this.activePlayers.remove(player);
    }
    public boolean containsPlayer(Player player)
    {
        return this.activePlayers.contains(player);
    }

    public boolean isOpen()
    {
        return this.open;
    }
    public void close()
    {
        this.open = false;
    }
    public void open()
    {
        this.open = true;
    }

    @Override
    public Map<String, Object> serialize()
    {
        Map<String, Object> result = new LinkedHashMap();
        result.put("name", this.name);
        if (this.contents != null)
        {
            result.put("contents", this.contents);
        }
        if (this.armor != null)
        {
            result.put("armor", this.armor);
        }

        if (this.location != null)
        {
            result.put("location", this.location);
        }

        return result;
    }

    public static Arena deserialize(Map<String, Object> args)
    {
        String name = "";
        ItemStack[] contents = null;
        ItemStack[] armor = null;
        Location location = null;

        if (args.containsKey("name"))
        {
            name = (String) args.get("name");
        }

        if (args.containsKey("contents"))
        {
            contents = (ItemStack[]) args.get("contents");
        }

        if (args.containsKey("armor"))
        {
            armor = (ItemStack[]) args.get("armor");
        }

        if (args.containsKey("location"))
        {
            location = (Location) args.get("location");
        }

        return new Arena(name, contents, armor, location);
    }

}
