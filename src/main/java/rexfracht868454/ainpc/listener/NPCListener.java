package rexfracht868454.ainpc.listener;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import rexfracht868454.ainpc.Main;
import rexfracht868454.ainpc.utils.ConvoTrait;

import java.util.concurrent.CompletableFuture;

public class NPCListener implements Listener {

    private final Main plugin;

    public NPCListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent e) {

        for (NPC npc : CitizensAPI.getNPCRegistry().sorted()) {

            if (npc.getEntity() == null || npc.getTraitNullable(ConvoTrait.class) == null) {
                continue;
            }

            var trait = npc.getTraitNullable(ConvoTrait.class);
            var talkingTo = trait.getTalkingTo();

            if (talkingTo == null || !talkingTo.equals(e.getPlayer())) {
                continue;
            }

            if (npc.getEntity().getLocation().distance(e.getPlayer().getLocation()) > 5) {
                trait.stopConversation();
            } else {
                trait.addMessage(e.getMessage());

                CompletableFuture.runAsync(() -> {
                    trait.getResponse(talkingTo, e.getMessage());
                });

                e.setCancelled(true);
            }

        }

    }
}
