package Commands;

import Core.Commands;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.impl.UserImpl;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class Help implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        String commands1 = "```Commands (there are so many!): \n\n" +
                "LMGTFY (lmgtfy) - Uses LMGTFY to search Google.\n\n" +
                "Wikipedia (wik) - Searches Wikipedia for a query.\n\n" +
                "Ping (ping) - Sends 'Pong!' in return.\n\n" +
                "RateWaifu (rw) - Gives a user their waifu rating.\n\n" +
                "Rename (rn) - Renames this bot.\n\n" +
                "RNG (rng) - Generates a random number from 1-50.\n\n" +
                "Help (help) - Shows this list of commands.\n\n" +
                "About (about) - Shows information about this bot.\n\n" +
                "Prefix (prefix) - Changes the prefix to an entered string.\n\n" +
                "Terminate (x) - Ends the bot.\n\n" +
                "GIPHY (gif) - Search for gifs on GIPHY.\n\n" +
                "CurrentPrefix (cp) - Displays the current prefix.\n\n" +
                "Kek (kek) - Sends a nice, friendly Kek.\n\n" +
                "Topic (topic) - Changes the current channel's topic.\n\n" +
                "TTS (tts) - Sends a message as TTS.\n\n" +
                "FlipTable (ft) - Flips a table. Poor table.\n\n" +
                "Define (d) - Defines a term using Merriam-Webster.\n\n" +
                "Echo (echo) - Echoes a message.\n\n" +
                "SetGame (sg) - Sets the current game I'm playing.\n\n" +
                "BooBot (bb) - Various insults aimed at BooBot, don't kill yourself.\n\n" +
                "Zap (zap) - Zap (and perhaps mildly annoy) a user.\n\n" +
                "Coin (coin) - Flips a coin.\n\n" +
                "Decide (dcd) - Randomly decides between up to 10 choices.\n\n" +
                "Emoji (em) - Sends a random emoji.\n\n" +
                "WikiHow (wh) - Searches WikiHow for something.\n\n" +
                "Prune (pr) - Deletes a specified number of messages.\n\n" +
                "Nickname (nn) - Sets a user's nickname in a server.\n\n" +
                "Join (jn) - Joins the current voice channel (must be on whitelist).\n\n" +
                "Disconnect (dc) - Disconnects from a voice channel.\n\n" +
                "Whitelist (whtl) - Whitelists a user (owner only).\n\n" +
                "Unwhitelist (uwhtl) - Removes a user from the whitelist (owner only).\n\n" +
                "URL (url) - Sends OAUTH URL to add this bot to a server.\n\n" +
                "Thesaurus (thsr) - See related words to a term with Merriam-Webster.\n\n" +
                "Play + Pause (p) - Plays a link when connected to a voice channel (use ~jn), pauses when something is playing.\n\n" +
                "Skip (skip) - Skips the current track.\n\n```";
        String commands2 = "```Queue (q) - Shows all tracks in the queue.\n\n" +
                "Status (st) - Gets the current status of the bot.\n\n" +
                "Messages (msgs) - Lists recent messages sent by you in a server, up to 100.\n\n" +
                "Pokemon (pkmn) - Pokedex entry by number.\n\n" +
                "Translate (trns) - Translates almost any language to English.```";
        event.getTextChannel().sendTyping().queue();
        event.getTextChannel().sendMessage("I've sent you some PMs, " + event.getMessage().getAuthor().getAsMention() + ".").queue();
        if(!event.getAuthor().hasPrivateChannel()) {
            event.getAuthor().openPrivateChannel().complete();
            ((UserImpl)event.getAuthor()).getPrivateChannel().sendMessage(commands1).queue();
            ((UserImpl)event.getAuthor()).getPrivateChannel().sendMessage(commands2).queue();
        } else {
            ((UserImpl)event.getAuthor()).getPrivateChannel().sendMessage(commands1).queue();
            ((UserImpl)event.getAuthor()).getPrivateChannel().sendMessage(commands2).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
