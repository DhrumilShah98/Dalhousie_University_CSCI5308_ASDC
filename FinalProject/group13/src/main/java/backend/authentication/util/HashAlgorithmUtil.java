package backend.authentication.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * {@code HashAlgorithmUtil} is a utility class that handles hashing.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class HashAlgorithmUtil {

  /**
   * Gets the SHA256 hash of the {@code source} argument.
   *
   * @param source source string to be hashed.
   *
   * @return SHA256 hash of the {@code source}.
   */
  public static String getSHA256Hash(final String source) {
    if (source == null) {
      return null;
    }
    try {
      final MessageDigest messageDigest =
          MessageDigest.getInstance("SHA-256");
      return String.format("%064x",
          new BigInteger(1,
              messageDigest.digest(source.getBytes(StandardCharsets.UTF_8))));
    } catch (final NoSuchAlgorithmException e) {
      return null;
    }
  }

  /**
   * Validates the SHA256 hash of the {@code source} argument with
   * {@code targetHash} argument.
   *
   * @param source     source source string to be verified.
   * @param targetHash target hash to be verified with.
   *
   * @return {@code true} if valid otherwise {@code false}.
   */
  public static boolean validateSHA256Hash(final String source,
                                           final String targetHash) {
    final String sourceHash = getSHA256Hash(source);
    if (sourceHash == null || targetHash == null) {
      return false;
    }
    return sourceHash.equals(targetHash);
  }
}