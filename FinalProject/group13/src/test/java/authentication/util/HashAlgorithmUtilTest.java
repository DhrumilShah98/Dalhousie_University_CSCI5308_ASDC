package authentication.util;

import backend.authentication.util.HashAlgorithmUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Hash algorithm test suite")
public class HashAlgorithmUtilTest {

    @Test
    @DisplayName("Validate incorrect SHA256 hash string")
    public void validateIncorrectSHA256Hash() {
        final String source = "D@hrumil1234Shah";
        final String destination = "D@hrumil134Shah";
        final String sourceSHA256Hash = HashAlgorithmUtil.getSHA256Hash(source);
        final String destinationSHA256Hash = HashAlgorithmUtil.getSHA256Hash(destination);
        Assertions.assertNotEquals(sourceSHA256Hash, destinationSHA256Hash, "Source and destination hashes are valid");
        Assertions.assertFalse(HashAlgorithmUtil.validateSHA256Hash(source, destinationSHA256Hash), "Source and destination hashes are valid");
    }

    @Test
    @DisplayName("Validate correct SHA256 hash string")
    public void validateCorrectSHA256Hash() {
        final String source = "D@hrumil1234Shah";
        final String destination = "D@hrumil1234Shah";
        final String sourceSHA256Hash = HashAlgorithmUtil.getSHA256Hash(source);
        final String destinationSHA256Hash = HashAlgorithmUtil.getSHA256Hash(destination);
        Assertions.assertEquals(sourceSHA256Hash, destinationSHA256Hash, "Source and destination hashes are invalid");
        Assertions.assertTrue(HashAlgorithmUtil.validateSHA256Hash(source, destinationSHA256Hash), "Source and destination hashes are invalid");
    }
}
