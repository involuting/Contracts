package me.involuting.contracts.listener.world;

import me.involuting.contracts.contract.manager.ContractCompletionManager;
import me.involuting.contracts.contract.manager.ContractManager;
import me.involuting.contracts.contract.model.Contract;
import me.involuting.contracts.contract.type.ContractStatus;
import me.involuting.contracts.contract.type.ContractType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class WorldListener implements Listener {

    private final ContractManager contractManager;
    private final ContractCompletionManager completionManager;

    public WorldListener(ContractManager contractManager,
                         ContractCompletionManager completionManager) {
        this.contractManager = contractManager;
        this.completionManager = completionManager;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();
        Material broken = event.getBlock().getType();

        for (Contract contract : contractManager.getAll()) {

            if (contract.getStatus() != ContractStatus.INPROGRESS) continue;
            if (contract.getType() != ContractType.BREAKBLOCK) continue;
            if (contract.getAssignedTo() == null) continue;
            if (!contract.getAssignedTo().equals(player.getUniqueId())) continue;

            if (contract.getTargetBlock() == null || contract.getTargetBlock() != broken) continue;

            contract.setProgress(contract.getProgress() + 1);

            player.sendMessage("§aBlock Contract Progress: §f"
                    + contract.getProgress() + "/" + contract.getRequired());


            if (contract.getProgress() >= contract.getRequired()) {
                completionManager.complete(contract, player);
                return;
            }



        }


    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {

        Material item = event.getItem().getItemStack().getType();

        contractManager.getAll().forEach(contract -> {

            if (contract.getStatus() != ContractStatus.ACTIVE) return;
            if (contract.getType() != ContractType.COLLECT) return;
            if (!contract.getTarget().equalsIgnoreCase(item.name())) return;

            contract.setProgress(contract.getProgress() + event.getItem().getItemStack().getAmount());
        });
    }


}