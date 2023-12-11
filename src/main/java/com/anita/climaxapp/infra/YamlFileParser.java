package com.anita.climaxapp.infra;

import com.anita.climaxapp.domain.Client;
import com.anita.climaxapp.domain.FileParserException;
import com.anita.climaxapp.domain.FileParser;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class YamlFileParser implements FileParser {
    @Override
    public List<Client> getFileContent(File file) throws FileParserException {
        try {
            Yaml parser = new Yaml();
            List<HashMap<String, Object>> clientsList = parser.load(new FileInputStream(file));

            return clientsList.stream().map(client -> new Client(
                client.get("nom").toString(),
                client.get("prenom").toString(),
                (int) client.get("age"),
                client.get("profession").toString(),
                Double.parseDouble(client.get("salaire").toString()) * 1000
            )).collect(Collectors.toList());

        } catch (FileNotFoundException e) {
            throw new FileParserException(e.getMessage());
        }
    }
}
