package Commands;

import Core.Commands;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.ErrorResponseException;
import net.dv8tion.jda.core.exceptions.PermissionException;

import java.util.Collection;

public class Prune implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getMessage().getAuthor().getId().equals("141025296072572929")) {
            if (event.getMessage().getRawContent().endsWith("~prune")) {
                event.getTextChannel().sendTyping().complete();
                event.getChannel().sendMessage("Please enter a number.").queue();
            } else {
                try {
                    int numberOfMessages = Integer.parseInt(event.getMessage().getRawContent().substring(7));
                    Collection<Message> prunedMessages = event.getTextChannel().getHistory().retrievePast(numberOfMessages).complete();
                    event.getTextChannel().deleteMessages(prunedMessages).queue();
                } catch (PermissionException p) {
                    event.getTextChannel().sendTyping().complete();
                    event.getTextChannel().sendMessage("I don't seem to have enough permission to use this command.").queue();
                } catch (IllegalArgumentException | ErrorResponseException i) {
                    event.getTextChannel().sendTyping().complete();
                    event.getTextChannel().sendMessage("You can only delete messages that are under 14 days old.").queue();
                }
            }
        } else {
            event.getTextChannel().sendTyping().queue();
            event.getChannel().sendMessage(event.getMessage().getAuthor().getAsMention() + ", you don't have enough permission to use this command.").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
