package Commands;

import Core.Commands;
import Core.KekBot;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Whitelist implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getMessage().getAuthor().getId().equals("141025296072572929")) {
            try {
                List<User> users = event.getMessage().getMentionedUsers();
                String user = users.toString();
                File whitelist = new File("Whitelist.txt");
                BufferedReader reader = new BufferedReader(new FileReader(whitelist));
                if (whitelist.exists()) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(whitelist, true));
                    if (reader.readLine() != null) {
                        writer.newLine();
                    }
                    writer.write(user);
                    writer.close();
                    event.getTextChannel().sendTyping().queue();
                    event.getTextChannel().sendMessage(users.get(0).getAsMention() + " has been added to the whitelist.").queue();
                } else {
                    FileWriter writer = new FileWriter(whitelist);
                    writer.write(user);
                    writer.close();
                    event.getTextChannel().sendTyping().queue();
                    event.getTextChannel().sendMessage(users.get(0).getAsMention() + " has been added to the whitelist.").queue();
                }
            } catch (IOException io) {
                event.getTextChannel().sendTyping().queue();
                event.getTextChannel().sendMessage("Hm. I can't write to the whitelist file.").queue();
            }
        } else {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("You don't have enough permission to run this command!").queue();
        }
    }

    private User[] getWhitelistedUsers(MessageReceivedEvent event) throws IOException {
        int whileCounter = 0;
        File whitelist = new File("Whitelist.txt");
        Scanner reader = new Scanner(whitelist);
        String currentLine;
        User[] users = new User[500];
        while (reader.hasNextLine()) {
            currentLine = reader.nextLine();
            String[] toStringArray = currentLine.substring(3).split("\\(");
            String[] idArray = toStringArray[1].split("\\)");
            String userID = idArray[0];
            User whitelistedUser = event.getJDA().getUserById(userID);
            users[whileCounter] = whitelistedUser;
            whileCounter++;
        }
        return users;
    }

    boolean isWhitelisted(MessageReceivedEvent event) throws IOException {
        String userID = event.getMessage().getAuthor().getId();
        User[] whitelistedUsers = KekBot.whitelist.getWhitelistedUsers(event);
        for (int i = 0; i < 500; i++) {
            if (whitelistedUsers[i] != null) {
                if (whitelistedUsers[i].getId().equals(userID)) {
                    KekBot.isWhitelisted = true;
                    break;
                }
                KekBot.isWhitelisted = false;
            }
        }
        return KekBot.isWhitelisted;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
