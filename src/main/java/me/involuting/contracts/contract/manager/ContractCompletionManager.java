package me.involuting.contracts.contract.manager;

import me.involuting.contracts.contract.model.Contract;
import me.involuting.contracts.contract.type.ContractStatus;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ContractCompletionManager {

    private final ContractManager contractManager;

    public ContractCompletionManager(ContractManager contractManager) {
        this.contractManager = contractManager;
    }

    public void complete(Contract contract, Player player) {

        if (contract.getStatus() == ContractStatus.COMPLETED) return;

        contract.setStatus(ContractStatus.COMPLETED);

        if (contract.getRewards() != null) {
            int money = contract.getRewards().getMoney();


            var economy = Bukkit.getServicesManager()
                    .getRegistration(net.milkbowl.vault.economy.Economy.class);

            if (economy != null) {
                economy.getProvider().depositPlayer(player, money);
            } else {
                player.sendMessage("§eVault not found, skipping money reward.");
            }

            player.sendMessage("§a+$" + money + " contract reward!");
        }


        if (contract.getRewards() != null) {
            player.giveExp(contract.getRewards().getExp());
        }


        player.sendMessage("§6§lContract Completed");
        player.sendMessage("§7You successfully finished your contract.");


        Bukkit.broadcastMessage("§a" + player.getName() + " completed a contract!");
    }
}