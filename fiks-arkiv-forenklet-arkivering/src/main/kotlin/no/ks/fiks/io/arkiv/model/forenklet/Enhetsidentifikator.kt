package no.ks.fiks.io.arkiv.model.forenklet

class Enhetsidentifikator {
    var organisasjonsnummer: String? = null
        private set
    var landkode: String? = null
        private set

    fun organisasjonsnummer(organisasjonsnummer: String) = apply { this.organisasjonsnummer = organisasjonsnummer }
    fun landkode(landkode: String) = apply { this.landkode = landkode }
}
