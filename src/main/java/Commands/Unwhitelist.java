package Commands;

import Core.Commands;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.*;
import java.util.List;

public class Unwhitelist implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try {
            if (event.getMessage().getAuthor().getId().equals("141025296072572929")) {
                List<User> mentionedUsers = event.getMessage().getMentionedUsers();
                String unwhitelistedUser = mentionedUsers.toString();
                File whitelist = new File("Whitelist.txt");
                File tempWhitelist = new File("TempWhitelist.txt");
                BufferedReader reader = new BufferedReader(new FileReader(whitelist));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempWhitelist));
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    String trimmedLine = currentLine.trim();
                    if (trimmedLine.equals(unwhitelistedUser)) {
                        continue;
                    }
                    writer.write(currentLine);
                    writer.close();
                }
            }
        } catch (FileNotFoundException fnf) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("An unexpected error occurred. Try checking the whitelist file.").queue();
        } catch (IOException io) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("Hm. I can't write to the whitelist file.").queue();
        }
        try {
            if (event.getMessage().getAuthor().getId().equals("141025296072572929")) {
                List<User> mentionedUsers = event.getMessage().getMentionedUsers();
                String unwhitelistedUser = mentionedUsers.toString();
                File whitelist = new File("Whitelist.txt");
                File tempWhitelist = new File("TempWhitelist.txt");
                BufferedReader reader = new BufferedReader(new FileReader(tempWhitelist));
                BufferedWriter writer = new BufferedWriter(new FileWriter(whitelist));
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    String trimmedLine = currentLine.trim();
                    if (trimmedLine.equals(unwhitelistedUser)) {
                        continue;
                    }
                    writer.write(currentLine);
                    writer.close();
                }
                event.getTextChannel().sendTyping().queue();
                event.getTextChannel().sendMessage(mentionedUsers.get(0).getAsMention() + " has been removed from the whitelist.").queue();
            }
        } catch (FileNotFoundException fnf) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("An unexpected error occurred. Try checking the whitelist file.").queue();
        } catch (IOException io) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("Hm. I can't write to the whitelist file.").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
