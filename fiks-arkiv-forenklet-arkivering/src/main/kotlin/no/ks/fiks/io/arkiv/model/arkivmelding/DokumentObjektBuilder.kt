package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.Dokumentobjekt
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.SystemID
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.FormatType
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.VariantFormatType
import java.time.ZonedDateTime

class DokumentObjektBuilder {

    var systemID: SystemID? = null
        private set
    var versjonsnummer: Int? = null
        private set
    var variantformat: VariantFormatType? = null
        private set
    var filnavn: String? = null
        private set
    var format: FormatType? = null
        private set
    var mimeType: String? = null
        private set
    var formatDetaljer: String? = null
        private set
    var opprettetDato: ZonedDateTime = ZonedDateTime.now()
        private set
    var opprettetAv: String? = null
        private set
    var referanseDokumentfil: String? = null
        private set
    var sjekksum: String? = null
        private set
    var sjekksumAlgoritme: String? = null
        private set
    var filstoerrelse: Int? = null
        private set

    fun systemID(systemID: SystemID) = apply { this.systemID = systemID }
    fun versjonsnummer(versjonsnummer: Int) = apply { this.versjonsnummer = versjonsnummer }
    fun variantformat(variantformat: VariantFormatType) = apply { this.variantformat = variantformat }
    fun filnavn(filnavn: String) = apply { this.filnavn = filnavn }
    fun format(format: FormatType) = apply { this.format = format }
    fun mimeType(mimeType: String) = apply { this.mimeType = mimeType }
    fun formatDetaljer(formatDetaljer: String) = apply { this.formatDetaljer = formatDetaljer }
    fun opprettetDato(opprettetDato: ZonedDateTime) = apply { this.opprettetDato = opprettetDato }
    fun opprettetAv(opprettetAv: String) = apply { this.opprettetAv = opprettetAv }
    fun referanseDokumentfil(referanseDokumentfil: String) = apply { this.referanseDokumentfil = referanseDokumentfil }
    fun sjekksum(sjekksum: String) = apply { this.sjekksum = sjekksum }
    fun sjekksumAlgoritme(sjekksumAlgoritme: String) = apply { this.sjekksumAlgoritme = sjekksumAlgoritme }
    fun filstoerrelse(filstoerrelse: Int) = apply { this.filstoerrelse = filstoerrelse }


    fun build() : Dokumentobjekt {
        return Dokumentobjekt().also {
            it.systemID = systemID
            it.versjonsnummer = versjonsnummer ?: throw IllegalStateException("Versjonsnummer er påkrevd for Dokumentobjekt")
            it.variantformat = variantformat?.value ?: throw IllegalStateException("Variantformat er påkrevd for Dokumentobjekt")
            it.filnavn = checkNotNull(filnavn) {"Filnavn er påkrevd for Dokumentobject"}
            it.format = format?.value ?: throw IllegalStateException("Format er påkrevd for Dokumentobjekt")
            it.mimeType = mimeType
            it.formatDetaljer = formatDetaljer
            it.opprettetDato = opprettetDato
            it.opprettetAv = opprettetAv
            it.referanseDokumentfil = checkNotNull(referanseDokumentfil) {"ReferanseDokumentfil er påkrevd for Dokumnetobjekt"}
            it.sjekksum = sjekksum
            it.sjekksumAlgoritme = sjekksumAlgoritme
            it.filstoerrelse = filstoerrelse
        }
    }
}
