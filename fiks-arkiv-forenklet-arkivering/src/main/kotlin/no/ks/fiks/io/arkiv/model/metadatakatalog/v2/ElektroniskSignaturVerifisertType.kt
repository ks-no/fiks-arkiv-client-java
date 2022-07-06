package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.ElektroniskSignaturVerifisert

enum class ElektroniskSignaturVerifisertType(val value: ElektroniskSignaturVerifisert) {
    SIGNATUR_PAFORT_IKKE_VERIFISERT(ElektroniskSignaturVerifisert().also { it.kode = "I"; it.beskrivelse = "Signatur påført, ikke verifisert" }),
    SIGNATUR_PAFORT_OG_VERIFISERT(ElektroniskSignaturVerifisert().also { it.kode = "V"; it.beskrivelse = "Signatur påført og verifisert" })
}