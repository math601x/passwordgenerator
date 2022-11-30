package password_generator.app;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

public class PasswordStrengthParser {
  Zxcvbn zxcvbn = new Zxcvbn();
  Strength strength;

  public Strength measurePasswordStrength(String password) {
    strength = zxcvbn.measure(password);
    return strength;
  }

  public int getPasswordStrengtNumericalValue(String password) {
    return measurePasswordStrength(password).getScore();
  }

  public String getPasswordStrengthFeedback(String password) {
    String output = "";
    if (getPasswordStrengtNumericalValue(password) == 0)
      output = "Weak (guesses < 10^3)";

    else if (getPasswordStrengtNumericalValue(password) == 1)
      output = "Fair (guesses < 10^6)";

    else if (getPasswordStrengtNumericalValue(password) == 2)
      output = "Good (guesses < 10^8)";

    else if (getPasswordStrengtNumericalValue(password) == 3)
      output = "Strong (guesses < 10^10)";

    else if (getPasswordStrengtNumericalValue(password) == 4)
      output = "Very strong (guesses >= 10^10)";

    return output;
  }

  public String getTimeToCrack(String password) {
    String output = "";
    return "\n Time to crack: "
        + measurePasswordStrength(password).getCrackTimesDisplay().getOnlineNoThrottling10perSecond();

  }
}
