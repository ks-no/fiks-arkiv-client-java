package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.Arkivmelding

class MappeArkivmeldingBuilder : ArkivmeldingBuilder() {
    override fun build(): Arkivmelding =
        super.build().also {
            checkNotNull(mappe) { "Mappe er påkrevd felt for MappeArkivmeldingBuilder" }
        }
}
