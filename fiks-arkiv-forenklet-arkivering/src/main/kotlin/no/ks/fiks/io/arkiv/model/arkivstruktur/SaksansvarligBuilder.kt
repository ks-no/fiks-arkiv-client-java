package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Saksansvarlig


open class SaksansvarligBuilder : IdentifikatorerBuilder() {

    fun build() : Saksansvarlig {
        return Saksansvarlig().also {
            it.epostadresse = epostadresse
            it.identifikator = identifikator
            it.initialer = initialer
            it.navn = navn
        }
    }
}
