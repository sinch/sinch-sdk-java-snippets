package voice.callouts;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.voice.api.v1.CalloutsService;
import com.sinch.sdk.domains.voice.models.v1.callouts.request.CalloutRequestTTS;
import com.sinch.sdk.domains.voice.models.v1.destination.DestinationPstn;
import com.sinch.sdk.models.Configuration;
import java.util.logging.*;
import java.util.logging.Logger;

public class TextToSpeech {

  public static void main(String[] args) {

    Logger LOGGER = Logger.getLogger(TextToSpeech.class.getName());

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

    CalloutsService service = client.voice().v1().callouts();

    String phoneNumber = "YOUR_phone_number";
    String message = "Hello, this is a call from Sinch. Congratulations! You made your first call.";

    LOGGER.info("Start call for: " + phoneNumber);

    CalloutRequestTTS parameters =
        CalloutRequestTTS.builder()
            .setDestination(DestinationPstn.from(phoneNumber))
            .setText(message)
            .build();

    String callId = service.textToSpeech(parameters);

    LOGGER.info("Call ID: " + callId);
  }
}
