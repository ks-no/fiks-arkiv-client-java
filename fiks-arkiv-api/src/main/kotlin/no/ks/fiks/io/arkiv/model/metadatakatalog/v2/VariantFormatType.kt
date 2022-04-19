package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.arkivverket.standarder.noark5.metadatakatalog.v2.Variantformat


enum class VariantFormatType(val value: Variantformat) {
    PRODUKSJONSFORMAT(Variantformat().also { it.kode = "P"; it.beskrivelse = "Produksjonsformat" }),
    ARKIVFORMAT(Variantformat().also { it.kode = "A"; it.beskrivelse = "Arkivformat" }),
    DOKUMENT_HVOR_DELER_AV_INNHOLDET_ER_SKJERMET(Variantformat().also { it.kode = "O"; it.beskrivelse = "Dokument hvor deler av innholdet er skjermet" })
}