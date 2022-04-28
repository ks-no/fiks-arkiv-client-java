package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SystemIDBuilder
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Klassifikasjon

class KlassifikasjonBuilder {
    var systemID: SystemIDBuilder? = null
        private set
    var klassifikasjonssystem: String? = null
        private set
    var tittel: String? = null
        private set
    var klasseID: String? = null
        private set
    var skjermetObjekt: String? = null
        private set

    fun systemID(systemID: SystemIDBuilder) = apply { this.systemID = systemID }
    fun klassifikasjonssystem(klassifikasjonssystem: String) = apply { this.klassifikasjonssystem = klassifikasjonssystem }
    fun klasseID(klasseID: String) = apply { this.klasseID = klasseID }
    fun skjermetObjekt(skjermetObjekt: String) = apply { this.skjermetObjekt = skjermetObjekt }
    fun tittel(tittel: String) = apply { this.tittel = tittel }


    fun build() : Klassifikasjon {
        return Klassifikasjon().also {
            it.systemID = systemID?.build()
            it.klassifikasjonssystem = klassifikasjonssystem
            it.tittel = tittel
            it.klasseID = klasseID
            it.skjermetObjekt = skjermetObjekt
        }
    }
}
