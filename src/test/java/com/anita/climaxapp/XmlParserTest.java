package com.anita.climaxapp;

import com.anita.climaxapp.domain.Client;
import com.anita.climaxapp.domain.FileParserException;
import com.anita.climaxapp.infra.XmlFileParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class XmlParserTest {
    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    void getFileContent() throws IOException, FileParserException {
        var fileParser = new XmlFileParser();
        var resource = resourceLoader.getResource("classpath:static/clients.xml");
        var clients = fileParser.getFileContent(resource.getFile());

        assertEquals(
            new Client("UCHIHA", "Itachi", 30, "shinobi", 200000),
            clients.get(0)
        );

        assertEquals(
            new Client("BAKYONO", "Arnaud", 30, "informaticien", 35000),
            clients.get(1)
        );
    }
}
