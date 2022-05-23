package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.io.arkiv.v1.client.models.arkivmelding.Planident

open class PlanidentBuilder {
    var planidentifikasjon: String? = null
        private set

    fun planidentifikasjon(planidentifikasjon: String) = apply { this.planidentifikasjon = planidentifikasjon }

    open fun build() : Planident {
        return Planident().also {
            it.planidentifikasjon = checkNotNull(planidentifikasjon) {"Bygningsnummer er påkrevd for Byggident"}
        }
    }
}
