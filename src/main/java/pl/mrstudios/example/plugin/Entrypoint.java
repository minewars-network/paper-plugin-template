package pl.mrstudios.example.plugin;

import dev.rollczi.litecommands.adventure.LiteAdventureExtension;
import org.bukkit.plugin.java.JavaPlugin;
import pl.mrstudios.example.command.CommandExample;
import pl.mrstudios.example.config.Configuration;
import pl.mrstudios.example.config.ConfigurationFactory;

import static dev.rollczi.litecommands.annotations.LiteCommandsAnnotations.ofClasses;
import static dev.rollczi.litecommands.bukkit.LiteBukkitFactory.builder;
import static dev.rollczi.litecommands.message.LiteMessages.INVALID_USAGE;
import static dev.rollczi.litecommands.message.LiteMessages.MISSING_PERMISSIONS;
import static dev.rollczi.litecommands.schematic.SchematicFormat.angleBrackets;
import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;
import static pl.mrstudios.example.util.TranslationUtility.translationOf;

@SuppressWarnings({ "FieldCanBeLocal", "UnstableApiUsage" })
public class Entrypoint extends JavaPlugin {

    private Configuration configuration;
    private ConfigurationFactory configurationFactory;

    @Override
    public void onEnable() {

        /* Configuration */
        this.configurationFactory = new ConfigurationFactory(getDataFolder().toPath());
        this.configuration = this.configurationFactory.produce("config.yml", Configuration.class);

        /* LiteCommands */
        builder(this)

                /* Extension */
                .extension(new LiteAdventureExtension<>(), (config) -> config
                        .miniMessage(true)
                        .serializer(miniMessage())
                )

                /* Commands */
                .commands(ofClasses(
                        CommandExample.class
                ))

                /* Schematic */
                .schematicGenerator(angleBrackets())

                /* Messages */
                .message(MISSING_PERMISSIONS, (handler) -> translationOf("command.no-permissions"))
                .message(
                        INVALID_USAGE, (handler) -> translationOf("command.correct-usage")
                                .replace("{usage}", handler.getSchematic().first())
                )

                /* Build */
                .build();

    }

}
