package me.senroht.spigot.bdn;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class PluginChannelListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subChannel = in.readUTF();
        if (subChannel.equals("BungeeDisplayName")) {
            String string = in.readUTF();
            String str1 = string.split(", ")[0];
            String str2 = string.split(", ")[1];
            Player pl = Bukkit.getPlayer(str1);
            pl.setDisplayName(str2);
            //pl.sendMessage("Set your nickname to " + pl.getDisplayName());
        }
    }

    public synchronized Object get(Player p, boolean integer) {  // here you can add parameters (e.g. String table, String column, ...)
        return null;
    }
}
