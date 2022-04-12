package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.arkivverket.standarder.noark5.metadatakatalog.v2.PresedensStatus


enum class PresedensStatusType(val value: PresedensStatus) {
    GJELDENDE(PresedensStatus().also { it.kode = "G"; it.beskrivelse = "Gjeldende" }),
    FORELDET(PresedensStatus().also { it.kode = "F"; it.beskrivelse = "Foreldet" })
}