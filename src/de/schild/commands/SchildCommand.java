package de.schild.commands;

import com.intellectualcrafters.plot.api.PlotAPI;
import de.schild.SchildMain;
import de.schild.data.Data;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class SchildCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] strings) {

        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(Data.PREFIX + "§cDu musst ein Spieler sein.");
        } else {
            Player player = (Player) commandSender;
            if(Bukkit.getServer().getPluginManager().getPlugin("PlotSquared") !=null &&
                    Bukkit.getServer().getPluginManager().getPlugin("PlotSquared").isEnabled()) {
                PlotAPI papi;
                papi = new PlotAPI();
                if (player.hasPermission(Data.SCHILDCOMMAND)) {
                    Block block = player.getTargetBlock((Set<Material>) null,5);
                    if ((block == null) || (block.getState() instanceof Sign)) {
                        if ((papi.getPlot(player.getTargetBlock((Set<Material>) null,5).getLocation()) != null) || player.hasPermission(Data.NOPLOTEDITPERM)) {
                            if (papi.isInPlot(player) && papi.getPlot(player.getLocation()).isOwner(player.getUniqueId()) || player.hasPermission(Data.NOPLOTOWNEREDITPERM)) {
                                Sign sign = (Sign) block.getState();
                                SchildMain.getSignEdit().editSign(player, sign);
                            } else {
                                player.sendMessage(Data.PREFIX + Data.NOPLOTOWNER);
                            }
                        } else {
                            player.sendMessage(Data.PREFIX + Data.NOPLOT);
                        }
                    } else {
                        player.sendMessage(Data.PREFIX + Data.NOSIGN);
                    }
                } else {
                    player.sendMessage(Data.NOPERMS);
                }
            } else {
                if (player.hasPermission(Data.SCHILDCOMMAND)) {
                    Block block = player.getTargetBlock((Set<Material>) null,5);
                    if ((block == null) || (block.getState() instanceof Sign)) {
                        Sign sign = (Sign) block.getState();
                        SchildMain.getSignEdit().editSign(player, sign);
                    } else {
                        player.sendMessage(Data.PREFIX + Data.NOSIGN);
                    }
                } else {
                    player.sendMessage(Data.NOPERMS);
                }
            }
        }

        return false;
    }
}
