package com.anita.climaxapp.domain;

import java.io.File;
import java.util.List;

public interface FileParser {
    List<Client> getFileContent(File file) throws FileParserException;
}
