# LearnApacheIsis
learn apache isis for backend project

1. Generating App
mvn archetype:generate \ 
    -D archetypeGroupId=org.apache.isis.archetype \ 
    -D archetypeArtifactId=simpleapp-archetype \ 
    -D archetypeVersion=1.10.0 \ 
    -D groupId=com.mycompany \ 
    -D artifactId=myapp \ 
    -D version=1.0-SNAPSHOT \ 
    -B 
Attention: artifactID must be myapp, O.W you will get some unexpected errors. 
           the maven version should be old(Eg,3.0.5), not to use the latest for isis. 
           You better download the archetype by using VPN, O.W some resources are hard to download and you will be waiting. 

2. Build App
mvn clean install

3. Running App
Method one: java -jar webapp/target/myapp-webapp-1.0-SNAPSHOT-jetty-console.jar
Method two: configuration
            main class: prg.apache.isis.WebServer
            Program parameters: --type SERVER_PROTOTYPE --port 8080
            working directory: choosing your project webapp directory
            use classpath of mod: choosing your project webapp directory
            choose maven goal, and dom directory and first one for datanucleus:enhance 
