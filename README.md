# SpringBootStarterAPI
Java API for start.spring.io project generator

## Usage
```Java
// Visit start.spring.io like a CURL
SpringBootProjectModel model = SpringBootProjectModel.initFromWeb();

model.setType(ProjectType.GRADLE_KOTLIN);
model.setParameter(StandardProjectParameters.NAME, "TestProjectName");
// Setup other parameters...
        
model.addDependency(StandardProjectDependency.Spring_Web);
// Setup other dependencies...
        
model.downloadProjectFile(new File("starter.zip"));//You've got the project!
```