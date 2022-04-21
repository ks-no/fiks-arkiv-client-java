package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.arkivverket.standarder.noark5.metadatakatalog.v2.Grad

enum class GradType(val value: Grad) {
    STRENGT_HEMMELIG(Grad().also { it.kode = "SH"; it.beskrivelse = "Strengt hemmelig" }),
    HEMMELIG(Grad().also { it.kode = "H"; it.beskrivelse = "Hemmelig" }),
    KONFIDENSIELT(Grad().also { it.kode = "K"; it.beskrivelse = "Konfedensielt" }),
    BEGRENSET(Grad().also { it.kode = "B"; it.beskrivelse = "Begrenset" }),
    FORTROLIG(Grad().also { it.kode = "F"; it.beskrivelse = "Fortrolig" }),
    STRENGT_FORTROLIG(Grad().also { it.kode = "SF"; it.beskrivelse = "Strengt fortrolig" })
}

