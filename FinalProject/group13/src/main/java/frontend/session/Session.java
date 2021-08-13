package frontend.session;

import backend.admin.login.model.AdminLogin;
import backend.authentication.blood_bank.model.BloodBank;
import backend.authentication.user.model.User;

public final class Session {
  public static User USER = null;
  public static BloodBank BLOOD_BANK = null;
  public static AdminLogin ADMIN = null;

  public static void clearSession() {
    USER = null;
    BLOOD_BANK = null;
    ADMIN = null;
  }
}