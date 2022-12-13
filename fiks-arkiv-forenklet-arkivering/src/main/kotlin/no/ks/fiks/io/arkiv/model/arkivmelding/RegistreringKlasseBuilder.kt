package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.Klasse
import no.ks.fiks.arkiv.v1.arkivmelding.Registrering

class RegistreringKlasseBuilder: KlasseBuilder() {
    var registreringer: List<IRegistrering<out Registrering>> = ArrayList()
        private set

    fun arkivnotater(arkivnotater: List<IRegistrering<out Registrering>>) = apply { this.registreringer = arkivnotater }

    override fun build(): Klasse {
        return super.build().also {
            it.registrerings.addAll(registreringer.map { j -> j.build() }.toList())
        }
    }
}