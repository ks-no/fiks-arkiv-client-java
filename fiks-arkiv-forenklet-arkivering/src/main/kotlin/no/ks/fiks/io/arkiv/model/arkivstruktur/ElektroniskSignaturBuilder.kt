package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.ElektroniskSignaturSikkerhetsnivaaType
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.ElektroniskSignaturVerifisertType
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.ElektroniskSignatur
import java.time.LocalDate
import java.time.ZonedDateTime

class ElektroniskSignaturBuilder {
    var elektroniskSignaturSikkerhetsnivaa: ElektroniskSignaturSikkerhetsnivaaType? = null
        private set
    var elektroniskSignaturVerifisert: ElektroniskSignaturVerifisertType? = null
        private set
    var verifisertDato: LocalDate? = null
        private set
    var verifisertAv: String? = null
        private set

    fun elektroniskSignaturSikkerhetsnivaa(elektroniskSignaturSikkerhetsnivaa: ElektroniskSignaturSikkerhetsnivaaType) = apply { this.elektroniskSignaturSikkerhetsnivaa = elektroniskSignaturSikkerhetsnivaa }
    fun elektroniskSignaturVerifisert(elektroniskSignaturVerifisert: ElektroniskSignaturVerifisertType) = apply { this.elektroniskSignaturVerifisert = elektroniskSignaturVerifisert }
    fun verifisertDato(verifisertDato: LocalDate) = apply { this.verifisertDato = verifisertDato }
    fun verifisertAv(verifisertAv: String) = apply { this.verifisertAv = verifisertAv }

    fun build(): ElektroniskSignatur {
        return ElektroniskSignatur().also {
            it.elektroniskSignaturSikkerhetsnivaa = elektroniskSignaturSikkerhetsnivaa?.value ?: throw IllegalStateException("ElektroniskSignaturSikkerhetsnivaa er påkrevd for ElektroniskSignatur")
            it.elektroniskSignaturVerifisert = elektroniskSignaturVerifisert?.value ?: throw IllegalStateException("ElektroniskSignaturVerifiser er påkrevd for ElektroniskSignatur")
            it.verifisertDato = checkNotNull(verifisertDato) {"VerifisertDatao er påkrevd for ElektroniskSignatur"}
            it.verifisertAv = checkNotNull(verifisertAv) {"VerifisertAv er påkrevd for ElektroniskSignatur"}
        }
    }
}
