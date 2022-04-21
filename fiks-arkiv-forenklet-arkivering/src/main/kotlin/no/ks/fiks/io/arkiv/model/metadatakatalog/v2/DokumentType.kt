package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.arkivverket.standarder.noark5.metadatakatalog.v2.Dokumenttype


enum class DokumentType(val value: Dokumenttype) {
    BREV(Dokumenttype().also { it.kode = "B"; it.beskrivelse = "Brev" }),
    RUNDSKRIV(Dokumenttype().also { it.kode = "R"; it.beskrivelse = "Rundskriv" }),
    FAKTURA(Dokumenttype().also { it.kode = "F"; it.beskrivelse = "Faktura" }),
    ORDREBEKREFTELSE(Dokumenttype().also { it.kode = "O"; it.beskrivelse = "Ordrebekreftelse" })
}