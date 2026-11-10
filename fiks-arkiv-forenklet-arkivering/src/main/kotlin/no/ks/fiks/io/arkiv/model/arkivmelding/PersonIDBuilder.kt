package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.PersonID


class PersonIDBuilder {
    var identifikator: String? = null
        private set
    var landkode: String? = null
        private set

    fun identifikator(identifikator: String) = apply { this.identifikator = identifikator }
    fun landkode(landkode: String) = apply { this.landkode = landkode }

    fun buildApiModel(): PersonID {
        return PersonID().also {
            it.identifikator = checkNotNull(identifikator) { "Identifikator er påkrevd for PersonID" }
            it.landkode = landkode
        }
    }
}

