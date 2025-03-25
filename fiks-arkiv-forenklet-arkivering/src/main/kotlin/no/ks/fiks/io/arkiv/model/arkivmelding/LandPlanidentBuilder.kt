package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.Planident
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Landkode

open class LandPlanidentBuilder : PlanidentBuilder() {
    var landkode: Landkode? = null
        private set

    fun landkode(landkode: Landkode) = apply { this.landkode = landkode }

    override fun build() : Planident {
        return super.build().also {
            it.landkode = landkode
        }
    }
}
