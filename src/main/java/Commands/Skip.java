package Commands;

import Core.Commands;
import Core.KekBot;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Skip implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        KekBot.skipTrack(event.getTextChannel());
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
