package com.psh10066.lecturemanagement.systemlog.application.port.in;

import com.psh10066.lecturemanagement.core.util.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class CreateErrorLogCommand extends SelfValidating<CreateErrorLogCommand> {

    Long userId;

    @NotBlank
    String httpMethod;

    @NotBlank
    String requestURL;

    @NotBlank
    String requestParameters;

    @NotBlank
    String requestHeaders;

    @NotBlank
    String responseHeaders;

    @NotBlank
    String errorMessage;

    public CreateErrorLogCommand(Long userId, String httpMethod, String requestURL, String requestParameters, String requestHeaders, String responseHeaders, String errorMessage) {
        this.userId = userId;
        this.httpMethod = httpMethod;
        this.requestURL = requestURL;
        this.requestParameters = requestParameters;
        this.requestHeaders = requestHeaders;
        this.responseHeaders = responseHeaders;
        this.errorMessage = errorMessage;

        this.validateSelf();
    }
}
