package com.anita.climaxapp.domain;

import com.anita.climaxapp.infra.XmlFileParser;
import com.anita.climaxapp.infra.YamlFileParser;

import static java.util.Arrays.stream;

public enum PARSER_TYPE {
    XML("xml", XmlFileParser.class),
    YAML("yaml", YamlFileParser.class);

    final String fileType;
    final Class<? extends FileParser> parser;

    PARSER_TYPE(String fileType, Class<? extends FileParser> parser) {
        this.fileType = fileType;
        this.parser = parser;
    }

    public static Class<? extends FileParser> getFileParser(String type) {
        return stream(values())
            .filter(t -> t.fileType.equals(type))
            .map(t -> t.parser)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("No parser found for type " + type));
    }
}
