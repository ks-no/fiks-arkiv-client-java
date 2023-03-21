package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.Klasse

class RegistreringKlasseBuilder: KlasseBuilder() {
    var registreringer: List<IRegistrering> = ArrayList()
        private set

    fun arkivnotater(arkivnotater: List<IRegistrering>) = apply { this.registreringer = arkivnotater }

    override fun build(): Klasse {
        return super.build().also {
            it.registrerings.addAll(registreringer.map { j -> j.build() }.toList())
        }
    }
}