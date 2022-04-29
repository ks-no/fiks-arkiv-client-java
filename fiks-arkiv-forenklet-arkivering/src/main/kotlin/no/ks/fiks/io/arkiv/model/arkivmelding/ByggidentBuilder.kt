package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.io.arkiv.v1.client.models.arkivmelding.Byggident

class ByggidentBuilder {
    var bygningsnummer: Long? = null
        private set
    var endringsloepenummer: Long? = null
        private set

    fun bygningsnummer(bygningsnummer: Long) = apply { this.bygningsnummer = bygningsnummer }
    fun endringsloepenummer(endringsloepenummer: Long) = apply { this.endringsloepenummer = endringsloepenummer }

    fun build() : Byggident {
        return Byggident().also {
            it.bygningsnummer = bygningsnummer?.toBigInteger() ?: throw IllegalStateException("Bygningsnummer er påkrevd for Byggident")
            it.endringsloepenummer = endringsloepenummer?.toBigInteger() ?: throw IllegalStateException("Endringsloepenummer er påkrevd for Byggident")
        }
    }
}
