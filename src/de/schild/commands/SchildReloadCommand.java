package de.schild.commands;

import de.schild.data.Data;
import de.schild.file.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SchildReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] strings) {

        if(commandSender.hasPermission(Data.RELOAD)) {
            if(strings.length == 0) {
                FileManager.reloadFile();
                commandSender.sendMessage(Data.PREFIX + "§aConfig erfolgreich reloadet.");
            } else {
                commandSender.sendMessage(Data.PREFIX + "§cNutze: §e/reloadschild");
            }
        } else {
            commandSender.sendMessage(Data.NOPERMS);
        }
        return false;
    }
}
