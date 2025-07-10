/**
 * Sinch Java Snippet
 *
 * <p>This snippet is available at https://github.com/sinch/sinch-sdk-java-snippets
 *
 * <p>See https://github.com/sinch/sinch-sdk-java-snippets/blob/main/README.md for details
 */
package verification.report;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.verification.api.v1.VerificationReportService;
import com.sinch.sdk.domains.verification.models.v1.NumberIdentity;
import com.sinch.sdk.domains.verification.models.v1.report.request.VerificationReportRequestSms;
import com.sinch.sdk.domains.verification.models.v1.report.response.VerificationReportResponse;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;
import utils.Settings;

public class ReportByIdentity {

  private static final Logger LOGGER = Logger.getLogger(ReportByIdentity.class.getName());

  public static void main(String[] args) {

    String applicationKey = Settings.getApplicationKey().orElse("MY_APPLICATION_KEY");
    String applicationSecret = Settings.getApplicationSecret().orElse("MY_APPLICATION_SECRET");

    String destinationPhoneNumber = "PHONE_NUMBER_WHICH_RECEIVED_THE_VERIFICATION_CODE";
    String receivedVerificationCode = "A_RECEIVED_VERIFICATION_CODE";

    Configuration configuration =
        Configuration.builder()
            .setApplicationKey(applicationKey)
            .setApplicationSecret(applicationSecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    VerificationReportService verificationReportService =
        client.verification().v1().verificationReport();

    LOGGER.info(
        String.format(
            "Report SMS verification code for phone number '%s'", destinationPhoneNumber));

    VerificationReportRequestSms request =
        VerificationReportRequestSms.builder().setCode(receivedVerificationCode).build();

    VerificationReportResponse response =
        verificationReportService.reportSmsByIdentity(
            NumberIdentity.valueOf(destinationPhoneNumber), request);

    LOGGER.info("Response: " + response);
  }
}
