package me.senroht.sponge.bdn;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Platform;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.network.ChannelBinding;
import org.spongepowered.api.network.ChannelRegistrar;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.spongepowered.api.command.args.GenericArguments.*;


@Plugin(id = "bungeedisplayname", name = "BungeeDisplayName", version = "1.2.4", description = "Plugin to set nickname over Bungeecord", dependencies=@Dependency(id="nucleus", optional=true))
public class Main {

    ChannelRegistrar channelRegistrar;
    ChannelBinding.RawDataChannel channel;


    @Inject
    private Logger logger;


    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        Sponge.getCommandManager().register(this, CommandSpec.builder()
                .description(Text.of("Changes nickname of yourself or others"))
                .permission("bdn.nickname")
                .arguments(GenericArguments.requiringPermission(seq(GenericArguments.onlyOne(GenericArguments.player(Text.of("player"))), GenericArguments.remainingJoinedStrings(Text.of("nickname"))), "bdn.nickname.other"))
                .executor(new ChangeDisplay())
                .build(), "nick", "nickname");

        Sponge.getCommandManager().register(this, CommandSpec.builder()
                .description(Text.of("Resets nickname of yourself or others"))
                .permission("bdn.resetname")
                .arguments(GenericArguments.requiringPermission(GenericArguments.onlyOne(GenericArguments.player(Text.of("player"))), "bdn.resetname.other"))
                .executor(new ResetDisplay())
                .build(), "unnick", "resetname");

        Sponge.getCommandManager().register(this, CommandSpec.builder()
                .description(Text.of("Tells you the real name of a given nicknamed user (ignores color codes)"))
                .permission("bdn.realname")
                .arguments(GenericArguments.remainingJoinedStrings(Text.of("nickname")))
                .executor(new RealName())
                .build(), "realname");

        HashMap<String, String> test = new HashMap<String, String>();
        HashMap<String, String> test2 = new HashMap<String, String>();
        HashMap<String, String> test3 = new HashMap<String, String>();
        test.put("true", "true");
        test.put("false", "false");
        test2.put("true", "true");
        test2.put("false", "false");
        test2.put("<Prefix>", "<Prefix>");
        test3.put("true", "true");
        test3.put("false", "false");
        test3.put("<Integer>", "<Integer>");
        HashMap<List<String>,CommandSpec> subCommands = new HashMap<List<String>, CommandSpec>();
        subCommands.put(Arrays.asList("reload"), CommandSpec.builder()
                .description(Text.of("Reload the configs"))
                .executor(new ReloadSubcommand())
                .build());

        subCommands.put(Arrays.asList("colors"), CommandSpec.builder()
                .description(Text.of("Toggle the use of Color Codes"))
                .arguments(seq(choices(Text.of("Boolean"), test)))
                .executor(new ColorsSubcommand())
                .build());

        subCommands.put(Arrays.asList("spaces"), CommandSpec.builder()
                .description(Text.of("Toggle the use of Spaces"))
                .arguments(seq(choices(Text.of("Boolean"), test)))
                .executor(new SpacesSubcommand())
                .build());

        subCommands.put(Arrays.asList("length"), CommandSpec.builder()
                .description(Text.of("Toggle limitting nickname length or setting max length"))
                .arguments(seq(choices(Text.of("Arguments"), test3)))
                .executor(new LengthSubcommand())
                .build());

        subCommands.put(Arrays.asList("prefix"), CommandSpec.builder()
                .description(Text.of("Toggle the use of Prefix or set Prefix"))
                .arguments(seq(choices(Text.of("Arguments"), test2)))
                .executor(new PrefixSubcommand())
                .build());

        Sponge.getCommandManager().register(this, CommandSpec.builder()
                .description(Text.of("Prefix for admin commands"))
                .permission("bdn.admin")
                .children(subCommands)
                .executor(new AdminOptions())
                .build(), "bdn", "bungeedisplayname");
        logger.info("[BungeeDisplayName] Successfully loaded!");
        if(!Sponge.getGame().getPluginManager().getPlugin("nucleus").isPresent()){
            logger.info("[BungeeDisplayName] No compatible nickname plugins, won't set nickname server side!");
        }

        channelRegistrar = Sponge.getGame().getChannelRegistrar();
        ChannelBinding.RawDataChannel channel = Sponge.getGame().getChannelRegistrar().createRawChannel(this, "BungeeCord");
        channel.addListener(Platform.Type.SERVER, new BungeeCordRawDataListener(channel));
        this.channel = channel;
    }
    @Listener
    public void onServerStop(GameStoppedServerEvent event) {
        Sponge.getChannelRegistrar().unbindChannel(channel);
    }
}