package me.realized.duels.hooks;

import me.NoChance.PvPManager.Managers.PlayerHandler;
import me.NoChance.PvPManager.PvPManager;
import me.NoChance.PvPManager.PvPlayer;
import me.realized.duels.DuelsPlugin;
import me.realized.duels.util.hook.PluginHook;
import org.bukkit.entity.Player;

public class PvPManagerHook extends PluginHook<DuelsPlugin> {

    public PvPManagerHook(final DuelsPlugin plugin) {
        super(plugin, "PvPManager");
    }

    public void removeTag(final Player player) {
        final PvPManager plugin = (PvPManager) getPlugin();
        final PlayerHandler playerHandler = plugin.getPlayerHandler();
        final PvPlayer pvPlayer = playerHandler.get(player);

        if (pvPlayer == null) {
            return;
        }

        playerHandler.untag(pvPlayer);
    }
}
