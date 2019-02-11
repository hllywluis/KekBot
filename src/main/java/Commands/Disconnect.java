package Commands;

import Core.Commands;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Disconnect implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getMessage().getAuthor().getId().equals("141025296072572929")) {
            if (event.getGuild().getAudioManager().isConnected()) {
                event.getGuild().getAudioManager().closeAudioConnection();
            } else {
                event.getTextChannel().sendTyping().queue();
                event.getTextChannel().sendMessage("I am not in a voice channel.").queue();
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
