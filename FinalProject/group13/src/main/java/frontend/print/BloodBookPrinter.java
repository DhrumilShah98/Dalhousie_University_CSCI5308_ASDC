package frontend.print;

public final class BloodBookPrinter {
  private static BloodBookPrinter instance;

  private BloodBookPrinter() {
    // Required private constructor
  }

  public static BloodBookPrinter getInstance() {
    if (instance == null) {
      instance = new BloodBookPrinter();
    }
    return instance;
  }

  public final void printScreenTitle(final String screenTitle) {
    System.out.println("**************************************************");
    System.out.println(screenTitle);
    System.out.println("**************************************************");
  }

  public final void printContent(final String content) {
    System.out.println(content);
  }

  public final void printBeautifyContent(final String beautifyPattern,
                                         final Object... args) {
    System.out.printf(beautifyPattern, args);
  }
}