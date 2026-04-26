package me.involuting.contracts;

import lombok.Getter;
import me.involuting.contracts.contract.command.ContractCommand;
import me.involuting.contracts.contract.generator.ContractFactory;
import me.involuting.contracts.contract.generator.ContractGenerator;
import me.involuting.contracts.contract.manager.ContractCompletionManager;
import me.involuting.contracts.contract.manager.ContractExpiryManager;
import me.involuting.contracts.contract.manager.ContractManager;
import me.involuting.contracts.listener.player.KillListener;
import me.involuting.contracts.listener.world.WorldListener;
import me.involuting.contracts.storage.ContractStorage;
import net.j4c0b3y.api.menu.MenuHandler;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class Contracts extends JavaPlugin {

    @Getter
    private static Contracts instance;

    private ContractManager contractManager;
    private ContractStorage contractStorage;
    private ContractCompletionManager contractCompletionManager;
    private ContractExpiryManager contractExpiryManager;
    private ContractGenerator generator;
    private ContractFactory contractFactory;
    private MenuHandler handler;

    @Override
    public void onEnable() {

        instance = this;

        handler = new MenuHandler(this);



        contractManager = new ContractManager();
        contractFactory = new ContractFactory();


        contractStorage = new ContractStorage(this, contractManager);
        contractStorage.load();


        contractCompletionManager = new ContractCompletionManager(contractManager);
        contractExpiryManager = new ContractExpiryManager(contractManager);


        generator = new ContractGenerator(contractManager, contractFactory);


        registerListeners();
        getCommand("contracts").setExecutor(new ContractCommand(contractManager));


        contractExpiryManager.start();
        generator.startDynamicGeneration();
    }

    @Override
    public void onDisable() {
        contractStorage.save();
    }

    private void registerListeners() {

        Bukkit.getPluginManager().registerEvents(
                new KillListener(contractManager, contractCompletionManager),
                this
        );


        Bukkit.getPluginManager().registerEvents(
                new WorldListener(contractManager, contractCompletionManager),
                this
        );
    }
}