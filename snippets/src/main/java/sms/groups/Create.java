/**
 * Sinch Java Snippet
 *
 * <p>This snippet is available at https://github.com/sinch/sinch-sdk-java-snippets
 *
 * <p>See https://github.com/sinch/sinch-sdk-java-snippets/blob/main/README.md for details
 */
package sms.groups;

import com.sinch.sdk.SinchClient;
import com.sinch.sdk.domains.sms.api.v1.GroupsService;
import com.sinch.sdk.domains.sms.models.v1.groups.Group;
import com.sinch.sdk.domains.sms.models.v1.groups.request.GroupRequest;
import com.sinch.sdk.models.Configuration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import utils.Settings;

public class Create {

  private static final Logger LOGGER = Logger.getLogger(Create.class.getName());

  public static void main(String[] args) {

    String projectId = Settings.getProjectId().orElse("MY_PROJECT_ID");
    String keyId = Settings.getKeyId().orElse("MY_KEY_ID");
    String keySecret = Settings.getKeySecret().orElse("MY_KEY_SECRET");

    String groupName = "Sinch Java SDK group";
    Set<String> members = new HashSet<>(Arrays.asList("A_RECIPIENT_PHONE_NUMBER"));

    Configuration configuration =
        Configuration.builder()
            .setProjectId(projectId)
            .setKeyId(keyId)
            .setKeySecret(keySecret)
            .build();

    SinchClient client = new SinchClient(configuration);

    GroupsService groupsService = client.sms().v1().groups();

    LOGGER.info("Creating group");

    GroupRequest request = GroupRequest.builder().setName(groupName).setMembers(members).build();

    Group response = groupsService.create(request);

    LOGGER.info("Response: " + response);
  }
}
