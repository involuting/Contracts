package me.involuting.contracts.reward;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Reward {

    private int money;
    private int exp;

    public Reward(int money, int exp){
        this.money = money;
        this.exp = exp;
    }
}
