package de.schild;

import de.schild.commands.SchildCommand;
import de.schild.commands.SchildReloadCommand;
import de.schild.file.FileManager;
import de.schild.listener.SignEditListener;
import de.schild.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public class SchildMain extends JavaPlugin {

    private static SchildMain plugin;
    private static SignEdit signedit;
    private static Logger log;

    public static SignEdit getSignEdit() {
        return signedit;
    }

    public void onEnable() {

        plugin = this;

        log = getLogger();

        Bukkit.getConsoleSender().sendMessage("§7======================================");
        Bukkit.getConsoleSender().sendMessage("§eSchild §7| §bStatus: §aenabled");
        Bukkit.getConsoleSender().sendMessage("§eSchild §7| §bVersion: §6" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§eSchild §7| §bDeveloper: §6" + getDescription().getAuthors());
        Bukkit.getConsoleSender().sendMessage("§7======================================");

        if(getServer().getPluginManager().getPlugin("PlotSquared") !=null) {
            if (getServer().getPluginManager().getPlugin("PlotSquared").isEnabled()) {
                log("§8[§eSchild§8] §fPlotSquared was found and hooked.");
            }
        }

        File main = new File("plugins/Schild");
        if (!main.exists()) {
            main.mkdirs();
        }
        loadFile();
        setupSignEdit();
        loadCommands();
        loadListener();
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§7======================================");
        Bukkit.getConsoleSender().sendMessage("§eSchild §7| §bStatus: §cdisabled");
        Bukkit.getConsoleSender().sendMessage("§eSchild §7| §bVersion: §6" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§eSchild §7| §bDeveloper: §6" + getDescription().getAuthors());
        Bukkit.getConsoleSender().sendMessage("§7======================================");
    }

    public static SchildMain getPlugin() {
        return plugin;
    }

    private void loadListener() {
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(new SignEditListener(), this);
    }

    private void loadCommands() {
        getCommand("schild").setExecutor(new SchildCommand());
        getCommand("reloadschild").setExecutor(new SchildReloadCommand());
    }

    private void loadFile(){
        FileManager.loadFile();
        FileManager.readFile();
        FileManager.reloadFile();
    }

    private boolean setupSignEdit() {
        String version;
        try {
            version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        log("§aYour server is running version §e" + version);
        if (version.equals("v1_8_R1")) {
            signedit = new SignEdit_1_8_R1();
        } else if (version.equals("v1_8_R2")) {
            signedit = new SignEdit_1_8_R2();
        } else if (version.equals("v1_8_R3")) {
            signedit = new SignEdit_1_8_R3();
        } else if(version.equals("v1_9_R1")) {
            signedit = new SignEdit_1_9_R1();
        } else if(version.equals("v1_9_R2")) {
            signedit = new SignEdit_1_9_R2();
        } else if(version.equals("v1_10_R1")) {
            signedit = new SignEdit_1_10_R1();
        } else if(version.equals("v1_11_R1")) {
            signedit = new SignEdit_1_11_R1();
        } else if(version.equals("v1_12_R1")) {
            signedit = new SignEdit_1_12_R1();
        } else if(version.equals("v1_13_R1")) {
            signedit = new SignEdit_1_13_R1();
        } else if(version.equals("v1_13_R2")) {
            signedit = new SignEdit_1_13_R2();
        } else if(version.equals("v1_14_R1")) {
            signedit = new SignEdit_1_14_R1();
        } else if(version.equals("v1_15_R1")) {
            signedit = new SignEdit_1_15_R1();
        }
        return signedit != null;
    }

    public static void log(String msg){
        Bukkit.getConsoleSender().sendMessage("§8[§eSchild§8]" + " §c" + msg);
    }
}
