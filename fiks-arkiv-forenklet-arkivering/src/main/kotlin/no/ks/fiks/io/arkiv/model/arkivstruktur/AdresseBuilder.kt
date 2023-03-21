package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.Adresse

class AdresseBuilder {
    var adresse: String? = null
        private set
    var postnummer: String? = null
        private set
    var poststed: String? = null
        private set

    fun adresse(adresse: String) = apply { this.adresse = adresse }
    fun postnummer(postnummer: String) = apply { this.postnummer = postnummer }
    fun poststed(poststed: String) = apply { this.poststed = poststed }

    fun build() : Adresse {
        return Adresse().also {
            it.adresse = checkNotNull(adresse) { "Adresse er påkrevd felt for Adresse"}
            it.postnummer = checkNotNull(postnummer) {"Postnummer er påkrevd for Adresse"}
            it.poststed = checkNotNull(poststed) {"Poststed er påkrevd for Adresse"}
        }
    }
}
