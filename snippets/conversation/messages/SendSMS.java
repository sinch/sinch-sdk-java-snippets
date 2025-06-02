package conversation.messages;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.conversation.api.v1.MessagesService;
import com.sinch.sdk.domains.conversation.models.v1.*;
import com.sinch.sdk.domains.conversation.models.v1.messages.*;
import com.sinch.sdk.domains.conversation.models.v1.messages.request.*;
import com.sinch.sdk.domains.conversation.models.v1.messages.response.SendMessageResponse;
import com.sinch.sdk.domains.conversation.models.v1.messages.types.text.*;
import com.sinch.sdk.models.Configuration;
import java.util.*;
import java.util.Collections;
import java.util.logging.*;
import java.util.logging.Logger;

public class SendSMS {

  private static final Logger LOGGER = Logger.getLogger(SendSMS.class.getName());

  public static void main(String[] args) {

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

    MessagesService service = client.conversation().v1().messages();

    String appId = "YOUR_app_id";
    String from = "YOUR_sms_sender";
    String to = "RECIPIENT_number";

    String body = "This is a test Conversation message using the Sinch Java SDK.";
    String smsSender = "SMS_SENDER";

    ChannelRecipientIdentities recipients =
        ChannelRecipientIdentities.of(
            ChannelRecipientIdentity.builder()
                .setChannel(ConversationChannel.SMS)
                .setIdentity(to)
                .build());

    AppMessage<TextMessage> message =
        AppMessage.<TextMessage>builder()
            .setBody(TextMessage.builder().setText(body).build())
            .build();

    SendMessageRequest<TextMessage> request =
        SendMessageRequest.<TextMessage>builder()
            .setAppId(appId)
            .setRecipient(recipients)
            .setMessage(message)
            .setChannelProperties(Collections.singletonMap(smsSender, from))
            .build();

    LOGGER.info("Sending SMS Text using Conversation API");

    SendMessageResponse value = service.sendMessage(request);

    LOGGER.info("Response: " + value);
  }
}
