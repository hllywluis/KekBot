package Core;

import Commands.*;
import com.google.cloud.translate.TranslateOptions;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.HashMap;
import java.util.Map;

public class KekBot extends ListenerAdapter {
    public static final Whitelist whitelist = new Whitelist();
    static final CommandParser parser = new CommandParser();
    private static final HashMap<String, Commands> cmdsHashMap = new HashMap<>();
    //  Properties instantiated.
    public static RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();
    public static boolean isWhitelisted = false;
    static String prefix = "~";
    private static AudioPlayerManager playerManager;
    private static Map<Long, PlayerManager> musicManagers;
    private static PokeApi pokeApi = new PokeApiClient();
    public static com.google.cloud.translate.Translate translate = TranslateOptions.newBuilder().setApiKey("").build().getService();

    // Method for PokéApi.
    public static PokeApi getPokeApi() {
        return pokeApi;
    }

    // Constructor for audioPlayer.
    private KekBot() {
        playerManager = new DefaultAudioPlayerManager();
        musicManagers = new HashMap<>();
        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioSourceManagers.registerLocalSource(playerManager);
    }

    // PlayerManager function. Handles the playback, pausing, and skips of music.
    public static synchronized PlayerManager getPlayerManager(Guild guild) {
        long guildID = Long.parseLong(guild.getId());
        PlayerManager musicManager = musicManagers.computeIfAbsent(guildID, k -> new PlayerManager(playerManager));
        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());
        return musicManager;
    }

    // Loads a track from a URL and plays it on the voice channel the bot is currently on.
    public static void loadAndPlay(final TextChannel channel, final String trackURL) {
        PlayerManager musicManager = getPlayerManager(channel.getGuild());
        playerManager.loadItemOrdered(musicManager, trackURL, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack audioTrack) {
                channel.sendTyping().queue();
                channel.sendMessage("Added " + audioTrack.getInfo().title + " to queue.").queue();
                play(musicManager, audioTrack);
            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {
                AudioTrack firstTrack = audioPlaylist.getSelectedTrack();
                if (firstTrack == null) {
                    firstTrack = audioPlaylist.getTracks().get(0);
                }
                channel.sendTyping().queue();
                channel.sendMessage("Adding " + firstTrack.getInfo().title + " to queue (first track of playlist " + audioPlaylist.getName() + ").").queue();
                play(musicManager, firstTrack);
            }

            @Override
            public void noMatches() {
                channel.sendTyping().queue();
                channel.sendMessage("Nothing found for: " + trackURL + ".").queue();
            }

            @Override
            public void loadFailed(FriendlyException e) {
                channel.sendTyping().queue();
                channel.sendMessage("Could not play: " + e.getMessage()).queue();
            }
        });
    }

    // If there are no tracks in the queue, play the track; otherwise, add it to the queue.
    private static void play(PlayerManager manager, AudioTrack track) {
        manager.scheduler.queue(track);
    }

    public static void skipTrack(TextChannel channel) {
        PlayerManager manager = getPlayerManager(channel.getGuild());
        manager.scheduler.nextTrack();
        channel.sendTyping().queue();
        channel.sendMessage("Track skipped.").queue();
    }

    public static void main(String args[]) {
        try {
//          Create a new JDA object with certain parameters.
            JDA jda = new JDABuilder(AccountType.BOT).setBulkDeleteSplittingEnabled(false).addEventListener(new BotListener()).setToken("").buildBlocking();
            jda.setAutoReconnect(true);
            jda.addEventListener(new KekBot());
        } catch (LoginException | InterruptedException | RateLimitedException e) {
            e.printStackTrace();
        }
//      Add commands to HashMap.
        cmdsHashMap.put("ping", new Ping());
        cmdsHashMap.put("rng", new RNG());
        cmdsHashMap.put("rw", new RateWaifu());
        cmdsHashMap.put("lmgtfy", new LMGTFY());
        cmdsHashMap.put("wik", new Wikipedia());
        cmdsHashMap.put("rn", new Rename());
        cmdsHashMap.put("help", new Help());
        cmdsHashMap.put("about", new About());
        cmdsHashMap.put("prefix", new Prefix());
        cmdsHashMap.put("x", new Terminate());
        cmdsHashMap.put("gif", new GIPHY());
        cmdsHashMap.put("cp", new CurrentPrefix());
        cmdsHashMap.put("ud", new UD());
        cmdsHashMap.put("kek", new Kek());
        cmdsHashMap.put("topic", new Topic());
        cmdsHashMap.put("tts", new TTS());
        cmdsHashMap.put("ft", new FlipTable());
        cmdsHashMap.put("d", new Define());
        cmdsHashMap.put("echo", new Echo());
        cmdsHashMap.put("sg", new SetGame());
        cmdsHashMap.put("bb", new BooBot());
        cmdsHashMap.put("zap", new Zap());
        cmdsHashMap.put("coin", new Coin());
        cmdsHashMap.put("dcd", new Decide());
        cmdsHashMap.put("em", new Emoji());
        cmdsHashMap.put("wh", new WikiHow());
        cmdsHashMap.put("pr", new Prune());
        cmdsHashMap.put("nn", new Name());
        cmdsHashMap.put("jn", new Join());
        cmdsHashMap.put("dc", new Disconnect());
        cmdsHashMap.put("whtl", new Whitelist());
        cmdsHashMap.put("uwhtl", new Unwhitelist());
        cmdsHashMap.put("url", new URL());
        cmdsHashMap.put("thsr", new Thesaurus());
        cmdsHashMap.put("p", new Play());
        cmdsHashMap.put("skip", new Skip());
        cmdsHashMap.put("trns", new Translate());
        cmdsHashMap.put("st", new Status());
        cmdsHashMap.put("msgs", new Messages());
        cmdsHashMap.put("q", new Queue());
        cmdsHashMap.put("pkmn", new Pokemon());
    }

    //  Handles when commands are sent.
    static void handleCommand(CommandParser.CommandContainer cmd) {
        if (cmdsHashMap.containsKey(cmd.invoke)) {
            boolean safe = cmdsHashMap.get(cmd.invoke).called(cmd.args, cmd.event);
            if (safe) {
                cmdsHashMap.get(cmd.invoke).action(cmd.args, cmd.event);
                cmdsHashMap.get(cmd.invoke).executed(true, cmd.event);
            } else {
                cmdsHashMap.get(cmd.invoke).executed(false, cmd.event);
            }
        }
    }

    //  Retrieves the current prefix.
    public static String getPrefix() {
        return prefix;
    }

    //  Allows a user to set a prefix with a String.
    public static void setPrefix(String prefix) {
        KekBot.prefix = prefix;
    }

    //  Prints status to console.
    static void msgLog(String uS) {
        System.out.print("---------------------------\n" + "Status" + ":\n" + uS + "." + "\n---------------------------\n");
    }

}