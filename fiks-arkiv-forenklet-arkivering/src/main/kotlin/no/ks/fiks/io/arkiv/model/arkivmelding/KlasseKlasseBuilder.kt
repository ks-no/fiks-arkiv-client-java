package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.Klasse


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