package me.involuting.contracts.contract.generator;

import me.involuting.contracts.Contracts;
import me.involuting.contracts.contract.manager.ContractManager;
import me.involuting.contracts.contract.model.Contract;
import me.involuting.contracts.contract.type.ContractStatus;
import me.involuting.contracts.contract.type.ContractType;
import org.bukkit.Bukkit;

import java.util.Random;

public class ContractGenerator {

    private final ContractManager contractManager;
    private final ContractFactory contractFactory;
    private final Random random = new Random();

    public ContractGenerator(ContractManager contractManager, ContractFactory contractFactory) {
        this.contractManager = contractManager;
        this.contractFactory = contractFactory;
    }

    public void startDynamicGeneration() {

        Bukkit.getScheduler().runTaskTimer(
                Contracts.getInstance(),
                () -> {

                    long active = contractManager.getAll()
                            .stream()
                            .filter(c -> c.getStatus() == ContractStatus.ACTIVE)
                            .count();


                    if (active >= 10) return;

                    Contract contract = generateContract();

                    contractManager.addContract(contract);

                    Bukkit.broadcastMessage("§6New Contract is available!");
                },
                20L * 60,
                20L * 300
        );
    }


    private Contract generateContract() {

        return switch (randomType()) {

            case KILL -> contractFactory.createRandomKillContract();

            case COLLECT -> contractFactory.createRandomCollectContract();

            case DELIVER -> contractFactory.createRandomDeliverContract();

            case BREAKBLOCK -> contractFactory.createRandomBlockContract();

            case KILL_MOB -> contractFactory.createRandomMobContract();
        };
    }


    private ContractType randomType() {

        ContractType[] types = ContractType.values();
        return types[random.nextInt(types.length)];
    }
}