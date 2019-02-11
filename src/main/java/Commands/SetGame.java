package Commands;

import Core.Commands;
import Core.KekBot;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class SetGame implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getAuthor().getId().equals("141025296072572929") || KekBot.isWhitelisted) {
            try {
                String game = event.getMessage().getRawContent().substring(4);
                event.getJDA().getPresence().setGame(Game.of(game));
                event.getTextChannel().sendTyping().queue();
                event.getTextChannel().sendMessage("Game '" + game + "' set.").queue();
            } catch (StringIndexOutOfBoundsException s) {
                event.getTextChannel().sendTyping().queue();
                event.getTextChannel().sendMessage("Please enter a game.").queue();
            }
        } else {
            event.getTextChannel().sendTyping().complete();
            event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention() + ", you don't have permission to change my current game.").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
