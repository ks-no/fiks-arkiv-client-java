package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.*
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.AdministrativEnhet
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Saksansvarlig
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SaksstatusType
import java.time.LocalDate

/**
 * Mappe er det overordnede objektet for å samle saker i. Det er mulig å ha mapper i mapper.
 */
open class SaksmappeBuilder : MappeBuilder() {
    var saksaar: Int? = null
        private set
    var sakssekvensnummer: Int? = null
        private set
    var saksdato: LocalDate? = null
        private set
    var administrativEnhet: AdministrativEnhet? = null
        private set
    var saksansvarlig: Saksansvarlig? = null
        private set
    var journalenhet: String? = null
        private set
    var saksstatus: SaksstatusType? = null
        private set
    var utlaantDato: LocalDate? = null
        private set
    var utlaantTil: String? = null
        private set
    var presedens: List<Presedens> = ArrayList()
        private set
    var adresser: List<Adresse> = ArrayList()
        private set
    var matrikkelnumre: List<Matrikkelnummer> = ArrayList()
        private set
    var byggindenter: List<Byggident> = ArrayList()
        private set
    var planident: Planident? = null
        private set
    var punkt: Punkt? = null
        private set

    fun saksaar(saksaar: Int) = apply { this.saksaar = saksaar }
    fun sakssekvensnummer(sakssekvensnummer: Int) = apply { this.sakssekvensnummer = sakssekvensnummer }
    fun saksdato(saksdato: LocalDate) = apply { this.saksdato = saksdato }
    fun administrativEnhet(administrativEnhet: AdministrativEnhet) = apply { this.administrativEnhet = administrativEnhet }
    fun saksansvarlig(saksansvarlig: Saksansvarlig) = apply { this.saksansvarlig = saksansvarlig }
    fun journalenhet(journalenhet: String) = apply { this.journalenhet = journalenhet }
    fun saksstatus(saksstatus: SaksstatusType) = apply { this.saksstatus = saksstatus }
    fun utlaantDato(utlaantDato: LocalDate) = apply { this.utlaantDato = utlaantDato }
    fun utlaantTil(utlaantTil: String) = apply { this.utlaantTil = utlaantTil }
    fun presedens(presedens: List<Presedens>) = apply { this.presedens = presedens }
    fun adresser(adresser: List<Adresse>) = apply { this.adresser = adresser }
    fun matrikkelnumre(matrikkelnumre: List<Matrikkelnummer>) = apply { this.matrikkelnumre = matrikkelnumre }
    fun byggindenter(byggindenter: List<Byggident>) = apply { this.byggindenter = byggindenter }
    fun planident(planident: Planident) = apply { this.planident = planident }
    fun punkt(punkt: Punkt) = apply { this.punkt = punkt }

    override fun build(): Saksmappe {
        return Saksmappe().also {
            it.systemID = systemID
            it.mappeID = mappeId
            it.referanseForeldermappe = referanseForeldermappe
            it.tittel = checkNotNull(tittel) { "Tittel er påkrevd for Saksmappe" }
            it.offentligTittel = offentligTittel
            it.beskrivelse = beskrivelse
            it.noekkelords.addAll(noekkelord)
            it.dokumentmedium = dokumentmedium?.value
            it.oppbevaringssteds.addAll(oppbevaringssteder)
            it.opprettetDato = opprettetDato
            it.opprettetAv = opprettetAv
            it.avsluttetDato = avsluttetDato
            it.avsluttetAv = avsluttetAv
            it.arkivdel = arkivdel
            it.parts.addAll(parts)
            it.kryssreferanses.addAll(kryssreferanser)
            it.merknads.addAll(merknader)
            it.skjerming = skjerming
            it.gradering = gradering
            it.klassifikasjons.addAll(klassifikasjoner)
            it.referanseEksternNoekkel = referanseEksternNoekkel
            it.registrerings.addAll(registreringer.map { r -> r.build() }.toList())
            it.mappes.addAll(mapper)
            it.mappetype = mappetype

            it.saksaar = saksaar
            it.sakssekvensnummer = sakssekvensnummer
            it.saksdato = saksdato
            it.administrativEnhet = administrativEnhet
            it.saksansvarlig = saksansvarlig
            it.journalenhet = journalenhet
            it.saksstatus = saksstatus?.value
            it.utlaantDato = utlaantDato
            it.utlaantTil = utlaantTil
            it.presedens.addAll(presedens)
            it.matrikkelnummers.addAll(matrikkelnumre)
            it.byggidents.addAll(byggindenter)
            it.planident = planident
            it.punkt = punkt
            it.adresses.addAll(adresser)
        }
    }
}
