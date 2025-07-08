package voice.conferences;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.voice.api.v1.ConferencesService;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;
import utils.Settings;

public class KickParticipant {

  private static final Logger LOGGER = Logger.getLogger(KickParticipant.class.getName());

  public static void main(String[] args) {

    String applicationKey = Settings.getApplicationKey().orElse("MY_APPLICATION_KEY");
    String applicationSecret = Settings.getApplicationSecret().orElse("MY_APPLICATION_SECRET");

    String conferenceId = "AN_EXISTING_CONFERENCE_ID";
    String callId = "A_CALL_ID";

    Configuration configuration =
        Configuration.builder()
            .setApplicationKey(applicationKey)
            .setApplicationSecret(applicationSecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    ConferencesService conferencesService = client.voice().v1().conferences();

    LOGGER.info(
        String.format(
            "Kick participant with call ID '%s' for conference with ID '%s'",
            callId, conferenceId));

    conferencesService.kickParticipant(conferenceId, callId);

    LOGGER.info("Done");
  }
}
