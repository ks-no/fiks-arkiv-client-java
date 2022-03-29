package no.ks.fiks.io.arkiv.model

import io.kotest.assertions.throwables.shouldNotThrowAny
import org.junit.jupiter.api.Test
import java.io.File
import java.io.StringWriter
import java.util.*
import javax.xml.XMLConstants
import javax.xml.bind.util.JAXBSource
import javax.xml.validation.SchemaFactory


class ArkivmeldingTest {

    @Test
    fun `Valider Mappe mot xsd`() {
        val mappe =
            Mappe(
                systemID = SystemID(value = UUID.randomUUID(), label = "label"),
                mappeId = "mappeId",
                referanseForeldermappe = SystemID(value = UUID.randomUUID(), label = "label"),
                tittel = "tittel"
            )

        val registrering =
            Journalpost(
                systemID = SystemID(value = UUID.randomUUID(), label = "registreringLabel"),
                tittel = "Reg tittel",
                opprettetDato = Date(),
                opprettetAv = "Tester",
                arkivertDato = Date(),
                arkivertAv = "Mr. Arkiv",
                referanseForelderMappe = mappe.systemID,
                referanseEksternNoekkel = EksternNoekkel("Faglig", "key"),
                journalposttype = "journalposttype",
                journalstatus = "journalstatus",
                korrespondanseparts = listOf(Korrespondansepart(
                    korrespondansepartType ="korrespondansepartType",
                    korrespondansepartNavn = "korrespondansepartNavn",
                    organisasjonId = "organisasjonId",
                    postadresse = emptyList(),
                    postnummer = "1234",
                    poststed = "poststed",
                    saksbehandler = "saksbehandler",
                    administrativEnhet = "administrativEnhet"
                ))
            )

        val arkivmelding = Arkivmelding("systemA", "meldingsId", Date(), emptyList(), listOf(registrering))

        val sw = StringWriter()
        arkivmelding.marshal(sw)
        val xmlContent = sw.toString()
        println(xmlContent)

        val schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        val schema = schemaFactory.newSchema(File("/home/audun/ks/fiks-arkiv-client-java/fiks-arkiv-api/target/schemas/v1/arkivmelding.xsd"))
        val validator = schema.newValidator()

        shouldNotThrowAny {
            validator.validate(JAXBSource(arkivmelding.jaxbContext(), arkivmelding.JAXBElement()))}
        }
    }
