# estafet-qapractices-examples
Set of examples for configuring and running tests

This is a multi module Maven project.

Currently, there is only a working example of a cucumber test being executed.

Update 18/09/2017:
* Added an environment yml file. It holds string values for environmental setup- URLs, credentials, etc.
* Added a test environment class. It loads the yml file and sets up the environment.
* Added a context class- a Java class with @scenarioScoped lifecycle. Used for storing scenario data.
* Added a custom Test exception to be used inside our code.
* Added an API class describing a few HttpBin resources used in the examples.
* Added a class configuring ReSTEasy to use the API with public methods calling the resources.
* Added a POJO model to be used in the examples.
* Added new feature and step defs with ReST API examples.
* Added Javadoc in multiple classes.


TBA
