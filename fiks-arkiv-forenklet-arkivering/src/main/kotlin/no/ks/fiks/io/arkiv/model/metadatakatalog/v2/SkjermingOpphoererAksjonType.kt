package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Kode


enum class SkjermingOpphoererAksjonType(val value: Kode) {
    AVGRADERES(Kode().also { it.kode = "A"; it.beskrivelse = "Avgraderes ved første kjøring av funksjon for avgradering etter at avgraderingstidspunktet er nådd" }),
    GJENNOMGAAS(Kode().also { it.kode = "G"; it.beskrivelse = "Gjennomgås for vurdering av avgradering når angitt avgraderingstidspunkt nås" }),
    SPERREFRIST(Kode().also { it.kode = "S"; it.beskrivelse = "Sperrefrist, avgraderes automatisk når avgraderingsdato nås" }),
    AVGRADERING_UTFOERT(Kode().also { it.kode = "AU"; it.beskrivelse = "Avgradering utført. Dette er den eneste avgraderingskoden som er tillatt etter at tilgangskode er slettet" })
}