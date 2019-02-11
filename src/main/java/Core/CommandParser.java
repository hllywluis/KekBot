package Core;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Collections;

class CommandParser {
    CommandContainer parse(String raw, MessageReceivedEvent event) {
        ArrayList<String> split = new ArrayList<>();
        String topkek = raw.replaceFirst(KekBot.prefix, "");
        String[] topSplit = topkek.split(" ");
        Collections.addAll(split, topSplit);
        String invoke = split.get(0);
        String[] args = new String[split.size() - 1];
        split.subList(1, split.size()).toArray(args);
        return new CommandContainer(raw, topkek, topSplit, invoke, args, event);
    }

    class CommandContainer {
        final String raw;
        final String kek;
        final String[] splitKek;
        final String invoke;
        final String[] args;
        final MessageReceivedEvent event;

        CommandContainer(String raw, String kek, String[] splitKek, String invoke, String[] args, MessageReceivedEvent event) {
            this.raw = raw;
            this.kek = kek;
            this.splitKek = splitKek;
            this.invoke = invoke;
            this.args = args;
            this.event = event;
        }
    }
}