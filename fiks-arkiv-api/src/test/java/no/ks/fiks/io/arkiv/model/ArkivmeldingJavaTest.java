package no.ks.fiks.io.arkiv.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.XMLConstants;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.StringWriter;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.UUID;

public class ArkivmeldingJavaTest {

    @Test
    @DisplayName("Valider Mappe mot xsd")
    public void validerModellMotXSDTest() throws Exception {
        MappeBuilder mappe =
                new MappeBuilder().avsluttetAv("Tester").avsluttetDato(ZonedDateTime.now()).opprettetAv("tester").opprettetDato(ZonedDateTime.now()).systemID(new SystemIDBuilder().value(UUID.randomUUID()).label("label")).mappeId("mappeId").referanseForeldermappe(new SystemIDBuilder().value(UUID.randomUUID()).label("label")).tittel("tittel");

        Arkivmelding arkivmelding = new Arkivmelding().system("systemA").meldingId("meldingsId").tidspunkt(ZonedDateTime.now()).mapper(Collections.singletonList(mappe)).registrering(Collections.emptyList());

        StringWriter sw = new StringWriter();
        arkivmelding.marshal(sw);
        String xmlContent = sw.toString();
        System.out.println(xmlContent);

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("target/schemas/v1/arkivmelding.xsd"));
        Validator validator = schema.newValidator();

        Assertions.assertDoesNotThrow(() -> validator.validate(new JAXBSource(arkivmelding.jaxbContext(), arkivmelding.JAXBElement())));
    }

}
