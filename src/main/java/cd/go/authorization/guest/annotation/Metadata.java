package cd.go.authorization.guest.annotation;

public interface Metadata {
    boolean isRequired();

    boolean isSecure();

    FieldType getType();
}
