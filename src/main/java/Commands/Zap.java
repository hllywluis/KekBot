package Commands;

import Core.Commands;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;

public class Zap implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try {
            List<User> mentionedUser = event.getMessage().getMentionedUsers();
            User user = mentionedUser.get(0);
            User mentioner = event.getMessage().getAuthor();
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":zap: " + user.getAsMention() + " has been zapped by " + mentioner.getAsMention() + "! :zap:").queue();
        } catch (IndexOutOfBoundsException i) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("Please mention a user to zap!").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
