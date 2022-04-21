package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.arkivverket.standarder.noark5.metadatakatalog.v2.Dokumentstatus


enum class DokumentStatus(val value: Dokumentstatus) {
    DOKUMENTET_ER_UNDER_REDIGERING(Dokumentstatus().also { it.kode = "B"; it.beskrivelse = "Dokumentet er under redigering" }),
    DOKUMENTET_ER_FERDIGSTILT(Dokumentstatus().also { it.kode = "F"; it.beskrivelse = "Dokumentet er ferdigstilt" })
}