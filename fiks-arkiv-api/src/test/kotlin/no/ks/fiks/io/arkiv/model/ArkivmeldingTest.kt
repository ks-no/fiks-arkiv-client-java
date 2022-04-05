package no.ks.fiks.io.arkiv.model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.File
import java.io.StringWriter
import java.time.ZonedDateTime
import java.util.*
import javax.xml.XMLConstants
import javax.xml.bind.util.JAXBSource
import javax.xml.validation.SchemaFactory


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
                .korrespondanseparts(listOf(KorrespondansepartBuilder()
                    .korrespondansepartType(KorrespondansepartTypeBuilder().kode("kode").beskrivelse("Beskrivelse"))
                    .korrespondansepartNavn("korrespondansepartNavn")
                    .postadresse(emptyList())
                    .postnummer("1234")
                    .poststed("poststed")
                    .saksbehandler("saksbehandler")
                    .administrativEnhet("administrativEnhet")
                ))

        val arkivmelding = Arkivmelding().system("systemA").meldingId("meldingsId").tidspunkt(ZonedDateTime.now()).mapper(emptyList()).registrering(listOf(registrering))

        val sw = StringWriter()
        arkivmelding.marshal(sw)
        val xmlContent = sw.toString()
        println(xmlContent)

        val schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        val schema = schemaFactory.newSchema(File("target/schemas/v1/arkivmelding.xsd"))
        val validator = schema.newValidator()

        shouldNotThrowAny {
            validator.validate(JAXBSource(arkivmelding.jaxbContext(), arkivmelding.JAXBElement()))}
    }

    @Test
    fun `Test at vi far feilmelding om vi oppretter arkivmelding med Mappe og Registrering`(){
        val mappe =
            MappeBuilder()
                .systemID(SystemIDBuilder().value(UUID.randomUUID()).label("label"))
                .mappeId("mappeId")
                .referanseForeldermappe(SystemIDBuilder().value(UUID.randomUUID()).label("label"))
                .tittel("tittel")

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
                .journalposttype(JournalpostType.ORGANINTERT_DOKUMENT_FOR_OPPFOLGING)
                .korrespondanseparts(listOf(KorrespondansepartBuilder()
                    .korrespondansepartType(KorrespondansepartTypeBuilder().kode("kode").beskrivelse("Beskrivelse"))
                    .korrespondansepartNavn("korrespondansepartNavn")
                    .postadresse(emptyList())
                    .postnummer("1234")
                    .poststed("poststed")
                    .saksbehandler("saksbehandler")
                    .administrativEnhet("administrativEnhet")
                ))

        val msg = shouldThrow<java.lang.IllegalArgumentException> {
            Arkivmelding()
                .system("systemA")
                .meldingId("meldingsId")
                .tidspunkt(ZonedDateTime.now())
                .mapper(listOf(mappe))
                .registrering(listOf(registrering))
        }

        msg.message shouldBe "Arkivmelding kan enten inneholde Mappe(r) eller Registreringe(r), men ikke begge."

    }
}
