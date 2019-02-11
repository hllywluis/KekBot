package Commands;

import Core.Commands;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Define implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        String term = event.getMessage().getRawContent().substring(3).replace(" ", "%20");
        event.getTextChannel().sendTyping().queue();
        event.getTextChannel().sendMessage("http://www.merriam-webster.com/dictionary/" + term).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}