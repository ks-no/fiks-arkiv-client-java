package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.arkivverket.standarder.noark5.metadatakatalog.v2.ElektroniskSignaturSikkerhetsnivaa

enum class ElektroniskSignaturSikkerhetsnivaaType(val value: ElektroniskSignaturSikkerhetsnivaa) {
    SYMETRISK_KRYPTERT(ElektroniskSignaturSikkerhetsnivaa().also { it.kode = "SK"; it.beskrivelse = "Eleketronisk kryptert" }),
    SENDT_MED_VIRKSOMHETSSERTIFIKAT(ElektroniskSignaturSikkerhetsnivaa().also { it.kode = "V"; it.beskrivelse = "Sendt med PKI/virksomhetssertifikat" }),
    SENDT_MED_PERSONSERTIFIKAT_STANDARD(ElektroniskSignaturSikkerhetsnivaa().also { it.kode = "PS"; it.beskrivelse = "Sendt med PKI/personsertifikat, standard" }),
    SENDT_MED_PERSONSERTIFIKAT_HOY(ElektroniskSignaturSikkerhetsnivaa().also { it.kode = "PH"; it.beskrivelse = "Sendt med PKI/personsertifikat, høy" })
}