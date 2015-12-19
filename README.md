# LearnApacheIsis
learn apache isis for backend project

1. Generating App
mvn archetype:generate \  <br>
    -D archetypeGroupId=org.apache.isis.archetype \  <br>
    -D archetypeArtifactId=simpleapp-archetype \  <br>
    -D archetypeVersion=1.10.0 \ <br>
    -D groupId=com.mycompany \ <br>
    -D artifactId=myapp \ <br>
    -D version=1.0-SNAPSHOT \ <br>
    -B <br>
Attention: artifactID must be myapp, O.W you will get some unexpected errors. <br>
           the maven version should be old(Eg,3.0.5), not to use the latest for isis. <br>
           You better download the archetype by using VPN, O.W some resources are hard to download and you will be waiting. <br>

2. Build App <br>
mvn clean install <br>

3. Running App <br>
Method one: java -jar webapp/target/myapp-webapp-1.0-SNAPSHOT-jetty-console.jar <br>
Method two: configuration <br>
            main class: prg.apache.isis.WebServer <br>
            Program parameters: --type SERVER_PROTOTYPE --port 8080 <br>
            working directory: choosing your project webapp directory <br>
            use classpath of mod: choosing your project webapp directory <br>
            choose maven goal, and dom directory and first one for datanucleus:enhance  <br>
