package me.senroht.bungee.bdn;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

@SuppressWarnings("ALL")
public class RealName extends Command
{
    Main main;

    public RealName(Main main)
    {
        super("realname");
        this.main = main;
    }

    public void execute(CommandSender commandSender, String[] strings)
    {
        main.Load_Config();
        if(main.configuration.getBoolean("Need_Permissions")){
            if(commandSender.hasPermission("bdn.realname")){
                realName(commandSender, strings);
            }else{
                commandSender.sendMessage(main.pluginTag + "You don't have permission for this command.");
            }
        }else{
            realName(commandSender, strings);
        }

    }
    public void realName(CommandSender commandSender, String[] strings){
        if(strings.length == 0){
            commandSender.sendMessage(main.pluginTag + "Player name needed.");
        }
        else{
            ProxiedPlayer sp = null;
            if (main.configuration.getBoolean("Use_Prefix")){
                if (main.configuration.getBoolean("Allow_Spaces")){
                    String fullName = "";
                    for(int i = 0; i < strings.length; i++)
                    {
                        fullName += strings[i] + " ";
                    }
                    fullName = fullName.trim();
                    for(ProxiedPlayer p : main.getProxy().getPlayers()){
                        if(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', p.getDisplayName())).substring(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', main.configuration.getString("Prefix"))).length()).toLowerCase().equalsIgnoreCase(fullName.toLowerCase())){
                            sp = p;
                        }
                    }
                    if(sp != null){
                        commandSender.sendMessage(main.pluginTag + sp.getDisplayName() + ChatColor.RESET + "'s real name is " + sp.getName());
                    }
                    else{
                        commandSender.sendMessage(main.pluginTag + strings[0] + ChatColor.RESET + " could not be found.");
                    }
                }
                else{
                    for(ProxiedPlayer p : main.getProxy().getPlayers()){
                        if(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', p.getDisplayName())).substring(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', main.configuration.getString("Prefix"))).length()).toLowerCase().equalsIgnoreCase(strings[0].toLowerCase())){
                            sp = p;
                        }
                    }
                    if(sp != null){
                        commandSender.sendMessage(main.pluginTag + sp.getDisplayName() + ChatColor.RESET + "'s real name is " + sp.getName());
                    }
                    else{
                        commandSender.sendMessage(main.pluginTag + strings[0] + ChatColor.RESET + " could not be found.");
                    }
                }
            }
            else{
                if (main.configuration.getBoolean("Allow_Spaces")){
                    String fullName = "";
                    for(int i = 0; i < strings.length; i++)
                    {
                        fullName += strings[i] + " ";
                    }
                    fullName = fullName.trim();
                    for(ProxiedPlayer p : main.getProxy().getPlayers()){
                        if(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', p.getDisplayName())).toLowerCase().equalsIgnoreCase(fullName.toLowerCase())){
                            sp = p;
                        }
                    }
                    if(sp != null){
                        commandSender.sendMessage(main.pluginTag + sp.getDisplayName() + ChatColor.RESET + "'s real name is " + sp.getName());
                    }
                    else{
                        commandSender.sendMessage(main.pluginTag + strings[0] + ChatColor.RESET + " could not be found.");
                    }
                }
                else {
                    for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                        if (ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', p.getDisplayName())).toLowerCase().equalsIgnoreCase(strings[0].toLowerCase())) {
                            sp = p;
                        }
                    }
                    if (sp != null) {
                        commandSender.sendMessage(main.pluginTag + sp.getDisplayName() + ChatColor.RESET + "'s real name is " + sp.getName());
                    } else {
                        commandSender.sendMessage(main.pluginTag + strings[0] + ChatColor.RESET + " could not be found.");
                    }
                }
            }

        }
    }
}