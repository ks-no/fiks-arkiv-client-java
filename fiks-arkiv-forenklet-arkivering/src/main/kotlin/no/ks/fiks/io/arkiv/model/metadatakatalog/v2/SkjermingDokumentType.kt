package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.SkjermingDokument


enum class SkjermingDokumentType(val value: SkjermingDokument) {
    SKJERMING_AV_HELE_DOKUMENTET(SkjermingDokument().also { it.kode = "H"; it.beskrivelse = "Skjerming av hele dokumentet" }),
    SKJERING_AV_DELER_AV_DOKUMENTET(SkjermingDokument().also { it.kode = "D"; it.beskrivelse = "Skjerming av deler av dkoumentet" })
}