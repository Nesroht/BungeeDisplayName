package me.senroht.bungee.bdn;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.event.EventHandler;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class Listener implements net.md_5.bungee.api.plugin.Listener {

    Main main;

    public Listener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void On_Connect(ServerConnectedEvent e){
        main.Load_Config(); //Reload system config
        final ProxiedPlayer p = e.getPlayer();
        main.getProxy().getScheduler().schedule(main, new Runnable() {
            @Override
            public void run() {
                if(main.configuration.getBoolean("Whitelist_On")){
                    if(main.configuration.getStringList("Whitelisted_Servers").contains(p.getServer().getInfo().getName())){
                        main.Load_Player_Config(); //Reload the players
                        main.Check_Display_Name(p);
                    }
                }
                else{
                    main.Load_Player_Config(); //Reload the players
                    main.Check_Display_Name(p);
                }

                //Tell who and what
                if (main.playerConfig.get(p.getUniqueId().toString()) != null){
                    main.getLogger().info("Player (" + p + ") logged in with display name of: " + main.playerConfig.get(p.getUniqueId().toString()));
                }
                else{
                    main.getLogger().info("Player (" + p + ") logged in with no set display name");
                }
            }
        }, 2, TimeUnit.SECONDS);
    }
}
