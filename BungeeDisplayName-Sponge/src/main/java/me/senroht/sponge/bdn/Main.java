package me.senroht.sponge.bdn;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.command.CommandManager;


@Plugin(id = "bungeedisplayname", name = "BungeeDisplayName", version = "1.1.3", description = "Plugin to set nickname over Bungeecord")
public class Main {

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        Sponge.getCommandManager().register(this, CommandSpec.builder()
                .description(Text.of("Changes nickname of yourself or others"))
                .permission("bdn.nickname")
                .arguments(GenericArguments.requiringPermission(GenericArguments.seq(GenericArguments.onlyOne(GenericArguments.player(Text.of("player"))), GenericArguments.remainingJoinedStrings(Text.of("nickname"))), "bdn.nickname.other"))
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
        Sponge.getCommandManager().register(this, CommandSpec.builder()
                .description(Text.of("Prefix for admin commands"))
                .permission("bdn.admin")
                .arguments(GenericArguments.requiringPermission(GenericArguments.string(Text.of("reload")), "bdn.admin.reload"))
                .executor(new AdminOptions())
                .build(), "bdn");
        logger.info("");
        logger.info("  ___");
        logger.info(" | _ )_  _ _ _  __ _ ___ ___ ");
        logger.info(" | _ \\ || | ' \\/ _` / -_) -_)");
        logger.info(" |___/\\_,_|_||_\\__, \\___\\___|");
        logger.info("  ___  _       |___/");
        logger.info(" |   \\(_)____ __| |__ _ _  _ ");
        logger.info(" | |) | (_-< '_ \\ / _` | || |");
        logger.info(" |___/|_/__/ .__/_\\__,_|\\_, |");
        logger.info(" | \\| |__ _|_|__  ___   |__/ ");
        logger.info(" | .` / _` | '  \\/ -_)");
        logger.info(" |_|\\_\\__,_|_|_|_\\___|");
        logger.info("");
        logger.info("BungeeDisplayName has successfully loaded");
    }

}