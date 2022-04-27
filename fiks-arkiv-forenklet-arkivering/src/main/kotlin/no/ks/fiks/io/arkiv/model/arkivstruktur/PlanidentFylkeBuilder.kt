package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.v1.client.models.arkivmelding.Planident

open class PlanidentFylkeBuilder : PlanidentBuilder() {
    var fylkesnummer: String? = null
        private set

    fun fylkesnummer(fylkesnummer: String) = apply { this.fylkesnummer = fylkesnummer }

    override fun build() : Planident {
        return super.build().also {
            it.fylkesnummer = fylkesnummer
        }
    }
}
