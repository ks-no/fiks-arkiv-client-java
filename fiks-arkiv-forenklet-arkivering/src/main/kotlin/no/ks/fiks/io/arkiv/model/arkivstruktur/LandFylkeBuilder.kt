package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.v1.client.models.arkivmelding.Planident

open class LandFylkeBuilder : PlanidentBuilder() {
    var landkode: String? = null
        private set

    fun landkode(landkode: String) = apply { this.landkode = landkode }

    override fun build() : Planident {
        return super.build().also {
            it.landkode = landkode
        }
    }
}
