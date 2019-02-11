package Core;

import com.michaelwflaherty.cleverbotapi.CleverBotQuery;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.impl.UserImpl;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.List;
import java.util.Objects;

class BotListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        try {
//          If user starts message with a prefix, do this.
            if (event.getMessage().getContent().startsWith(KekBot.prefix) && !Objects.equals(event.getMessage().getAuthor().getId(), event.getJDA().getSelfUser().getId())) {
                KekBot.handleCommand(KekBot.parser.parse(event.getMessage().getContent().toLowerCase(), event));
            } else {
//              Get mentioned users in a message.
                List<User> mentionedUsers = event.getMessage().getMentionedUsers();
                if (mentionedUsers.toString().contains("199399348721680385")) {
                    try {
//                      Get user's message and return a reply.
                        event.getTextChannel().sendTyping().queue();
                        String query = event.getMessage().getRawContent().substring(5);
                        CleverBotQuery botQuery = new CleverBotQuery("e4e19b45c551f5e940fcc64b3ce94bda", query);
                        botQuery.sendRequest();
                        String reply = botQuery.getResponse();
                        event.getTextChannel().sendMessage("\uD83D\uDCAC " + reply).queue();
                    } catch (Exception e) {
//                      Catch any errors.
                        event.getTextChannel().sendTyping().queue();
                        event.getTextChannel().sendMessage("An unexpected error occured. Sorry!").queue();
                        e.printStackTrace();
                    }
                }
                if (!Objects.equals(event.getMessage().getAuthor().getId(), event.getJDA().getSelfUser().getId())) {
                    if (!event.getAuthor().hasPrivateChannel()) {
                        try {
//                  Get user's message and try to reply.
                            event.getAuthor().openPrivateChannel().complete();
                            String query = event.getMessage().getRawContent();
                            CleverBotQuery privateQuery = new CleverBotQuery("e4e19b45c551f5e940fcc64b3ce94bda", query);
                            privateQuery.sendRequest();
                            String privateReply = privateQuery.getResponse();
                            event.getPrivateChannel().sendMessage("\uD83D\uDCAC " + privateReply).queue();
                        } catch (Exception e) {
//                  Catch any errors.
                            event.getPrivateChannel().sendTyping().queue();
                            event.getPrivateChannel().sendMessage("An unexpected error occured. Sorry!").queue();
                        }
                    } else if (event.getAuthor().hasPrivateChannel()) {
                        try {
                            String query = event.getMessage().getRawContent();
                            CleverBotQuery privateQuery = new CleverBotQuery("e4e19b45c551f5e940fcc64b3ce94bda", query);
                            privateQuery.sendRequest();
                            String privateReply = privateQuery.getResponse();
                            event.getPrivateChannel().sendMessage("\uD83D\uDCAC " + privateReply).queue();
                        } catch (Exception e) {
                            event.getPrivateChannel().sendTyping().queue();
                            event.getPrivateChannel().sendMessage("An unexpected error occured. Sorry!").queue();
                        }
                    }
                }
            }
        } catch (NullPointerException n) {
            if (!event.getAuthor().hasPrivateChannel()) {
                event.getAuthor().openPrivateChannel().complete();
                ((UserImpl) event.getAuthor()).getPrivateChannel().sendMessage("I can't receive PMs right now. Try sending a command in a server!").queue();
            }
        } catch (StringIndexOutOfBoundsException s) {
            if (!event.getAuthor().hasPrivateChannel()) {
                event.getAuthor().openPrivateChannel().complete();
                ((UserImpl) event.getAuthor()).getPrivateChannel().sendMessage("Please send this command in a server.").queue();
            }
        }
    }

    //  Print status to console.
    public void onReady(ReadyEvent event) {
        KekBot.msgLog("Logged in as " + event.getJDA().getSelfUser().getName() + " (ID: " + event.getJDA().getSelfUser().getId() + ")");
    }
}