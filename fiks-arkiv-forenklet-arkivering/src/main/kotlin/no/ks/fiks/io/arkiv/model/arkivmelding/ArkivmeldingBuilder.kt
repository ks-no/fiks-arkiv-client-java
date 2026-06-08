package no.ks.fiks.io.arkiv.model.arkivmelding

import jakarta.xml.bind.JAXBContext
import jakarta.xml.bind.JAXBElement
import jakarta.xml.bind.Marshaller
import no.ks.fiks.arkiv.v1.arkivmelding.opprett.Arkivmelding
import no.ks.fiks.arkiv.v1.arkivmelding.opprett.Mappe
import org.w3c.dom.Node
import org.xml.sax.ContentHandler
import java.io.File
import java.io.OutputStream
import java.io.StringWriter
import java.io.Writer
import javax.xml.namespace.QName
import javax.xml.stream.XMLEventWriter
import javax.xml.stream.XMLStreamWriter


open class ArkivmeldingBuilder {
    var system: String? = null
        private set
    var antallFiler: Int? = null
        private set
    var regel: String? = null
        private set
    var mappe: Mappe? = null
        private set
    var registrering: IRegistrering? = null
        private set

    open fun build(): Arkivmelding {
        return Arkivmelding().also {
            it.system = checkNotNull(system) {"System er påkrevd felt for Arkivmelding"}
            it.antallFiler = checkNotNull(antallFiler) {"Antall filer er påkrevd felt for Arkivmelding"}
            it.regel = regel
            it.mappe = mappe
            it.registrering = registrering?.build()
        }
    }

    fun system(system: String) = apply { this.system = system }

    fun antallFiler(antallFiler: Int) = apply { this.antallFiler = antallFiler }

    fun regel(regel: String) = apply { this.regel = regel }

    fun mappe(mappe: Mappe) = apply { this.mappe = mappe }

    fun registrering(registrering: IRegistrering) = apply { this.registrering = registrering }

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
            QName("https://ks-no.github.io/standarder/fiks-protokoll/fiks-arkiv/arkivmelding/opprett/v1", "arkivmelding"),
            Arkivmelding::class.java,
            build()
        )
    }
}
