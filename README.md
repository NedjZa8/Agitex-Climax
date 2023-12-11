
#   Agitex-climax</br>

Il s'agit d'une application Spring Boot simple comprenant un contrôleur permettant de tester la lecture de fichiers. Actuellement, deux formats sont pris en charge (XML et YAML), et ces fichiers peuvent être localisés dans le répertoire resources/static.

### Procédure de test</br>
Pour tester la lecture des fichiers XML et YAML, effectuez une requête GET à l'adresse **http://localhost:8000/xml** ou **http://localhost:8000/yaml**.

### Ajout d'un nouveau format</br>
L'ajout d'un nouveau format est grandement facilité par la structure du code. Il suffit de créer une classe qui implémente l'interface FileParser, comme illustré dans l'exemple suivant :

```java
public class CsvFileParser implements FileParser {
    @Override
    public List<Client> getFileContent(File file) throws FileParserException {
       // Implémentation spécifique au nouveau format...
    }
```
Ensuite, il vous suffit de modifier le résolveur pour prendre en compte ce nouveau format, comme indiqué ci-dessous :

```java
case "xml":
    return new XmlFileParser();
case "csv":
    return new CsvFileParser();
```
L'ajout de ces une ou deux lignes dans le résolveur est donc tout ce qui est nécessaire pour intégrer un nouveau format.