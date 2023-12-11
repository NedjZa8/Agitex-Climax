# Agitex-climax-test
La solution est une simple application Springboot avec un controleur pour tester

Il y a deux formats pris en charge actuellement (XML et YAML), ces fichiers peuvent être trouvés dans le dossier *resources/static*

### Tester

GET **http://localhost:8000/xml** ou GET **http://localhost:8000/yaml** pour tester la lecture des fichiers xml et yml.


### Comment rajouter un nouveau format?

La structure du code facilite extrêment cet ajout, il suffit juste de créer une classe implémentant l'interface **FileParser**, exemple

```java
public class CsvFileParser implements FileParser {
  @Override
    public List<Client> getFileContent(File file) throws FileParserException {
       ...
    }
}
```

Et de modifier le resolver pour prendre en compte ce nouveau format

```java
case "xml":
    return new XmlFileParser();
case "csv":
    return new CsvFileParser();
```

Une ou deux lignes à rajouter dans le resolver donc
