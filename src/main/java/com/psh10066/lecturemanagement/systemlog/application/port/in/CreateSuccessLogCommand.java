package com.psh10066.lecturemanagement.systemlog.application.port.in;

import com.psh10066.lecturemanagement.core.util.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class CreateSuccessLogCommand extends SelfValidating<CreateSuccessLogCommand> {

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

    String responseBody;

    public CreateSuccessLogCommand(Long userId, String httpMethod, String requestURL, String requestParameters, String requestHeaders, String responseHeaders, String responseBody) {
        this.userId = userId;
        this.httpMethod = httpMethod;
        this.requestURL = requestURL;
        this.requestParameters = requestParameters;
        this.requestHeaders = requestHeaders;
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;

        this.validateSelf();
    }
}
