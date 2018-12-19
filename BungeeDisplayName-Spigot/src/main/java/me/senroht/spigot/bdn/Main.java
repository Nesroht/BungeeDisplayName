package me.senroht.spigot.bdn;


import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

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
    }
    @Override
    public void onDisable() {
        getLogger().info("BungeeDisplayName saying byebye!");
    }
}
