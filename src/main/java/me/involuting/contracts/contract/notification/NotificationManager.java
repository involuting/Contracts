package me.involuting.contracts.contract.notification;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NotificationManager {

    public void send(UUID playerId, String message) {

        Player player = Bukkit.getPlayer(playerId);

        if (player == null || !player.isOnline()) return;

        player.sendMessage(message);
    }

    public void broadcast(String message) {
        Bukkit.broadcastMessage(message);
    }
}