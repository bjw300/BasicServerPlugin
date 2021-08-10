package com.cfriend.basicserverplugin.bukkit.api.gui;

public class AnvilGUi {

    /*public void openAnvil(Player player) {
        EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();
        openContainer(entityPlayer, new ContainerAnvil(entityPlayer.inventory, entityPlayer.world, new BlockPosition(0,0,0), entityPlayer), "minecraft:anvil", "");
    }

    private void openContainer(EntityPlayer player, Container container, String name, String txt) {
        Container cont = CraftEventFactory.callInventoryOpenEvent(player, container);

        int nextContainerID = player.nextContainerCounter();

        cont.windowId = nextContainerID;
        cont.addSlotListener(player);
        cont.checkReachable = false;

        player.activeContainer = cont;

        player.playerConnection.sendPacket(new PacketPlayOutOpenWindow(nextContainerID, name, new ChatComponentText(txt)));
    }*/
}