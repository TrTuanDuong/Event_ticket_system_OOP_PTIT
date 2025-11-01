package com.ptit.ticketing.auth;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class DjangoPassword {
  private static final int ITERATIONS = 390000; // Django default for PBKDF2
  private static final int SALT_LENGTH = 12;

  public static boolean verify(String raw, String encoded) {
    try {
      String[] p = encoded.split("\\$");
      if (p.length != 4)
        return false;
      String algo = p[0];
      int iter = Integer.parseInt(p[1]);
      byte[] salt = p[2].getBytes(StandardCharsets.US_ASCII);
      byte[] hash = Base64.getDecoder().decode(p[3]);
      if (!"pbkdf2_sha256".equals(algo))
        return false;
      PBEKeySpec spec = new PBEKeySpec(raw.toCharArray(), salt, iter, 256);
      byte[] calc = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
      if (calc.length != hash.length)
        return false;
      int r = 0;
      for (int i = 0; i < calc.length; i++)
        r |= calc[i] ^ hash[i];
      return r == 0;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Hash password using Django's PBKDF2 algorithm
   */
  public String hashPassword(String password) {
    try {
      // Generate random salt
      byte[] saltBytes = new byte[SALT_LENGTH];
      new SecureRandom().nextBytes(saltBytes);
      String salt = Base64.getEncoder().encodeToString(saltBytes).substring(0, SALT_LENGTH);

      // Generate hash
      PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(StandardCharsets.US_ASCII), ITERATIONS,
          256);
      byte[] hash = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
      String encodedHash = Base64.getEncoder().encodeToString(hash);

      // Return in Django format: pbkdf2_sha256$iterations$salt$hash
      return String.format("pbkdf2_sha256$%d$%s$%s", ITERATIONS, salt, encodedHash);
    } catch (Exception e) {
      throw new RuntimeException("Failed to hash password", e);
    }
  }
}
