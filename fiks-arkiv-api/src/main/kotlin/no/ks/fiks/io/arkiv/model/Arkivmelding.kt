package no.ks.fiks.io.arkiv.model

import no.ks.fiks.io.arkiv.model.arkivstruktur.MappeBuilder
import no.ks.fiks.io.arkiv.v1.client.models.arkivmelding.Arkivmelding
import org.w3c.dom.Node
import org.xml.sax.ContentHandler
import java.io.File
import java.io.OutputStream
import java.io.StringWriter
import java.io.Writer
import java.time.ZonedDateTime
import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBElement
import javax.xml.bind.Marshaller
import javax.xml.namespace.QName
import javax.xml.stream.XMLEventWriter
import javax.xml.stream.XMLStreamWriter


class Arkivmelding {
    var system: String? = null
        private set
    var meldingId: String? = null
        private set
    var antallFiler: Int? = null
        private set
    var tidspunkt: ZonedDateTime = ZonedDateTime.now()
        private set
    var mapper: List<MappeBuilder>? = emptyList()
        private set
    var registrering: List<IRegistrering>? = emptyList()
        private set

    fun build(): Arkivmelding {
        return Arkivmelding().also {
            it.system = checkNotNull(system) {"System er påkrevd felt for Arkivmelding"}
            it.meldingId = checkNotNull(meldingId) {"MeldingId er påkrevd felt for Arkivmelding"}
            it.tidspunkt = tidspunkt
            it.antallFiler = if(mapper?.isEmpty() == false) 0 else checkNotNull(antallFiler) {"Antall er påkrevd felt for Arkivmelding"}
            it.mappes.addAll( mapper?.map { m -> m.build() }?.toList() ?: emptyList() )
            it.registrerings.addAll( registrering?.map { m -> m.buildApiModel() }?.toList() ?: emptyList() )
        }
    }

    fun system(system: String) = apply { this.system = system }
    fun meldingId(meldingId: String) = apply { this.meldingId = meldingId }
    fun tidspunkt(tidspunkt: ZonedDateTime) = apply { this.tidspunkt = tidspunkt }
    fun antallFiler(antallFiler: Int) = apply { this.antallFiler = antallFiler }
    fun mapper(mapper: List<MappeBuilder>) = apply { if (this.registrering?.isEmpty() == true || mapper.isEmpty() ) this.mapper = mapper else throw IllegalArgumentException("Arkivmelding kan enten inneholde Mappe(r) eller Registreringe(r), men ikke begge.") }
    fun registrering(registrering: List<IRegistrering>) = apply { if (this.mapper?.isEmpty() == true || registrering.isEmpty()) this.registrering = registrering else throw IllegalArgumentException("Arkivmelding kan enten inneholde Mappe(r) eller Registreringe(r), men ikke begge.") }

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
            build()
        )
    }
}
