package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.Klasse


class KlasseKlasseBuilder: KlasseBuilder() {
    var klasser: List<Klasse> = ArrayList()
        private set

    fun klasser(klasser: List<Klasse>) = apply { this.klasser = klasser }

    override fun build(): Klasse {
        return super.build().also {
            it.klasses.addAll(klasser)
        }
    }
}