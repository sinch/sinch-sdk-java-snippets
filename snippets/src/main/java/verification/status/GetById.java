/**
 * Sinch Java Snippet
 *
 * <p>This snippet is available at https://github.com/sinch/sinch-sdk-java-snippets
 *
 * <p>See https://github.com/sinch/sinch-sdk-java-snippets/blob/main/README.md for details
 */
package verification.status;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.verification.api.v1.VerificationStatusService;
import com.sinch.sdk.domains.verification.models.v1.status.response.VerificationStatusResponse;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;
import utils.Settings;

public class GetById {

  private static final Logger LOGGER = Logger.getLogger(GetById.class.getName());

  public static void main(String[] args) {

    String applicationKey = Settings.getApplicationKey().orElse("MY_APPLICATION_KEY");
    String applicationSecret = Settings.getApplicationSecret().orElse("MY_APPLICATION_SECRET");

    String verificationID = "A_VERIFICATION_ID";

    Configuration configuration =
        Configuration.builder()
            .setApplicationKey(applicationKey)
            .setApplicationSecret(applicationSecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    VerificationStatusService verificationStatusService =
        client.verification().v1().verificationStatus();

    LOGGER.info(String.format("Verification status for verification ID '%s'", verificationID));

    VerificationStatusResponse response = verificationStatusService.getById(verificationID);

    LOGGER.info("Response: " + response);
  }
}
