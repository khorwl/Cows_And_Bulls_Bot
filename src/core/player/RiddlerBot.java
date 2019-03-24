package core.player;

import com.google.inject.Inject;
import tools.handler.IHandler;
import core.primitives.HandlerAnswer;
import core.primitives.UserGameRole;
import tools.HiddenNumberGenerator;

import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

public class RiddlerBot implements IPlayer {

    private final String name = "RiddlerBot";
    private Integer tries;
    private List<Integer> hiddenNumber;
    private String chatID;
    private final UserGameRole role = UserGameRole.RIDDLER;
    private IHandler handler;

    @Inject
    public RiddlerBot(String chatID,@Named("RiddlerBot")IHandler handler){
        this.tries = 0;
        this.chatID = chatID;
        this.hiddenNumber = HiddenNumberGenerator.createHiddenNumber();
        this.handler = handler;
    }

    public HandlerAnswer getAnswer(String message, IPlayer user){
        return handler.handleInput(message, user);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getChatID() {
        return this.chatID;
    }

    @Override
    public Integer getTries() {
        return this.tries;
    }

    @Override
    public UserGameRole getRole() {
        return this.role;
    }

    @Override
    public String getStringCowsAndBullsNumber() {
        return String
                .join("", hiddenNumber.stream().map(Object::toString).collect(Collectors.toList()));
    }

    @Override
    public void setRole(UserGameRole role) {
    }

    @Override
    public List<Integer> getHiddenNumber() {
        return this.hiddenNumber;
    }

    @Override
    public void increaseTries() {
        tries++;
    }
}
