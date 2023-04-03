package stepdefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import utils.JavaUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;


public class AssurityStepDefinition {
    public HashMap saveData = new HashMap<>();

    static Logger log = Logger.getLogger(AssurityStepDefinition.class.getName());

    @When("user submits the sandbox api with {string}")
    public void submitRequest(String baseEndpoint) throws IOException {

        //submit request
        Response response = (Response) given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).queryParam("catalogue", "false").
                when().get(saveData.get("url").toString());

        //saving response and status code in a map
        saveData.put("responseStatus", response.getStatusCode());
        saveData.put("responseBody", response.asString());
        //logging and saving response in a map
        log.info("response status is " + saveData.get("responseStatus"));
        log.info("response is " + saveData.get("responseBody"));

    }

    @Then("user validates the {string} response status code")
    public void validateStatus(String status) {
        Assert.assertTrue("Status is not coming as expected ", saveData.get("responseStatus").toString().equals(status));
    }

    @Given("{string} base uri with {string} endpoint")
    public void createURL(String uri, String endpoint) throws IOException {
        //create url
        String baseURI = JavaUtils.readProperties("baseURI");
        String url = baseURI + endpoint + ".json";
        saveData.put("url", url);

    }

    @And("user validates data in the response")
    public void validateReponseData(DataTable table) throws ParseException {
        String response = saveData.get("responseBody").toString();
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response);
        String val = json.get("Name").toString();
        boolean val1 = (boolean) json.get("CanRelist");
        log.info("value in name is " + val);
        log.info("value in name is " + val1);

        Map<String, String> dataTableMap = table.asMap();
        for (Map.Entry<String, String> entry : dataTableMap.entrySet()) {
            if (entry.getKey().equals("Promotions")) {
                if (json.get("Promotions").toString().contains(entry.getValue().split(":")[0])) {
                    String value[] = entry.getValue().split(":");
                    org.json.JSONObject json1 = new org.json.JSONObject(response);
                    org.json.JSONArray promotions = json1.getJSONArray(entry.getKey());
                    for (int i = 0; i < promotions.length(); i++) {
                        org.json.JSONObject obj = promotions.getJSONObject(i);
                        if (obj.get("Name").toString().equals(value[0])) {
                            Assert.assertTrue("values are not matching as expected", obj.get("Description").toString().equals(value[1]));
                            break;
                        }
                    }
                } else
                    Assert.assertTrue("the expected value is not present in the json response ", false);
            } else
                Assert.assertTrue("The actual and expected values are not equal", json.get(entry.getKey()).toString().equals(entry.getValue()));
        }
    }
}
