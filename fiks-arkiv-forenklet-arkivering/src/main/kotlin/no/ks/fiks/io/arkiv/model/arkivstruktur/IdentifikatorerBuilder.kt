package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Kode

abstract class IdentifikatorerBuilder (
    var navn: String? = null,
    var identifikator: String? = null,
    var initialer: String? = null,
    var epostadresse: String? = null) {

    fun navn(navn: String) = apply { this.navn = navn }
    fun identifikator(identifikator: String) = apply { this.identifikator = identifikator }
    fun initialer(initialer: String) = apply { this.initialer = initialer }
    fun epostadresse(epostadresse: String) = apply { this.epostadresse = epostadresse }

}
