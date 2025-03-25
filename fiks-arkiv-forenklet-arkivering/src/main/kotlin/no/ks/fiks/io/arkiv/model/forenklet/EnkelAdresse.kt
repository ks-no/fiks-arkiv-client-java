package no.ks.fiks.io.arkiv.model.forenklet

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Landkode

class EnkelAdresse {
    var adresselinje1: String? = null
        private set
    var adresselinje2: String? = null
        private set
    var adresselinje3: String? = null
        private set
    var postnr: String? = null
        private set
    var poststed: String? = null
        private set
    var landkode: Landkode? = null
        private set

    fun adresselinje1(adresselinje1: String) = apply { this.adresselinje1 = adresselinje1 }
    fun adresselinje2(adresselinje2: String) = apply { this.adresselinje2 = adresselinje2 }
    fun adresselinje3(adresselinje3: String) = apply { this.adresselinje3 = adresselinje3 }
    fun postnr(postnr: String) = apply { this.postnr = postnr }
    fun poststed(poststed: String) = apply { this.poststed = poststed }
    fun landkode(landkode: Landkode) = apply { this.landkode = landkode }
}
