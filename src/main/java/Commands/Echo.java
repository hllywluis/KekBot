package Commands;

import Core.Commands;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Echo implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendTyping().queue();
        String echo = event.getMessage().getRawContent().substring(6);
        event.getTextChannel().sendMessage(echo).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
