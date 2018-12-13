package me.senroht.bdn;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

@SuppressWarnings("ALL")
public class Reset_Display extends Command
{
    Main main;

    public Reset_Display(Main main)
    {
        super("resetname", "", "unnick");
        this.main = main;
    }

    public void execute(CommandSender commandSender, String[] strings)
    {
        main.Load_Config();
        if(main.configuration.getBoolean("Need_Permissions")){
            if(commandSender.hasPermission("bdn.resetname")){
                resetName(commandSender, strings);
            }else{
                commandSender.sendMessage(main.pluginTag + "You don't have permission for this command.");
            }
        }else{
            resetName(commandSender, strings);
        }
    }

    public void resetName(CommandSender commandSender, String[] strings){
        ProxiedPlayer sp = null;
        if (strings.length == 0){
            //Change Your name.
            if(commandSender instanceof ProxiedPlayer){
                ProxiedPlayer pp = (ProxiedPlayer) commandSender;
                main.Change_Display_Name(pp, pp.getName());
                pp.sendMessage(main.pluginTag + "Reset your name to: " + pp.getDisplayName());
            }
            else{
                commandSender.sendMessage(main.pluginTag + "Only a player can change their display name.");
            }
        }
        else{
            if (!commandSender.hasPermission("bdn.resetname.other")){
                if (strings[0] != commandSender.getName()){
                    commandSender.sendMessage(main.pluginTag + "You do not have permission to reset other peoples nicks!");
                    return;
                }
            }
            else{
                for(ProxiedPlayer p : main.getProxy().getPlayers()){
                    if(p.getName().toLowerCase().matches(strings[0].toLowerCase())){
                        sp = p;
                    }
                }
                if(sp != null){
                    commandSender.sendMessage(main.pluginTag + "You reset " + sp.getDisplayName() + ChatColor.RESET + "'s name to: " + sp.getName());
                    main.Change_Display_Name(sp, sp.getName());
                    sp.sendMessage(main.pluginTag + "Your name was reset to: " + sp.getDisplayName());
                }
                else {
                    commandSender.sendMessage(main.pluginTag + "No one by the name " + strings[0]);
                }
            }

        }
    }
}
