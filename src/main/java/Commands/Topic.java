package Commands;

import Core.Commands;
import Core.KekBot;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.PermissionException;

public class Topic implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getMessage().getAuthor().getId().equals("141025296072572929") || KekBot.isWhitelisted) {
            try {
                String topic = event.getMessage().getRawContent().substring(7);
                event.getTextChannel().getManager().setTopic(topic).queue();
                event.getTextChannel().sendTyping().queue();
                event.getTextChannel().sendMessage("#" + event.getTextChannel().getName() + "'s topic has been changed to: " + topic + ".").queue();
            } catch (PermissionException p) {
                event.getTextChannel().sendTyping().queue();
                event.getTextChannel().sendMessage("I don't have enough permission to change the topic.").queue();
            } catch (StringIndexOutOfBoundsException s) {
                event.getTextChannel().sendTyping().queue();
                event.getTextChannel().sendMessage("Please enter a topic.").queue();
            }
        } else {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention() + ", you don't have enough permission to change the channel's topic.").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
