package Commands;

import Core.Commands;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;

public class Decide implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendTyping().queue();
        String option1 = event.getMessage().getRawContent().substring(5);
        String[] options = option1.split(" or ");
        Random random = new Random();
        int rng = random.nextInt(options.length) + 1;
        if (rng == 1) {
            event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention() + ", I have decided upon **" + options[0] + "**!").queue();
        } else if (rng == 2) {
            event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention() + ", I have decided upon **" + options[1] + "**!").queue();
        } else if (rng == 3) {
            event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention() + ", I have decided upon **" + options[2] + "**!").queue();
        } else if (rng == 4) {
            event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention() + ", I have decided upon **" + options[3] + "**!").queue();
        } else if (rng == 5) {
            event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention() + ", I have decided upon **" + options[4] + "**!").queue();
        } else if (rng == 6) {
            event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention() + ", I have decided upon **" + options[5] + "**!").queue();
        } else if (rng == 7) {
            event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention() + ", I have decided upon **" + options[6] + "**!").queue();
        } else if (rng == 8) {
            event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention() + ", I have decided upon **" + options[7] + "**!").queue();
        } else if (rng == 9) {
            event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention() + ", I have decided upon **" + options[8] + "**!").queue();
        } else {
            event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention() + ", I have decided upon **" + options[9] + "**!").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}