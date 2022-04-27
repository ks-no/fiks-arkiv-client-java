package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Klasse

class ArkivNotatKlasseBuilder: KlasseBuilder() {
    var arkivnotater: List<ArkivnotatBuilder> = ArrayList()
        private set

    fun arkivnotater(arkivnotater: List<ArkivnotatBuilder>) = apply { this.arkivnotater = arkivnotater }

    override fun build(): Klasse {
        return super.build().also {
            it.registrerings.addAll(arkivnotater.map { j -> j.build() }.toList())
        }
    }
}