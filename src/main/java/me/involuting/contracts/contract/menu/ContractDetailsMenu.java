package me.involuting.contracts.contract.menu;

import me.involuting.contracts.contract.model.Contract;
import net.j4c0b3y.api.menu.Menu;
import net.j4c0b3y.api.menu.MenuSize;
import net.j4c0b3y.api.menu.button.Button;
import net.j4c0b3y.api.menu.layer.impl.BackgroundLayer;
import net.j4c0b3y.api.menu.layer.impl.ForegroundLayer;
import net.j4c0b3y.api.menu.pagination.PaginatedMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ContractDetailsMenu extends Menu {

    private final Contract contract;

    public ContractDetailsMenu(Contract contract, Player player) {
        super("§6Contract Details", MenuSize.THREE, player);
        this.contract = contract;
    }

    @Override
    public void setup(BackgroundLayer background, ForegroundLayer foreground) {

        Player player = getPlayer();



        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§eContract Overview");

        meta.setLore(List.of(
                "§7Type: §f" + contract.getType(),
                "§7Target: §f" + contract.getTarget(),
                "§7Progress: §a" + contract.getProgress() + "/" + contract.getRequired(),
                "§7Reward: §a$" + contract.getReward()
        ));

        item.setItemMeta(meta);
        }
    }

