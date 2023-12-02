package io.github.laffeymyth.command;

import org.bukkit.command.Command;

@SuppressWarnings("unused")
public interface CommandService {

    /**
     * Registers the command without writing it to plugin.yml
     *
     * @param command command
     */
    void registerCommand(Command command);

    void enable();
}
