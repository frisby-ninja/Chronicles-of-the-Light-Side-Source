package net.ninjaworks.chroniclesofthelightside.manasystem.client;

import net.minecraftforge.client.event.InputEvent;
import net.ninjaworks.chroniclesofthelightside.manasystem.network.PacketGatherMana;
import net.ninjaworks.chroniclesofthelightside.setup.Messages;

public class KeyInputHandler {

    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBindings.gatherManaKeyMapping.consumeClick()) {
            Messages.sendToServer(new PacketGatherMana());
        }
    }
}
