package Commands;

import Core.Commands;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;
import java.util.Random;

public class RateWaifu implements Commands {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try {
            Random random = new Random();
            int rate = random.nextInt(10) + 1;
            List<User> mentionedUsers = event.getMessage().getMentionedUsers();
            User user = mentionedUsers.get(0);
            if (!mentionedUsers.isEmpty() && !user.getId().equals("199399348721680385")) {
                event.getTextChannel().sendTyping().queue();
                event.getTextChannel().sendMessage("I rate " + user.getAsMention() + " a " + rate + "/10.").queue();
            } else if (user.getId().equals("199399348721680385")) {
                event.getTextChannel().sendTyping().queue();
                event.getTextChannel().sendMessage("I rate myself a 10/10!").queue();
            } else {
                event.getTextChannel().sendTyping().queue();
                event.getTextChannel().sendMessage("```Please mention a user and then try again!```").queue();
            }
        } catch (IndexOutOfBoundsException i) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("@everyone or @here doesn't work! Please mention a user!").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
