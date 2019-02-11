package Commands;

import Core.Commands;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Wikipedia implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendTyping().queue();
        event.getTextChannel().sendMessage("https://en.wikipedia.org/wiki/" + event.getMessage().getRawContent().substring(5).replace(" ", "_")).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
