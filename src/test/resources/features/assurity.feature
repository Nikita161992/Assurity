@assurity
Feature: To validate tmsandbox api response

  @smoke
  Scenario Outline: To validate api response
    Given "sandboxApi" base uri with "<baseEndpoint>" endpoint
    When user submits the sandbox api with "<baseEndpoint>"
    Then user validates the "<statuscode>" response status code
    And user validates data in the response
      | <key1> | <val1> |
      | <key2> | <val2> |
      | <key3> | <val3> |

    Examples:
      | ScenarioName                         | key1 | key2      | key3       | val1           | val2 | val3                              | baseEndpoint                | statuscode |
      | To validate api response- Scenario 1 | Name | CanRelist | Promotions | Carbon credits | true | Gallery:Good position in category | /v1/Categories/6327/Details | 200        |
