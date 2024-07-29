package com.aetherplugins.lostedd.events;

import com.aetherplugins.lostedd.aDeathsLoots;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerDeathListener implements Listener {


    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player killer = event.getEntity();
        Location location = killer.getLocation();

        Block block = location.getBlock();
        block.setType(Material.CHEST);
        Chest chest = (Chest) block.getState();
        for(ItemStack item : event.getDrops()) {
            chest.getBlockInventory().addItem(item);
        }
        event.getDrops().clear();

        // Criar o holograma

        ArmorStand hologram = (ArmorStand) location.getWorld().spawnEntity(location.add(0, 0.30, 0), EntityType.ARMOR_STAND);
        hologram.setCustomName(ChatColor.YELLOW + "Player " + killer.getName() + "'s loot chest");
        hologram.setCustomNameVisible(true);
        hologram.setGravity(false);
        hologram.setVisible(false);
        hologram.setSmall(true);

        Location hologramLocation1 = location.add(0, 0.10, 0);
        Location hologramLocation2 = hologramLocation1.add(0, 0.10, 0);
        ArmorStand hologram2 = (ArmorStand) location.getWorld().spawnEntity(hologramLocation2, EntityType.ARMOR_STAND);
        hologram2.setCustomNameVisible(true);
        hologram2.setGravity(false);
        hologram2.setVisible(false);
        hologram2.setSmall(true);

        new BukkitRunnable() {
            int time = 10;

            @Override
            public void run() {
                if(time <= 0) {
                    block.setType(Material.AIR);
                    hologram.remove();
                    hologram2.remove();
                    this.cancel();
                } else {
                    hologram2.setCustomName(ChatColor.RED + "Disappear in " + time + "s");

                    time--;
                }
            }
        }.runTaskTimer(aDeathsLoots.getPlugin(aDeathsLoots.class), 0, 20);
    }
}
