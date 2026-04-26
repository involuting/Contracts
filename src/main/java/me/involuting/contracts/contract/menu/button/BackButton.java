package me.involuting.contracts.contract.menu.button;

import net.j4c0b3y.api.menu.button.Button;
import net.j4c0b3y.api.menu.button.ButtonClick;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BackButton extends Button {

    private final Runnable action;

    public BackButton(Runnable action) {
        this.action = action;
    }

    @Override
    public void onClick(ButtonClick click) {
        action.run();
    }

    @Override
    public ItemStack getIcon() {

        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§cBack");
        meta.setLore(List.of("§7Click to go back"));

        item.setItemMeta(meta);

        return item;
    }
}