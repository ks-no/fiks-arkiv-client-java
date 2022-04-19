package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.FormatType
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SystemIDBuilder
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.VariantFormatType
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Dokumentobjekt
import java.time.ZonedDateTime

class DokumentObjektBuilder {

    var systemID: SystemIDBuilder? = null
        private set
    var versjonsnummer: Long? = null
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
    var filstoerrelse: Long? = null
        private set
    var elektroniskSignatur: ElektroniskSignaturBuilder? = null
        private set
    var konverteringer: List<KonverteringBuilder> = ArrayList()
        private set

    fun systemID(systemID: SystemIDBuilder) = apply { this.systemID = systemID }
    fun versjonsnummer(versjonsnummer: Long) = apply { this.versjonsnummer = versjonsnummer }
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
    fun filstoerrelse(filstoerrelse: Long) = apply { this.filstoerrelse = filstoerrelse }
    fun elektroniskSignatur(elektroniskSignatur: ElektroniskSignaturBuilder) = apply { this.elektroniskSignatur = elektroniskSignatur }
    fun konverteringer(konverteringer: List<KonverteringBuilder>) = apply { this.konverteringer = konverteringer }


    fun build() : Dokumentobjekt {
        return Dokumentobjekt().also {
            it.systemID = systemID?.build() ?: throw IllegalStateException("SystemID er påkrevd for Dokumentobjekt")
            it.versjonsnummer = versjonsnummer?.toBigInteger() ?: throw IllegalStateException("Versjonsnummer er påkrevd for Dokumentobjekt")
            it.variantformat = variantformat?.value ?: throw IllegalStateException("Variantformat er påkrevd for Dokumentobjekt")
            it.filnavn = checkNotNull(filnavn) {"Filnavn er påkrevd for Dokumentobject"}
            it.format = format?.value ?: throw IllegalStateException("Format er påkrevd for Dokumentobjekt")
            it.mimeType = checkNotNull(mimeType) {"Mimetype er påkrevd for Dokumentobject"}
            it.formatDetaljer = formatDetaljer
            it.opprettetDato = opprettetDato
            it.opprettetAv = checkNotNull(opprettetAv) {"OpprettetAv er påkrevd for Dokumentobjekt"}
            it.referanseDokumentfil = checkNotNull(referanseDokumentfil) {"ReferanseDokumentfil er påkrevd for Dokumnetobjekt"}
            it.sjekksum = checkNotNull(sjekksum) {"Sjekksum er påkrevd for Dokumentobjekt"}
            it.sjekksumAlgoritme = checkNotNull(sjekksumAlgoritme) { "SjekksumAlgoritme er påkrevd for Dokumentobjekt" }
            it.filstoerrelse = filstoerrelse?.toBigInteger() ?: throw IllegalStateException("Filstørrelse er påkrevd for Dokumentobjekt")
            it.elektroniskSignatur = elektroniskSignatur?.build()
            it.konverterings.addAll(konverteringer.map { k -> k.build() }.toList())
        }
    }
}
