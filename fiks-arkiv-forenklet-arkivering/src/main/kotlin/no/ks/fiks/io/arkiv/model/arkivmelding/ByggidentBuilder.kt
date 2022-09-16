package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.Byggident


class ByggidentBuilder {
    var bygningsnummer: Int? = null
        private set
    var endringsloepenummer: Int? = null
        private set

    fun bygningsnummer(bygningsnummer: Int) = apply { this.bygningsnummer = bygningsnummer }
    fun endringsloepenummer(endringsloepenummer: Int) = apply { this.endringsloepenummer = endringsloepenummer }

    fun build() : Byggident {
        return Byggident().also {
            it.bygningsnummer = bygningsnummer ?: throw IllegalStateException("Bygningsnummer er påkrevd for Byggident")
            it.endringsloepenummer = endringsloepenummer ?: throw IllegalStateException("Endringsloepenummer er påkrevd for Byggident")
        }
    }
}
