package pl.minewars.example.command;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import org.bukkit.entity.Player;

@Command(name = "example")
public class CommandExample {

    @Execute
    public void executeDefault(
            @Context Player player
    ) {
        player.sendMessage("Hello World!");
    }

}
