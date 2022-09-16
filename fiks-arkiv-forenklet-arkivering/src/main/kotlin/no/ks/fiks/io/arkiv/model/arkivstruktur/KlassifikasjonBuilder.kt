package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.arkiv.v1.arkivmelding.Klassifikasjon

class KlassifikasjonBuilder {
    var klassifikasjonssystemID: String? = null
        private set
    var tittel: String? = null
        private set
    var klasseID: String? = null
        private set
    var skjermetObjekt: String? = null
        private set

    fun klassifikasjonssystemID(klassifikasjonssystemID: String) = apply { this.klassifikasjonssystemID = klassifikasjonssystemID }
    fun klasseID(klasseID: String) = apply { this.klasseID = klasseID }
    fun skjermetObjekt(skjermetObjekt: String) = apply { this.skjermetObjekt = skjermetObjekt }
    fun tittel(tittel: String) = apply { this.tittel = tittel }


    fun build() : Klassifikasjon {
        return Klassifikasjon().also {
            it.klassifikasjonssystemID = checkNotNull(klassifikasjonssystemID) {"Klassifikasjonssystem er påkrevd for Klassifikasjon"}
            it.tittel = checkNotNull(tittel) {"Tittel er påkrevd for Klassifikasjon"}
            it.klasseID = klasseID
            it.skjermetObjekt = skjermetObjekt
        }
    }
}
