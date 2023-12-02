package io.github.laffeymyth.command.impl;

import io.github.laffeymyth.command.CommandService;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class CommandServiceImpl implements CommandService {
    private final NmsUtil nmsUtil = new NmsUtil();
    private final String prefixName;
    private CommandMap commandMap;

    public CommandServiceImpl(String prefixName) {
        this.prefixName = prefixName;
    }

    public void enable() {
        try {
            Field commandMapField = nmsUtil.getCraftBukkit("CraftServer").getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("ignore all")
    public void registerCommand(Command command) {
        String commandName = command.getName();

        List<String> commands = new ArrayList<>(command.getAliases());
        commands.add(commandName);

        try {
            Method register = SimpleCommandMap.class.getDeclaredMethod("register", String.class, Command.class, Boolean.TYPE, String.class);
            register.setAccessible(true);

            for (String cmd : commands) {
                register.invoke(commandMap, cmd, command, !(commandName.equals(cmd)), prefixName);
            }

            Field knownCommands = SimpleCommandMap.class.getDeclaredField("knownCommands");
            knownCommands.setAccessible(true);

            Map<String, Command> map = (Map) knownCommands.get(commandMap);

            for (String cmd : commands) {
                map.put(cmd.toLowerCase(), command);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
