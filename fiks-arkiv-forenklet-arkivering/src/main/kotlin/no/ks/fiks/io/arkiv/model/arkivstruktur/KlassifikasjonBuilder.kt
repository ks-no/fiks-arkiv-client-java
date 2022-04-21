package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SystemIDBuilder
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Klassifikasjon
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Klassifikasjonssystem
import java.time.ZonedDateTime

class KlassifikasjonBuilder {
    var systemID: SystemIDBuilder? = null
        private set
    var klassifikasjonssystem: Klassifikasjonssystem? = null
        private set
    var sendtDato: ZonedDateTime? = null
        private set
    var forfallsdato: ZonedDateTime? = null
        private set
    var offentlighetsvurdertDato: ZonedDateTime? = null
        private set
    var antallVedlegg: Long? = null
        private set
    var utlaantDato: ZonedDateTime? = null
        private set
    var utlaantTil: String? = null
        private set

    fun systemID(systemID: SystemIDBuilder) = apply { this.systemID = systemID }

    fun build() : Klassifikasjon {
        return Klassifikasjon().also {
            it.systemID = systemID?.build()
            it.klassifikasjonssystem
        }
    }
}
