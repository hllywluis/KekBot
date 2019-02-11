package Commands;

import Core.Commands;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.impl.UserImpl;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class URL implements Commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if(!event.getAuthor().hasPrivateChannel()){
            event.getAuthor().openPrivateChannel().complete();
            ((UserImpl)event.getAuthor()).getPrivateChannel().sendTyping().complete();
            ((UserImpl)event.getAuthor()).getPrivateChannel().sendMessage("Use this link to add me to your server: https://discordapp.com/oauth2/authorize?&client_id=199399259332673536&scope=bot&permissions=12659727").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
