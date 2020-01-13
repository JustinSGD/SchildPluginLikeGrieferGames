package de.schild.utils;

import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_13_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_13_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_13_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_13_R1.BlockPosition;
import net.minecraft.server.v1_13_R1.PacketPlayOutOpenSignEditor;
import net.minecraft.server.v1_13_R1.PlayerConnection;
import net.minecraft.server.v1_13_R1.TileEntitySign;

public class SignEdit_1_13_R1 implements SignEdit {
    public void editSign(Player player, Sign sign) {
        String[] lines = new String[4];
        for (int i = 0; i < sign.getLines().length; i++) {
            if (sign.getLine(i) != null) {
                lines[i] = sign.getLine(i).replaceAll("§", "&");
            }
        }
        TileEntitySign tes = (TileEntitySign)((CraftWorld)sign.getWorld()).getHandle().getTileEntity(new BlockPosition(sign.getX(), sign.getY(), sign.getZ()));
        tes.isEditable = true;
        tes.a(((CraftHumanEntity)player).getHandle());

        player.sendSignChange(sign.getLocation(), lines);
        PacketPlayOutOpenSignEditor packet2 = new PacketPlayOutOpenSignEditor(tes.getPosition());
        PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        connection.sendPacket(packet2);
    }
}