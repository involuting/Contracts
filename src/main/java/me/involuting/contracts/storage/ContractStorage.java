package me.involuting.contracts.storage;

import me.involuting.contracts.Contracts;
import me.involuting.contracts.contract.manager.ContractManager;
import me.involuting.contracts.contract.model.Contract;
import me.involuting.contracts.contract.type.ContractStatus;
import me.involuting.contracts.contract.type.ContractType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.Material;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ContractStorage {

    private final Contracts plugin;
    private final ContractManager contractManager;

    private File file;
    private FileConfiguration config;

    public ContractStorage(Contracts plugin, ContractManager contractManager) {
        this.plugin = plugin;
        this.contractManager = contractManager;
    }

    public void load() {

        file = new File(plugin.getDataFolder(), "contracts.yml");

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource("contracts.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(file);

        loadContracts();
    }

    private void loadContracts() {

        if (!config.isConfigurationSection("contracts")) return;

        for (String key : config.getConfigurationSection("contracts").getKeys(false)) {

            String path = "contracts." + key;

            UUID id = UUID.fromString(key);

            Contract contract = new Contract(
                    id,
                    UUID.fromString(config.getString(path + ".creator"))
            );


            String assigned = config.getString(path + ".assignedTo");
            if (assigned != null && !assigned.equals("null")) {
                contract.setAssignedTo(UUID.fromString(assigned));
            }

            contract.setType(ContractType.valueOf(config.getString(path + ".type")));
            contract.setStatus(ContractStatus.valueOf(config.getString(path + ".status")));

            contract.setRequired(config.getInt(path + ".amount"));
            contract.setProgress(config.getInt(path + ".progress"));
            contract.setExpiry(config.getLong(path + ".expiry"));
            contract.setReward(config.getInt(path + ".reward"));



            if (contract.getType() == ContractType.KILL) {
                contract.setTarget(config.getString(path + ".target"));
            }

            if (contract.getType() == ContractType.BREAKBLOCK) {
                String mat = config.getString(path + ".blockTarget");
                if (mat != null) {
                    contract.setTargetBlock(Material.valueOf(mat));
                }
            }

            if (contract.getType() == ContractType.KILL_MOB) {
                String mob = config.getString(path + ".mobTarget");
                if (mob != null) {
                    contract.setMob(EntityType.valueOf(mob));
                }
            }

            contractManager.addContract(contract);
        }
    }

    public void save() {

        config.set("contracts", null);

        for (Contract contract : contractManager.getAll()) {

            String path = "contracts." + contract.getUuid();

            config.set(path + ".creator", contract.getCreator().toString());

            config.set(path + ".assignedTo",
                    contract.getAssignedTo() != null
                            ? contract.getAssignedTo().toString()
                            : null
            );

            config.set(path + ".type", contract.getType().name());
            config.set(path + ".status", contract.getStatus().name());

            config.set(path + ".amount", contract.getRequired());
            config.set(path + ".progress", contract.getProgress());
            config.set(path + ".expiry", contract.getExpiry());
            config.set(path + ".reward", contract.getReward());



            switch (contract.getType()) {

                case KILL -> config.set(path + ".target", contract.getTarget());

                case BREAKBLOCK -> {
                    if (contract.getTargetBlock() != null) {
                        config.set(path + ".blockTarget", contract.getTargetBlock().name());
                    }
                }

                case KILL_MOB -> {
                    if (contract.getMob() != null) {
                        config.set(path + ".mobTarget", contract.getMob().name());
                    }
                }
            }
        }

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}