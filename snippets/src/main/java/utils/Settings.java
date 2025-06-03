package utils;

import com.sinch.sdk.core.utils.StringUtil;
import java.util.Optional;
import java.util.Properties;

public class Settings {

  private static final Properties properties;

  static {
    properties = new Properties();
    try {
      // load a properties file from class path, inside static method
      properties.load(
          Settings.class.getClassLoader().getResourceAsStream("config.properties"));
    } catch (Exception ioe) {
      // ignore exception: properties file is just an helper
    }
  }

  public static Optional<String> get(String key) {

    String value = properties.getProperty(key);
    if (StringUtil.isEmpty(value)) {
      return Optional.empty();
    }
    return Optional.of(value.trim());
  }

  public static Optional<String> getProjectId() {
    return get("SINCH_PROJECT_ID");
  }

  public static Optional<String> getKeyId() {
    return get("SINCH_KEY_ID");
  }

  public static Optional<String> getKeySecret() {
    return get("SINCH_KEY_SECRET");
  }

  public static Optional<String> getServicePlanId() {
    return get("SINCH_SERVICE_PLAN_ID");
  }

  public static Optional<String> getPhoneNumber() {
    return get("SINCH_PHONE_NUMBER");
  }
}
