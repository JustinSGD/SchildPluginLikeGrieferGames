package de.schild.listener;

import de.schild.data.Data;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignEditListener implements Listener {

    @EventHandler
    public void onSignEdit(SignChangeEvent event) {
        Player player = event.getPlayer();
        if(player.hasPermission(Data.SCHILDCOLOR)) {
            String[] lines = event.getLines();
            for (int i = 0; i <= 3; i++) {
                event.setLine(i, ChatColor.translateAlternateColorCodes('&', lines[i]));
            }
        }
    }

}
