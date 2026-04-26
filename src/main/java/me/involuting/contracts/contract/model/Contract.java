package me.involuting.contracts.contract.model;

import lombok.Getter;
import lombok.Setter;
import me.involuting.contracts.contract.type.ContractStatus;
import me.involuting.contracts.contract.type.ContractType;
import me.involuting.contracts.reward.Reward;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.UUID;
@Getter
@Setter
public class Contract {

    private final UUID uuid;
    private final UUID creator;
    private UUID assignedTo;

    private ContractType type;

    private Reward rewards;

    private EntityType mob;

    private Material targetBlock;

    @Getter @Setter ContractStatus status;

    @Getter @Setter  private String target;
    @Getter @Setter private int required;
    @Getter @Setter private int progress;

    @Getter @Setter  private long expiry;

    @Getter @Setter private int reward;
    @Getter @Setter
    private Material targetItem;

    @Getter @Setter
    private Material deliverItem;


    public Contract(UUID uuid, UUID creator) {
        this.uuid = uuid;
        this.creator = creator;
        this.status = ContractStatus.ACTIVE;
    }



}
