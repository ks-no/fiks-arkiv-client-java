package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.DokumentType

class ForenkletDokument{
    var dokumenttype: DokumentType? = null
        private set
    var filnavn: String? = null
        private set
    var tittel: String? = null
        private set
    var skjermetDokument: Boolean? = null
        private set
    var referanseDokumentFil: String? = null
        private set
    var systemID: String? = null
        private set

    fun dokumenttype(dokumenttype: DokumentType) = apply { this.dokumenttype = dokumenttype }
    fun filnavn(filnavn: String) = apply { this.filnavn = filnavn }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun skjermetDokument(filnavn: Boolean) = apply { this.skjermetDokument = skjermetDokument }
    fun referanseDokumentFil(referanseDokumentFil: String) = apply { this.referanseDokumentFil = referanseDokumentFil }
    fun systemID(systemID: String) = apply { this.systemID = systemID }
}