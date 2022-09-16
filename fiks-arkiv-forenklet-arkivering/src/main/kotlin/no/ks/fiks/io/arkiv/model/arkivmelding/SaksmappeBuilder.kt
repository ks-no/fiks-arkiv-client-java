package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.Saksmappe
import no.ks.fiks.io.arkiv.model.arkivstruktur.AdministrativEnhetBuilder
import no.ks.fiks.io.arkiv.model.arkivstruktur.AdresseBuilder
import no.ks.fiks.io.arkiv.model.arkivstruktur.PresedensBuilder
import no.ks.fiks.io.arkiv.model.arkivstruktur.SaksansvarligBuilder
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
    var administrativEnhet: AdministrativEnhetBuilder? = null
        private set
    var saksansvarlig: SaksansvarligBuilder? = null
        private set
    var journalenhet: String? = null
        private set
    var saksstatus: SaksstatusType? = null
        private set
    var utlaantDato: LocalDate? = null
        private set
    var utlaantTil: String? = null
        private set
    var presedens: List<PresedensBuilder> = ArrayList()
        private set
    var adresser: List<AdresseBuilder> = ArrayList()
        private set
    var matrikkelnumre: List<MatrikkelnummerBuilder> = ArrayList()
        private set
    var byggindenter: List<ByggidentBuilder> = ArrayList()
        private set
    var planident: PlanidentBuilder? = null
        private set
    var punkt: PunktBuilder? = null
        private set

    fun saksaar(saksaar: Int) = apply { this.saksaar = saksaar }
    fun sakssekvensnummer(sakssekvensnummer: Int) = apply { this.sakssekvensnummer = sakssekvensnummer }
    fun saksdato(saksdato: LocalDate) = apply { this.saksdato = saksdato }
    fun administrativEnhet(administrativEnhet: AdministrativEnhetBuilder) = apply { this.administrativEnhet = administrativEnhet }
    fun saksansvarlig(saksansvarlig: SaksansvarligBuilder) = apply { this.saksansvarlig = saksansvarlig }
    fun journalenhet(journalenhet: String) = apply { this.journalenhet = journalenhet }
    fun saksstatus(saksstatus: SaksstatusType) = apply { this.saksstatus = saksstatus }
    fun utlaantDato(utlaantDato: LocalDate) = apply { this.utlaantDato = utlaantDato }
    fun utlaantTil(utlaantTil: String) = apply { this.utlaantTil = utlaantTil }
    fun presedens(presedens: List<PresedensBuilder>) = apply { this.presedens = presedens }
    fun adresser(adresser: List<AdresseBuilder>) = apply { this.adresser = adresser }
    fun matrikkelnumre(matrikkelnumre: List<MatrikkelnummerBuilder>) = apply { this.matrikkelnumre = matrikkelnumre }
    fun byggindenter(byggindenter: List<ByggidentBuilder>) = apply { this.byggindenter = byggindenter }
    fun planident(planident: PlanidentBuilder) = apply { this.planident = planident }
    fun punkt(punkt: PunktBuilder) = apply { this.punkt = punkt }

    override fun build(): Saksmappe {
        return Saksmappe().also {
            it.systemID = systemID?.build()
            it.mappeID = mappeId
            it.referanseForeldermappe = referanseForeldermappe?.build()
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
            it.arkivdel = arkivdel?.build()
            it.parts.addAll(parts.map { p -> p.build() }.toList())
            it.kryssreferanses.addAll(kryssreferanser.map { k -> k.build() }.toList())
            it.merknads.addAll(merknader.map { m -> m.build() }.toList())
            it.skjerming = skjerming?.build()
            it.gradering = gradering?.build()
            it.klassifikasjons.addAll(klassifikasjoner.map { k -> k.build() }.toList())
            it.referanseEksternNoekkel = referanseEksternNoekkel?.build()
            it.registrerings.addAll(registreringer.map { r -> r.build() }.toList())
            it.mappes.addAll(mapper.map { m -> m.build() }.toList())
            it.mappetype = mappetype?.build()

            it.saksaar = saksaar
            it.sakssekvensnummer = sakssekvensnummer
            it.saksdato = saksdato
            it.administrativEnhet = administrativEnhet?.build()
            it.saksansvarlig = saksansvarlig?.build()
            it.journalenhet = journalenhet
            it.saksstatus = saksstatus?.value
            it.utlaantDato = utlaantDato
            it.utlaantTil = utlaantTil
            it.presedens.addAll(presedens.map { p -> p.build() }.toList())
            it.matrikkelnummers.addAll(matrikkelnumre.map { m -> m.build() }.toList())
            it.byggidents.addAll(byggindenter.map { b -> b.build() }.toList())
            it.planident = planident?.build()
            it.punkt = punkt?.build()
            it.adresses.addAll(adresser.map { a -> a.build() }.toList())
        }
    }
}
