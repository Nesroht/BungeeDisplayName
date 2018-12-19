package me.senroht.bungee.bdn;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

@SuppressWarnings("ALL")
public class ChangeDisplay extends Command
{
    Main main;

    public ChangeDisplay(Main main)
    {
        super("nickname", "", "nick");
        this.main = main;
    }

    public void execute(CommandSender commandSender, String[] strings)
    {
        main.Load_Config();
        if(main.configuration.getBoolean("Need_Permissions")) {
            if (commandSender.hasPermission("bdn.nickname")) {
                if(main.configuration.getBoolean("Allow_Spaces")){
                    spaces(commandSender, strings);
                }
                else{
                    noSpaces(commandSender, strings);
                }
            }
            else{
                commandSender.sendMessage(main.pluginTag + "You don't have permission for this.");
            }
        }
        else{
            if(main.configuration.getBoolean("Allow_Spaces")){
                spaces(commandSender, strings);
            }
            else{
                noSpaces(commandSender, strings);
            }
        }
    }

    public void noSpaces(CommandSender commandSender, String[] strings){
        if(strings.length == 0){
            commandSender.sendMessage(main.pluginTag + "/Changename <newName> or <playerName> [newName]");
        }
        else{
            if(strings.length == 1){
                if(commandSender instanceof ProxiedPlayer){
                    ProxiedPlayer player = (ProxiedPlayer) commandSender;
                    if(strings[0].contains("&")){
                        if(commandSender.hasPermission("bdn.nickname.color")){
                            if(main.configuration.getBoolean("Length_Limit")) {
                                String colorStrip = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', strings[0]));
                                if (colorStrip.length() <= main.configuration.getInt("Length")) {
                                    main.Change_Display_Name(player, strings[0]);
                                    player.sendMessage(main.pluginTag + "Changed your display name to: " + player.getDisplayName());
                                }
                                else{
                                    commandSender.sendMessage("The new name is: " + strings[0].length());
                                    commandSender.sendMessage("But it must be under or equal to: " + main.configuration.getInt("Length"));
                                }
                            }
                            else{
                                main.Change_Display_Name(player, strings[0]);
                                player.sendMessage(main.pluginTag + "Changed your display name to: " + player.getDisplayName());
                            }
                        }
                        else{
                            commandSender.sendMessage(main.pluginTag + "You cant use color codes in your nickname!");
                        }
                    }
                    else{
                        if(main.configuration.getBoolean("Length_Limit")) {
                            if (strings[0].length() <= main.configuration.getInt("Length")) {
                                main.Change_Display_Name(player, strings[0]);
                                player.sendMessage(main.pluginTag + "Changed your display name to: " + player.getDisplayName());
                            }
                            else{
                                commandSender.sendMessage("The new name is: " + strings[0].length());
                                commandSender.sendMessage("But it must be under or equal to: " + main.configuration.getInt("Length"));
                            }
                        }
                        else{
                            main.Change_Display_Name(player, strings[0]);
                            player.sendMessage(main.pluginTag + "Changed your display name to: " + player.getDisplayName());
                        }
                    }

                }
                else{
                    commandSender.sendMessage(main.pluginTag + "Only a player can do this.");
                }
            }else if(strings.length == 2) {
                ProxiedPlayer sp = null;
                if (!commandSender.hasPermission("bdn.nickname.other")){
                    if (strings[0] != commandSender.getName()){
                        commandSender.sendMessage(main.pluginTag + "You do not have permission to change other peoples nicks!");
                        return;
                    }
                }
                else {
                    for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                        if (p.getName().toLowerCase().equalsIgnoreCase(strings[0].toLowerCase())) {
                            sp = p;
                        }
                    }
                    if (sp != null) {
                        if(strings[1].contains("&")) {
                            if (commandSender.hasPermission("bdn.nickname.color")) {
                                if (main.configuration.getBoolean("Length_Limit")) {
                                    String colorStrip = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', strings[1]));
                                    if (colorStrip.length() <= main.configuration.getInt("Length")) {
                                        main.Change_Display_Name(sp, strings[1]);
                                        commandSender.sendMessage(main.pluginTag + "You changed " + sp.getName() + "'s name to: " + sp.getDisplayName());
                                        sp.sendMessage(main.pluginTag + commandSender.getName() + " changed your name to: " + sp.getDisplayName());
                                    }
                                    else {
                                        commandSender.sendMessage("The new name is: " + colorStrip.length());
                                        commandSender.sendMessage("But it must be under or equal to: " + main.configuration.getInt("Length"));
                                    }
                                }
                                else{
                                    main.Change_Display_Name(sp, strings[1]);
                                    commandSender.sendMessage(main.pluginTag + "You changed " + sp.getName() + "'s name to: " + sp.getDisplayName());
                                    sp.sendMessage(main.pluginTag + commandSender.getName() + " changed your name to: " + sp.getDisplayName());
                                }
                            }
                            else{
                                commandSender.sendMessage(main.pluginTag + "You cant use color codes in nicknames!");
                            }
                        }
                        else {
                            main.Change_Display_Name(sp, strings[1]);
                            commandSender.sendMessage(main.pluginTag + "You changed " + sp.getName() + "'s name to: " + sp.getDisplayName());
                            sp.sendMessage(main.pluginTag + "Your name was changed to: " + sp.getDisplayName());
                        }
                    }
                    else {
                        commandSender.sendMessage(main.pluginTag + "Player not found.");
                    }
                }
            }
            else{
                commandSender.sendMessage(main.pluginTag + "Too many arguments.");
            }
        }
    }

    public void spaces(CommandSender commandSender, String[] strings){
        if(strings.length == 0){
            commandSender.sendMessage(main.pluginTag + "/Changename <newName> or <playerName> [newName]");
        }
        else{
            ProxiedPlayer sp = null;
            for(ProxiedPlayer p : main.getProxy().getPlayers()){
                if(p.getName().toLowerCase().equalsIgnoreCase(strings[0].toLowerCase())){
                    sp = p;
                }
            }
            if(sp != null){
                if (!commandSender.hasPermission("bdn.nickname.other")){
                    if (strings[0] != commandSender.getName()){
                        commandSender.sendMessage(main.pluginTag + "You do not have permission to change other peoples nicks!");
                        return;
                    }
                }
                else{
                    if(strings.length > 1){
                        //Change player name.
                        String newName = "";
                        for(int i = 1; i < strings.length; i++)
                        {
                            newName += strings[i] + " ";
                        }
                        newName = newName.trim();
                        if(!commandSender.hasPermission("bdn.nickname.color")){
                            if(newName.contains("&")){
                                commandSender.sendMessage(main.pluginTag + "You cant use color codes in nicknames");
                            }
                            else{
                                if(main.configuration.getBoolean("Length_Limit")){
                                    if( newName.length() <= main.configuration.getInt("Length")){
                                        main.Change_Display_Name(sp, newName);
                                        commandSender.sendMessage(main.pluginTag + "You changed " + sp.getName() + "'s name to: " + sp.getDisplayName());
                                        sp.sendMessage(main.pluginTag + "Your name was changed to: " + sp.getDisplayName());
                                    }
                                    else{
                                        commandSender.sendMessage("The new name is: " + newName.length());
                                        commandSender.sendMessage("But it must be under or equal to: " + main.configuration.getInt("Length"));
                                    }
                                }
                                else{
                                    main.Change_Display_Name(sp, newName);
                                    commandSender.sendMessage(main.pluginTag + "You changed " + sp.getName() + ChatColor.RESET + "'s name to: " + sp.getDisplayName());
                                    sp.sendMessage(main.pluginTag + "Your name was changed to: " + sp.getDisplayName());
                                }
                            }
                        }
                        else{
                            if(main.configuration.getBoolean("Length_Limit")){
                                String colorStrip = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', newName));
                                if( colorStrip.length() <= main.configuration.getInt("Length")){
                                    main.Change_Display_Name(sp, newName);
                                    commandSender.sendMessage(main.pluginTag + "You changed " + sp.getName() + ChatColor.RESET + "'s name to: " + sp.getDisplayName());
                                    sp.sendMessage(main.pluginTag + "Your name was changed to: " + sp.getDisplayName());
                                }
                                else{
                                    commandSender.sendMessage("The new name is: " + colorStrip.length());
                                    commandSender.sendMessage("But it must be under or equal to: " + main.configuration.getInt("Length"));
                                }
                            }
                            else{
                                main.Change_Display_Name(sp, newName);
                                commandSender.sendMessage(main.pluginTag + "You changed " + sp.getName() + ChatColor.RESET + "'s name to: " + sp.getDisplayName());
                                sp.sendMessage(main.pluginTag + "Your name was changed to: " + sp.getDisplayName());
                            }
                        }
                    }
                    else{
                        commandSender.sendMessage(main.pluginTag + "The new name cannot be blank.");
                    }
                }
            }
            else{
                //Change Your name.
                if(commandSender instanceof ProxiedPlayer) {
                    ProxiedPlayer pp = (ProxiedPlayer) commandSender;
                    String newName = "";
                    for (int i = 0; i < strings.length; i++) {
                        newName += strings[i] + " ";
                    }
                    newName = newName.trim();
                    if (!commandSender.hasPermission("bdn.nickname.color")) {
                        if (newName.contains("&")) {
                            commandSender.sendMessage(main.pluginTag + "You cant use color codes in your nick!");
                        }
                        else {
                            if (main.configuration.getBoolean("Length_Limit")) {
                                if (newName.length() <= main.configuration.getInt("Length")) {
                                    main.Change_Display_Name(pp, newName);
                                    pp.sendMessage(main.pluginTag + "Changed your display name to: " + pp.getDisplayName());
                                } else {
                                    commandSender.sendMessage("The new name is: " + newName.length());
                                    commandSender.sendMessage("But it must be under or equal to: " + main.configuration.getInt("Length"));
                                }
                            } else {
                                main.Change_Display_Name(pp, newName);
                                pp.sendMessage(main.pluginTag + "Changed your display name to: " + pp.getDisplayName());
                            }
                        }
                    }
                    else {
                        if (main.configuration.getBoolean("Length_Limit")) {
                            String colorStrip = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', newName));
                            if (colorStrip.length() <= main.configuration.getInt("Length")) {
                                main.Change_Display_Name(pp, newName);
                                pp.sendMessage(main.pluginTag + "Changed your display name to: " + pp.getDisplayName());
                            }
                            else {
                                commandSender.sendMessage("The new name is: " + colorStrip.length());
                                commandSender.sendMessage("But it must be under or equal to: " + main.configuration.getInt("Length"));
                            }
                        }
                        else {
                            main.Change_Display_Name(pp, newName);
                            pp.sendMessage(main.pluginTag + "Changed your display name to: " + pp.getDisplayName());
                        }
                    }
                }
                else{
                    commandSender.sendMessage(main.pluginTag + "Only a player can change their display name.");
                }
            }
        }
    }
}