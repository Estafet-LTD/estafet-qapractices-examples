Feature: Use Skyscreamer's JSONAssert to compare JSONs

  @Json
  Scenario: Compare two JSONs in lenient mode
    Given the user has a JSON
    """
    {
       "unwantedParameterInRoot" : "nobodyExpects",
       "nodes":[
          {
             "nodeType":"http://alertme.com/schema/json/node.class.synthetic.testing_mirror_device.json#",
             "parentNodeId":"someRandomNumber",
             "additionalUnwantedParameter" : "thespanishincquisition",
             "name":"FakeContact2",
             "features":{
                "contact_sensor_v1":{
                   "isOpen":{
                      "targetValue":false
                   }
                }
             }
          }
       ]
    }
    """
    And the same JSON with out of order attributes
    """
    {
       "nodes":[
          {
             "name":"FakeContact2",
             "features":{
                "contact_sensor_v1":{
                   "isOpen":{
                      "targetValue":false
                   }
                }
             },
             "parentNodeId":"someRandomNumber",
             "nodeType":"http://alertme.com/schema/json/node.class.synthetic.testing_mirror_device.json#"
          }
       ]
    }
    """
    Then we compare the two JSONs

    