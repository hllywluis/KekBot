package Commands;

import Core.Commands;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;

public class RNG implements Commands {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendTyping().queue();
        Random random = new Random();
        int lel = random.nextInt(50) + 1;
        event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention() + "'s number is " + lel + ".").queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
