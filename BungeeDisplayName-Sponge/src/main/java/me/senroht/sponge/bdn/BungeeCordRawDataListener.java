package me.senroht.sponge.bdn;

import io.github.nucleuspowered.nucleus.api.NucleusAPI;
import io.github.nucleuspowered.nucleus.api.exceptions.NicknameException;
import org.spongepowered.api.Platform;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.mutable.DisplayNameData;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.network.ChannelBinding;
import org.spongepowered.api.network.ChannelBuf;
import org.spongepowered.api.network.RawDataListener;
import org.spongepowered.api.network.RemoteConnection;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializer;
import org.spongepowered.api.text.serializer.TextSerializers;
import io.github.nucleuspowered.nucleus.api.service.NucleusNicknameService;

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
        String subChannel = data.readUTF();
        Task.Builder taskBuilder = Task.builder();
        if(subChannel.equals("BungeeDisplayName")){
            String string = data.readUTF();
            String str1 = string.split(", ")[0];
            String str2 = string.split(", ")[1];
            if(Sponge.getGame().getPluginManager().isLoaded("nucleus")){
                taskBuilder.execute(new Runnable() {
                    public void run() {
                        try {
                            if(NucleusAPI.getNicknameService().isPresent()){
                                if(!NucleusAPI.getNicknameService().get().getNickname(Sponge.getServer().getPlayer(str1).get()).get().toPlain().equals(str2)){
                                    NucleusAPI.getNicknameService().get().setNickname(Sponge.getServer().getPlayer(str1).get(), Text.of(str2), true);
                                }
                            }
                            else{
                                //Sponge.getServer().getPlayer(str1).get().sendMessage(Text.of("No workings, No nickname service"));
                            }

                        } catch (NicknameException e) {
                            e.printStackTrace();
                        }
                        //Sponge.getServer().getPlayer(str1).get().sendMessage(Text.of( str2 + " is your new nickname. "));

                    }
                }).submit(Sponge.getPluginManager().getPlugin("bungeedisplayname").get().getInstance().get());

            }
        }
    }
}
