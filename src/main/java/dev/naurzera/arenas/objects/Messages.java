package dev.naurzera.arenas.objects;

import dev.naurzera.arenas.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Messages
    extends MessageDelivery
{
    private final FileConfiguration config;
    public List<String>
    ENTERING,
    LEAVING,
    DEATH,
    OTHER_ENTERING,
    OTHER_LEAVING,
    OTHER_DEATH,
    STAFF_CREATED,
    STAFF_REMOVE,
    STAFF_KIT_SETTED,
    STAFF_ENRTY_SETTED,
    STAFF_SPAWN_SETTED,
    ERROR_SPAWN_NOT_SET,
    ERROR_EXIT_NOT_SET,
    ERROR_CREATING,
    ERROR_ARENA_NOT_EXISTS,
    ERROR_YOU_ARE_IN_ANOTHER_ARENA,
    PERMISSION_DENIED,
    ERROR_BLOCKED_COMMAND,
    HELP,
    HELP_STAFF,
    INVENTORY_NOT_CLEARED;

    public Messages()
    {
        this.config = Main.getInstance().getConfig();
        ENTERING = get("entrando");
        LEAVING = get("saindo");
        DEATH = get("morrendo");
        OTHER_ENTERING = get("outro-entrando");
        OTHER_LEAVING = get("outro-saindo");
        OTHER_DEATH = get("outro-morrendo");
        STAFF_CREATED = get("arena-criada");
        STAFF_REMOVE = get("arena-removida");
        STAFF_KIT_SETTED = get("kit-definido");
        STAFF_ENRTY_SETTED = get("entrada-definida");
        STAFF_SPAWN_SETTED = get("spawn-definido");
        ERROR_CREATING = get("erro-ao-criar");
        ERROR_ARENA_NOT_EXISTS = get("arena-nao-encontrada");
        ERROR_YOU_ARE_IN_ANOTHER_ARENA = get("voce-esta-em-outra-arena");
        ERROR_SPAWN_NOT_SET = get("entrada-nao-definida");
        ERROR_EXIT_NOT_SET = get("saida-nao-definida");
        PERMISSION_DENIED = get("sem-permissao");
        ERROR_BLOCKED_COMMAND = get("comandos-bloqueados");
        HELP = get("command-help");
        HELP_STAFF = get("command-help-staff");
        INVENTORY_NOT_CLEARED = get("inventario-com-itens");
    }

    private List<String> get(String message)
    {
        return config.getStringList("mensagens."+message);
    }

}
