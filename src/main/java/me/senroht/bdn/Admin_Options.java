package me.senroht.bdn;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class Admin_Options extends Command {
    Main main;
    public Admin_Options(Main main)
    {
        super("bdn", "", "BungeeDisplayName");
        this.main = main;
    }

    public void execute(CommandSender commandSender, String[] strings) {
        if (strings.length == 1 && strings[0].equals("reload")){
            if(main.configuration.getBoolean("Need_Permissions")) {
                if (commandSender.hasPermission("bdn.admin.reload")) {
                    main.Load_Config();
                    main.Load_Player_Config();
                    commandSender.sendMessage(main.pluginTag + "Reloaded configs! (If any were removed they have been regenerated)");
                }
                else{
                    commandSender.sendMessage(main.pluginTag + "You don't have permission for this.");
                }
            }
            else{
                main.Load_Config();
                main.Load_Player_Config();
                commandSender.sendMessage(main.pluginTag + "Reloaded configs! (If any were removed they have been regenerated)");
            }
        }

    }
}
