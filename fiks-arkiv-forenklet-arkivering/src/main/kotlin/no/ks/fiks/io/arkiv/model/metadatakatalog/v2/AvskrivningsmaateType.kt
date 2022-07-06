package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Avskrivningsmaate


enum class AvskrivningsmaateType(val value: Avskrivningsmaate) {
    BESVART_MED_BREV(Avskrivningsmaate().also { it.kode = "BU"; it.beskrivelse = "Besvart med brev" }),
    BESVART_MED_EPOST(Avskrivningsmaate().also { it.kode = "BE"; it.beskrivelse = "Besvart med e-post" }),
    BESVART_PA_TELEFON(Avskrivningsmaate().also { it.kode = "TLF"; it.beskrivelse = "Besvart på telefon" }),
    TATT_TIL_ETTERRETNING(Avskrivningsmaate().also { it.kode = "TE"; it.beskrivelse = "Tatt til etterretning" }),
    TATT_TIL_ORIENTERING(Avskrivningsmaate().also { it.kode = "TO"; it.beskrivelse = "Tatt til orientering" })
}