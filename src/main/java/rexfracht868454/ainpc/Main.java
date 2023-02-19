package rexfracht868454.ainpc;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;
import rexfracht868454.ainpc.command.SummonNPCCommand;
import rexfracht868454.ainpc.listener.NPCListener;
import rexfracht868454.ainpc.utils.ConvoTrait;

import java.util.logging.Level;

public final class Main extends JavaPlugin implements Listener {

    private BukkitAudiences adventure;

    public @NonNull BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }

    @Override
    public void onEnable() {
        if (this.getConfig().getString("api-key").equals("")) {
            this.getLogger().warning("Api-key is null!");
        }
        if (this.getConfig().getString("api-key").contains(" ")) {
            this.getLogger().warning("Api-key are not an available!");
        }
        saveDefaultConfig();
        this.adventure = BukkitAudiences.create(this);

        if(getServer().getPluginManager().getPlugin("Citizens") == null || !getServer().getPluginManager().getPlugin("Citizens").isEnabled()) {
            getLogger().log(Level.SEVERE, "Citizens 2.0 not found or not enabled");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(ConvoTrait.class));

        getCommand("summonnpc").setExecutor(new SummonNPCCommand(this));
        getServer().getPluginManager().registerEvents(new NPCListener(this), this);
    }

    @Override
    public void onDisable() {

    }

    @NotNull
    @Override
    public FileConfiguration getConfig() {
        return super.getConfig();
    }
}
