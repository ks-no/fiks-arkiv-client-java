package no.ks.fiks.io.arkiv.model.forenklet

class KlasseForenklet {
    var klasseID: String? = null
        private set
    var klassifikasjonssystem: String? = null
        private set
    var skjermetKlasse: Boolean? = null
        private set
    var tittel: String? = null
        private set

    fun klasseID(klasseID: String) = apply { this.klasseID = klasseID }
    fun klassifikasjonssystem(klassifikasjonssystem: String) = apply { this.klassifikasjonssystem = klassifikasjonssystem }
    fun skjermetKlasse(skjermetKlasse: Boolean) = apply { this.skjermetKlasse = skjermetKlasse }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
}