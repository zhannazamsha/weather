package weather.client;


public class ResourceErrorException extends RuntimeException {
    public ResourceErrorException(String message) {
        super("Something went wrong. " + message);
    }
}
