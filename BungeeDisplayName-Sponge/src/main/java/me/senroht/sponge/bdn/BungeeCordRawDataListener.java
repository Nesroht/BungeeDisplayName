package me.senroht.sponge.bdn;

import org.spongepowered.api.Platform;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.mutable.DisplayNameData;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.network.ChannelBinding;
import org.spongepowered.api.network.ChannelBuf;
import org.spongepowered.api.network.RawDataListener;
import org.spongepowered.api.network.RemoteConnection;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializer;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.Optional;

public class BungeeCordRawDataListener implements RawDataListener {

    private ChannelBinding.RawDataChannel channel;

    public BungeeCordRawDataListener(ChannelBinding.RawDataChannel channel) {
        super();
        this.channel = channel;
        //System.out.println("Created!");
    }

    @Override
    public void handlePayload(ChannelBuf data, RemoteConnection connection, Platform.Type side) {
        //System.out.println("Got Ping Successfully");
        /*String subChannel = data.readUTF();
        if(subChannel.equals("BungeeDisplayName")){
            String string = data.readUTF();
            String str1 = string.split(", ")[0];
            String str2 = string.split(", ")[1];
            Text nickname = TextSerializers.LEGACY_FORMATTING_CODE.deserialize(str2);
            Player pl = Sponge.getServer().getPlayer(str1).get();
            Optional<DisplayNameData> data2;
            Text newText;
            pl.offer(Keys.DISPLAY_NAME, nickname);
            if ((data2 = pl.get(DisplayNameData.class)).isPresent()){
                newText = data2.get().displayName().get();
            }
            else{
                newText = Text.of(pl.getName());
            }
            pl.sendMessage(Text.of( newText + "is your new nickname. "));
        }*/
    }
}
