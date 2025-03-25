package no.ks.fiks.io.arkiv.model.forenklet

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Landkode

class Enhetsidentifikator {
    var organisasjonsnummer: String? = null
        private set
    var landkode: Landkode? = null
        private set

    fun organisasjonsnummer(organisasjonsnummer: String) = apply { this.organisasjonsnummer = organisasjonsnummer }
    fun landkode(landkode: Landkode) = apply { this.landkode = landkode }
}
