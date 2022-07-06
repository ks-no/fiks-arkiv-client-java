package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Kassasjonsvedtak


enum class KassasjonsvedtakType(val value: Kassasjonsvedtak) {
    BEVARES(Kassasjonsvedtak().also { it.kode = "B"; it.beskrivelse = "Bevares" }),
    KASSERES(Kassasjonsvedtak().also { it.kode = "K"; it.beskrivelse = "Kasseres" }),
    VURDERES_SENERE(Kassasjonsvedtak().also { it.kode = "G"; it.beskrivelse = "Vurderes senere" })
}

