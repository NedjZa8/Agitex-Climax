package com.anita.climaxapp;

import com.anita.climaxapp.domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ApplicationTest {
    @Mock
    private FileParserResolverInterface fileParserResolver;

    private static List<Client> clientsList;

    private static File file;

    private ApplicationService applicationService;

    @Mock
    private FileParser fileParser;

    @BeforeAll
    public static void beforeAll() {
        file = new File("fakeFile.xml");

        clientsList = Arrays.asList(
            new Client("NEDJOLBE", "Bintou", 43, "comptable", 200000),
            new Client("NEDJOLBE", "Stephanie", 34, "comptable", 35000),
            new Client("NEDJOLBE", "Annie", 30, "informaticienne", 45000)
        );
    }

    @BeforeEach
    void beforeEach() {
        applicationService = new ApplicationService(fileParserResolver);
    }

    @Test
    void getClientsList() throws FileParserException {
        when(fileParserResolver.resolve(file)).thenReturn(fileParser);
        whenFileParserIsCalledReturnClientsList();

        List<Client> clients = applicationService.getClientsList(file);

        assertEquals(3, clients.size());
        assertClientsListMatches(clientsList, clients);
    }

    private void whenFileParserIsCalledReturnClientsList() throws FileParserException {
        when(fileParser.getFileContent(file)).thenReturn(Arrays.asList(
            new Client("NEDJOLBE", "Bintou", 43, "comptable", 400000),
            new Client("NEDJOLBE", "Stephanie", 34, "comptable", 250000),
            new Client("NEDJOLBE", "Annie", 30, "informaticienne", 250000)
        ));
    }

    @Test
    void calculateMeanByProfession() throws FileParserException {
        var result = applicationService.getMeanByProfession(clientsList);
        assertEquals(result.get(0), new GroupResult("comptable", 400000));
        assertEquals(result.get(1), new GroupResult("informaticienne", 250000));
    }

    private void assertClientsListMatches(List<Client> expectedClients, List<Client> clients) {
        for(int i = 0; i < expectedClients.size(); i++) {
            assertEquals(expectedClients.get(i), clients.get(i));
        }
    }

}
