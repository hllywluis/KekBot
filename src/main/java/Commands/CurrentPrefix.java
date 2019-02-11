package Commands;

import Core.Commands;
import Core.KekBot;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CurrentPrefix implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendTyping().queue();
        event.getTextChannel().sendMessage("The current prefix is: '" + KekBot.getPrefix() + "'.").queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}