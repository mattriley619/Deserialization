import java.io.Serializable;
import java.util.UUID;

public class Verifier implements Serializable {
    private static final long serialVersionUID = 1L;
    private UUID uuid;
    private byte[] userHash;

    public Verifier() {
        this.uuid = UUID.randomUUID();
    }

    public UUID getUUID() {
        return uuid;
    }

    public byte[] getUserHash() {
        return userHash;
    }

    public void setUserHash(byte[] userHash) {
        this.userHash = userHash;
    }
}