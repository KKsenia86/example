package my.pack.api.exception;

import lombok.Getter;

@Getter
public class RequiredValueException extends RuntimeException {
    private final String fieldName;

    public RequiredValueException(String fieldName) {
        super("Required attribute value is null: " + fieldName);

        this.fieldName = fieldName;
    }
}