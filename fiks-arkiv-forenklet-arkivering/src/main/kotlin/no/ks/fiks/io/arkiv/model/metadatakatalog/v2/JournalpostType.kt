package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Journalposttype

enum class JournalpostType(val value: Journalposttype) {
    INNGAENDE_DOKUMENT(Journalposttype().also { it.kode = "I"; it.beskrivelse = "Inngående dokument" }),
    UTGAENDE_DOKUMENT(Journalposttype().also { it.kode = "U"; it.beskrivelse = "Utgående dokument" }),
    ORGANINTERT_DOKUMENT_FOR_OPPFOLGING(Journalposttype().also { it.kode = "N"; it.beskrivelse = "Organinternt dokument for oppfølging" }),
    ORGANINTERT_DOKUMENT_UTEN_OFFOLGING(Journalposttype().also { it.kode = "X"; it.beskrivelse = "Organinternt dokument uten oppfølging" }),
    SAKSFRAMLEGG(Journalposttype().also { it.kode = "S"; it.beskrivelse = "Saksframlegg" })
}