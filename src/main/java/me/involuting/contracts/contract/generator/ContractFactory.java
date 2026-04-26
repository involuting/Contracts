package me.involuting.contracts.contract.generator;

import me.involuting.contracts.contract.model.Contract;
import me.involuting.contracts.contract.type.ContractStatus;
import me.involuting.contracts.contract.type.ContractType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ContractFactory {

    private final Random random = new Random();

    public Contract createRandomKillContract() {

        Player target = randomPlayer();

        Contract contract = new Contract(UUID.randomUUID(), null);

        contract.setType(ContractType.KILL);
        contract.setTarget(target != null ? target.getName() : "Unknown");
        contract.setRequired(1 + random.nextInt(3));
        contract.setProgress(0);
        contract.setStatus(ContractStatus.ACTIVE);

        contract.setReward(200 + random.nextInt(800));

        contract.setExpiry(System.currentTimeMillis() + randomExpiry());

        return contract;
    }

    public Contract createRandomBlockContract() {

        Material material = randomBlock();

        Contract contract = new Contract(UUID.randomUUID(), null);

        contract.setType(ContractType.BREAKBLOCK);
        contract.setTargetBlock(material);
        contract.setRequired(10 + random.nextInt(40));
        contract.setProgress(0);
        contract.setStatus(ContractStatus.ACTIVE);

        contract.setReward(150 + random.nextInt(600));

        contract.setExpiry(System.currentTimeMillis() + randomExpiry());

        return contract;
    }

    private Player randomPlayer() {

        List<Player> players = List.copyOf(Bukkit.getOnlinePlayers());

        if (players.isEmpty()) return null;

        return players.get(random.nextInt(players.size()));
    }

    private Material randomBlock() {

        Material[] values = {
                Material.STONE,
                Material.DIRT,
                Material.DIAMOND_ORE,
                Material.IRON_ORE,
                Material.COAL_ORE
        };

        return values[random.nextInt(values.length)];
    }

    private long randomExpiry() {
        return 1000L * 60 * (5 + random.nextInt(10)); // 5–15 min
    }

    public Contract createRandomMobContract() {

        Random random = new Random();

        EntityType mob = randomMob();

        Contract contract = new Contract(UUID.randomUUID(), null);

        contract.setType(ContractType.KILL_MOB);
        contract.setStatus(ContractStatus.ACTIVE);

        contract.setMob(mob);

        contract.setRequired(5 + random.nextInt(10)); // 5–15 mobs
        contract.setProgress(0);

        contract.setReward(300 + random.nextInt(1200)); // higher than normal contracts

        contract.setExpiry(System.currentTimeMillis() + randomExpiry());

        return contract;
    }

    private EntityType randomMob() {

        EntityType[] mobs = {
                EntityType.ZOMBIE,
                EntityType.SKELETON,
                EntityType.CREEPER,
                EntityType.SPIDER,
                EntityType.ENDERMAN,
                EntityType.SLIME
        };

        return mobs[new Random().nextInt(mobs.length)];
    }

    public Contract createRandomDeliverContract() {

        Material material = randomDeliverMaterial();

        Contract contract = new Contract(UUID.randomUUID(), null);

        contract.setTarget(material.name());
        contract.setRequired(5 + random.nextInt(20));
        contract.setProgress(0);
        contract.setStatus(ContractStatus.ACTIVE);

        contract.setReward(200 + random.nextInt(700));

        contract.setExpiry(System.currentTimeMillis() + randomExpiry());

        return contract;
    }



    private Material randomDeliverMaterial() {

        Material[] materials = {
                Material.DIAMOND,
                Material.EMERALD,
                Material.GOLD_INGOT,
                Material.IRON_INGOT,
                Material.COPPER_INGOT,
                Material.COAL,
                Material.REDSTONE,
                Material.LAPIS_LAZULI,
                Material.NETHERITE_SCRAP,
                Material.QUARTZ
        };

        return materials[random.nextInt(materials.length)];
    }

    public Contract createRandomCollectContract() {

        Material material = randomCollectMaterial();

        Contract contract = new Contract(UUID.randomUUID(), null);

        contract.setTarget(material.name());
        contract.setRequired(10 + random.nextInt(40));
        contract.setProgress(0);
        contract.setStatus(ContractStatus.ACTIVE);

        contract.setReward(150 + random.nextInt(600));

        contract.setExpiry(System.currentTimeMillis() + randomExpiry());

        return contract;
    }



    private Material randomCollectMaterial() {

        Material[] materials = {
                Material.OAK_LOG,
                Material.SPRUCE_LOG,
                Material.BIRCH_LOG,
                Material.DARK_OAK_LOG,
                Material.STONE,
                Material.COBBLESTONE,
                Material.SAND,
                Material.DIRT,
                Material.WHEAT,
                Material.CARROT,
                Material.POTATO,
                Material.BONE,
                Material.STRING,
                Material.FEATHER
        };

        return materials[random.nextInt(materials.length)];
    }
}