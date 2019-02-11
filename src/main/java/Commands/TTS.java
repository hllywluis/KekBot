package Commands;

import Core.Commands;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.PermissionException;

public class TTS implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try {
            String msg = event.getMessage().getRawContent().substring(5);
            MessageBuilder builder = new MessageBuilder();
            builder.getStringBuilder().append(msg);
            builder.setTTS(true);
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(builder.build()).queue();
        } catch (PermissionException p) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("I can't send TTS messages.").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
