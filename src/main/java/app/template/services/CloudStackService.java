package app.template.services;
import br.com.autonomiccs.apacheCloudStack.client.ApacheCloudStackClient;
import br.com.autonomiccs.apacheCloudStack.client.ApacheCloudStackRequest;
import br.com.autonomiccs.apacheCloudStack.client.beans.ApacheCloudStackUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class CloudStackService {
    String apiKey = "your-api-key";
    String secretKey = "your-secret-key";
    String cloudStackUrl = "http://your-cloudstack-url/clie/api";
    ApacheCloudStackClient cloudstackConnection;
    public CloudStackService() {
        this.cloudstackConnection = apacheLogin();
    }

    private ApacheCloudStackClient apacheLogin(){
        ApacheCloudStackUser apacheCloudStackUser = new ApacheCloudStackUser(secretKey, apiKey);
        ApacheCloudStackClient apacheCloudStackClient = new ApacheCloudStackClient(cloudStackUrl, apacheCloudStackUser);

        return apacheCloudStackClient;
    }

    public String executeCloudStackRequest(String apiName, String parameters) throws JsonProcessingException {
        ApacheCloudStackRequest apacheCloudStackRequest = new ApacheCloudStackRequest(apiName);
        apacheCloudStackRequest.addParameter("response", "json");
        Map<String, Object> mapParams = convertParameters(parameters);
        for (Map.Entry<String, Object> entry : mapParams.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key != null && value != null) {
                apacheCloudStackRequest.addParameter(key, value);
            }
        }
        String response = cloudstackConnection.executeRequest(apacheCloudStackRequest);
        return response;
    }

    public Map<String, Object> convertParameters(String parameters) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> mapParams = objectMapper.readValue(parameters, new TypeReference<Map<String, Object>>() {});

        return mapParams;
    }
}
