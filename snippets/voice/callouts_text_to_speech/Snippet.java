package voice;

import com.sinch.sdk.domains.voice.*;
import com.sinch.sdk.domains.voice.models.*;
import com.sinch.sdk.domains.voice.models.requests.*;

public class Snippet {

  public static String execute(VoiceService voiceService) {

    CalloutsService calloutsService = voiceService.callouts();

    String phoneNumber = "YOUR_phone_number";
    String message = "Hello, this is a call from Sinch. Congratulations! You made your first call.";

    CalloutRequestParametersTTS parameters =
        CalloutRequestParametersTTS.builder()
            .setDestination(DestinationNumber.valueOf(phoneNumber))
            .setText(message)
            .build();

    String callId = calloutsService.textToSpeech(parameters);
    return callId;
  }
}
