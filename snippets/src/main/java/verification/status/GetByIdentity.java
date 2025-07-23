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
import com.sinch.sdk.domains.verification.models.v1.NumberIdentity;
import com.sinch.sdk.domains.verification.models.v1.VerificationMethod;
import com.sinch.sdk.domains.verification.models.v1.status.response.VerificationStatusResponse;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;
import utils.Settings;

public class GetByIdentity {

  private static final Logger LOGGER = Logger.getLogger(GetByIdentity.class.getName());

  public static void main(String[] args) {

    String applicationKey = Settings.getApplicationKey().orElse("MY_APPLICATION_KEY");
    String applicationSecret = Settings.getApplicationSecret().orElse("MY_APPLICATION_SECRET");

    String destinationPhoneNumber = "PHONE_NUMBER_WHICH_RECEIVED_THE_VERIFICATION_CODE";

    Configuration configuration =
        Configuration.builder()
            .setApplicationKey(applicationKey)
            .setApplicationSecret(applicationSecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    VerificationStatusService verificationStatusService =
        client.verification().v1().verificationStatus();

    LOGGER.info(String.format("Verification status for phone number '%s'", destinationPhoneNumber));

    NumberIdentity identity = NumberIdentity.valueOf(destinationPhoneNumber);

    VerificationStatusResponse response =
        verificationStatusService.getByIdentity(identity, VerificationMethod.SMS);

    LOGGER.info("Response: " + response);
  }
}
