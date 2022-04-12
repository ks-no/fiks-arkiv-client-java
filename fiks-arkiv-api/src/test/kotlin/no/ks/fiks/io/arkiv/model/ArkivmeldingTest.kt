package no.ks.fiks.io.arkiv.model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import no.ks.fiks.io.arkiv.model.arkivmelding.Arkivmelding
import no.ks.fiks.io.arkiv.model.arkivstruktur.EksternNoekkelBuilder
import no.ks.fiks.io.arkiv.model.arkivstruktur.JournalpostBuilder
import no.ks.fiks.io.arkiv.model.arkivstruktur.KorrespondansepartBuilder
import no.ks.fiks.io.arkiv.model.arkivstruktur.MappeBuilder
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.JournalpostType
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SystemIDBuilder
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File
import java.io.StringWriter
import java.time.ZonedDateTime
import java.util.*
import javax.xml.XMLConstants
import javax.xml.bind.util.JAXBSource
import javax.xml.validation.SchemaFactory
import mu.KotlinLogging
import no.ks.fiks.io.arkiv.model.arkivmelding.RegistreringArkivmelding
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.KorrespondansepartType

val logger = KotlinLogging.logger {  }

class ArkivmeldingTest {

    @Test
    fun `Test gyldig arkivmelding`() {
        val registrering =
            JournalpostBuilder()
                .journaldato(ZonedDateTime.now())
                .journalpostnummer(42213L)
                .journalsekvensnummer(1234L)
                .journalaar(2022)
                .systemID(SystemIDBuilder().value(UUID.randomUUID()).label("registreringLabel"))
                .tittel("Reg tittel")
                .opprettetDato(ZonedDateTime.now())
                .opprettetAv("Tester")
                .arkivertDato(ZonedDateTime.now())
                .arkivertAv("Mr. Arkiv")
                .referanseForelderMappe(SystemIDBuilder().value(UUID.randomUUID()).label("registreringLabel"))
                .referanseEksternNoekkel(EksternNoekkelBuilder().fagstystem("Faglig").noekkel("key"))
                .korrespondanseparts(listOf(
                    KorrespondansepartBuilder()
                    .korrespondansepartType(KorrespondansepartType.MOTTAKER)
                    .korrespondansepartNavn("korrespondansepartNavn")
                    .postadresse(emptyList())
                    .postnummer("1234")
                    .poststed("poststed")
                    .saksbehandler("saksbehandler")
                    .administrativEnhet("administrativEnhet")
                ))

        val arkivmelding = RegistreringArkivmelding()
            .registrering(listOf(registrering))
            .system("systemA")
            .meldingId("meldingsId")
            .tidspunkt(ZonedDateTime.now())
            .antallFiler(2)

        val sw = StringWriter()
        arkivmelding.marshal(sw)
        val xmlContent = sw.toString()
        logger.info { xmlContent }

        val schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        val schema = schemaFactory.newSchema(File("target/schemas/v1/arkivmelding.xsd"))
        val validator = schema.newValidator()

        shouldNotThrowAny {
            validator.validate(JAXBSource(arkivmelding.jaxbContext(), arkivmelding.JAXBElement()))}
    }

    @Test
    fun `Test arkivmelding uten system definert, skal kaste exception`() {
        val registrering =
            JournalpostBuilder()
                .journaldato(ZonedDateTime.now())
                .journalpostnummer(42213L)
                .journalsekvensnummer(1234L)
                .journalaar(2022)
                .systemID(SystemIDBuilder().value(UUID.randomUUID()))
                .tittel("Reg tittel")
                .opprettetDato(ZonedDateTime.now())
                .opprettetAv("Tester")
                .arkivertDato(ZonedDateTime.now())
                .arkivertAv("Mr. Arkiv")
                .referanseForelderMappe(SystemIDBuilder().value(UUID.randomUUID()).label("registreringLabel"))
                .referanseEksternNoekkel(EksternNoekkelBuilder().fagstystem("Faglig").noekkel("key"))
                .korrespondanseparts(listOf(
                    KorrespondansepartBuilder()
                    .korrespondansepartType(KorrespondansepartType.MOTTAKER)
                    .korrespondansepartNavn("korrespondansepartNavn")
                    .postadresse(emptyList())
                    .postnummer("1234")
                    .poststed("poststed")
                    .saksbehandler("saksbehandler")
                    .administrativEnhet("administrativEnhet")
                ))

        val arkivmelding = RegistreringArkivmelding()
            .registrering(listOf(registrering))
            .meldingId("meldingsId")
            .antallFiler(1)
            .tidspunkt(ZonedDateTime.now())

        val exception = assertThrows<IllegalStateException> { arkivmelding.build() }
        exception.message shouldBe "System er påkrevd felt for Arkivmelding"

    }
}
