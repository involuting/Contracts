package me.involuting.contracts.listener.player;

import me.involuting.contracts.contract.manager.ContractCompletionManager;
import me.involuting.contracts.contract.manager.ContractManager;
import me.involuting.contracts.contract.model.Contract;
import me.involuting.contracts.contract.type.ContractStatus;
import me.involuting.contracts.contract.type.ContractType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Collection;

public class KillListener implements Listener {

    private final ContractManager contractManager;
    private final ContractCompletionManager completionManager;

    public KillListener(ContractManager contractManager,
                        ContractCompletionManager completionManager) {
        this.contractManager = contractManager;
        this.completionManager = completionManager;
    }

    @EventHandler
    public void onKill(PlayerDeathEvent event) {

        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        if (killer == null) return;

        Collection<Contract> contracts = contractManager.getAll();

        for (Contract contract : contracts) {

            if (contract.getStatus() != ContractStatus.ACTIVE) continue;

            if (contract.getAssignedTo() == null) continue;

            if (!contract.getAssignedTo().equals(killer.getUniqueId())) continue;


            if (contract.getType() == ContractType.KILL) {

                if (!contract.getTarget().equalsIgnoreCase(victim.getName())) continue;

                progressContract(contract, killer, 1);
            }


            if (contract.getType() == ContractType.KILL_MOB) {

                Entity killerEntity = event.getEntity().getKiller();
                if (killerEntity == null) return;


                EntityType type = victim.getType();

                if (contract.getMob() == null) continue;

                if (contract.getMob() != type) continue;

                progressContract(contract, killer, 1);
            }
        }
    }

    private void progressContract(Contract contract, Player killer, int amount) {

        contract.setProgress(contract.getProgress() + amount);

        killer.sendMessage("§aContract Updated: §f"
                + contract.getProgress() + "/" + contract.getRequired());

        if (contract.getProgress() >= contract.getRequired()) {
            completionManager.complete(contract, killer);
        }
    }
}