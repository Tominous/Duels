package me.realized.duels.gui.inventory;

import lombok.Getter;
import me.realized.duels.DuelsPlugin;
import me.realized.duels.gui.inventory.buttons.EffectsButton;
import me.realized.duels.gui.inventory.buttons.HeadButton;
import me.realized.duels.gui.inventory.buttons.HealthButton;
import me.realized.duels.gui.inventory.buttons.HungerButton;
import me.realized.duels.gui.inventory.buttons.PotionCounterButton;
import me.realized.duels.util.StringUtil;
import me.realized.duels.util.gui.SinglePageGui;
import me.realized.duels.util.inventory.ItemBuilder;
import me.realized.duels.util.inventory.Slots;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryGui extends SinglePageGui<DuelsPlugin> {

    @Getter
    private final long creation;

    public InventoryGui(final DuelsPlugin plugin, final Player player) {
        super(plugin, StringUtil.color("&e" + player.getName()), 6);
        this.creation = System.currentTimeMillis();

        final ItemStack spacing = ItemBuilder.of(Material.STAINED_GLASS_PANE, 1, (short) 7).name(" ").build();
        Slots.run(0, 9, slot -> inventory.setItem(slot, spacing));
        set(4, new HeadButton(plugin, player));

        int potions = 0;
        int slot = 9;

        for (final ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() != Material.AIR) {
                if (item.getType() == Material.POTION && item.getDurability() == 16421) {
                    potions++;
                }

                inventory.setItem(slot, item.clone());
            }

            slot++;
        }

        slot = 48;

        for (final ItemStack item : player.getInventory().getArmorContents()) {
            if (item != null && item.getType() != Material.AIR) {
                inventory.setItem(slot, item.clone());
            }

            slot--;
        }

        inventory.setItem(49, spacing);
        set(50, new PotionCounterButton(plugin, potions));
        set(51, new EffectsButton(plugin, player));
        set(52, new HungerButton(plugin, player));
        set(53, new HealthButton(plugin, player));
    }
}
