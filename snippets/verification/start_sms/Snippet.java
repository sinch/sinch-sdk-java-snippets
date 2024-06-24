package verification;

import com.sinch.sdk.domains.verification.api.v1.VerificationService;
import com.sinch.sdk.domains.verification.api.v1.VerificationStartService;
import com.sinch.sdk.domains.verification.models.v1.NumberIdentity;
import com.sinch.sdk.domains.verification.models.v1.start.request.VerificationStartRequestSms;
import com.sinch.sdk.domains.verification.models.v1.start.response.VerificationStartResponseSms;
import java.util.logging.Logger;

public class Snippet {

  private static final Logger LOGGER = Logger.getLogger(Snippet.class.getName());

  static void execute(VerificationService verificationService) {

    // REMINDER: verification Service require to have set application key/secret
    // onto Sinch Client init
    VerificationStartService startService = verificationService.verificationStart();

    String phoneNumber = "YOUR_phone_number";

    LOGGER.info(String.format("Sending a SMS verification to '%s'", phoneNumber));

    VerificationStartResponseSms response =
        startService.startSms(
            VerificationStartRequestSms.builder()
                .setIdentity(NumberIdentity.valueOf(phoneNumber))
                .build());

    LOGGER.info("Response: " + response);
  }
}
