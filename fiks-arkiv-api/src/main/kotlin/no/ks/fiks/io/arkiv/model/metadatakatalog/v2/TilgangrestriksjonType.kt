package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.arkivverket.standarder.noark5.metadatakatalog.v2.Tilgangsrestriksjon


enum class TilgangrestriksjonType(val value: Tilgangsrestriksjon) {
    BEGRENSET_ETTER_SIKKERHETSINSTRUKSEN(Tilgangsrestriksjon().also { it.kode = "B"; it.beskrivelse = "Begrenset etter sikkerhetsinstruksen" }),
    KONFIDENSIELT_ETTER_SIKKERHETSINSTRUKSEN(Tilgangsrestriksjon().also { it.kode = "K"; it.beskrivelse = "Konfidensielt etter sikkerhetsinstruksen" }),
    HEMMELIG_ETTER_SIKKERHETSINSTRUKSEN(Tilgangsrestriksjon().also { it.kode = "H"; it.beskrivelse = "Hemmelig etter sikkerhetsinstruksen" }),
    FORTROLIG_ETTER_BESKYTTELSESINSTRUKSEN(Tilgangsrestriksjon().also { it.kode = "F"; it.beskrivelse = "Fortrolig etter beskyttelsesinstruksen" }),
    STRENGT_FORTROLIG_ETTER_BESKYTTELSESINSTRUKSEN(Tilgangsrestriksjon().also { it.kode = "SF"; it.beskrivelse = "Strengt fortrolig etter beskyttelsesinstruksen" }),
    UNTATT_ETTER_OFFENTLIGHETSLOVEN_5(Tilgangsrestriksjon().also { it.kode = "5"; it.beskrivelse = "Untatt etter offentlighetsloven $5" }),
    UNTATT_ETTER_OFFENTLIGHETSLOVEN_5A(Tilgangsrestriksjon().also { it.kode = "5a"; it.beskrivelse = "Untatt etter offentlighetsloven $5a" }),
    UNTATT_ETTER_OFFENTLIGHETSLOVEN_6(Tilgangsrestriksjon().also { it.kode = "6"; it.beskrivelse = "Untatt etter offentlighetsloven $6" }),
    UNTATT_ETTER_OFFENTLIGHETSLOVEN_11(Tilgangsrestriksjon().also { it.kode = "11"; it.beskrivelse = "Untatt etter offentlighetsloven $11" }),
    MIDLERTIDIG_SPERRET(Tilgangsrestriksjon().also { it.kode = "XX"; it.beskrivelse = "Midlertidig sperret" }),
    PERSONALSAKER(Tilgangsrestriksjon().also { it.kode = "P"; it.beskrivelse = "Personalsaker" }),
    KLIENTSAKER(Tilgangsrestriksjon().also { it.kode = "K"; it.beskrivelse = "Klientsaker" })
}