package no.ks.fiks.io.arkiv.model

import io.kotest.assertions.throwables.shouldNotThrowAny
import org.junit.jupiter.api.Test
import java.io.File
import java.io.StringWriter
import java.math.BigInteger
import java.time.ZonedDateTime
import java.util.*
import javax.xml.XMLConstants
import javax.xml.bind.util.JAXBSource
import javax.xml.validation.SchemaFactory


class ArkivmeldingTest {

    @Test
    fun `Valider Mappe mot xsd`() {
        val mappe =
            MappeBuilder()
                .systemID(SystemIDBuilder().value(UUID.randomUUID()).label("label"))
                .mappeId("mappeId")
                .referanseForeldermappe(SystemIDBuilder().value(UUID.randomUUID()).label("label"))
                .tittel("tittel")

        val registrering =
            JournalpostBuilder()
                .journaldato(ZonedDateTime.now())
                .journalpostnummer(BigInteger.valueOf(42213))
                .journalsekvensnummer(BigInteger.valueOf(1234))
                .journalaar(2022)
                .systemID(SystemIDBuilder().value(UUID.randomUUID()).label("registreringLabel"))
                .tittel("Reg tittel")
                .opprettetDato(ZonedDateTime.now())
                .opprettetAv("Tester")
                .arkivertDato(ZonedDateTime.now())
                .arkivertAv("Mr. Arkiv")
                .referanseForelderMappe(SystemIDBuilder().value(UUID.randomUUID()).label("registreringLabel"))
                .referanseEksternNoekkel(EksternNoekkelBuilder().fagstystem("Faglig").noekkel("key"))
                .journalposttype(JournalPostTypeBuilder().kode("kode").beskrivelse("beskrivelse"))
                .journalstatus(JournalstatusBuilder().kode("kode").beskrivelse("beskrivelse"))
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
    }
