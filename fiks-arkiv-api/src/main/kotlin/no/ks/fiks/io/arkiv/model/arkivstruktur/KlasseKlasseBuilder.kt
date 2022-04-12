package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Klasse

class KlasseKlasseBuilder: KlasseBuilder() {
    var klasser: List<KlasseBuilder> = ArrayList()
        private set

    fun klasser(klasser: List<KlasseBuilder>) = apply { this.klasser = klasser }

    override fun build(): Klasse {
        return super.build().also {
            it.klasses.addAll(klasser.map { j -> j.build() }.toList())
        }
    }
}