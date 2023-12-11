package com.anita.climaxapp.infra;

import com.anita.climaxapp.domain.Client;
import com.anita.climaxapp.domain.FileParser;
import com.anita.climaxapp.domain.FileParserException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class XmlFileParser implements FileParser {
    @Override
    public List<Client> getFileContent(File file) throws FileParserException {
        try {
            var context = JAXBContext.newInstance(Climax.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            var climax = (Climax) unmarshaller.unmarshal(new FileInputStream(file));

            return climax.getClientList().stream().map(c -> new Client(
               c.name, c.forename, c.age, c.profession, c.salary * 1000
            )).collect(Collectors.toList());
        } catch (JAXBException | FileNotFoundException e) {
            throw new FileParserException(e.getMessage());
        }
    }
}

@XmlRootElement
class Climax {
    private List<ClientXML> clientList;

    @XmlElement(name = "client")
    public List<ClientXML> getClientList() {
        return clientList;
    }

    public void setClientList(List<ClientXML> clientList) {
        this.clientList = clientList;
    }
}

@XmlRootElement(name = "client")
class ClientXML {
    @XmlElement(name = "nom")
    public String name;

    @XmlElement(name = "prenom")
    public String forename;
    public int age;
    public String profession;

    @XmlElement(name = "salaire")
    public double salary;
}
