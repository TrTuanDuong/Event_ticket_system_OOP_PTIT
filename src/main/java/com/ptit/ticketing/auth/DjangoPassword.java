package com.ptit.ticketing.auth;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class DjangoPassword {
  public static boolean verify(String raw, String encoded) {
    try {
      String[] p = encoded.split("\\$");
      if (p.length != 4) return false;
      String algo = p[0];
      int iter = Integer.parseInt(p[1]);
      byte[] salt = p[2].getBytes(StandardCharsets.US_ASCII);
      byte[] hash = Base64.getDecoder().decode(p[3]);
      if (!"pbkdf2_sha256".equals(algo)) return false;
      PBEKeySpec spec = new PBEKeySpec(raw.toCharArray(), salt, iter, 256);
      byte[] calc = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
      if (calc.length != hash.length) return false;
      int r = 0;
      for (int i = 0; i < calc.length; i++) r |= calc[i] ^ hash[i];
      return r == 0;
    } catch (Exception e) {
      return false;
    }
  }
}
