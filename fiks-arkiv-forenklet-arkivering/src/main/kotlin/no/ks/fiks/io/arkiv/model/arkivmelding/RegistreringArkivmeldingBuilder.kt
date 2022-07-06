package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.Arkivmelding

class RegistreringArkivmeldingBuilder: ArkivmeldingBuilder() {

    var registrering: List<IRegistrering>? = emptyList()
        private set

    fun registrering(registrering: List<IRegistrering>) = apply { this.registrering = registrering }

    override fun build(): Arkivmelding {
        return super.build().also {
            it.registrerings.addAll( registrering?.map { m -> m.build() }?.toList() ?: emptyList() )
        }
    }

}