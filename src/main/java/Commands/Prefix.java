package Commands;

import Core.Commands;
import Core.KekBot;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Prefix implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getMessage().getAuthor().getId().equals("141025296072572929")) {
            if (event.getMessage().getRawContent().endsWith("~prefix")) {
                event.getTextChannel().sendTyping().queue();
                event.getTextChannel().sendMessage("```You cannot set a blank prefix.```").queue();
            } else {
                String prefix = event.getMessage().getRawContent().substring(8);
                KekBot.setPrefix(prefix);
                event.getTextChannel().sendTyping().queue();
                event.getTextChannel().sendMessage("Prefix changed to '" + prefix + "'.").queue();
            }
        } else {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(event.getMessage().getAuthor().getName() + " does not sufficient permission to change the prefix.").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
