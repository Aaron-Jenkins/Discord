package discordBot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;


public class BotSetup {

    public long cid;
    String token;

    public BotSetup(long cid, String token) {
        this.cid = cid;
        this.token = token;
    }

    public boolean joinServer() {
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        api.updateActivity("Discord-Bot", ActivityType.PLAYING);
        TextChannel channel = (TextChannel) api.getChannelById(cid).get();
        new MessageBuilder().setEmbed(new EmbedBuilder()
                .setTitle("Connected")
                .setDescription("Type !help for a list of commands")
        ).send(channel);
        api.addMessageCreateListener(event -> {
            if(event.getMessage().getContent().equalsIgnoreCase("!help")){
                event.getChannel().sendMessage("This could be a list of help commands!");
            }
        });
        if (channel.getId() > 0) {
            return true;
        }
        return false;
    }
}
/*
*/
