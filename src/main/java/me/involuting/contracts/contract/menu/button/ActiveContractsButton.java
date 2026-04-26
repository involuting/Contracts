package me.involuting.contracts.contract.menu.button;

import me.involuting.contracts.contract.manager.ContractManager;
import me.involuting.contracts.contract.menu.ActiveContractsMenu;
import net.j4c0b3y.api.menu.button.Button;
import net.j4c0b3y.api.menu.button.ButtonClick;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ActiveContractsButton extends Button {

    private final ContractManager contractManager;

    public ActiveContractsButton(ContractManager contractManager) {
        this.contractManager = contractManager;
    }

    @Override
    public void onClick(ButtonClick click) {
        Player player = click.getMenu().getPlayer();
        new ActiveContractsMenu(contractManager, player).open();
    }

    @Override
    public ItemStack getIcon() {

        ItemStack item = new ItemStack(Material.CLOCK);
        ItemMeta meta = item.getItemMeta();

        if (meta == null) return item;

        meta.setDisplayName("§6Active Contracts");
        meta.setLore(List.of(
                "§7View and track your active contracts",
                "",
                "§eClick to open"
        ));

        item.setItemMeta(meta);
        return item;
    }
}