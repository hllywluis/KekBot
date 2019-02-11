package Commands;

import Core.Commands;
import Core.KekBot;
import Core.PlayerManager;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Play implements Commands {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        PlayerManager manager = KekBot.getPlayerManager(event.getGuild());
        String title = event.getMessage().getRawContent().substring(2).replace(" ", "");
        Guild guild = event.getGuild();
        System.out.println(title);
        if (guild != null && !title.equals("")) {
            KekBot.loadAndPlay(event.getTextChannel(), title);
        } else {
            if (manager.player.isPaused()) {
                manager.player.setPaused(false);
                event.getTextChannel().sendTyping().complete();
                event.getTextChannel().sendMessage("Playing " + manager.player.getPlayingTrack().getInfo().title + ".").queue();
            } else if (!manager.player.isPaused()) {
                manager.player.setPaused(true);
                event.getTextChannel().sendTyping().complete();
                event.getTextChannel().sendMessage("Paused.").queue();
            } else {
                event.getTextChannel().sendTyping().complete();
                event.getTextChannel().sendMessage("Please enter a link for audio.").queue();
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}