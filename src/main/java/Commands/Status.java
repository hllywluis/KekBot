package Commands;

import Core.Commands;
import Core.KekBot;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

public class Status implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        long start = System.currentTimeMillis();
        event.getTextChannel().sendTyping().queue();
        int servers = event.getJDA().getGuilds().size();
        int channels = event.getJDA().getTextChannels().size();
        int voice = event.getJDA().getVoiceChannels().size();
        long uptime = KekBot.mxBean.getUptime();
        long stop = System.currentTimeMillis();
        long totalTime = stop - start;
        String time = String.format("%d hours %d minutes %d seconds",
                TimeUnit.MILLISECONDS.toHours(uptime),
                TimeUnit.MILLISECONDS.toMinutes(uptime),
                TimeUnit.MILLISECONDS.toSeconds(uptime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(uptime))
        );
        event.getTextChannel().sendMessage("Status:\n\n" +
                "Uptime: " + time + "\n" +
                "Text Channels: " + channels + "\n" +
                "Voice Channels: " + voice + "\n" +
                "Connected Servers: " + servers + "\n" +
                "\n" +
                "\n" +
                "*Generated in " + totalTime + "ms.*").queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}