/**
 * Sinch Java Snippet
 *
 * <p>This snippet is available at https://github.com/sinch/sinch-sdk-java-snippets
 *
 * <p>See https://github.com/sinch/sinch-sdk-java-snippets/blob/main/README.md for details
 */
package verification.start;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.verification.api.v1.VerificationStartService;
import com.sinch.sdk.domains.verification.models.v1.NumberIdentity;
import com.sinch.sdk.domains.verification.models.v1.start.request.VerificationStartRequestSms;
import com.sinch.sdk.domains.verification.models.v1.start.response.VerificationStartResponse;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;
import utils.Settings;

public class Start {

  private static final Logger LOGGER = Logger.getLogger(Start.class.getName());

  public static void main(String[] args) {

    String applicationKey = Settings.getApplicationKey().orElse("MY_APPLICATION_KEY");
    String applicationSecret = Settings.getApplicationSecret().orElse("MY_APPLICATION_SECRET");

    String destinationPhoneNumber = "PHONE_NUMBER_TO_SEND_SMS_TO";

    Configuration configuration =
        Configuration.builder()
            .setApplicationKey(applicationKey)
            .setApplicationSecret(applicationSecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    VerificationStartService verificationStartService =
        client.verification().v1().verificationStart();

    LOGGER.info(
        String.format(
            "Start a verification by SMS onto phone number '%s'", destinationPhoneNumber));

    VerificationStartRequestSms request =
        VerificationStartRequestSms.builder()
            .setIdentity(NumberIdentity.valueOf(destinationPhoneNumber))
            .build();

    VerificationStartResponse response = verificationStartService.startSms(request);

    LOGGER.info("Response: " + response);
  }
}
