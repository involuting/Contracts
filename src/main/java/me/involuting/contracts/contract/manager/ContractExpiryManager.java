package me.involuting.contracts.contract.manager;

import me.involuting.contracts.contract.model.Contract;
import me.involuting.contracts.contract.type.ContractStatus;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class ContractExpiryManager {

    private final ContractManager contractManager;

    public ContractExpiryManager(ContractManager contractManager) {
        this.contractManager = contractManager;
    }

    public void start() {

        new BukkitRunnable() {

            @Override
            public void run() {

                long now = System.currentTimeMillis();

                for (Contract contract : contractManager.getAll()) {

                    if (contract.getStatus() != ContractStatus.ACTIVE
                            && contract.getStatus() != ContractStatus.INPROGRESS) {
                        continue;
                    }

                    if (contract.getExpiry() <= 0) continue;

                    if (now >= contract.getExpiry()) {
                        expire(contract);
                    }
                }
            }

        }.runTaskTimer(
                /* plugin */ me.involuting.contracts.Contracts.getInstance(),
                20L * 10,
                20L * 10
        );
    }

    private void expire(Contract contract) {

        contract.setStatus(ContractStatus.EXPIRED);
        contract.setAssignedTo(null);

        Bukkit.getLogger().info("[Contracts] Contract HAS expired: " + contract.getUuid());
    }
}