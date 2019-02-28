package me.senroht.spigot.bdn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class AdminOptions implements TabCompleter {

    private static final String[] COMMANDS = { "reload" , "colors", "spaces", "prefix", "length", "info"};

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if((command.getName().equalsIgnoreCase("bdn") || command.getName().equalsIgnoreCase("bungeedisplayname")) && args.length <= 1){
            List<String> tabComplete = new ArrayList<String>();
            for(String commands : COMMANDS){
                if (commands.toLowerCase().startsWith(args[0].toLowerCase()) || args[0] == null){
                    tabComplete.add(commands);
                }
            }
            return tabComplete;
        }
        return null;
    }
}
