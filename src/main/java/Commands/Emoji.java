package Commands;

import Core.Commands;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;

public class Emoji implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        Random random = new Random();
        int rng = random.nextInt(20) + 1;
        if (rng == 0) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":yum:").queue();
        } else if (rng == 1) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":shit:").queue();
        } else if (rng == 2) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":candy:").queue();
        } else if (rng == 3) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":v:").queue();
        } else if (rng == 4) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":couple_mm:").queue();
        } else if (rng == 5) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":snake:").queue();
        } else if (rng == 6) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":evergreen_tree:").queue();
        } else if (rng == 7) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":joy:").queue();
        } else if (rng == 8) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":grinning:").queue();
        } else if (rng == 9) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":vulcan:").queue();
        } else if (rng == 10) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":maple_leaf:").queue();
        } else if (rng == 11) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":eggplant:").queue();
        } else if (rng == 12) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":peach:").queue();
        } else if (rng == 13) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":ramen: Mmm! ラーメン！ :ramen:").queue();
        } else if (rng == 14) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":pizza:").queue();
        } else if (rng == 15) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":rice_ball:").queue();
        } else if (rng == 16) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":bento:").queue();
        } else if (rng == 17) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":8ball:").queue();
        } else if (rng == 18) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":slot_machine:").queue();
        } else if (rng == 19) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":rainbow:").queue();
        } else {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage(":crystal_ball:").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
