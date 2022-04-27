package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.v1.client.models.arkivmelding.Planident

open class PlanidentKommuneBuilder : PlanidentBuilder() {
    var kommunenummer: String? = null
        private set

    fun kommunenummer(kommunenummer: String) = apply { this.kommunenummer = kommunenummer }

    override fun build() : Planident {
        return super.build().also {
            it.kommunenummer = kommunenummer
        }
    }
}
