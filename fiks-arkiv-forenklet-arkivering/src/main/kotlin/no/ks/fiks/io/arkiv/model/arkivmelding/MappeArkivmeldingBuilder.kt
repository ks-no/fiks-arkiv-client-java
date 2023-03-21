package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.Arkivmelding
import no.ks.fiks.arkiv.v1.arkivmelding.opprett.Mappe

class MappeArkivmeldingBuilder: ArkivmeldingBuilder() {

    var mapper: List<Mappe> = emptyList()
        private set

    fun mapper(mapper: List<Mappe>) = apply { this.mapper = mapper }

    override fun build(): Arkivmelding {
        return super.build().also {
            it.mappes.addAll(mapper)
        }
    }

}