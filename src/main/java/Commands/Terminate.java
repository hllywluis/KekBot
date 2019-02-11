package Commands;

import Core.Commands;
import Core.KekBot;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.IOException;

public class Terminate implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getMessage().getAuthor().getId().equals("141025296072572929")) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("See you next time!\n- osu! 2016").queue();
            System.exit(0);
        } else {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("You do not have permission to run this command.").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
