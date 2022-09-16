package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Saksbehandler


open class SaksbehandlerBuilder : IdentifikatorerBuilder() {

    fun build() : Saksbehandler {
        return Saksbehandler().also {
            it.kode = kode?.build()
            it.epostadresse = epostadresse
            it.identifikator = identifikator
            it.initialer = initialer
            it.navn = navn
        }
    }
}
