package com.aetherplugins.lostedd;

import com.aetherplugins.lostedd.events.PlayerDeathListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class aDeathsLoots extends JavaPlugin {



    public static aDeathsLoots instance;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), this);
        logger(ChatColor.GREEN, "Detected version: " + Bukkit.getServer().getBukkitVersion());
        logger(ChatColor.GREEN, "O plugin foi ativado.");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void logger(ChatColor color, String message) {
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        console.sendMessage(color + message + ChatColor.RESET);
    }

    public static aDeathsLoots getInstance() {
        return instance;
    }
}
