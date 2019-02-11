package Commands;

import Core.Commands;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class UD implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendTyping().queue();
        String search = event.getMessage().getRawContent().substring(4).replace(" ", "+");
        event.getTextChannel().sendMessage("https://www.urbandictionary.com/define.php?term=" + search).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
