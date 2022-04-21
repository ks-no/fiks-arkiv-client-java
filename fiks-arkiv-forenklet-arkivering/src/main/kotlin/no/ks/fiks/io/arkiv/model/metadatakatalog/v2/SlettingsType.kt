package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.arkivverket.standarder.noark5.metadatakatalog.v2.Slettingstype


enum class SlettingsType(val value: Slettingstype) {
    SLETTING_AV_PRODUKSJONSFORMAT(Slettingstype().also { it.kode = "SP"; it.beskrivelse = "Sletting av produksjonsformat" }),
    SLETTING_AV_TIDLIGERE_VERSJON(Slettingstype().also { it.kode = "SV"; it.beskrivelse = "Sletting av tidligere versjon" }),
    SLETTING_AV_VARIANT_MED_SLADDET_INFORMASJON(Slettingstype().also { it.kode = "SS"; it.beskrivelse = "Sletting av variant med sladdet informasjon" }),
    SLETTING_AV_HELE_INNHOLDET(Slettingstype().also { it.kode = "SA"; it.beskrivelse = "Sletting av hele innholdet i arkivdelen" })
}