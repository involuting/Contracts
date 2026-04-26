package me.involuting.contracts.contract.menu.button;

import me.involuting.contracts.contract.model.Contract;

import me.involuting.contracts.contract.type.ContractStatus;
import net.j4c0b3y.api.menu.button.Button;
import net.j4c0b3y.api.menu.button.ButtonClick;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ContractButton extends Button {

    private final Contract contract;

    public ContractButton(Contract contract) {
        this.contract = contract;
    }

    @Override
    public void onClick(ButtonClick click) {

        Player player = click.getMenu().getPlayer();

        if (contract.getStatus() != ContractStatus.ACTIVE) {
            player.sendMessage("§cThis contract is not available.");
            return;
        }

        if (contract.getAssignedTo() != null) {
            player.sendMessage("§cAlready Taken.");
            return;
        }

        contract.setAssignedTo(player.getUniqueId());
        contract.setStatus(ContractStatus.INPROGRESS);

        player.sendMessage("§aContract Accepted");
        player.closeInventory();
    }

    @Override
    public ItemStack getIcon() {

        if (contract == null) {
            ItemStack item = new ItemStack(Material.BARRIER);
            var meta = item.getItemMeta();

            meta.setDisplayName("§cNo Contract");
            item.setItemMeta(meta);
            return item;
        }

        ItemStack item = new ItemStack(Material.PAPER);
        var meta = item.getItemMeta();

        if (meta == null) return item;

        meta.setDisplayName("§eContract #" +
                contract.getUuid().toString().substring(0, 8));

        meta.setLore(List.of(
                "§7Type: §f" + safe(contract.getType()),
                "§7Objective: §f" + getObjectiveLine(),
                "§7Progress: §f" + safe(contract.getProgress()) + "/" + safe(contract.getRequired()),
                "§7Reward: §a$" + safe(contract.getReward()),
                "",
                contract.getAssignedTo() == null
                        ? "§aClick to accept"
                        : "§cAlready taken"
        ));

        item.setItemMeta(meta);
        return item;
    }

    private String getObjectiveLine() {

        if (contract == null || contract.getType() == null) {
            return "Unknown objective";
        }

        return switch (contract.getType()) {

            case KILL -> {
                String target = contract.getTarget();
                yield "Kill player: " + (target != null ? target : "Unknown");
            }

            case KILL_MOB -> {
                String mob = contract.getMob().toString();
                yield "Kill mobs: " + (mob != null ? mob : "Unknown");
            }

            case BREAKBLOCK -> {
                String block = contract.getTargetBlock().toString() != null
                        ? contract.getTargetBlock().toString()
                        : null;

                yield "Mine block: " + (block != null ? block : "Unknown");
            }

            case COLLECT -> {
                String item = contract.getTargetItem().toString();
                yield "Collect: " + (item != null ? item : "Unknown");
            }

            case DELIVER -> {
                String item = contract.getDeliverItem().toString();
                yield "Deliver: " + (item != null ? item : "Unknown");
            }
        };
    }
    private String safe(Object obj) {
        return obj != null ? obj.toString() : "0";
    }
}