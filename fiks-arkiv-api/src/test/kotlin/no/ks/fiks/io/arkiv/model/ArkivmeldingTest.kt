package no.ks.fiks.io.arkiv.model


import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldNotThrowAny
import no.arkivverket.standarder.noark5.metadatakatalog.v2.Journalposttype
import no.arkivverket.standarder.noark5.metadatakatalog.v2.Journalstatus
import no.arkivverket.standarder.noark5.metadatakatalog.v2.Korrespondanseparttype
import no.arkivverket.standarder.noark5.metadatakatalog.v2.SystemID
import no.ks.fiks.io.arkiv.v1.client.models.arkivmelding.Arkivmelding
import no.ks.fiks.io.arkiv.v1.client.models.arkivmelding.Journalpost
import no.ks.fiks.io.arkiv.v1.client.models.arkivmelding.Korrespondansepart
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.EksternNoekkel
import org.junit.jupiter.api.Test
import java.io.File
import java.io.StringWriter
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.*
import javax.xml.XMLConstants
import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBElement
import javax.xml.bind.Marshaller
import javax.xml.bind.util.JAXBSource
import javax.xml.namespace.QName
import javax.xml.validation.SchemaFactory

class ArkivmeldingTest {

    @Test
    fun `Test autogenerert modell mot xsd`() {
        val journalDate = LocalDate.now()
        val arkivertDato = ZonedDateTime.now()

        val arkivmelding = Arkivmelding().also {
            it.registrerings.addAll(listOf(Journalpost().also {
                it.journaldato = journalDate
                it.journalpostnummer = 5000.toBigInteger()
                it.journalsekvensnummer = 1234.toBigInteger()
                it.journalaar = 2022.toBigInteger()
                it.systemID = SystemID().also {
                    it.label = "JournalId"
                    it.value = UUID.randomUUID().toString()
                }
                it.tittel = "Tittel"
                it.opprettetDato = ZonedDateTime.now()
                it.opprettetAv = "Ole"
                it.arkivertAv = "Kari"
                it.arkivertDato = arkivertDato
                it.referanseForelderMappe = SystemID().also {
                    it.label = "ReferanseMappe"
                    it.value = UUID.randomUUID().toString()
                }
                it.referanseEksternNoekkel = EksternNoekkel().also {
                    it.noekkel = "Nøkkel"
                    it.fagsystem = "Fagsystem"
                }
                it.korrespondanseparts.addAll(listOf(Korrespondansepart().also {
                    it.korrespondanseparttype = Korrespondanseparttype().also { it.kode = "M"; it.beskrivelse = "Mottaker" }
                    it.korrespondansepartNavn = "Navn"
                    it.postadresses.addAll(listOf("Gate 1"))
                    it.postnummer = "1234"
                    it.poststed = "Poststed"
                    it.saksbehandler = "Saksbehandler"
                    it.administrativEnhet = "administrasjonsenhet"
                }))
                it.journalposttype = Journalposttype().also {
                    it.kode = "T"
                    it.beskrivelse = "Beskrivelse"
                }
                it.journalstatus = Journalstatus().also {
                    it.kode = "S"
                    it.beskrivelse = "Beskrivelse"
                }
            }))
            it.system = "System"
            it.meldingId = "MeldingsId"
            it.tidspunkt = ZonedDateTime.now()
            it.antallFiler = 2
        }

        val jaxbContext = JAXBContext.newInstance(Arkivmelding::class.java)
        val schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        val schema = schemaFactory.newSchema(File("target/schemas/v1/arkivmelding.xsd"))
        val validator = schema.newValidator()

        val element = JAXBElement(
            QName("http://www.arkivverket.no/standarder/noark5/arkivmelding/v2", "arkivmelding"),
            Arkivmelding::class.java,
            arkivmelding)

        shouldNotThrowAny {
            validator.validate(JAXBSource(jaxbContext, element))}

        val sw = StringWriter()
        val marshaller = jaxbContext.createMarshaller().also { it.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true) }
        marshaller.marshal(element, sw)

        val unmarshaller = jaxbContext.createUnmarshaller()
        val parsedArkivmelding: Arkivmelding = unmarshaller.unmarshal(sw.toString().byteInputStream()) as Arkivmelding

        (parsedArkivmelding.registrerings[0] as Journalpost).journaldato.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE) shouldBe journalDate.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE)
        (parsedArkivmelding.registrerings[0] as Journalpost).arkivertDato.format(java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME) shouldBe arkivertDato.format(java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    }
}