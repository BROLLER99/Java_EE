package example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.StringWriter;

public class Convertor {
    StringWriter writer = new StringWriter();
    JAXBContext context;

    public String fromEntityToXML(UserEntity user) throws JAXBException {
        context = JAXBContext.newInstance(UserEntity.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(user, writer);
        return writer.toString();
    }

    public UserEntity fromXmlToEntity(FileReader reader) throws JAXBException {
        context = JAXBContext.newInstance(UserEntity.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        UserEntity user = (UserEntity) unmarshaller.unmarshal(reader);
        return user;
    }
}
