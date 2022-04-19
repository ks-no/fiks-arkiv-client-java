package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.arkivverket.standarder.noark5.metadatakatalog.v2.TilknyttetRegistreringSom

enum class TilknyttetRegistreringSomType(val value: TilknyttetRegistreringSom) {
    HOVEDDOKUMENT(TilknyttetRegistreringSom().also { it.kode = "H"; it.beskrivelse = "Hoveddokument" }),
    VEDLEGG(TilknyttetRegistreringSom().also { it.kode = "V"; it.beskrivelse = "Vedlegg" })
}