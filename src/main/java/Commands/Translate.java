package Commands;

import Core.Commands;
import Core.KekBot;
import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translation;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Translate implements Commands {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try {
            event.getTextChannel().sendTyping().queue();
            String input = event.getMessage().getRawContent().substring(6);
            Detection detectedText = KekBot.translate.detect(input);
            String detectedLanguage = detectedText.getLanguage();
            Translation translation = KekBot.translate.translate(input, com.google.cloud.translate.Translate.TranslateOption.sourceLanguage(detectedLanguage), com.google.cloud.translate.Translate.TranslateOption.targetLanguage("en"));
            String translatedText = translation.getTranslatedText().replace("&#39;", "\'");
            event.getTextChannel().sendMessage(translatedText).queue();
        } catch (Error e) {
            event.getTextChannel().sendMessage("Sorry, you can't translate English to English..").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}