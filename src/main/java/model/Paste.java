package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Paste implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("encryptedContent")
    private String encryptedContent;

    @JsonProperty("hash")
    private String hash;

    @JsonProperty("iv")
    private String iv;

    @JsonProperty("protected")
    private Boolean protection;
}
