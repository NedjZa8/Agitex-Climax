package com.anita.climaxapp;

import com.anita.climaxapp.domain.Client;
import com.anita.climaxapp.domain.FileParserException;
import com.anita.climaxapp.infra.YamlFileParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class YamlParserTest {
    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    void getFileContent() throws IOException, FileParserException {
        var fileParser = new YamlFileParser();
        var resource = resourceLoader.getResource("classpath:static/clients.yaml");
        var clients = fileParser.getFileContent(resource.getFile());

        assertEquals(
            new Client("NEDJOLBE", "stephanie", 34, "comptable", 250000),
            clients.get(0)
        );

        assertEquals(
            new Client("NEDJOLBE", "Annie", 25, "informaticienne", 250000),
            clients.get(1)
        );
    }
}
