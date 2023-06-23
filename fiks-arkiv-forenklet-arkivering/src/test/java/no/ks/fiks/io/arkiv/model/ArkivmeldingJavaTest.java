package no.ks.fiks.io.arkiv.model;

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.Korrespondansepart;
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
                                .label("SystemId label").build())
                        .mappeId("mappeId")
                        .referanseForeldermappe(new ReferanseTilMappeBuilder().systemID(
                                new SystemIDBuilder()
                                        .value(UUID.randomUUID())
                                        .label("label").build()).build())
                        .tittel("Mappe tittel");

        ArkivmeldingBuilder arkivmeldingBuilder = new MappeArkivmeldingBuilder()
                .mappe(mappe.build())
                .system("System A")
                .antallFiler(0);

        StringWriter sw = new StringWriter();
        arkivmeldingBuilder.marshal(sw);
        String xmlContent = sw.toString();
        System.out.println(xmlContent);

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("target/schemas/v1/no.ks.fiks.arkiv.v1.arkivering.arkivmelding.opprett.xsd"));
        Validator validator = schema.newValidator();

        Assertions.assertDoesNotThrow(() -> validator.validate(new JAXBSource(arkivmeldingBuilder.jaxbContext(), arkivmeldingBuilder.JAXBElement())));
    }

    @Test
    @DisplayName("Opprett Arkivmelding med Journalposter")
    public void opprettArkivmeldingMedJournalposterTest() throws Exception {
        JournalpostBuilder journalPostBuilder = new JournalpostBuilder()
                .avskrivningsdato(LocalDate.now())
                .journalposttype(JournalpostType.UTGAENDE_DOKUMENT)
                .journalstatus(JournalStatus.FERDIGSTILT_FRA_SAKSBEHANDLER)
                .systemID(new SystemIDBuilder().value(UUID.randomUUID()).label("Journalpost label").build())
                .tittel("Journalpost tittel")
                .beskrivelse("Journalpost beskrivelse")
                .referanseEksternNoekkel(new EksternNoekkelBuilder().noekkel("Key").fagstystem("System A").build())
                .opprettetAv("Ole Olsen")
                .arkivertAv("Petter Pettersen")
                .journalsekvensnummer(111)
                .dokumentetsDato(LocalDate.now())
                .offentlighetsvurdertDato(LocalDate.now())
                .mottattDato(ZonedDateTime.now())
                .journalpostnummer(222);

        ArkivmeldingBuilder arkivmeldingBuilder = new RegistreringArkivmeldingBuilder()
                .registrering(journalPostBuilder)
                .system("System A")
                .antallFiler(1);

        StringWriter sw = new StringWriter();
        arkivmeldingBuilder.marshal(sw);
        String xmlContent = sw.toString();
        System.out.println(xmlContent);

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("target/schemas/v1/no.ks.fiks.arkiv.v1.arkivering.arkivmelding.opprett.xsd"));
        Validator validator = schema.newValidator();

        Assertions.assertDoesNotThrow(() -> validator.validate(new JAXBSource(arkivmeldingBuilder.jaxbContext(), arkivmeldingBuilder.JAXBElement())));
    }

    @Test
    @DisplayName("ArkivmeldingForenkletUtgaaende, Opprett Journalpost med dokumenter")
    public void OpprettJournalpostMedDokument() throws Exception{
        List<Korrespondansepart> korrespondanseparts = new ArrayList<>();
        SaksbehandlerBuilder saksbehandlerBuilder = new SaksbehandlerBuilder();
        saksbehandlerBuilder.navn("Birger Brannmann");
        korrespondanseparts.add(new KorrespondansepartBuilder()
                .korrespondansepartType(KorrespondansepartType.MOTTAKER)
                .korrespondansepartNavn("Birger Brannmann")
                .saksbehandler(saksbehandlerBuilder.build()).build());
        korrespondanseparts.add(new KorrespondansepartBuilder()
                .korrespondansepartType(KorrespondansepartType.MOTTAKER)
                .korrespondansepartNavn("Mons Mottaker")
                .saksbehandler(saksbehandlerBuilder.build())
                .postadresse(Collections.singletonList("Gate 1"))
                .postnummer("3801")
                .land("NO")
                .poststed("Bø").build());


        final JournalpostBuilder journalpost = new JournalpostBuilder()
                        .avskrivningsdato(LocalDate.now())
                        .journalposttype(JournalpostType.UTGAENDE_DOKUMENT)
                        .journalstatus(JournalStatus.JOURNALFORT)
                        .systemID(new SystemIDBuilder().value(UUID.randomUUID()).label("Journalpost label").build())
                        .journalsekvensnummer(7)
                        .opprettetAv("Kari")
                        .arkivertAv("Kari")
                        .tittel("Vedtak etter tilsyn")
                        .referanseEksternNoekkel(
                                new EksternNoekkelBuilder()
                                .fagstystem("Fagsystem X")
                                .noekkel(UUID.randomUUID().toString()).build())
                        .korrespondanseparts(korrespondanseparts)
                        .journalpostnummer(222)
                        .dokumentbeskrivelser(
                                Collections.singletonList(
                                        new DokumentbeskrivelseBuilder()
                                                .systemID(new SystemIDBuilder().value(UUID.randomUUID()).label("Dokumentbeskrivelse label").build())
                                                .dokumentType(DokumentType.KORRESPONDANSE)
                                                .dokumentStatus(DokumentStatus.DOKUMENTET_ER_FERDIGSTILT)
                                                .dokumentnummer(1)
                                                .opprettetAv("Kari")
                                                .tittel("Vedtak")
                                                .tilknyttetRegistreringSom(TilknyttetRegistreringSomType.HOVEDDOKUMENT)
                                                .tilknyttetAv("Kari")
                                                .dokumentobjekter(
                                                    Collections.singletonList(
                                                            new DokumentObjektBuilder()
                                                                    .systemID(new SystemIDBuilder().value(UUID.randomUUID()).label("Dokumentobjekt label").build())
                                                                    .filnavn("vedtak.pdf")
                                                                    .versjonsnummer(1)
                                                                    .variantformat(VariantFormatType.ARKIVFORMAT)
                                                                    .mimeType("application/pdf")
                                                                    .opprettetAv("Kari")
                                                                    .sjekksum(UUID.randomUUID().toString())
                                                                    .sjekksumAlgoritme("hash")
                                                                    .filstoerrelse(12345)
                                                                    .referanseDokumentfil("/en/path")
                                                                    .format(FormatType.PDF_A_ISO_19005_1_2005).build())).build()));
        ArkivmeldingBuilder arkivmeldingBuilder = new RegistreringArkivmeldingBuilder()
                .registrering(journalpost)
                .system("Fagsystem X")
                .antallFiler(1);

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("target/schemas/v1/no.ks.fiks.arkiv.v1.arkivering.arkivmelding.opprett.xsd"));
        Validator validator = schema.newValidator();
        Assertions.assertDoesNotThrow(() -> validator.validate(new JAXBSource(arkivmeldingBuilder.jaxbContext(), arkivmeldingBuilder.JAXBElement())));
    }

    @Test
    @DisplayName("ArkivmeldingForenkletUtgaaende, brukerhistorie 4 med forenklet modell")
    public void Brukerhistorie4ProAktivTest() throws Exception{
        SaksbehandlerBuilder saksbehandlerBuilder = new SaksbehandlerBuilder();
        saksbehandlerBuilder.navn("Birger Brannmann");
        ArkivmeldingBuilder arkivmelding = new ArkivmeldingForenkletUtgaaende()
                .sluttbrukerIdentifikator("ABC")
                .nyUtgaaendeJournalpost(new UtgaaendeJournalpost()
                        .avskrivningsdato(LocalDate.now())
                        .tittel("Vedtak etter tilsyn")
                        .referanseEksternNoekkelForenklet(new EksternNoekkelForenklet()
                                .noekkel(UUID.randomUUID().toString())
                                .fagstystem("Fagsystem X"))
                        .internAvsender(Collections.singletonList(new KorrespondansepartIntern()
                                .saksbehandler(saksbehandlerBuilder.build())
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
                                .forsendelsemaate("SvarUt")))
                        .hoveddokument(new ForenkletDokument()
                                .tittel("Vedtak")
                                .filnavn("vedtak.pdf")
                                .referanseDokumentFil("/en/path")))
                .referanseSaksmappeForenklet(new SaksmappeForenklet().tittel("Tilsyn eiendom 21/400"))
                .byggArkivmelding();

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("target/schemas/v1/no.ks.fiks.arkiv.v1.arkivering.arkivmelding.opprett.xsd"));
        Validator validator = schema.newValidator();
        Assertions.assertDoesNotThrow(() -> validator.validate(new JAXBSource(arkivmelding.jaxbContext(), arkivmelding.JAXBElement())));
    }

}
