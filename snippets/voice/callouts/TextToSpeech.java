package voice.callouts;

import com.sinch.sdk.domains.voice.*;
import com.sinch.sdk.domains.voice.models.DestinationNumber;
import com.sinch.sdk.domains.voice.models.requests.CalloutRequestParametersTTS;

public class TextToSpeech {

    public String call(VoiceService voiceService, String phoneNumber) {

        var calloutsService = voiceService.callouts();

        var destination = DestinationNumber.valueOf(phoneNumber);
        var message =
                "Hello, this is a call from Sinch. Congratulations! You made your first call.";

        var parameters =
                CalloutRequestParametersTTS.builder()
                        .setDestination(destination)
                        .setText(message)
                        .build();

        var callId = calloutsService.textToSpeech(parameters);
        return callId;
    }
}
