package rexfracht868454.ainpc.utils;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.trait.TraitName;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import rexfracht868454.ainpc.Main;

import java.util.List;

@TraitName("convotrait")
public class ConvoTrait extends Trait {

    private final Main plugin = Main.getPlugin(Main.class);
    private final String CONVO_STARTER;
    private Player talkingTo = null;
    private StringBuilder conversation = new StringBuilder();
    public String role;

    public ConvoTrait() {
        super("convotrait");
        CONVO_STARTER = "The AI: ";
    }

    public ConvoTrait(String role) {
        super("convotrait");
        this.role = role;
        this.CONVO_STARTER = "The following is a conversation with an AI who represents a " + this.role.toLowerCase() + " NPC character in Minecraft. " +
                "The AI should limit his knowledge of the world to minecraft and being a " + this.role.toLowerCase() + " and try not to stray even if asked about something else. " +
                "Play this " + this.role.toLowerCase() + "role the best you can.\n\nHuman: Hey!\n\nAI:";
    }

    @EventHandler
    public void startConversation(NPCRightClickEvent event) {

        if (event.getNPC() != npc) return;

        Player p = event.getClicker();
        if (this.talkingTo == null) {
            startConversation(p);
        } else {
            if (this.talkingTo != p) {

                if (npc.getEntity().getLocation().distance(this.talkingTo.getLocation()) > 20) {
                    this.talkingTo.sendMessage(ChatColor.RED + "The " + this.role + " NPC stopped talking to you because you moved too far away.");
                    stopConversation();
                }
                p.sendMessage(ChatColor.RED + "I am talking to someone else right now!");
            } else {
                p.sendMessage(ChatColor.RED + "I am already talking to you!");
            }
        }
    }

    private void startConversation(Player p) {
        this.talkingTo = p;
        this.conversation = new StringBuilder(this.CONVO_STARTER);
        getResponse(this.talkingTo, null);
    }

    public void stopConversation() {
        this.talkingTo.sendMessage(ChatColor.RED + "You are no longer talking to the " + this.role + " NPC.");
        this.talkingTo = null;
        this.conversation = new StringBuilder();
    }

    public Player getTalkingTo() {
        return talkingTo;
    }

    public void addMessage(String message) {
        this.conversation.append("\n\nHuman:").append(message).append("\n\nAI:");
    }

    public void getResponse(Player p, String playerMessage) {
        plugin.adventure().sender(p).sendActionBar(Component.text(ChatColor.GREEN + "Thinking..."));
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, 1);

        OpenAiService service = new OpenAiService(plugin.getConfig().getString("api-key"), 0);
        CompletionRequest request = CompletionRequest.builder()
                .prompt(this.conversation.toString())
                .model("text-davinci-003")
                .temperature(0.50)
                .maxTokens(150)
                .topP(1.0)
                .frequencyPenalty(0.0)
                .presencePenalty(0.6)
                .stop(List.of("Human:", "AI:"))
                .build();
        var choices = service.createCompletion(request).getChoices();
        var response = choices.get(0).getText();
        this.conversation.append(response.stripLeading());
        if (playerMessage != null) p.sendMessage("\nYou: " + playerMessage);
        p.sendMessage("\n" + this.npc.getName() + ": " + response.stripLeading());
    }
}
