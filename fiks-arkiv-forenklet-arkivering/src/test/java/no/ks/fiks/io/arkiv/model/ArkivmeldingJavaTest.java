package no.ks.fiks.io.arkiv.model;

import no.ks.fiks.io.arkiv.model.arkivmelding.*;
import no.ks.fiks.io.arkiv.model.arkivstruktur.*;
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.*;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

        ArkivmeldingBuilder arkivmeldingBuilder = new MappeArkivmeldingBuilder()
                .mapper(Collections.singletonList(mappe))
                .system("System A")
                .meldingId("MeldingsId")
                .tidspunkt(ZonedDateTime.now())
                .antallFiler(0);

        StringWriter sw = new StringWriter();
        arkivmeldingBuilder.marshal(sw);
        String xmlContent = sw.toString();
        System.out.println(xmlContent);

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("target/schemas/v1/arkivmelding.xsd"));
        Validator validator = schema.newValidator();

        Assertions.assertDoesNotThrow(() -> validator.validate(new JAXBSource(arkivmeldingBuilder.jaxbContext(), arkivmeldingBuilder.JAXBElement())));
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

        ArkivmeldingBuilder arkivmeldingBuilder = new RegistreringArkivmeldingBuilder()
                .registrering(Collections.singletonList(journalPostBuilder))
                .system("System A")
                .meldingId("MeldingsId")
                .tidspunkt(ZonedDateTime.now())
                .antallFiler(1);

        StringWriter sw = new StringWriter();
        arkivmeldingBuilder.marshal(sw);
        String xmlContent = sw.toString();
        System.out.println(xmlContent);

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("target/schemas/v1/arkivmelding.xsd"));
        Validator validator = schema.newValidator();

        Assertions.assertDoesNotThrow(() -> validator.validate(new JAXBSource(arkivmeldingBuilder.jaxbContext(), arkivmeldingBuilder.JAXBElement())));
    }

    @Test
    @DisplayName("Opprett Journalpost med dokumenter")
    public void OpprettJournalpostMedDokument() throws Exception{
        List<KorrespondansepartBuilder> korrespondanseparts = new ArrayList<>();
        korrespondanseparts.add(new KorrespondansepartBuilder()
                .korrespondansepartNavn("Birger Brannmann")
                .saksbehandler("Birger Brannmann"));
        korrespondanseparts.add(new KorrespondansepartBuilder()
                .korrespondansepartNavn("Mons Mottaker")
                .saksbehandler("Mons Mottaker")
                .postadresse(Collections.singletonList("Gate 1"))
                .postnummer("3801")
                .land("NO")
                .poststed("Bø"));


        final List<JournalpostBuilder> journalposter = Collections.singletonList(
                new JournalpostBuilder()
                        .systemID(new SystemIDBuilder().value(UUID.randomUUID()).label("Journalpost label"))
                        .journalsekvensnummer(7L)
                        .opprettetAv("Kari")
                        .arkivertAv("Kari")
                        .tittel("Vedtak etter tilsyn")
                        .referanseEksternNoekkel(
                                new EksternNoekkelBuilder()
                                .fagstystem("Fagsystem X")
                                .noekkel(UUID.randomUUID().toString()))
                        .korrespondanseparts(korrespondanseparts)
                        .journalpostnummer(222L)
                        .dokumentbeskrivelser(
                                Collections.singletonList(
                                        new DokumentbeskrivelseBuilder()
                                                .systemID(new SystemIDBuilder().value(UUID.randomUUID()).label("Dokumentbeskrivelse label"))
                                                .dokumentType(DokumentType.BREV)
                                                .dokumentStatus(DokumentStatus.DOKUMENTET_ER_FERDIGSTILT)
                                                .dokumentnummer(1L)
                                                .opprettetAv("Kari")
                                                .tittel("Vedtak")
                                                .tilknyttetRegistreringSom(TilknyttetRegistreringSomType.HOVEDDOKUMENT)
                                                .tilknyttetAv("Kari")
                                                .dokumentobjekter(
                                                    Collections.singletonList(
                                                            new DokumentObjektBuilder()
                                                                    .systemID(new SystemIDBuilder().value(UUID.randomUUID()).label("Dokumentobjekt label"))
                                                                    .filnavn("vedtak.pdf")
                                                                    .versjonsnummer(1L)
                                                                    .variantformat(VariantFormatType.ARKIVFORMAT)
                                                                    .mimeType("application/pdf")
                                                                    .opprettetAv("Kari")
                                                                    .sjekksum(UUID.randomUUID().toString())
                                                                    .sjekksumAlgoritme("hash")
                                                                    .filstoerrelse(12345L)
                                                                    .referanseDokumentfil("/en/path")
                                                                    .format(FormatType.PDF_A_ISO_19005_1_2005))))));
        ArkivmeldingBuilder arkivmeldingBuilder = new RegistreringArkivmeldingBuilder()
                .registrering(journalposter)
                .system("Fagsystem X")
                .antallFiler(1)
                .meldingId(UUID.randomUUID().toString());

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("target/schemas/v1/arkivmelding.xsd"));
        Validator validator = schema.newValidator();
        Assertions.assertDoesNotThrow(() -> validator.validate(new JAXBSource(arkivmeldingBuilder.jaxbContext(), arkivmeldingBuilder.JAXBElement())));
    }

}
