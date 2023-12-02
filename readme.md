# CommandService

This service allows you to register a command without adding it to plugin.yml

## Maven dependency

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/LaffeyMyth/command-service</url>
    </repository>
</repositories>
    
<dependencies>
    <dependency>
        <groupId>io.github.laffeymyth</groupId>
        <artifactId>command-service</artifactId>
        <version>1.0</version>
    </dependency>
</dependencies>
```

## Example

```java
import io.github.laffeymyth.command.impl.CommandServiceImpl;
import org.bukkit.plugin.java.JavaPlugin;

public class BedWars extends JavaPlugin {
    @Override
    public void onEnable() {
        CommandService commandService = new CommandServiceImpl("bedwars");
        commandService.enable();

        commandService.registerCommand(new StartCommand());
    }
}
```