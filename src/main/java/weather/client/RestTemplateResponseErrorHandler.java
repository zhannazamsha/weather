package weather.client;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (
                httpResponse.getStatusCode().series() == CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == SERVER_ERROR);
        //3rd part api for getting location return status 200 in any case - success or error
        //3rd part api for weather is demo version - always return result for one location

    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        throw new ResourceErrorException(httpResponse.getStatusText());

    }
}
