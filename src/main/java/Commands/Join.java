package Commands;

import Core.Commands;
import Core.KekBot;
import net.dv8tion.jda.core.entities.GuildVoiceState;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.IOException;

public class Join implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try {
            if (event.getMessage().getAuthor().getId().equals("141025296072572929") || KekBot.whitelist.isWhitelisted(event)) {
                User user = event.getMessage().getAuthor();
                GuildVoiceState voiceState = event.getGuild().getMember(user).getVoiceState();
                VoiceChannel channel = voiceState.getChannel();
                event.getGuild().getAudioManager().openAudioConnection(channel);
            }
        } catch (IllegalStateException illegal) {
            if (event.getGuild().getAudioManager().isConnected()) {
                event.getGuild().getAudioManager().closeAudioConnection();
                User author = event.getMessage().getAuthor();
                GuildVoiceState authorState = event.getGuild().getMember(author).getVoiceState();
                VoiceChannel newChannel = authorState.getChannel();
                event.getGuild().getAudioManager().openAudioConnection(newChannel);
            }
        } catch (IOException io) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("Hm. I can't read the whitelist file.").queue();
        } catch (IllegalArgumentException np) {
            event.getTextChannel().sendTyping().queue();
            event.getTextChannel().sendMessage("You are not in a voice channel.").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
