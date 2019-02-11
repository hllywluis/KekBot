package Commands;

import Core.Commands;
import Core.KekBot;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.PermissionException;

public class Name implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try {
            if (event.getAuthor().getId().equals(event.getGuild().getOwner().getEffectiveName()) || event.getAuthor().getId().equals("141025296072572929") || KekBot.isWhitelisted) {
                String user = event.getMessage().getRawContent().substring(4);
                String[] changedNameArray = user.split("\\s+");
                User nickChange = event.getMessage().getMentionedUsers().get(0);
                Member changeNick = event.getGuild().getMember(nickChange);
                event.getGuild().getController().setNickname(changeNick, changedNameArray[1]).queue();
            } else {
                event.getTextChannel().sendTyping().queue();
                event.getTextChannel().sendMessage("You don't have enough permission to use this command.").queue();
            }
        } catch (PermissionException p) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("I can't modify a user's nickname with a role above or equal to me!").queue();
        } catch (IndexOutOfBoundsException i) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("Please enter a nickname after the mentioned user.").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
