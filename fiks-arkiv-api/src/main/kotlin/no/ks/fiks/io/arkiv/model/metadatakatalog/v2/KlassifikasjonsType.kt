package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.arkivverket.standarder.noark5.metadatakatalog.v2.Klassifikasjonstype

enum class KlassifikasjonsType(val value: Klassifikasjonstype) {
    GARDS_OG_BRUKSNUMMER(Klassifikasjonstype().also { it.kode = "GBN"; it.beskrivelse = "Gårds- og bruksnummer" }),
    FUNKSJONSBASERT_HIERARKISK(Klassifikasjonstype().also { it.kode = "FH"; it.beskrivelse = "Funksjonsbasert, hierarkisk" }),
    EMNEBASERT_HIERARKISK_ARKIVNØKKEL(Klassifikasjonstype().also { it.kode = "EH"; it.beskrivelse = "Emnebasert, hierarkisk arkivnøkkel" }),
    EMNEBASERT_ETT_NIVA(Klassifikasjonstype().also { it.kode = "E1"; it.beskrivelse = "Emnebasert, ett nivå" }),
    K_KODER(Klassifikasjonstype().also { it.kode = "KK"; it.beskrivelse = "K-Koder" }),
    MANGEFASETTERT_IKKE_HIERARKI(Klassifikasjonstype().also { it.kode = "MF"; it.beskrivelse = "Mangefasettert, ikke hierarki" }),
    OBJEKTBASERT(Klassifikasjonstype().also { it.kode = "UO"; it.beskrivelse = "Objektbasert" }),
    FODSELSNUMMER(Klassifikasjonstype().also { it.kode = "PNR"; it.beskrivelse = "Fødselsnummer" })
}