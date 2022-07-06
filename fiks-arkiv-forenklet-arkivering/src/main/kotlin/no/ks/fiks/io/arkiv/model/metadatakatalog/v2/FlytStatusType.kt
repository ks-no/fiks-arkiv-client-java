package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.FlytStatus


enum class FlytStatusType(val value: FlytStatus) {
    GODKJENT(FlytStatus().also { it.kode = "G"; it.beskrivelse = "Godkjent" }),
    IKKE_GODKJENT(FlytStatus().also { it.kode = "I"; it.beskrivelse = "Ikke godkjent" }),
    SENDT_TILBAKE_TIL_SAKSBEHANDLER_MED_KOMMENTARER(FlytStatus().also { it.kode = "S"; it.beskrivelse = "Sendt tilbake til saksbehandler med kommmentarer" })
}