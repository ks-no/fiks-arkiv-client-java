package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.Arkivmelding
import no.ks.fiks.arkiv.v1.arkivmelding.opprett.Mappe

class MappeArkivmeldingBuilder: ArkivmeldingBuilder() {

    var mappe: Mappe = Mappe()
        private set

    fun mappe(mappe: Mappe) = apply { this.mappe = mappe }

    override fun build(): Arkivmelding {
        return super.build().also {
            it.mappe = mappe
        }
    }

}