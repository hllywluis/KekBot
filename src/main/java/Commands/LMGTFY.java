package Commands;

import Core.Commands;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class LMGTFY implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendTyping().queue();
        event.getTextChannel().sendMessage("http://lmgtfy.com/?q=" + event.getMessage().getRawContent().substring(8).replace(" ", "+")).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
