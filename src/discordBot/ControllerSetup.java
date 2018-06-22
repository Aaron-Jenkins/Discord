package discordBot;


import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ControllerSetup {

    public Button botStartStop;
    public Button closeApp;
    public TextField channelID;
    public TextField token;
    public Label loginFailed;
    boolean botRunning = false;

    public void botStartStop() {
        if (!botRunning) {
            System.out.println("Bot starting...");
            startBot();
        }
    }

    public void closeApp() {
        Stage root = (Stage) closeApp.getScene().getWindow();
        root.close();
        Platform.exit();
        System.exit(0);
    }

    public void startBot(){
        if(channelID.getText() .length() > 0 &&  token.getText().length() > 0) {
            BotSetup botSetup = new BotSetup(Long.parseLong(channelID.getText()), token.getText());
            if(botSetup.joinServer()){
                System.out.println("Bot is running...");
                botStartStop.setText("Stop Bot");
                loginFailed.setVisible(false);
                botRunning = true;
            }
        }else{
            System.out.println("Bot failed to start!");
            botRunning = false;
            loginFailed.setVisible(true);
            botStartStop.setText("Start Bot");
        }
    }
    

}
