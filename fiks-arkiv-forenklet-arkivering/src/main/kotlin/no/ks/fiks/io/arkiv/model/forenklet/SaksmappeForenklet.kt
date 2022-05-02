package no.ks.fiks.io.arkiv.model.forenklet

import no.arkivverket.standarder.noark5.metadatakatalog.v2.Kode
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.KodeBuilder
import java.time.ZonedDateTime

class SaksmappeForenklet {

    var saksaar: Long? = null
        private set
    var sakssekvensnummer: Long? = null
        private set
    var mappetype: KodeBuilder? = null
        private set
    var saksdato: ZonedDateTime? = null
        private set
    var tittel: String? = null
        private set
    var administrativEnhet: String? = null
        private set
    var referanseAdministrativEnhet: String? = null
        private set
    var offentligTittel: String? = null
        private set
    var saksansvarlig: String? = null
        private set
    var referanseSaksansvarlig: String? = null
        private set
    var saksstatus: String? = null
        private set
    var avsluttetAv: String? = null
        private set
    var skjermetTittel: Boolean = false
        private set
    var referanseEksternNoekkelForenklet: EksternNoekkelForenklet? = null
        private set
    var klasse: List<KlasseForenklet> = ArrayList()
        private set

    fun saksaar(saksaar: Long) = apply { this.saksaar = saksaar }
    fun sakssekvensnummer(sakssekvensnummer: Long) = apply { this.sakssekvensnummer = sakssekvensnummer }
    fun mappetype(mappetype: KodeBuilder) = apply { this.mappetype = mappetype }
    fun saksdato(saksdato: ZonedDateTime) = apply { this.saksdato = saksdato }
    fun administrativEnhet(administrativEnhet: String) = apply { this.administrativEnhet = administrativEnhet }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun referanseAdministrativEnhet(referanseAdministrativEnhet: String) = apply { this.referanseAdministrativEnhet = referanseAdministrativEnhet }
    fun offentligTittel(offentligTittel: String) = apply { this.offentligTittel = offentligTittel }
    fun saksansvarlig(saksansvarlig: String) = apply { this.saksansvarlig = saksansvarlig }
    fun referanseSaksansvarlig(referanseSaksansvarlig: String) = apply { this.referanseSaksansvarlig = referanseSaksansvarlig }
    fun saksstatus(saksstatus: String) = apply { this.saksstatus = saksstatus }
    fun avsluttetAv(avsluttetAv: String) = apply { this.avsluttetAv = avsluttetAv }
    fun skjermetTittel(skjermetTittel: Boolean) = apply { this.skjermetTittel = skjermetTittel }
    fun referanseEksternNoekkelForenklet(referanseEksternNoekkelForenklet: EksternNoekkelForenklet) = apply { this.referanseEksternNoekkelForenklet = referanseEksternNoekkelForenklet }
    fun klasse(klasse: List<KlasseForenklet>) = apply { this.klasse = klasse }


}