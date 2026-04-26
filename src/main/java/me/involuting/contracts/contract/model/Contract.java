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
    private ContractStatus status;

    private Reward rewards;

    private EntityType mob;

    private Material targetBlock;
    private Material targetItem;
    private Material deliverItem;

    private String target;

    private int required;
    private int progress;

    private long expiry;
    private int reward;

    public Contract(UUID uuid, UUID creator) {
        this.uuid = uuid;
        this.creator = creator;
        this.status = ContractStatus.ACTIVE;
        this.progress = 0;
    }


    public boolean isAssigned() {
        return assignedTo != null;
    }

    public boolean isActive() {
        return status == ContractStatus.INPROGRESS;
    }
}