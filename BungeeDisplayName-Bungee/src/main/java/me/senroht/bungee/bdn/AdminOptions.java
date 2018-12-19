package me.senroht.bungee.bdn;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AdminOptions extends Command {
    Main main;
    public AdminOptions(Main main)
    {
        super("bdn", "", "BungeeDisplayName");
        this.main = main;
    }

    public void execute(CommandSender commandSender, String[] strings) {
        if (strings.length == 1 && strings[0].equals("reload")){
            if(main.configuration.getBoolean("Need_Permissions")) {
                if (commandSender.hasPermission("bdn.admin.reload")) {
                    main.Load_Config();
                    for (ProxiedPlayer p : main.getProxy().getPlayers()) {
                        if (!p.getName().equals(p.getDisplayName())) {
                            main.Check_Display_Name(p);
                        }
                    }
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
