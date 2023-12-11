package com.anita.climaxapp.domain;


import java.io.File;
import java.util.List;

import static java.util.stream.Collectors.*;

public class ApplicationService {
    private final FileParserResolverInterface fileParserResolver;

    public ApplicationService(FileParserResolverInterface fileParserResolver) {
        this.fileParserResolver = fileParserResolver;
    }

    public List<Client> getClientsList(File file) throws FileParserException {
        var parser = fileParserResolver.resolve(file);
        return parser.getFileContent(file);
    }

    public List<GroupResult> getMeanByProfession(List<Client> clientsList) {
        return clientsList.stream().collect(
                groupingBy(
                    Client::profession,
                    mapping(Client::salary, averagingDouble(salary -> salary))
                )
            )
            .entrySet()
            .stream()
            .map(entry -> new GroupResult(entry.getKey(), entry.getValue())).collect(toList());
    }
}
