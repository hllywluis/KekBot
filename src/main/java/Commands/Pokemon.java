package Commands;

import Core.Commands;
import Core.KekBot;
import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Pokemon implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        // Get the number of the Pokemon and return an image + the Pokemon's description.
        event.getTextChannel().sendTyping().queue();
        PokeApi pokeApi = KekBot.getPokeApi();
        int species = Integer.parseInt(event.getMessage().getRawContent().substring(6));
        PokemonSpecies pokemon = pokeApi.getPokemonSpecies(species);
        String output = pokemon.getName().replaceFirst(String.valueOf(pokemon.getName().charAt(0)), String.valueOf(pokemon.getName().charAt(0)).toUpperCase()) + ":\n\n" + pokemon.getFlavorTextEntries().get(1).getFlavorText() + "\n";
        String url = "https://img.pokemondb.net/artwork/" + pokemon.getName() + ".jpg";
        event.getTextChannel().sendMessage(url + "\n\n" + output).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}