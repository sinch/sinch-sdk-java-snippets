package conversation;

import com.sinch.sdk.domains.conversation.api.v1.ConversationService;
import com.sinch.sdk.domains.conversation.api.v1.MessagesService;
import com.sinch.sdk.domains.conversation.models.v1.*;
import com.sinch.sdk.domains.conversation.models.v1.messages.*;
import com.sinch.sdk.domains.conversation.models.v1.messages.request.*;
import com.sinch.sdk.domains.conversation.models.v1.messages.response.SendMessageResponse;
import com.sinch.sdk.domains.conversation.models.v1.messages.types.text.*;
import java.util.*;
import java.util.Collections;
import java.util.logging.Logger;

public class Snippet {

  private static final Logger LOGGER = Logger.getLogger(Snippet.class.getName());

  static void execute(ConversationService conversationService) {

    MessagesService messagesService = conversationService.messages();

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

    SendMessageResponse value = messagesService.sendMessage(request);

    LOGGER.info("Response: " + value);
  }
}
