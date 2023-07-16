package net.byalexius.skulls;

import lombok.Getter;
import lombok.SneakyThrows;
import net.byalexius.skulls.commands.SkullCommand;
import net.byalexius.skulls.listeners.DeathListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Skulls extends JavaPlugin {

    @Getter
    private static Skulls Instance;

    @Getter
    private final FileConfiguration cfg = this.getConfig();

    @Override
    public void onEnable() {
        Instance = this;
        saveDefaultConfig();

        if (!getCfg().getBoolean("enabled")) {
            return;
        }

        registerListeners();
        registerCommands();
    }

    private void registerListeners() {
        PluginManager pl = this.getServer().getPluginManager();

        pl.registerEvents(new DeathListener(), this);
    }

    @SneakyThrows
    private void registerCommands() {
        this.getCommand("skull").setExecutor(new SkullCommand());
    }
}