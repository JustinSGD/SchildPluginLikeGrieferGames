package de.schild.file;

import de.schild.SchildMain;
import de.schild.data.Data;

public class FileManager {

    private static FileWriter file = new FileWriter(SchildMain.getPlugin().getDataFolder().getPath(), "config.yml");

    public static FileWriter getFile(){
        return file;
    }

    public static void loadFile() {
        if(!file.exist()) {
            file.setValue("Prefix", Data.PREFIX);

            file.setValue("NoPerms", Data.NOPERMS);
            file.setValue("NoSign", Data.NOSIGN);
            file.setValue("NoPlot", Data.NOPLOT);
            file.setValue("NoPlotOwner", Data.NOPLOTOWNER);


            file.setValue("ReloadPermission", Data.RELOAD);
            file.setValue("SchildPermission", Data.SCHILDCOMMAND);
            file.setValue("SchildColor", Data.SCHILDCOLOR);
            file.setValue("NoPlotEditPermission", Data.NOPLOTEDITPERM);
            file.setValue("NoPlotOwnerEditPermission", Data.NOPLOTOWNEREDITPERM);
            file.save();
        }
    }

    public static void readFile() {
        Data.PREFIX = file.getString("Prefix").replaceAll("&", "§");

        Data.NOPERMS = file.getString("NoPerms").replaceAll("&", "§");
        Data.NOSIGN = file.getString("NoSign").replaceAll("&", "§");
        Data.NOPLOT = file.getString("NoPlot").replaceAll("&", "§");
        Data.NOPLOTOWNER = file.getString("NoPlotOwner").replaceAll("&", "§");

        Data.RELOAD = file.getString("ReloadPermission");
        Data.SCHILDCOMMAND = file.getString("SchildPermission");
        Data.SCHILDCOLOR = file.getString("SchildColor");
        Data.NOPLOTEDITPERM = file.getString("NoPlotEditPermission");
        Data.NOPLOTOWNEREDITPERM = file.getString("NoPlotOwnerEditPermission");
    }

    public static void reloadFile() {
        file = new FileWriter(SchildMain.getPlugin().getDataFolder().getPath(), "config.yml");
        readFile();
    }
}
