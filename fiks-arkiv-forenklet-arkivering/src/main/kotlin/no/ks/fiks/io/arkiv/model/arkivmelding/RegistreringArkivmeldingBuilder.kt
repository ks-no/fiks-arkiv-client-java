package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.Arkivmelding

class RegistreringArkivmeldingBuilder : ArkivmeldingBuilder() {
    override fun build(): Arkivmelding =
        super.build().also {
            checkNotNull(registrering) { "Registrering er påkrevd felt for RegistreringArkivmeldingBuilder" }
        }
}
