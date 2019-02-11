package Commands;

import Core.Commands;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Messages implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        int counter = 0;
        event.getTextChannel().sendTyping().queue();
        Message messageToSend = event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention() + " has sent " + counter + " messages.").complete();
        for (Message message : event.getTextChannel().getHistory().retrievePast(100).complete()) {
            if (message.getAuthor().equals(event.getMessage().getAuthor())) {
                counter++;
                event.getTextChannel().editMessageById(messageToSend.getId(), event.getMessage().getAuthor().getAsMention() + " has sent " + counter + " messages.").queue();
            }
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}