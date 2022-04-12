package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.arkivverket.standarder.noark5.metadatakatalog.v2.ElektroniskSignaturVerifisert

enum class ElektroniskSignaturVerifisertType(val value: ElektroniskSignaturVerifisert) {
    SIGNATUR_PAFORT_IKKE_VERIFISERT(ElektroniskSignaturVerifisert().also { it.kode = "I"; it.beskrivelse = "Signatur påført, ikke verifisert" }),
    SIGNATUR_PAFORT_OG_VERIFISERT(ElektroniskSignaturVerifisert().also { it.kode = "V"; it.beskrivelse = "Signatur påført og verifisert" })
}