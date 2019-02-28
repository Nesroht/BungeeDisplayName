package me.senroht.bungee.bdn;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static java.lang.Integer.parseInt;

public class AdminOptions extends Command {
    Main main;

    public AdminOptions(Main main) {
        super("bdn", "", "BungeeDisplayName");
        this.main = main;
    }

    public void execute(CommandSender commandSender, String[] strings) {
        //bdn reload
        if (strings.length == 1 && strings[0].equals("reload")) {
            onReload(commandSender, strings);
        }
        //bdn prefix
        else if (strings[0].equals("prefix")) {
            onPrefix(commandSender, strings);
        }
        //bdn length
        else if (strings[0].equals("length")){
            onLength(commandSender, strings);
        }
        else if (strings[0].equals("spaces")){
            onSpaces(commandSender, strings);
        }
        else if (strings[0].equals("colors")){
            onColors(commandSender, strings);
        }
        else if (strings[0].equals("info")){
            onInfo(commandSender, strings);
        }
        else{
            commandSender.sendMessage("Unknown command. Type \"/help\" for help.");
        }

    }

    private void onInfo(CommandSender commandSender, String[] strings) {
        if (main.configuration.getBoolean("Need_Permissions")) {
            if (commandSender.hasPermission("bdn.admin.info")) {
                main.Load_Config();
                if(strings.length == 1){
                    commandSender.sendMessage(main.pluginTag + ChatColor.GOLD + "Config info for BungeeDisplayName");
                    commandSender.sendMessage(ChatColor.BLUE + "  |-|" + ChatColor.GOLD + " Version" + ChatColor.RED + ": " + ChatColor.RESET + main.configuration.getString("Version"));
                    commandSender.sendMessage(ChatColor.BLUE + "  |-|" + ChatColor.GOLD + " Plugin Tag" + ChatColor.RED + ": " + ChatColor.RESET + ChatColor.translateAlternateColorCodes( '&' ,main.configuration.getString("Plugin_Tag")));
                    commandSender.sendMessage(ChatColor.BLUE + "  |-|" + ChatColor.GOLD + " Need Permissions" + ChatColor.RED + ": " + ChatColor.RESET + main.configuration.getBoolean("Need_Permissions").toString());
                    commandSender.sendMessage(ChatColor.BLUE + "  |-|" + ChatColor.GOLD + " Whitelist On" + ChatColor.RED + ": " + ChatColor.RESET + main.configuration.getBoolean("Whitelist_On").toString());
                    commandSender.sendMessage(ChatColor.BLUE + "  |-|" + ChatColor.GOLD + " Whitelisted Servers" + ChatColor.RED + ": " + ChatColor.RESET + main.configuration.getStringList("Whitelisted_Servers"));
                    commandSender.sendMessage(ChatColor.BLUE + "  |-|" + ChatColor.GOLD + " Use Prefix" + ChatColor.RED + ": " + ChatColor.RESET + main.configuration.getBoolean("Use_Prefix").toString() + ChatColor.BLUE + " | " + ChatColor.GOLD + "Prefix" + ChatColor.RED + ": " + ChatColor.RESET + main.configuration.getString("Prefix"));
                    commandSender.sendMessage(ChatColor.BLUE + "  |-|" + ChatColor.GOLD + " Length Limit" + ChatColor.RED + ": " + ChatColor.RESET + main.configuration.getBoolean("Length_Limit").toString() + ChatColor.BLUE + " | " + ChatColor.GOLD + "Length" + ChatColor.RED + ": " + ChatColor.RESET + main.configuration.getInt("Length").toString());
                    commandSender.sendMessage(ChatColor.BLUE + "  |-|" + ChatColor.GOLD + " Use Colors" + ChatColor.RED + ": " + ChatColor.RESET + main.configuration.getBoolean("Use_Colors").toString());
                    commandSender.sendMessage(ChatColor.BLUE + "  |-|" + ChatColor.GOLD + " Allow Spaces" + ChatColor.RED + ": " +ChatColor.RESET + main.configuration.getBoolean("Allow_Spaces").toString());
                }
                else{

                }
            }
        }
        else{
            main.Load_Config();
            if(strings.length == 1){
                commandSender.sendMessage(main.pluginTag + "Config info for BungeeDisplayName");
                commandSender.sendMessage("Config Version: " + main.configuration.getString("Config_Version"));
                commandSender.sendMessage("Plugin Tag: " + main.configuration.getString("Plugin_Tag"));
                commandSender.sendMessage("Need Permissions: " + main.configuration.getString("Need_Permissions"));
                commandSender.sendMessage("Whitelist On: " + main.configuration.getString("Whitelist_On"));
                commandSender.sendMessage("Whitelisted Servers: " + main.configuration.getString("Whitelisted_Servers"));
                commandSender.sendMessage("Prefix: " + main.configuration.getString("Use_Prefix") + " - " + main.configuration.getString("Prefix"));
                commandSender.sendMessage("Length: " + main.configuration.getString("Length_Limit") + " - " + main.configuration.getString("Length_Limit"));
                commandSender.sendMessage("Use Colors: " + main.configuration.getString("Use_Colors"));
                commandSender.sendMessage("Allow Spaces: " + main.configuration.getString("Allow_Spaces"));
            }
            else{

            }
        }
    }

    private void onSpaces(CommandSender commandSender, String[] strings) {
        if (main.configuration.getBoolean("Need_Permissions")) {
            if (commandSender.hasPermission("bdn.admin.spaces.toggle")) {
                if (strings.length == 1) {
                    main.Load_Config();
                    if (main.configuration.getBoolean("Allow_Spaces")) {
                        main.configuration.set("Allow_Spaces", new Boolean(false));
                    }
                    else {
                        main.configuration.set("Allow_Spaces", new Boolean(true));
                    }
                    main.Save_Config();
                    main.Load_Config();
                    commandSender.sendMessage(main.pluginTag + "Set the config variable " + ChatColor.GREEN + "\"Allow_Spaces\"" + ChatColor.RESET + " to: " + ChatColor.BLUE + main.configuration.get("Allow_Spaces"));
                    for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                        if (!p.getName().equals(p.getDisplayName())) {
                            main.Check_Display_Name(p);
                            main.CheckDisplayNameServer(p);
                        }
                    }
                }
                else {
                    if (strings.length == 2) {
                        if (strings[1].equals("true") || strings[1].equals("false")) {
                            main.Load_Config();
                            main.configuration.set("Allow_Spaces", new Boolean(strings[1]));
                            main.Save_Config();
                            main.Load_Config();
                            commandSender.sendMessage(main.pluginTag + "Set the config variable " + ChatColor.GREEN + "\"Allow_Spaces\"" + ChatColor.RESET + " to: " + ChatColor.BLUE + main.configuration.get("Allow_Spaces"));
                            for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                                if (!p.getName().equals(p.getDisplayName())) {
                                    main.Check_Display_Name(p);
                                    main.CheckDisplayNameServer(p);
                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            if (strings.length == 1) {
                main.Load_Config();
                if (main.configuration.getBoolean("Allow_Spaces")) {
                    main.configuration.set("Allow_Spaces", new Boolean(false));
                }
                else {
                    main.configuration.set("Allow_Spaces", new Boolean(true));
                }
                main.Save_Config();
                main.Load_Config();
                commandSender.sendMessage(main.pluginTag + "Set the config variable " + ChatColor.GREEN + "\"Allow_Spaces\"" + ChatColor.RESET + " to: " + ChatColor.BLUE + main.configuration.get("Allow_Spaces"));
                for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                    if (!p.getName().equals(p.getDisplayName())) {
                        main.Check_Display_Name(p);
                        main.CheckDisplayNameServer(p);
                    }
                }
            }
            if (strings.length == 2) {
                if (strings[1].equals("true") || strings[1].equals("false")) {
                    main.Load_Config();
                    main.configuration.set("Allow_Spaces", new Boolean(strings[1]));
                    main.Save_Config();
                    main.Load_Config();
                    commandSender.sendMessage(main.pluginTag + "Set the config variable " + ChatColor.GREEN + "\"Allow_Spaces\"" + ChatColor.RESET + " to: " + ChatColor.BLUE + main.configuration.get("Allow_Spaces"));
                    for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                        if (!p.getName().equals(p.getDisplayName())) {
                            main.Check_Display_Name(p);
                            main.CheckDisplayNameServer(p);
                        }
                    }
                }
            }
        }
    }

    private void onColors(CommandSender commandSender, String[] strings) {
        if (main.configuration.getBoolean("Need_Permissions")) {
            if (commandSender.hasPermission("bdn.admin.colors.toggle")) {
                if (strings.length == 1) {
                    main.Load_Config();
                    if (main.configuration.getBoolean("Use_Colors")) {
                        main.configuration.set("Use_Colors", new Boolean(false));
                    }
                    else {
                        main.configuration.set("Use_Colors", new Boolean(true));
                    }
                    main.Save_Config();
                    main.Load_Config();
                    commandSender.sendMessage(main.pluginTag + "Set the config variable " + ChatColor.GREEN + "\"Use_Colors\"" + ChatColor.RESET + " to: " + ChatColor.BLUE + main.configuration.get("Use_Colors"));
                    for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                        if (!p.getName().equals(p.getDisplayName())) {
                            main.Check_Display_Name(p);
                            main.CheckDisplayNameServer(p);
                        }
                    }
                }
                else {
                    if (strings.length == 2) {
                        if (strings[1].equals("true") || strings[1].equals("false")) {
                            main.Load_Config();
                            main.configuration.set("Use_Colors", new Boolean(strings[1]));
                            main.Save_Config();
                            main.Load_Config();
                            commandSender.sendMessage(main.pluginTag + "Set the config variable " + ChatColor.GREEN + "\"Use_Colors\"" + ChatColor.RESET + " to: " + ChatColor.BLUE + main.configuration.get("Use_Colors"));
                            for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                                if (!p.getName().equals(p.getDisplayName())) {
                                    main.Check_Display_Name(p);
                                    main.CheckDisplayNameServer(p);
                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            if (strings.length == 1) {
                main.Load_Config();
                if (main.configuration.getBoolean("Use_Colors")) {
                    main.configuration.set("Use_Colors", new Boolean(false));
                }
                else {
                    main.configuration.set("Use_Colors", new Boolean(true));
                }
                main.Save_Config();
                main.Load_Config();
                commandSender.sendMessage(main.pluginTag + "Set the config variable " + ChatColor.GREEN + "\"Use_Colors\"" + ChatColor.RESET + " to: " + ChatColor.BLUE + main.configuration.get("Use_Colors"));
                for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                    if (!p.getName().equals(p.getDisplayName())) {
                        main.Check_Display_Name(p);
                        main.CheckDisplayNameServer(p);
                    }
                }
            }
            if (strings.length == 2) {
                if (strings[1].equals("true") || strings[1].equals("false")) {
                    main.Load_Config();
                    main.configuration.set("Use_Colors", new Boolean(strings[1]));
                    main.Save_Config();
                    main.Load_Config();
                    commandSender.sendMessage(main.pluginTag + "Set the config variable " + ChatColor.GREEN + "\"Use_Colors\"" + ChatColor.RESET + " to: " + ChatColor.BLUE + main.configuration.get("Use_Colors"));
                    for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                        if (!p.getName().equals(p.getDisplayName())) {
                            main.Check_Display_Name(p);
                            main.CheckDisplayNameServer(p);
                        }
                    }
                }
            }
        }
    }

    private void onLength(CommandSender commandSender, String[] strings) {
        if (main.configuration.getBoolean("Need_Permissions")) {
            if (commandSender.hasPermission("bdn.admin.length.toggle")) {
                if (strings.length == 1){
                    main.Load_Config();
                    if (main.configuration.getBoolean("Length_Limit")) {
                        main.configuration.set("Length_Limit", new Boolean(false));
                    } else {
                        main.configuration.set("Length_Limit", new Boolean(true));
                    }
                    main.Save_Config();
                    main.Load_Config();
                    commandSender.sendMessage(main.pluginTag + "Set the config variable " + ChatColor.GREEN + "\"Length_Limit\"" + ChatColor.RESET + " to: " + ChatColor.BLUE + main.configuration.get("Length_Limit"));
                    for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                        if (!p.getName().equals(p.getDisplayName())) {
                            main.Check_Display_Name(p);
                            main.CheckDisplayNameServer(p);
                        }
                    }
                }
                else{
                    if (strings.length == 2) {
                        if (strings[1].equals("true") || strings[1].equals("false")) {
                            main.Load_Config();
                            main.configuration.set("Length_Limit", new Boolean(strings[1]));
                            main.Save_Config();
                            main.Load_Config();
                            commandSender.sendMessage(main.pluginTag + "Set the config variable " + ChatColor.GREEN + "\"Length_Limit\"" + ChatColor.RESET + " to: " + ChatColor.BLUE + main.configuration.get("Length_Limit"));
                            for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                                if (!p.getName().equals(p.getDisplayName())) {
                                    main.Check_Display_Name(p);
                                    main.CheckDisplayNameServer(p);
                                }
                            }
                        }
                    }
                }
            }
            if(commandSender.hasPermission("bdn.admin.length.set")){
                if (strings.length == 2){
                    if (!strings[1].equals("true") && !strings[1].equals("false")){
                        main.Load_Config();
                        main.configuration.set("Length", parseInt(strings[1]));
                        main.Save_Config();
                        main.Load_Config();
                        commandSender.sendMessage(main.pluginTag + "Set the max length of a nick to: " + main.configuration.getString("Length"));
                        for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                            if (!p.getName().equals(p.getDisplayName())) {
                                main.Check_Display_Name(p);
                                main.CheckDisplayNameServer(p);
                            }
                        }
                    }
                }
            }
        }
        else {
            if (strings.length == 1){
                main.Load_Config();
                if (main.configuration.getBoolean("Length_Limit")) {
                    main.configuration.set("Length_Limit", new Boolean(false));
                } else {
                    main.configuration.set("Length_Limit", new Boolean(true));
                }
                main.Save_Config();
                main.Load_Config();
                commandSender.sendMessage(main.pluginTag + "Set the config variable " + ChatColor.GREEN + "\"Length_Limit\"" + ChatColor.RESET + " to: " + ChatColor.BLUE + main.configuration.get("Length_Limit"));
                for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                    if (!p.getName().equals(p.getDisplayName())) {
                        main.Check_Display_Name(p);
                        main.CheckDisplayNameServer(p);
                    }
                }
            }
            if (strings.length == 2) {
                if (strings[1].equals("true") || strings[1].equals("false")) {
                    main.Load_Config();
                    main.configuration.set("Length_Limit", new Boolean(strings[1]));
                    main.Save_Config();
                    main.Load_Config();
                    commandSender.sendMessage(main.pluginTag + "Set the config variable " + ChatColor.GREEN + "\"Length_Limit\"" + ChatColor.RESET + " to: " + ChatColor.BLUE + main.configuration.get("Length_Limit"));
                    for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                        if (!p.getName().equals(p.getDisplayName())) {
                            main.Check_Display_Name(p);
                            main.CheckDisplayNameServer(p);
                        }
                    }
                }
                else{
                    main.Load_Config();
                    main.configuration.set("Length", parseInt(strings[1]));
                    main.Save_Config();
                    main.Load_Config();
                    commandSender.sendMessage(main.pluginTag + "Set the max length of a nick to: " + main.configuration.getString("Length"));
                }
            }
        }
    }

    public void onReload(CommandSender commandSender, String[] strings){
        if (main.configuration.getBoolean("Need_Permissions")) {
            if (commandSender.hasPermission("bdn.admin.reload")) {
                main.Load_Config();
                for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                    if (!p.getName().equals(p.getDisplayName())) {
                        main.Check_Display_Name(p);
                        main.CheckDisplayNameServer(p);
                    }
                }
                commandSender.sendMessage(main.pluginTag + "Reloaded configs! (If any were removed they have been regenerated)");
            }
            else {
                commandSender.sendMessage(main.pluginTag + "You don't have permission for this.");
            }
        }
        else {
            main.Load_Config();
            main.Load_Player_Config();
            commandSender.sendMessage(main.pluginTag + "Reloaded configs! (If any were removed they have been regenerated)");
        }
    }

    public void onPrefix(CommandSender commandSender, String[] strings){
        if (main.configuration.getBoolean("Need_Permissions")) {
            if (commandSender.hasPermission("bdn.admin.prefix.toggle")) {
                if (strings.length == 1){
                    main.Load_Config();
                    if (main.configuration.getBoolean("Use_Prefix")) {
                        main.configuration.set("Use_Prefix", new Boolean(false));
                    } else {
                        main.configuration.set("Use_Prefix", new Boolean(true));
                    }
                    main.Save_Config();
                    main.Load_Config();
                    commandSender.sendMessage(main.pluginTag + "Set the config variable " + ChatColor.GREEN + "\"Use_Prefix\"" + ChatColor.RESET + " to: " + ChatColor.BLUE + main.configuration.get("Use_Prefix"));
                    for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                        if (!p.getName().equals(p.getDisplayName())) {
                            main.Check_Display_Name(p);
                            main.CheckDisplayNameServer(p);
                        }
                    }
                }
                else{
                    if (strings.length == 2) {
                        if (strings[1].equals("true") || strings[1].equals("false")) {
                            main.Load_Config();
                            main.configuration.set("Use_Prefix", new Boolean(strings[1]));
                            main.Save_Config();
                            main.Load_Config();
                            commandSender.sendMessage(main.pluginTag + "Set the config variable " + ChatColor.GREEN + "\"Use_Prefix\"" + ChatColor.RESET + " to: " + ChatColor.BLUE + main.configuration.get("Use_Prefix"));
                            for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                                if (!p.getName().equals(p.getDisplayName())) {
                                    main.Check_Display_Name(p);
                                    main.CheckDisplayNameServer(p);
                                }
                            }
                        }
                    }
                }
            }
            if(commandSender.hasPermission("bdn.admin.prefix.change")){
                if (strings.length == 2){
                    if (!strings[1].equals("true") && !strings[1].equals("false")){
                        main.Load_Config();
                        main.configuration.set("Prefix", strings[1]);
                        main.Save_Config();
                        main.Load_Config();
                        commandSender.sendMessage(main.pluginTag + "Set the display name prefix to: \"" + ChatColor.translateAlternateColorCodes('&', main.configuration.getString("Prefix")) + ChatColor.RESET + "\"");
                        for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                            if (!p.getName().equals(p.getDisplayName())) {
                                main.Check_Display_Name(p);
                                main.CheckDisplayNameServer(p);
                            }
                        }
                    }
                }
            }
        }
        else {
            if (strings.length == 1){
                main.Load_Config();
                if (main.configuration.getBoolean("Use_Prefix")) {
                    main.configuration.set("Use_Prefix", new Boolean(false));
                } else {
                    main.configuration.set("Use_Prefix", new Boolean(true));
                }
                main.Save_Config();
                main.Load_Config();
                commandSender.sendMessage(main.pluginTag + "Set the config variable " + ChatColor.GREEN + "\"Use_Prefix\"" + ChatColor.RESET + " to: " + ChatColor.BLUE + main.configuration.get("Use_Prefix"));
                for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                    if (!p.getName().equals(p.getDisplayName())) {
                        main.Check_Display_Name(p);
                        main.CheckDisplayNameServer(p);
                    }
                }
            }
            if (strings.length == 2) {
                if (strings[1].equals("true") || strings[1].equals("false")) {
                    main.Load_Config();
                    main.configuration.set("Use_Prefix", new Boolean(strings[1]));
                    main.Save_Config();
                    main.Load_Config();
                    commandSender.sendMessage(main.pluginTag + "Set the config variable " + ChatColor.GREEN + "\"Use_Prefix\"" + ChatColor.RESET + " to: " + ChatColor.BLUE + main.configuration.get("Use_Prefix"));
                    for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                        if (!p.getName().equals(p.getDisplayName())) {
                            main.Check_Display_Name(p);
                            main.CheckDisplayNameServer(p);
                        }
                    }
                }
                else{
                    main.Load_Config();
                    main.configuration.set("Prefix", strings[1]);
                    main.Save_Config();
                    main.Load_Config();
                    commandSender.sendMessage(main.pluginTag + "Set the display name prefix to: " + main.configuration.get("Prefix"));
                }
            }
        }
    }
}
