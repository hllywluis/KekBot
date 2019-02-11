package Commands;

import Core.Commands;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;

public class Coin implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendTyping().queue();
        Random random = new Random();
        int flip = random.nextInt(2) + 1;
        if (flip == 1) {
            event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention() + ", you got heads! :v::skin-tone-3:").queue();
        } else {
            event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention() + ", you got tails! :ok_hand::skin-tone-3: ").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
