package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.KodeBuilder

abstract class IdentifikatorerBuilder (
    var kode: KodeBuilder? = null,
    var navn: String? = null,
    var identifikator: String? = null,
    var initialer: String? = null,
    var epostadresse: String? = null) {

    fun kode(kode: KodeBuilder) = apply { this.kode = kode }
    fun navn(navn: String) = apply { this.navn = navn }
    fun identifikator(identifikator: String) = apply { this.identifikator = identifikator }
    fun initialer(initialer: String) = apply { this.initialer = initialer }
    fun epostadresse(epostadresse: String) = apply { this.epostadresse = epostadresse }

}
