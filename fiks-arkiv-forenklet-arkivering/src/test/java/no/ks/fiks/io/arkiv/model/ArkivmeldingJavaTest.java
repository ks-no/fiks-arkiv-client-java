package no.ks.fiks.io.arkiv.model;

import no.ks.fiks.io.arkiv.model.arkivmelding.*;
import no.ks.fiks.io.arkiv.model.arkivstruktur.*;
import no.ks.fiks.io.arkiv.model.forenklet.*;
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
import java.time.LocalDate;
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
                        .referanseForeldermappe(new ReferanseForelderMappeBuilder().systemID(
                                new SystemIDBuilder()
                                        .value(UUID.randomUUID())
                                        .label("label")))
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
        Schema schema = schemaFactory.newSchema(new File("target/schemas/v1/no.ks.fiks.arkiv.v1.arkivering.arkivmelding.xsd"));
        Validator validator = schema.newValidator();

        Assertions.assertDoesNotThrow(() -> validator.validate(new JAXBSource(arkivmeldingBuilder.jaxbContext(), arkivmeldingBuilder.JAXBElement())));
    }

    @Test
    @DisplayName("Opprett Arkivmelding med Journalposter")
    public void opprettArkivmeldingMedJournalposterTest() throws Exception {
        JournalpostBuilder journalPostBuilder = new JournalpostBuilder()
                .journalposttype(JournalpostType.UTGAENDE_DOKUMENT)
                .journalstatus(JournalStatus.FERDIGSTILT_FRA_SAKSBEHANDLER)
                .systemID(new SystemIDBuilder().value(UUID.randomUUID()).label("Journalpost label"))
                .tittel("Journalpost tittel")
                .beskrivelse("Journalpost beskrivelse")
                .referanseEksternNoekkel(new EksternNoekkelBuilder().noekkel("Key").fagstystem("System A"))
                .opprettetAv("Ole Olsen")
                .arkivertAv("Petter Pettersen")
                .journalsekvensnummer(111L)
                .dokumentetsDato(LocalDate.now())
                .offentlighetsvurdertDato(LocalDate.now())
                .mottattDato(ZonedDateTime.now())
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
        Schema schema = schemaFactory.newSchema(new File("target/schemas/v1/no.ks.fiks.arkiv.v1.arkivering.arkivmelding.xsd"));
        Validator validator = schema.newValidator();

        Assertions.assertDoesNotThrow(() -> validator.validate(new JAXBSource(arkivmeldingBuilder.jaxbContext(), arkivmeldingBuilder.JAXBElement())));
    }

    @Test
    @DisplayName("ArkivmeldingForenkletUtgaaende, Opprett Journalpost med dokumenter")
    public void OpprettJournalpostMedDokument() throws Exception{
        List<KorrespondansepartBuilder> korrespondanseparts = new ArrayList<>();
        korrespondanseparts.add(new KorrespondansepartBuilder()
                .korrespondansepartType(KorrespondansepartType.MOTTAKER)
                .korrespondansepartNavn("Birger Brannmann")
                .saksbehandler("Birger Brannmann"));
        korrespondanseparts.add(new KorrespondansepartBuilder()
                .korrespondansepartType(KorrespondansepartType.MOTTAKER)
                .korrespondansepartNavn("Mons Mottaker")
                .saksbehandler("Mons Mottaker")
                .postadresse(Collections.singletonList("Gate 1"))
                .postnummer("3801")
                .land("NO")
                .poststed("Bø"));


        final List<JournalpostBuilder> journalposter = Collections.singletonList(
                new JournalpostBuilder()
                        .journalposttype(JournalpostType.UTGAENDE_DOKUMENT)
                        .journalstatus(JournalStatus.JOURNALFORT)
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
                                                .dokumentType(DokumentType.KORRESPONDANSE)
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
        Schema schema = schemaFactory.newSchema(new File("target/schemas/v1/no.ks.fiks.arkiv.v1.arkivering.arkivmelding.xsd"));
        Validator validator = schema.newValidator();
        Assertions.assertDoesNotThrow(() -> validator.validate(new JAXBSource(arkivmeldingBuilder.jaxbContext(), arkivmeldingBuilder.JAXBElement())));
    }

    @Test
    @DisplayName("ArkivmeldingForenkletUtgaaende, brukerhistorie 4 med forenklet modell")
    public void Brukerhistorie4ProAktivTest() throws Exception{
        ArkivmeldingBuilder arkivmelding = new ArkivmeldingForenkletUtgaaende()
                .sluttbrukerIdentifikator("ABC")
                .nyUtgaaendeJournalpost(new UtgaaendeJournalpost()
                        .tittel("Vedtak etter tilsyn")
                        .referanseEksternNoekkelForenklet(new EksternNoekkelForenklet()
                                .noekkel(UUID.randomUUID().toString())
                                .fagstystem("Fagsystem X"))
                        .internAvsender(Collections.singletonList(new KorrespondansepartIntern()
                                .saksbehandler("Birger Brannmann")
                                .referanseSaksbehandler("60577438-1f97-4c5f-b254-aa758c8786c4")))
                        .mottakere(Collections.singletonList(new KorrespondansepartForenklet()
                                .navn("Mons Mottaker")
                                .personid(new Personidentifikator()
                                        .personidentifikatorLandkode("NO")
                                        .personidentifikatorNr("12345678901"))
                                .postadresse(new EnkelAdresse()
                                        .adresselinje1("Gate 1")
                                        .adresselinje2("Andre adresselinje")
                                        .adresselinje3("Tredje adresselinje")
                                        .postnr("3801")
                                        .poststed("Bø"))
                                .forsendelsemåte("SvarUt")))
                        .hoveddokument(new ForenkletDokument()
                                .tittel("Vedtak")
                                .filnavn("vedtak.pdf")
                                .referanseDokumentFil("/en/path")))
                .referanseSaksmappeForenklet(new SaksmappeForenklet().tittel("Tilsyn eiendom 21/400"))
                .byggArkivmelding();

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("target/schemas/v1/no.ks.fiks.arkiv.v1.arkivering.arkivmelding.xsd"));
        Validator validator = schema.newValidator();
        Assertions.assertDoesNotThrow(() -> validator.validate(new JAXBSource(arkivmelding.jaxbContext(), arkivmelding.JAXBElement())));
    }

}
