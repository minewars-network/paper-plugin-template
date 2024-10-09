package pl.minewars.example.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

import static eu.okaeri.configs.ConfigManager.create;

public record ConfigurationFactory(
        @NotNull Path directory
) {

    public <CONFIG extends OkaeriConfig> @NotNull CONFIG produce(
            @NotNull String configurationFile,
            @NotNull Class<CONFIG> configurationClass
    ) {
        return this.produce(this.directory.resolve(configurationFile), configurationClass);
    }

    public <CONFIG extends OkaeriConfig> @NotNull CONFIG produce(
            @NotNull Path configurationFile,
            @NotNull Class<CONFIG> configurationClass
    ) {
        return create(configurationClass, (initializer) ->
                initializer.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit())
                        .withBindFile(configurationFile)
                        .withRemoveOrphans(true)
                        .saveDefaults()
                        .load(true)
        );
    }

}
