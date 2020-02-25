package io.vertx.webrest;

import io.github.cdimascio.dotenv.Dotenv;

import javax.inject.Inject;

public class EnvContainer {

    private Dotenv d = Dotenv.load();

    @Inject
    public EnvContainer() {}

    /**
     * Get an ENV variable
     *
     * <p>The dotenv library used doesn't handle multi-line values, so a string is used to indicate
     * there should be a new line
     *
     * @param key the key
     * @return the ENV amount_dollars for the key
     */
    public String get(String key) {
        String value = d.get(key);
        if (value != null) {
            String nl = "__DOTENV__NEW__LINE__";
            return value.replaceAll(nl, "\n");
        } else {
            return null;
        }
    }

    public boolean isDev() {
        String value = (String) d.get("ENV");
        return value == null || value.equals("dev");
    }

    public String get(String key, boolean replaceNewLine) {
        return replaceNewLine ? this.get(key) : d.get(key);
    }
}
