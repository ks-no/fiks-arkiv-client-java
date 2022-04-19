package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.arkivverket.standarder.noark5.metadatakatalog.v2.Merknadstype

enum class MerknadsType(val value: Merknadstype) {
    MERKNAD_FRA_SAKSBEHANDLER(Merknadstype().also { it.kode = "MS"; it.beskrivelse = "Merknad fra saksbehandler" }),
    MERKNAD_FRA_LEDER(Merknadstype().also { it.kode = "ML"; it.beskrivelse = "Merknad fra leder" }),
    MERKNAD_FRA_ARKIVANSVARLIG(Merknadstype().also { it.kode = "MA"; it.beskrivelse = "Merknad fra arkivansvarlig" })
}