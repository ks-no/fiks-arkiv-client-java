package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.Planident

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
