package me.involuting.contracts.contract.menu.button;

import net.j4c0b3y.api.menu.button.Button;
import net.j4c0b3y.api.menu.button.ButtonClick;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class NextPageButton extends Button {

    private final Runnable action;

    public NextPageButton(Runnable action) {
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

        meta.setDisplayName("§aNext Page");
        meta.setLore(List.of("§7Go to next page"));

        item.setItemMeta(meta);

        return item;
    }
}