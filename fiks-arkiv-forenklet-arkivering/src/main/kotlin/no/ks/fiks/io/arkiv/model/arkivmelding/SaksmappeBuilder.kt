package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.io.arkiv.model.arkivstruktur.AdresseBuilder
import no.ks.fiks.io.arkiv.model.arkivstruktur.PresedensBuilder
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SaksstatusType
import no.ks.fiks.io.arkiv.v1.client.models.arkivmelding.Saksmappe
import java.time.ZonedDateTime

/**
 * Mappe er det overordnede objektet for å samle saker i. Det er mulig å ha mapper i mapper.
 */
open class SaksmappeBuilder : MappeBuilder() {
    var saksaar: Long? = null
        private set
    var sakssekvensnummer: Long? = null
        private set
    var saksdato: ZonedDateTime? = null
        private set
    var administrativEnhet: String? = null
        private set
    var saksansvarlig: String? = null
        private set
    var journalenhet: String? = null
        private set
    var saksstatus: SaksstatusType? = null
        private set
    var utlaantDato: ZonedDateTime? = null
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

    fun saksaar(saksaar: Long) = apply { this.saksaar = saksaar }
    fun sakssekvensnummer(sakssekvensnummer: Long) = apply { this.sakssekvensnummer = sakssekvensnummer }
    fun saksdato(saksdato: ZonedDateTime) = apply { this.saksdato = saksdato }
    fun administrativEnhet(administrativEnhet: String) = apply { this.administrativEnhet = administrativEnhet }
    fun saksansvarlig(saksansvarlig: String) = apply { this.saksansvarlig = saksansvarlig }
    fun journalenhet(journalenhet: String) = apply { this.journalenhet = journalenhet }
    fun saksstatus(saksstatus: SaksstatusType) = apply { this.saksstatus = saksstatus }
    fun utlaantDato(utlaantDato: ZonedDateTime) = apply { this.utlaantDato = utlaantDato }
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

            it.saksaar = saksaar?.toBigInteger()
            it.sakssekvensnummer = sakssekvensnummer?.toBigInteger()
            it.saksdato = saksdato
            it.administrativEnhet = administrativEnhet
            it.saksansvarlig = saksansvarlig
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
