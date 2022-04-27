package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.io.arkiv.model.arkivstruktur.IRegistrering

class RegistreringArkivmeldingBuilder: ArkivmeldingBuilder() {

    var registrering: List<IRegistrering>? = emptyList()
        private set

    fun registrering(registrering: List<IRegistrering>) = apply { this.registrering = registrering }

    override fun build(): no.ks.fiks.io.arkiv.v1.client.models.arkivmelding.Arkivmelding {
        return super.build().also {
            it.registrerings.addAll( registrering?.map { m -> m.buildApiModel() }?.toList() ?: emptyList() )
        }
    }

}