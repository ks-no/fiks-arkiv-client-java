package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.Arkivmelding

class RegistreringArkivmeldingBuilder: ArkivmeldingBuilder() {

    lateinit var registrering: IRegistrering
        private set

    fun registrering(registrering: IRegistrering) = apply { this.registrering = registrering }

    override fun build(): Arkivmelding {
        return super.build().also {
            it.registrering = this.registrering.build()
        }
    }

}