package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.AdministrativEnhet

open class AdministrativEnhetBuilder : IdentifikatorerBuilder() {

    fun build() : AdministrativEnhet {
        return AdministrativEnhet().also {
            it.kode = kode?.build()
            it.epostadresse = epostadresse
            it.identifikator = identifikator
            it.initialer = initialer
            it.navn = navn
        }
    }
}
