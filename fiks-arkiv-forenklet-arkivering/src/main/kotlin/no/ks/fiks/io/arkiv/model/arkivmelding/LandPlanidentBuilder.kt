package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.Planident

open class LandPlanidentBuilder : PlanidentBuilder() {
    var landkode: String? = null
        private set

    fun landkode(landkode: String) = apply { this.landkode = landkode }

    override fun build() : Planident {
        return super.build().also {
            it.landkode = landkode
        }
    }
}
