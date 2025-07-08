package voice.applications;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.voice.api.v1.ApplicationsService;
import com.sinch.sdk.domains.voice.models.v1.applications.Callbacks;
import com.sinch.sdk.models.Configuration;
import java.util.logging.Logger;
import utils.Settings;

public class GetCallbackUrls {

  private static final Logger LOGGER = Logger.getLogger(GetCallbackUrls.class.getName());

  public static void main(String[] args) {

    String applicationKey = Settings.getApplicationKey().orElse("MY_APPLICATION_KEY");
    String applicationSecret = Settings.getApplicationSecret().orElse("MY_APPLICATION_SECRET");

    Configuration configuration =
        Configuration.builder()
            .setApplicationKey(applicationKey)
            .setApplicationSecret(applicationSecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    ApplicationsService applicationsService = client.voice().v1().applications();

    LOGGER.info("Get callback URLs for application key '%s'".formatted(applicationKey));

    Callbacks response = applicationsService.getCallbackUrls(applicationKey);

    LOGGER.info("Response: " + response);
  }
}
