package com.splitscale.shield.sanitizer;

import java.util.regex.Pattern;

public class UsernameValidator {

  public UsernameValidator() {
    // default
  }

  public boolean isValid(String username) {
    return isValidLength(username)
        && isNotBlank(username)
        && isNotHtml(username)
        && hasNoSpace(username)
        && isNotSqlInjection(username);
  }

  private boolean isNotBlank(String username) {
    return !username.isBlank();
  }

  private boolean isValidLength(String username) {
    return username.length() >= 3 && username.length() <= 16;
  }

  private boolean hasNoSpace(String username) {
    return !username.contains(" ");
  }

  private boolean isNotHtml(String string) {
    Pattern pattern = Pattern.compile("<[^>]*>");
    return !pattern.matcher(string).find();
  }

  private boolean isNotSqlInjection(String string) {
    Pattern pattern = Pattern.compile("[\\';]+|(--)+");
    return !pattern.matcher(string).find();
  }
}
