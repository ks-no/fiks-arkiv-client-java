package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Dokumentmedium


enum class DokumentmediumType(val value: Dokumentmedium) {
    FYSISK_MEDIUM(Dokumentmedium().also { it.kode = "F"; it.beskrivelse = "Fysisk medium" }),
    ELEKTRONISK_ARKIV(Dokumentmedium().also { it.kode = "E"; it.beskrivelse = "Elektronisk arkiv" }),
    BLANDET_FYSISK_OG_ELEKTRONISK_ARKIV(Dokumentmedium().also { it.kode = "B"; it.beskrivelse = "Blandet fysisk og elektronisk arkiv" })
}