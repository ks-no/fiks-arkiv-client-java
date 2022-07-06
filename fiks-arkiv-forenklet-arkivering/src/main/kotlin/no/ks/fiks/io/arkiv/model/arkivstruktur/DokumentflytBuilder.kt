package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.arkiv.v1.arkivmelding.Dokumentflyt
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.FlytStatusType
import java.time.ZonedDateTime

class DokumentflytBuilder {
    var flytFra: String? = null
        private set
    var flytTil: String? = null
        private set
    var flytMottattDato: ZonedDateTime = ZonedDateTime.now()
        private set
    var flytSendtDato: ZonedDateTime = ZonedDateTime.now()
        private set
    var flytStatus: FlytStatusType? = null
        private set
    var flytMerknad: String? = null
        private set

    fun flytFra(flytFra: String) = apply { this.flytFra = flytFra }
    fun flytTil(flytTil: String) = apply { this.flytTil = flytTil }
    fun flytMottattDato(flytMottattDato: ZonedDateTime) = apply { this.flytMottattDato = flytMottattDato }
    fun flytSendtDato(flytSendtDato: ZonedDateTime) = apply { this.flytSendtDato =  flytSendtDato }
    fun flytStatus(flytStatus: FlytStatusType) = apply { this.flytStatus = flytStatus }
    fun flytMerknad(flytMerknad: String) = apply { this.flytMerknad = flytMerknad }

    fun build(): Dokumentflyt {
        return Dokumentflyt().also {
            it.flytFra = checkNotNull(this.flytFra) {"FlytFra er påkrevd for Dokumentflyt"}
            it.flytTil = checkNotNull(this.flytTil) {"FlytTil er påkrevd for Dokumentflyt"}
            it.flytMottattDato = flytMottattDato
            it.flytSendtDato = flytSendtDato
            it.flytStatus = flytStatus?.value ?: throw IllegalStateException("FlytStatus er påkrevd for Dokumentflyt")
            it.flytMerknad = checkNotNull(this.flytMerknad) {"FlytMerknad er påkrevd for Dokumentflyt"}
        }
    }
}
