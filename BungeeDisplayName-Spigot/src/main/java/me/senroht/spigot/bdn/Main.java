package me.senroht.spigot.bdn;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Main extends JavaPlugin implements TabCompleter {
    @Override
    public void onEnable() {
        getCommand("bdn").setTabCompleter(new AdminOptions());
        getLogger().info("");
        getLogger().info("  ___");
        getLogger().info(" | _ )_  _ _ _  __ _ ___ ___ ");
        getLogger().info(" | _ \\ || | ' \\/ _` / -_) -_)");
        getLogger().info(" |___/\\_,_|_||_\\__, \\___\\___|");
        getLogger().info("  ___  _       |___/");
        getLogger().info(" |   \\(_)____ __| |__ _ _  _ ");
        getLogger().info(" | |) | (_-< '_ \\ / _` | || |");
        getLogger().info(" |___/|_/__/ .__/_\\__,_|\\_, |");
        getLogger().info(" | \\| |__ _|_|__  ___   |__/ ");
        getLogger().info(" | .` / _` | '  \\/ -_)");
        getLogger().info(" |_|\\_\\__,_|_|_|_\\___|");
        getLogger().info("");
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new PluginChannelListener());
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

    }
    @Override
    public void onDisable() {
        getLogger().info("BungeeDisplayName saying byebye!");
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("broadcast")){
            //Setup a way to write the data
        }
        return false;
    }

    public void onMessage(){

    }
}
