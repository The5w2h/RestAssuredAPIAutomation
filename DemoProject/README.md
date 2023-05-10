# API Testing

## Important Classes and Methods
* SessionFilter: Used to store session cookie. Can be used to replace JsonPath
**Usage** body().**filter(session)**.when()
```java
SessionFilter session = new SesssionFilter();
given().header().body().filter(session).when().post().then().extract().response().asString();
```
* pathParam("key","0101"): sent in curly braces
* mvn archetype:generate -DgroupId=tsclickacademy -DartifactId=Mavenjava -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
* mvn eclipse:eclipse
* For attachments use: multiPart("file", new File("jira.txt")). When multiPart is used then use header("Context-Type","multipart/form-data")
