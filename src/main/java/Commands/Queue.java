package Commands;

import Core.Commands;
import Core.KekBot;
import Core.PlayerManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.concurrent.BlockingQueue;

public class Queue implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendTyping().complete();
        PlayerManager manager = KekBot.getPlayerManager(event.getGuild());
        BlockingQueue<AudioTrack> queue = manager.scheduler.getQueue();
        String queueList;
        int counter = 0;
        StringBuilder queueListBuilder = new StringBuilder("Queue:\n");
        for(AudioTrack track : queue) {
            counter++;
            queueListBuilder.append("\n").append(counter).append(". ").append(track.getInfo().title).append("\n");
        }
        queueList = queueListBuilder.toString();
        if(counter == 0) {
            queueList += "\nNothing yet..";
        }
        event.getTextChannel().sendMessage(queueList).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}