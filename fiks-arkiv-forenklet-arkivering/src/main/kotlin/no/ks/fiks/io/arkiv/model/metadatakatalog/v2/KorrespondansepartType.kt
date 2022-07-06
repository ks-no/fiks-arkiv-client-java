package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Korrespondanseparttype


enum class KorrespondansepartType(val value: Korrespondanseparttype) {
    AVSENDER(Korrespondanseparttype().also { it.kode = "EA"; it.beskrivelse = "Avsender" }),
    MOTTAKER(Korrespondanseparttype().also { it.kode = "EM"; it.beskrivelse = "Mottaker" }),
    KOPIMOTTAKER(Korrespondanseparttype().also { it.kode = "EK"; it.beskrivelse = "Kopimottaker" }),
    GRUPPEMOTTAKER(Korrespondanseparttype().also { it.kode = "GM"; it.beskrivelse = "Gruppemottaker" }),
    INTER_AVSENDER(Korrespondanseparttype().also { it.kode = "IA"; it.beskrivelse = "Intern avsender" }),
    INTERN_MOTTAKER(Korrespondanseparttype().also { it.kode = "IM"; it.beskrivelse = "Intern mottaker" }),
    INTERN_KOPIMOTTAKER(Korrespondanseparttype().also { it.kode = "IK"; it.beskrivelse = "Intern kopimottaker" })
}