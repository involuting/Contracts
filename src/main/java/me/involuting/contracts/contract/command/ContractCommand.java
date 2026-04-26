package me.involuting.contracts.contract.command;

import me.involuting.contracts.contract.manager.ContractManager;
import me.involuting.contracts.contract.menu.ContractMenu;
import me.involuting.contracts.contract.menu.ActiveContractsMenu;
import me.involuting.contracts.contract.model.Contract;
import me.involuting.contracts.contract.type.ContractStatus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class ContractCommand implements CommandExecutor {

    private final ContractManager contractManager;

    public ContractCommand(ContractManager contractManager) {
        this.contractManager = contractManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cOnly players can use this command.");
            return true;
        }
        if (args.length == 0) {
            new ContractMenu(contractManager, player).open();
            return true;
        }


        if (args[0].equalsIgnoreCase("active")) {

            new ActiveContractsMenu(contractManager, player).open();
            return true;
        }

        player.sendMessage("§cUsage: /contract or /contract active");
        return true;
    }
}