package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.TilknyttetRegistreringSom

enum class TilknyttetRegistreringSomType(val value: TilknyttetRegistreringSom) {
    HOVEDDOKUMENT(TilknyttetRegistreringSom().also { it.kode = "H"; it.beskrivelse = "Hoveddokument" }),
    VEDLEGG(TilknyttetRegistreringSom().also { it.kode = "V"; it.beskrivelse = "Vedlegg" })
}