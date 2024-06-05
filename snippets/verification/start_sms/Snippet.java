package verification;

import com.sinch.sdk.domains.verification.*;
import com.sinch.sdk.domains.verification.models.*;
import com.sinch.sdk.domains.verification.models.requests.*;
import com.sinch.sdk.domains.verification.models.response.*;
import java.util.logging.Logger;

public class Snippet {

    private static final Logger LOGGER = Logger.getLogger(Snippet.class.getName());

    static void execute(VerificationService verificationService) {

        // REMINDER: verification Service require to have set application key/secret
        // onto Sinch Client init
        VerificationsService verificationsService = verificationService.verifications();

        String phoneNumber = "YOUR_phone_number";

        LOGGER.info(String.format("Sending a SMS verification to '%s'", phoneNumber));

        StartVerificationResponseSMS response =
                verificationsService.startSms(
                        StartVerificationSMSRequestParameters.builder()
                                .setIdentity(NumberIdentity.valueOf(phoneNumber))
                                .build());

        LOGGER.info("Response: " + response);
    }
}
