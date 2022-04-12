package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.arkivverket.standarder.noark5.metadatakatalog.v2.Kassasjonsvedtak

enum class KassasjonsvedtakType(val value: Kassasjonsvedtak) {
    BEVARES(Kassasjonsvedtak().also { it.kode = "B"; it.beskrivelse = "Bevares" }),
    KASSERES(Kassasjonsvedtak().also { it.kode = "K"; it.beskrivelse = "Kasseres" }),
    VURDERES_SENERE(Kassasjonsvedtak().also { it.kode = "G"; it.beskrivelse = "Vurderes senere" })
}

