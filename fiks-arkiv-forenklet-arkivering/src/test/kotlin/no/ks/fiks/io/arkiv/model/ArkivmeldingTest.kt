package no.ks.fiks.io.arkiv.model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import mu.KotlinLogging
import no.ks.fiks.io.arkiv.model.arkivmelding.RegistreringArkivmeldingBuilder
import no.ks.fiks.io.arkiv.model.arkivstruktur.EksternNoekkelBuilder
import no.ks.fiks.io.arkiv.model.arkivmelding.JournalpostBuilder
import no.ks.fiks.io.arkiv.model.arkivmelding.KorrespondansepartBuilder
import no.ks.fiks.io.arkiv.model.arkivmelding.ReferanseTilMappeBuilder
import no.ks.fiks.io.arkiv.model.arkivstruktur.AdministrativEnhetBuilder
import no.ks.fiks.io.arkiv.model.arkivstruktur.SaksbehandlerBuilder
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.JournalStatus
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.JournalpostType
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.KorrespondansepartType
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SystemIDBuilder
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File
import java.io.StringWriter
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*
import javax.xml.XMLConstants
import javax.xml.bind.util.JAXBSource
import javax.xml.validation.SchemaFactory

val logger = KotlinLogging.logger {  }

class ArkivmeldingTest {

    @Test
    fun `Test gyldig arkivmelding`() {
        val mottattDato = ZonedDateTime.of(2022, 10, 12, 10, 15, 11, 100, ZoneId.of("Europe/Oslo") )

        val saksbehandlerBuilder = SaksbehandlerBuilder()
        saksbehandlerBuilder.navn("saksbehandler")
        val administrativEnhetBuilder = AdministrativEnhetBuilder()
        administrativEnhetBuilder.navn("Administrativenhet")
        val registrering =
            JournalpostBuilder()
                .avskrivningsdato(mottattDato.toLocalDate())
                .dokumentetsDato(mottattDato.toLocalDate())
                .offentlighetsvurdertDato(LocalDate.now())
                .mottattDato(mottattDato)
                .journalstatus(JournalStatus.GODKJENT_AV_LEDER)
                .journalposttype(JournalpostType.UTGAENDE_DOKUMENT)
                .journaldato(LocalDate.now())
                .journalpostnummer(42213)
                .journalsekvensnummer(1234)
                .journalaar(2022)
                .systemID(SystemIDBuilder().value(UUID.randomUUID()).label("registreringLabel").build())
                .tittel("Reg tittel")
                .opprettetDato(ZonedDateTime.now())
                .opprettetAv("Tester")
                .arkivertDato(ZonedDateTime.now())
                .arkivertAv("Mr. Arkiv")
                .referanseForelderMappe(ReferanseTilMappeBuilder().systemID(SystemIDBuilder().value(UUID.randomUUID()).label("registreringLabel").build()).build())
                .referanseEksternNoekkel(EksternNoekkelBuilder().fagstystem("Faglig").noekkel("key").build())
                .korrespondanseparts(listOf(
                    KorrespondansepartBuilder()
                    .korrespondansepartType(KorrespondansepartType.MOTTAKER)
                    .korrespondansepartNavn("korrespondansepartNavn")
                    .postadresse(emptyList())
                    .postnummer("1234")
                    .poststed("poststed")
                    .saksbehandler(saksbehandlerBuilder.build())
                    .administrativEnhet(administrativEnhetBuilder.build()).build()
                ))

        val arkivmelding = RegistreringArkivmeldingBuilder()
            .registrering(listOf(registrering))
            .system("systemA")
            .tidspunkt(ZonedDateTime.now())
            .antallFiler(2)

        val sw = StringWriter()
        arkivmelding.marshal(sw)
        val xmlContent = sw.toString()
        logger.info { xmlContent }

        xmlContent shouldContain "<dokumentetsDato>2022-10-12</dokumentetsDato>"
        xmlContent shouldContain "<mottattDato>2022-10-12T10:15:11.0000001+02:00</mottattDato>"

        val schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        val schema = schemaFactory.newSchema(File("target/schemas/v1/no.ks.fiks.arkiv.v1.arkivering.arkivmelding.xsd"))
        val validator = schema.newValidator()

        shouldNotThrowAny {
            validator.validate(JAXBSource(arkivmelding.jaxbContext(), arkivmelding.JAXBElement()))}
    }

    @Test
    fun `Test arkivmelding uten system definert, skal kaste exception`() {
        val saksbehandlerBuilder = SaksbehandlerBuilder()
        saksbehandlerBuilder.navn("saksbehandler")
        val administrativEnhetBuilder = AdministrativEnhetBuilder()
        administrativEnhetBuilder.navn("Administrativenhet")

        val registrering =
            JournalpostBuilder()
                .avskrivningsdato(LocalDate.now())
                .journaldato(LocalDate.now())
                .journalpostnummer(42213)
                .journalsekvensnummer(1234)
                .journalaar(2022)
                .systemID(SystemIDBuilder().value(UUID.randomUUID()).build())
                .tittel("Reg tittel")
                .opprettetDato(ZonedDateTime.now())
                .opprettetAv("Tester")
                .arkivertDato(ZonedDateTime.now())
                .arkivertAv("Mr. Arkiv")
                .referanseForelderMappe(ReferanseTilMappeBuilder().systemID(SystemIDBuilder().value(UUID.randomUUID()).label("registreringLabel").build()).build())
                .referanseEksternNoekkel(EksternNoekkelBuilder().fagstystem("Faglig").noekkel("key").build())
                .korrespondanseparts(listOf(
                    KorrespondansepartBuilder()
                    .korrespondansepartType(KorrespondansepartType.MOTTAKER)
                    .korrespondansepartNavn("korrespondansepartNavn")
                    .postadresse(emptyList())
                    .postnummer("1234")
                    .poststed("poststed")
                    .saksbehandler(saksbehandlerBuilder.build())
                    .administrativEnhet(administrativEnhetBuilder.build()).build()
                ))

        val arkivmelding = RegistreringArkivmeldingBuilder()
            .registrering(listOf(registrering))
            .antallFiler(1)
            .tidspunkt(ZonedDateTime.now())

        val exception = assertThrows<IllegalStateException> { arkivmelding.build() }
        exception.message shouldBe "System er påkrevd felt for Arkivmelding"

    }
}
