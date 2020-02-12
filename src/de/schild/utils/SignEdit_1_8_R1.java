package de.schild.utils;

import net.minecraft.server.v1_8_R1.*;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class SignEdit_1_8_R1 implements SignEdit {

    public void editSign(Player player, Sign sign) {
        IChatBaseComponent[] lines = new IChatBaseComponent[4];
        for (int i = 0; i < sign.getLines().length; i++) {
            if (sign.getLine(i) != null) {
                lines[i] = new ChatComponentText(sign.getLine(i).replaceAll("§", "&"));
            }
        }
        TileEntitySign tes = (TileEntitySign)((CraftWorld)sign.getWorld()).getHandle().getTileEntity(new BlockPosition(sign.getX(), sign.getY(), sign.getZ()));
        tes.isEditable = true;
        tes.a(((CraftHumanEntity)player).getHandle());

        PacketPlayOutUpdateSign packet1 = new PacketPlayOutUpdateSign(tes.getWorld(), tes.getPosition(), lines);
        PacketPlayOutOpenSignEditor packet2 = new PacketPlayOutOpenSignEditor(tes.getPosition());
        PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        connection.sendPacket(packet1);
        connection.sendPacket(packet2);
    }
}