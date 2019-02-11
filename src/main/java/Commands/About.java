package Commands;

import Core.Commands;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class About implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendTyping().queue();
        event.getTextChannel().sendMessage("```Hi. My name is " + event.getJDA().getSelfUser().getName() + ". I was created at around 11:30pm on July 4th 2016. My creator is hllywluis. No, I don't say Kek a lot, but my creator sure does!```").queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
