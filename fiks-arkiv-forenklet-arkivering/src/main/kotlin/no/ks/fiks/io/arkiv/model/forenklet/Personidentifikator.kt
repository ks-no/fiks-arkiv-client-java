package no.ks.fiks.io.arkiv.model.forenklet

class Personidentifikator {
    var personidentifikatorNr: String? = null
        private set
    var personidentifikatorLandkode: String? = null
        private set

    fun personidentifikatorNr(personidentifikatorNr: String) = apply { this.personidentifikatorNr = personidentifikatorNr }
    fun personidentifikatorLandkode(personidentifikatorLandkode: String) = apply { this.personidentifikatorLandkode = personidentifikatorLandkode }
}
