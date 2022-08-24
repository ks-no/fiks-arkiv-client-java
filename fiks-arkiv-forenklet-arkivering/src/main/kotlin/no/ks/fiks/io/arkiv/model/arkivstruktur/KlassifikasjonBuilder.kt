package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.arkiv.v1.arkivmelding.Klassifikasjon
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SystemIDBuilder

class KlassifikasjonBuilder {
    var klassifikasjonssystem: String? = null
        private set
    var tittel: String? = null
        private set
    var klasseID: String? = null
        private set
    var skjermetObjekt: String? = null
        private set

    fun klassifikasjonssystem(klassifikasjonssystem: String) = apply { this.klassifikasjonssystem = klassifikasjonssystem }
    fun klasseID(klasseID: String) = apply { this.klasseID = klasseID }
    fun skjermetObjekt(skjermetObjekt: String) = apply { this.skjermetObjekt = skjermetObjekt }
    fun tittel(tittel: String) = apply { this.tittel = tittel }


    fun build() : Klassifikasjon {
        return Klassifikasjon().also {
            it.klassifikasjonssystem = checkNotNull(klassifikasjonssystem) {"Klassifikasjonssystem er påkrevd for Klassifikasjon"}
            it.tittel = checkNotNull(tittel) {"Tittel er påkrevd for Klassifikasjon"}
            it.klasseID = klasseID
            it.skjermetObjekt = skjermetObjekt
        }
    }
}
