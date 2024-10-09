package pl.minewars.example.mixin;

import net.minecraft.obfuscate.DontObfuscate;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    /**
     * @author Hubert Kuliniak
     * @reason Created for example plugin.
     */
    @Overwrite
    @DontObfuscate
    public @NotNull String getServerModName() {
        return "Paper (Modified)";
    }

}
