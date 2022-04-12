package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.arkivverket.standarder.noark5.metadatakatalog.v2.PartRolle

enum class PartRolleType(val value: PartRolle) {
    KLIENT(PartRolle().also { it.kode = "K"; it.beskrivelse = "Klient" }),
    PARORENDE(PartRolle().also { it.kode = "P"; it.beskrivelse = "Pårørende" }),
    FORMYNDER(PartRolle().also { it.kode = "F"; it.beskrivelse = "Formynder" }),
    ADVOKAT(PartRolle().also { it.kode = "A"; it.beskrivelse = "Advokat" })
}