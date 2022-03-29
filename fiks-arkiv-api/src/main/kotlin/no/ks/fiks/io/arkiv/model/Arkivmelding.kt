package no.ks.fiks.io.arkiv.model

import no.ks.fiks.io.arkiv.v1.client.models.arkivering.arkivmelding.Arkivmelding
import org.w3c.dom.Node
import org.xml.sax.ContentHandler
import java.io.File
import java.io.OutputStream
import java.io.StringWriter
import java.io.Writer
import java.util.*
import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBElement
import javax.xml.bind.Marshaller
import javax.xml.datatype.DatatypeFactory
import javax.xml.namespace.QName
import javax.xml.stream.XMLEventWriter
import javax.xml.stream.XMLStreamWriter


class Arkivmelding (val system: String, val meldingId: String, val tidspunkt: Date, val mapper: List<Mappe>? = emptyList(), val registrering: List<Registrering<*>>? = emptyList()) {
    fun buildApiModel(): Arkivmelding {
        return Arkivmelding().also {
            it.system = system
            it.meldingId = meldingId
            it.tidspunkt = DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar().also { it.time = tidspunkt })
            it.mappe.addAll( mapper?.map { m -> m.buildApiModel() }?.toList() ?: emptyList() )
            it.registrering.addAll( registrering?.map { m -> m.buildApiModel() }?.toList() ?: emptyList() )
        }
    }

    fun marshal(stringWriter: StringWriter) =
        marshaller(jaxbContext()).marshal(JAXBElement(), stringWriter)

    fun marshal(outputStream: OutputStream) =
        marshaller(jaxbContext()).marshal(JAXBElement(), outputStream)

    fun marshal(file: File) =
        marshaller(jaxbContext()).marshal(JAXBElement(), file)

    fun marshal(writer: Writer) =
        marshaller(jaxbContext()).marshal(JAXBElement(), writer)

    fun marshal(handler: ContentHandler) =
        marshaller(jaxbContext()).marshal(JAXBElement(), handler)

    fun marshal(node: Node) =
        marshaller(jaxbContext()).marshal(JAXBElement(), node)

    fun marshal(writer: XMLStreamWriter) =
        marshaller(jaxbContext()).marshal(JAXBElement(), writer)

    fun marshal(writer: XMLEventWriter) =
        marshaller(jaxbContext()).marshal(JAXBElement(), writer)

    private fun marshaller(context: JAXBContext): Marshaller {
        val marshaller = context.createMarshaller()
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
        return marshaller
    }

    fun jaxbContext(): JAXBContext = JAXBContext.newInstance(Arkivmelding::class.java)

    fun JAXBElement(): JAXBElement<Arkivmelding> {
        return JAXBElement(
            QName("http://www.arkivverket.no/standarder/noark5/arkivmelding/v2", "arkivmelding"),
            Arkivmelding::class.java,
            buildApiModel()
        )
    }
}
