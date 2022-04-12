package no.ks.fiks.io.arkiv.model;

import no.ks.fiks.io.arkiv.model.arkivmelding.Arkivmelding;
import no.ks.fiks.io.arkiv.model.arkivmelding.MappeArkivmelding;
import no.ks.fiks.io.arkiv.model.arkivmelding.RegistreringArkivmelding;
import no.ks.fiks.io.arkiv.model.arkivstruktur.EksternNoekkelBuilder;
import no.ks.fiks.io.arkiv.model.arkivstruktur.JournalpostBuilder;
import no.ks.fiks.io.arkiv.model.arkivstruktur.MappeBuilder;
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SystemIDBuilder;
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
    @DisplayName("Opprett Arkivmelding med Mappe")
    public void opprettArkivmeldingMedMappeTest() throws Exception {
        MappeBuilder mappe =
                new MappeBuilder()
                        .opprettetAv("Ole Olsen")
                        .avsluttetAv("Petter Pettersen")
                        .avsluttetDato(ZonedDateTime.now())
                        .opprettetDato(ZonedDateTime.now())
                        .systemID(new SystemIDBuilder()
                                .value(UUID.randomUUID())
                                .label("SystemId label"))
                        .mappeId("mappeId")
                        .referanseForeldermappe(
                                new SystemIDBuilder()
                                        .value(UUID.randomUUID())
                                        .label("label"))
                        .tittel("Mappe tittel");

        Arkivmelding arkivmelding = new MappeArkivmelding()
                .mapper(Collections.singletonList(mappe))
                .system("System A")
                .meldingId("MeldingsId")
                .tidspunkt(ZonedDateTime.now())
                .antallFiler(0);

        StringWriter sw = new StringWriter();
        arkivmelding.marshal(sw);
        String xmlContent = sw.toString();
        System.out.println(xmlContent);

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("target/schemas/v1/arkivmelding.xsd"));
        Validator validator = schema.newValidator();

        Assertions.assertDoesNotThrow(() -> validator.validate(new JAXBSource(arkivmelding.jaxbContext(), arkivmelding.JAXBElement())));
    }

    @Test
    @DisplayName("Opprett Arkivmelding med Journalposter")
    public void opprettArkivmeldingMedJournalposterTest() throws Exception {
        JournalpostBuilder journalPostBuilder = new JournalpostBuilder()
                .systemID(new SystemIDBuilder().value(UUID.randomUUID()).label("Journalpost label"))
                .tittel("Journalpost tittel")
                .beskrivelse("Journalpost beskrivelse")
                .referanseEksternNoekkel(new EksternNoekkelBuilder().noekkel("Key").fagstystem("System A"))
                .opprettetAv("Ole Olsen")
                .arkivertAv("Petter Pettersen")
                .journalsekvensnummer(111L)
                .journalpostnummer(222L);

        Arkivmelding arkivmelding = new RegistreringArkivmelding()
                .registrering(Collections.singletonList(journalPostBuilder))
                .system("System A")
                .meldingId("MeldingsId")
                .tidspunkt(ZonedDateTime.now())
                .antallFiler(1);

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
