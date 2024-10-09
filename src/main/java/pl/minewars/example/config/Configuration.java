package pl.minewars.example.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.CustomKey;
import org.jetbrains.annotations.NotNull;

public class Configuration extends OkaeriConfig {

    @CustomKey("exampleKey")
    public @NotNull String exampleKey = "Example Property";

}
