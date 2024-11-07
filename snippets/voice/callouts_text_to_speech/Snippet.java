package voice;

import com.sinch.sdk.domains.voice.api.v1.CalloutsService;
import com.sinch.sdk.domains.voice.api.v1.VoiceService;
import com.sinch.sdk.domains.voice.models.v1.callouts.request.CalloutRequestTTS;
import com.sinch.sdk.domains.voice.models.v1.destination.DestinationPstn;

public class Snippet {

  public static String execute(VoiceService voiceService) {

    CalloutsService calloutsService = voiceService.callouts();

    String phoneNumber = "YOUR_phone_number";
    String message = "Hello, this is a call from Sinch. Congratulations! You made your first call.";

    CalloutRequestTTS parameters =
        CalloutRequestTTS.builder()
            .setDestination(DestinationPstn.from(phoneNumber))
            .setText(message)
            .build();

    String callId = calloutsService.textToSpeech(parameters);
    return callId;
  }
}
