package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.arkivverket.standarder.noark5.metadatakatalog.v2.Journalstatus

enum class JournalStatus(val value: Journalstatus) {
    JOURNALFORT(Journalstatus().also { it.kode = "J"; it.beskrivelse = "Journalført" }),
    FERDIGSTILT_FRA_SAKSBEHANDLER(Journalstatus().also { it.kode = "F"; it.beskrivelse = "Ferdigstilt fra saksbehandler" }),
    GODKJENT_AV_LEDER(Journalstatus().also { it.kode = "G"; it.beskrivelse = "Godkjent av leder" }),
    EKSPEDERT(Journalstatus().also { it.kode = "E"; it.beskrivelse = "Ekspedert" }),
    ARKIVERT(Journalstatus().also { it.kode = "A"; it.beskrivelse = "Arkivert" }),
    UTGAR(Journalstatus().also { it.kode = "U"; it.beskrivelse = "Utgår" }),
    MIDLERTIDIG_REGISTRERING_AV_INNKOMMET_DOKUMENT(Journalstatus().also { it.kode = "M"; it.beskrivelse = "Midlertidig registrering av innkommet dokument" }),
    SAKSBEHANDLER_HAR_REGISTRERT_INNKOMMET_DOKUMENT(Journalstatus().also { it.kode = "S"; it.beskrivelse = "Saksbehandler har registrert innkommet dokument" }),
    RESERVERT_DOKUMENT(Journalstatus().also { it.kode = "R"; it.beskrivelse = "Reservert dokument" })
}