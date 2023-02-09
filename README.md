# phrase-interview
https://docs.google.com/document/d/1ilLzH8lz8aREG_lmGX2d_gaRcTBvOfteB5Hrvk18oWA/edit

## Requirements:
- Java 17
- Maven 3.8.6+

## Prequisites:
Port 8080 must not be in use or changed in ```/src/main/resources/application.properties```

## Compile & run:
mvn spring-boot:run

## Endpoints: (adjust if port was changed)
- Setup page - http://localhost:8080/config
- Projects page - http://localhost:8080/projects (use 'Load Projects' button for Ajax call loading items)

## Notes:
- Took around 5h
- First 1h burned on having maven repository initiated for older springboot version -> application was terminating immediatelly after start -> local maven repo deletion helped
- Config Endpoint is tested properly, Projects endpoint has just basic tests - need to go to sleep :-)
- Not sure if 'render' projects in Javascript was ment to process/parse the response inside Javascript - I took the path of processing that in Java as this was easier for me
