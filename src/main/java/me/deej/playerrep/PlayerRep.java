package me.deej.playerrep;

import me.deej.playerrep.commands.CommandManager;
import me.deej.playerrep.listeners.ListenerManager;
import me.deej.playerrep.placeholders.Rep;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class PlayerRep extends JavaPlugin {

    private static PlayerRep plugin;
    private File pdataConfigFile;
    private FileConfiguration pdataConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        ListenerManager.registerListeners();
        CommandManager.registerCommands();

        this.saveDefaultConfig();
        this.createCustomConfig();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Rep().register();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getPlugin().saveConfig();
        try {
            this.pdataConfig.save(this.pdataConfigFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public FileConfiguration getpadataConfig() {
        return this.pdataConfig;
    }

    public File getpdataConfigFile() {
        return this.pdataConfigFile;
    }

    private void createCustomConfig() {
        this.pdataConfigFile = new File(this.getDataFolder(), "playerdata.yml");
        if (!this.pdataConfigFile.exists()) {
            this.pdataConfigFile.getParentFile().mkdirs();
            this.saveResource("playerdata.yml", false);
        }
        this.pdataConfig = (FileConfiguration)new YamlConfiguration();
        try {
            this.pdataConfig.load(this.pdataConfigFile);
        }
        catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }
    }

    public static PlayerRep getPlugin() {
        return plugin;
    }
}
