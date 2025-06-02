package verification;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.verification.api.v1.VerificationStartService;
import com.sinch.sdk.domains.verification.models.v1.NumberIdentity;
import com.sinch.sdk.domains.verification.models.v1.start.request.VerificationStartRequestSms;
import com.sinch.sdk.domains.verification.models.v1.start.response.VerificationStartResponseSms;
import com.sinch.sdk.models.Configuration;
import java.util.logging.*;
import java.util.logging.Logger;

public class StartSms {

  private static final Logger LOGGER = Logger.getLogger(StartSms.class.getName());

  public static void main(String[] args) {

    String projectId = "SINCH_PROJECT_ID";
    String keyId = "SINCH_KEY_ID";
    String keySecret = "SINCH_KEY_SECRET";

    Configuration configuration =
        Configuration.builder()
            .setProjectId(projectId)
            .setKeyId(keyId)
            .setKeySecret(keySecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    // REMINDER: verification Service require to have set application key/secret
    // onto Sinch Client init
    VerificationStartService service = client.verification().v1().verificationStart();

    String phoneNumber = "YOUR_phone_number";

    LOGGER.info(String.format("Sending a SMS verification to '%s'", phoneNumber));

    VerificationStartResponseSms response =
        service.startSms(
            VerificationStartRequestSms.builder()
                .setIdentity(NumberIdentity.valueOf(phoneNumber))
                .build());

    LOGGER.info("Response: " + response);
  }
}
