package Commands;

import Core.Commands;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;

public class BooBot implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        Random random = new Random();
        int topkek = random.nextInt(5) + 1;
        if (topkek == 1) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("He's a pleb.").queue();
        } else if (topkek == 2) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("Please kill BooBot, " + event.getMessage().getAuthor().getAsMention() + ".").queue();
        } else if (topkek == 3) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention() + " should kill himself for thinking of BooBot.").queue();
        } else if (topkek == 4) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("He will never have as much kek as I.").queue();
        } else if (topkek == 5) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("Please find him and bring him to me. I want to see the look on his sad little ghost face when he sees his family, his users, and all of his loved ones suffer and die in front of him.").queue();
        } else {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("Nice job RNG.").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
