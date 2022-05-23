package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.arkivverket.standarder.noark5.metadatakatalog.v2.Saksstatus


enum class SaksstatusType(val value: Saksstatus) {
    UNDER_BEHANDLING(Saksstatus().also { it.kode = "B"; it.beskrivelse = "Under behandling" }),
    AVSLUTTET(Saksstatus().also { it.kode = "A"; it.beskrivelse = "Avsluttet" }),
    UTGAR(Saksstatus().also { it.kode = "U"; it.beskrivelse = "Utgår" }),
    OPPRETTET_AV_SAKSBEHANDLER(Saksstatus().also { it.kode = "R"; it.beskrivelse = "Opprettet av saksbehandler" }),
    AVSLUTTET_AV_SAKSBEHANDLER(Saksstatus().also { it.kode = "S"; it.beskrivelse = "Avsluttet av saksbehandler" }),
    UNTTAT_PROSESSTYRING(Saksstatus().also { it.kode = "P"; it.beskrivelse = "Untatt prosesstyring" }),
    FERDIG_FRA_SAKSBEHANDLER(Saksstatus().also { it.kode = "F"; it.beskrivelse = "Ferdig fra saksbehandler" })
}