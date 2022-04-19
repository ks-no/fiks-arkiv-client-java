package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.arkivverket.standarder.noark5.metadatakatalog.v2.Format


enum class FormatType(val value: Format) {
    REN_TEKST(Format().also { it.kode = "RA-TEKST"; it.beskrivelse = "Ren tekst" }),
    TIFF_VERSJON_6(Format().also { it.kode = "RA-TIFF6"; it.beskrivelse = "TIFF versjon 6" }),
    PDF_A_ISO_19005_1_2005(Format().also { it.kode = "RA-PDF"; it.beskrivelse = "PDF/A - ISO 19005-1:2005" }),
    XML(Format().also { it.kode = "RA-XML"; it.beskrivelse = "XML" }),
    JPEG(Format().also { it.kode = "RA-JPEG"; it.beskrivelse = "JPEG" }),
    SOSI(Format().also { it.kode = "RA-SOSI"; it.beskrivelse = "SOSI" }),
    MPEG_2(Format().also { it.kode = "RA-MPEG-2"; it.beskrivelse = "MPEG-2" }),
    MP3(Format().also { it.kode = "RA-MP3"; it.beskrivelse = "SOSI" })
}