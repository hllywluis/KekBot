package Commands;

import Core.Commands;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Rename implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getMessage().getAuthor().getId().equals("141025296072572929")) {
            event.getJDA().getSelfUser().getManager().setName(event.getMessage().getRawContent().substring(4)).queue();
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("I have been renamed to: " + event.getMessage().getRawContent().substring(4) + ".").queue();
        } else {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("You do not have permission to run this command.").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
