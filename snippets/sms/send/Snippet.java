package sms;

import com.sinch.sdk.domains.sms.*;
import com.sinch.sdk.domains.sms.models.*;
import com.sinch.sdk.domains.sms.models.requests.*;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Logger;

public class Snippet {

    private static final Logger LOGGER = Logger.getLogger(Snippet.class.getName());

    static void execute(SMSService smsService) {

        BatchesService batchesService = smsService.batches();

        String from = "YOUR_sinch_phone_number";
        String recipient = "YOUR_recipient_phone_number";
        String body = "This is a test SMS message using the Sinch Java SDK.";

        LOGGER.info("Sending SMS Text");
        BatchText value =
                batchesService.send(
                        SendSmsBatchTextRequest.builder()
                                .setTo(Collections.singletonList(recipient))
                                .setBody(body)
                                .setFrom(from)
                                .build());

        LOGGER.info("Response: " + value);
    }
}
