package password_generator.framework;

import java.util.stream.Stream;

/*
  interface for potential future variants
  */
public interface PasswordGenerator {

  public Stream<Character> getRandomSpecialChars(int count);

  public Stream<Character> getRandomNumbers(int count);

  public Stream<Character> getRandomAlphabets(int count, boolean upperCase);

  public String generatePassword();
}
