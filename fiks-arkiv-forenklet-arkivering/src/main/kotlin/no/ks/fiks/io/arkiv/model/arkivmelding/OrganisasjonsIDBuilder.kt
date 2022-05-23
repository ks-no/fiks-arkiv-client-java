package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.io.arkiv.v1.client.models.arkivmelding.OrganisasjonsID

class OrganisasjonsIDBuilder {
    var identifikator: String? = null
        private set
    var landkode: String? = null
        private set

    fun identifikator(identifikator: String) = apply { this.identifikator = identifikator }
    fun landkode(label: String) = apply { this.landkode = landkode }

    fun buildApiModel(): OrganisasjonsID {
        return OrganisasjonsID().also {
            it.identifikator = checkNotNull(identifikator) { "Identifikator er påkrevd for OrganisasjonsID" }
            it.landkode = landkode
        }
    }
}

