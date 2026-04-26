package me.involuting.contracts.contract.manager;

import me.involuting.contracts.Contracts;
import me.involuting.contracts.contract.model.Contract;

import java.util.*;

public class ContractManager {

    private final Map<UUID, Contract> contracts = new HashMap<>();

    public void addContract(Contract contract){
        contracts.put(contract.getUuid(), contract);
    }

    public Contract getContract(UUID uuid){
        return contracts.get(uuid);
    }

    public Collection<Contract> getAll(){
        return contracts.values();
    }

    public List<Contract> getActiveContracts(){
        return contracts.values()
                .stream()
                .filter(c -> c.getStatus().name().equals("ACTIVE"))
                .toList();
    }

    public void removeContract(UUID uuid){
        contracts.remove(uuid);
    }
}
