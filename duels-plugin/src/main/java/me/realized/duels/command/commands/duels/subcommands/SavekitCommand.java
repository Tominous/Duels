package me.realized.duels.command.commands.duels.subcommands;

import me.realized.duels.DuelsPlugin;
import me.realized.duels.command.BaseCommand;
import me.realized.duels.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SavekitCommand extends BaseCommand {

    public SavekitCommand(final DuelsPlugin plugin) {
        super(plugin, "savekit", "savekit [name]", "Saves a kit with given name.", null, 2, true);
    }

    @Override
    protected void execute(final CommandSender sender, final String label, final String[] args) {
        final String name = StringUtils.join(args, " ", 1, args.length);

        if (!StringUtil.isAlphanumeric(name)) {
            // send msg
            return;
        }

        if (kitManager.get(name) != null) {
            return;
        }

        kitManager.create((Player) sender, name);
        sender.sendMessage("Saved kit " + name);
    }
}
