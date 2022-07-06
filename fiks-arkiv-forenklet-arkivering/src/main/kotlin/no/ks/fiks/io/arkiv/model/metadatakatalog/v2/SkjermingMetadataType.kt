package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.SkjermingMetadata


enum class SkjermingMetadataType(val value: SkjermingMetadata) {
    SKJERMING_KLASSE_ID(SkjermingMetadata().also { it.kode = "KID"; it.beskrivelse = "Skjerming klasseID" }),
    SKJERMING_TITTEL_KLASSE(SkjermingMetadata().also { it.kode = "TKL"; it.beskrivelse = "Skjerming tittel klasse" }),
    SKJERMING_TITTEL_MAPPE_UNTATT_FORSTE_LINJE(SkjermingMetadata().also { it.kode = "TM1"; it.beskrivelse = "Skjerming tittel mappe - untatt første linje" }),
    SKJERMING_TITTEL_MAPPE_UTVALGTE_ORD(SkjermingMetadata().also { it.kode = "TMO"; it.beskrivelse = "Skjerming tittel mappe - utvalgte ord" }),
    SKJERMING_NAVN_PART_I_SAK(SkjermingMetadata().also { it.kode = "NPS"; it.beskrivelse = "Skjerming navn part i sak" }),
    SKJERMING_TITTEL_REGISTRERING_UNTATT_FORSTE_LINJE(SkjermingMetadata().also { it.kode = "TR1"; it.beskrivelse = "Skjerming tittel registrering - unntatt første linje" }),
    SKJERMING_TITTEL_REGISTRERING_UTVALGTE_ORD(SkjermingMetadata().also { it.kode = "TRO"; it.beskrivelse = "Skjerming tittel registrering - utvalgte ord" }),
    SKJERMING_NAVN_AVSENDER(SkjermingMetadata().also { it.kode = "NA"; it.beskrivelse = "Skjerming navn avsender" }),
    SKJERMING_NAVN_MOTTAKER(SkjermingMetadata().also { it.kode = "NM"; it.beskrivelse = "Skjerming navn mottaker" }),
    SKJERMING_TITTEL_DOKUMENTBESKRIVELSE(SkjermingMetadata().also { it.kode = "TD"; it.beskrivelse = "Skjerming tittel dokumentbeskrivelse" }),
    SKJERMING_MERKNADSTEKST(SkjermingMetadata().also { it.kode = "MT"; it.beskrivelse = "Skjerming merknadstekst" }),
    MIDLERTIDIG_SKJERMING(SkjermingMetadata().also { it.kode = "M"; it.beskrivelse = "Midlertidig skjerming" }),
}