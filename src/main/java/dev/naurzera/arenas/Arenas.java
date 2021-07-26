package dev.naurzera.arenas;

import dev.naurzera.arenas.objects.Arena;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class Arenas
{
    private final List<Arena> arenas = new ArrayList<>();
    private Location saida;

    public List<Arena> getAll()
    {
        return this.arenas;
    }
    public void add(Arena arena)
    {
        this.arenas.add(arena);
    }
    public void remove(Arena arena)
    {
        this.arenas.remove(arena);
    }
    public boolean contains(Arena arena)
    {
        return this.arenas.contains(arena);
    }
    public boolean contains(String arena)
    {
        for (Arena a : this.arenas)
        {
            if (a.getName().equalsIgnoreCase(arena)) return true;
        }
        return false;
    }
    public Arena get(String arena)
    {
        for (Arena a : this.arenas)
        {
            if (a.getName().equalsIgnoreCase(arena)) return a;
        }
        return null;
    }
    public Location getSaida()
    {
        return this.saida;
    }
    public void setSaida(Location saida)
    {
        this.saida = saida;
    }
}
