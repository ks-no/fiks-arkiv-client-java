package no.ks.fiks.io.arkiv.model.forenklet

import no.ks.fiks.io.arkiv.model.arkivstruktur.*
import java.time.LocalDate
import java.time.ZonedDateTime

class UtgaaendeJournalpost {
    var avskrivningsdato: LocalDate? = null
    var journalaar: Int? = null
    var journalsekvensnummer: Int? = null
    var journalpostnummer: Int? = null
    var dokumentetsDato: LocalDate? = null
    var sendtDato: ZonedDateTime? = null
    var hoveddokument: ForenkletDokument? = null
        private set
    var skjermetTittel: Boolean = false
        private set
    var offentligTittel: String? = null
    var offentlighetsvurdertDato: LocalDate? = null
    var referanseEksternNoekkelForenklet: EksternNoekkelForenklet? = null
    var tittel: String? = null
    var vedlegg: List<ForenkletDokument> = ArrayList()
    var mottakere: List<KorrespondansepartForenklet> = ArrayList()
    var avsender: List<KorrespondansepartForenklet> = ArrayList()
    var internAvsender: List<KorrespondansepartIntern> = ArrayList()

    fun avskrivningsdato(avskrivningsdato: LocalDate) = apply { this.avskrivningsdato = avskrivningsdato }
    fun journalaar(journalaar: Int) = apply { this.journalaar = journalaar }
    fun journalsekvensnummer(journalsekvensnummer: Int) = apply { this.journalsekvensnummer = journalsekvensnummer }
    fun journalpostnummer(journalpostnummer: Int) = apply { this.journalpostnummer = journalpostnummer }
    fun dokumentetsDato(dokumentetsDato: LocalDate) = apply { this.dokumentetsDato = dokumentetsDato }
    fun sendtDato(sendtDato: ZonedDateTime) = apply { this.sendtDato = sendtDato }
    fun hoveddokument(hoveddokument: ForenkletDokument) = apply { this.hoveddokument = hoveddokument }
    fun skjermetTittel(skjermetTittel: Boolean) = apply { this.skjermetTittel = skjermetTittel }
    fun offentligTittel(offentligTittel: String) = apply { this.offentligTittel = offentligTittel }
    fun offentlighetsvurdertDato(offentlighetsvurdertDato: LocalDate) = apply { this.offentlighetsvurdertDato = offentlighetsvurdertDato }
    fun referanseEksternNoekkelForenklet(referanseEksternNoekkelForenklet: EksternNoekkelForenklet) = apply { this.referanseEksternNoekkelForenklet = referanseEksternNoekkelForenklet }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun vedlegg(skjermetTittel: List<ForenkletDokument>) = apply { this.vedlegg = vedlegg }
    fun mottakere(mottakere: List<KorrespondansepartForenklet>) = apply { this.mottakere = mottakere }
    fun avsender(avsender: List<KorrespondansepartForenklet>) = apply { this.avsender = avsender }
    fun internAvsender(internAvsender: List<KorrespondansepartIntern>) = apply { this.internAvsender = internAvsender }
}