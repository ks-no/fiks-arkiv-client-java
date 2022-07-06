package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.PresedensStatus


enum class PresedensStatusType(val value: PresedensStatus) {
    GJELDENDE(PresedensStatus().also { it.kode = "G"; it.beskrivelse = "Gjeldende" }),
    FORELDET(PresedensStatus().also { it.kode = "F"; it.beskrivelse = "Foreldet" })
}