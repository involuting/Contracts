package me.involuting.contracts.contract.menu;

import me.involuting.contracts.contract.manager.ContractManager;
import me.involuting.contracts.contract.menu.button.ActiveContractsButton;
import me.involuting.contracts.contract.menu.button.ContractButton;
import me.involuting.contracts.contract.model.Contract;
import me.involuting.contracts.contract.type.ContractStatus;
import net.j4c0b3y.api.menu.Menu;
import net.j4c0b3y.api.menu.MenuSize;
import net.j4c0b3y.api.menu.button.Button;
import net.j4c0b3y.api.menu.layer.impl.BackgroundLayer;
import net.j4c0b3y.api.menu.layer.impl.ForegroundLayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Collectors;

public class ContractMenu extends Menu {

    private final ContractManager contractManager;

    private static final int[] SLOTS = {
            10,11,12,13,14,15,16,
            19,20,21,22,23,24,25,
            28,29,30,31,32,33,34
    };

    public ContractMenu(ContractManager contractManager, Player player) {
        super("Contracts", MenuSize.SIX, player);
        this.contractManager = contractManager;
    }

    @Override
    public void setup(BackgroundLayer background, ForegroundLayer foreground) {

        List<Contract> contracts = contractManager.getAll()
                .stream()
                .filter(c -> c.getStatus() == ContractStatus.ACTIVE)
                .collect(Collectors.toList());


        if (contracts.isEmpty()) {

            ItemStack empty = new ItemStack(Material.BARRIER);
            var meta = empty.getItemMeta();

            meta.setDisplayName("§cNo Contracts Available");
            meta.setLore(List.of("§7Check back later for new contracts"));

            empty.setItemMeta(meta);

            foreground.set(22, new Button() {
                @Override
                public ItemStack getIcon() {
                    return empty;
                }
            });
        }


        int index = 0;

        for (Contract contract : contracts) {

            if (index >= SLOTS.length) break;

            foreground.set(SLOTS[index], new ContractButton(contract));

            index++;
        }

        foreground.set(49, new ActiveContractsButton(contractManager));
    }
}