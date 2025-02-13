package cn.memoryzy.deepSeek.persistent;

import com.intellij.credentialStore.CredentialAttributes;
import com.intellij.credentialStore.CredentialAttributesKt;
import com.intellij.credentialStore.Credentials;
import com.intellij.ide.passwordSafe.PasswordSafe;

/**
 * @author Memory
 * @since 2025/2/13
 */
public class PasswordHandler {

    public static final String SYSTEM = "DeepSeek Assistant Key Passphrase";

    private static CredentialAttributes createCredentialAttributes(String key) {
        return new CredentialAttributes(CredentialAttributesKt.generateServiceName(SYSTEM, key));
    }

    public static String getPassword(String key) {
        CredentialAttributes attributes = createCredentialAttributes(key);
        PasswordSafe passwordSafe = PasswordSafe.getInstance();

        Credentials credentials = passwordSafe.get(attributes);
        if (credentials != null) {
            return credentials.getPasswordAsString();
        }

        return null;
        // or get password only
        // return passwordSafe.getPassword(attributes);
    }

    public static void setPassword(String key, String username, String password) {
        CredentialAttributes attributes = createCredentialAttributes(key);
        Credentials credentials = new Credentials(username, password);
        PasswordSafe.getInstance().set(attributes, credentials);
    }

    public static void clearPassword(String key) {
        CredentialAttributes attributes = createCredentialAttributes(key);
        PasswordSafe.getInstance().set(attributes, null);
    }

}
