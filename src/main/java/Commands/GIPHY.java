package Commands;

import Core.Commands;
import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.search.SearchRandom;
import at.mukprojects.giphy4j.exception.GiphyException;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class GIPHY implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendTyping().queue();
        Giphy giphy = new Giphy("dc6zaTOxFJmzC");
        String gifSearch = event.getMessage().getRawContent().substring(5);
        try {
            SearchRandom feed = giphy.searchRandom(gifSearch);
            String gif = feed.getData().getImageOriginalUrl();
            event.getTextChannel().sendMessage(gif).queue();
        } catch (GiphyException e) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("No results found. Try another search term!").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
