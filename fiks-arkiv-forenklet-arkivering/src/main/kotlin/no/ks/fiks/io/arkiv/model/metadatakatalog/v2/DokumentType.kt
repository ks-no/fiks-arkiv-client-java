package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Dokumenttype


enum class DokumentType(val value: Dokumenttype) {
    SØKNAD(Dokumenttype().also { it.kode = "SØKNAD"; it.beskrivelse = "Søknad" }),
    MELDING(Dokumenttype().also { it.kode = "MELDING"; it.beskrivelse = "Melding" }),
    KORRESPONDANSE(Dokumenttype().also { it.kode = "KORR"; it.beskrivelse = "Faktura" }),
    KART(Dokumenttype().also { it.kode = "KART"; it.beskrivelse = "Kart" }),
    FOTO(Dokumenttype().also { it.kode = "FOTO"; it.beskrivelse = "Foto" }),
    TEGNING(Dokumenttype().also { it.kode = "TEGNING"; it.beskrivelse = "Tegning" }),
    ANSVAR_OG_KONTROLL(Dokumenttype().also { it.kode = "ANSVKONT"; it.beskrivelse = "Ansvar og kontroll" }),
    TILSYN(Dokumenttype().also { it.kode = "TILSYN"; it.beskrivelse = "Tilsyn" }),
    AVTALE(Dokumenttype().also { it.kode = "AVTALE"; it.beskrivelse = "Avtale" }),
    VEDTAK(Dokumenttype().also { it.kode = "VEDTAK"; it.beskrivelse = "Vedtak" })
}