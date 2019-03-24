package core.player;

import java.util.List;
import java.util.stream.Collectors;

import core.primitives.UserGameRole;
import tools.HiddenNumberGenerator;

public class User implements IPlayer {

  private String name;
  private Integer tries;
  private List<Integer> hiddenNumber;
  private String chatID;
  private UserGameRole role;

  public User(String name, String chatID, UserGameRole role) {
    this.name = name;
    this.chatID = chatID;
    this.role = role;
    tries = 0;
    hiddenNumber = HiddenNumberGenerator.createHiddenNumber();
  }

  public UserGameRole getRole() {
    return role;
  }

  public void setRole(UserGameRole role) {
    this.role = role;
  }

  public String getChatID() {
    return chatID;
  }

  public String getName() {
    return name;
  }

  public Integer getTries() {
    return tries;
  }

  public List<Integer> getHiddenNumber() {
    return hiddenNumber;
  }

  public void setHiddenNumber(List<Integer> number) { this.hiddenNumber = number; }

  public String getStringCowsAndBullsNumber() {
    return String
        .join("", hiddenNumber.stream().map(Object::toString).collect(Collectors.toList()));
  }

  public void increaseTries() {
    tries++;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof User) {
      var other = (User) obj;
      return this.chatID.equals(other.chatID);
    }
    return false;
  }

  @Override
  public String toString() {
    return String.format("%$1/%$2/\n", name, tries.toString());
  }
}
