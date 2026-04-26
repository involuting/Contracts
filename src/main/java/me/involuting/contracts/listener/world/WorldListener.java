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

import java.util.Collection;

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

        Collection<Contract> contracts = contractManager.getAll();

        for (Contract contract : contracts) {

            if (contract.getStatus() != ContractStatus.ACTIVE) continue;

            if (contract.getType() != ContractType.BREAKBLOCK) continue;

            if (contract.getAssignedTo() == null) continue;

            if (!contract.getAssignedTo().equals(player.getUniqueId())) continue;

            if (contract.getTargetBlock() != broken) continue;

            contract.setProgress(contract.getProgress() + 1);

            player.sendMessage("§aBlock Contract Progress: §f"
                    + contract.getProgress() + "/" + contract.getRequired());

            if (contract.getProgress() >= contract.getRequired()) {
                completionManager.complete(contract, player);
            }
        }
    }
}

