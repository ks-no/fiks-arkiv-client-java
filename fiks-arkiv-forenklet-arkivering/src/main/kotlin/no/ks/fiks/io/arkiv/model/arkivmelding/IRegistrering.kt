package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.*
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.EksternNoekkel
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Kode
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.ReferanseTilMappe
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.SystemID
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.DokumentmediumType
import java.time.ZonedDateTime


/**
 * Registrering er det overordnede objektet for å samle et sett med dokumenter som hører sammen, eller for å angi en hendelse.
 */
abstract class IRegistrering<T : Registrering> {

    var systemID: SystemID? = null
        private set
    var opprettetDato: ZonedDateTime = ZonedDateTime.now()
        private set
    var opprettetAv: String? = null
        private set
    var arkivertDato: ZonedDateTime = ZonedDateTime.now()
        private set
    var arkivertAv: String? = null
        private set
    var arkivdel: Kode? = null
        private set
    var referanseForelderMappe: ReferanseTilMappe? = null
        private set
    var parts: List<Part> = ArrayList()
        private set
    var skjerming: Skjerming? = null
        private set
    var gradering: Gradering? = null
        private set
    var dokumentbeskrivelser: List<Dokumentbeskrivelse> = ArrayList()
        private set
    var registreringsID: String? = null
        private set
    var tittel: String? = null
        private set
    var offentligTittel: String? = null
        private set
    var beskrivelse: String? = null
        private set
    var nokkelord: List<String> = ArrayList()
        private set
    var forfattere: List<String> = ArrayList()
        private set
    var dokumentmedium: DokumentmediumType? = null
        private set
    var oppbevaringssteder: List<String> = ArrayList()
        private set
    var merknader: List<Merknad> = ArrayList()
        private set
    var kryssreferanser: List<Kryssreferanse> = ArrayList()
        private set
    var korrespondanseparts: List<Korrespondansepart> = ArrayList()
        private set
    var klassifikasjoner: List<Klassifikasjon> = ArrayList()
        private set
    var referanseEksternNoekkel: EksternNoekkel? = null
        private set

    fun <B : IRegistrering<T>> B.systemID(systemID: SystemID): B = apply { this.systemID = systemID }
    fun arkivdel(arkivdel: Kode) = apply { if(referanseForelderMappe == null) this.arkivdel = arkivdel else throw IllegalArgumentException("ReferanseArkivdel kan ikke settes i kombinasjon med ReferanseForelderMappe") }
    fun referanseForelderMappe(referanseForelderMappe: ReferanseTilMappe) = apply { if(arkivdel == null) this.referanseForelderMappe = referanseForelderMappe else throw IllegalArgumentException("ReferanseForelderMappe kan ikke settes i kombinasjon med ReferanseArkivdel") }
    fun opprettetDato(opprettetDato: ZonedDateTime) = apply { this.opprettetDato = opprettetDato }
    fun opprettetAv(opprettetAv: String) = apply { this.opprettetAv = opprettetAv }
    fun arkivertDato(arkivertDato: ZonedDateTime) = apply { this.arkivertDato = arkivertDato }
    fun arkivertAv(arkivertAv: String) = apply { this.arkivertAv = arkivertAv }
    fun parts(parts: List<Part>) = apply { this.parts = parts }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun korrespondanseparts(korrespondanseparts: List<Korrespondansepart>) = apply { this.korrespondanseparts = korrespondanseparts }
    fun referanseEksternNoekkel(referanseEksternNoekkel: EksternNoekkel) = apply { this.referanseEksternNoekkel =  referanseEksternNoekkel}
    fun beskrivelse(beskrivelse: String) = apply { this.beskrivelse = beskrivelse }
    fun klassifikasjoner(klassifikasjoner: List<Klassifikasjon>) = apply { this.klassifikasjoner = klassifikasjoner }
    fun kryssreferanser(kryssreferanser: List<Kryssreferanse>) = apply { this.kryssreferanser = kryssreferanser }
    fun merknader(merknader: List<Merknad>) = apply { this.merknader = merknader }
    fun oppbevaringssteder(oppbevaringssteder: List<String>) = apply { this.oppbevaringssteder = oppbevaringssteder }
    fun dokumentmedium(dokumentmedium: DokumentmediumType) = apply { this.dokumentmedium = dokumentmedium }
    fun forfattere(forfattere: List<String>) = apply { this.forfattere = forfattere }
    fun nokkelord(nokkelord: List<String>) = apply { this.nokkelord = nokkelord }
    fun offentligTittel(offentligTittel: String) = apply { this.offentligTittel = offentligTittel }
    fun registreringsID(registreringsID: String) = apply { this.registreringsID = registreringsID }
    fun dokumentbeskrivelser(dokumentbeskrivelser: List<Dokumentbeskrivelse>) = apply { this.dokumentbeskrivelser = dokumentbeskrivelser }
    fun gradering(gradering: Gradering) = apply { this.gradering = gradering }
    fun skjerming(skjerming: Skjerming) = apply { this.skjerming = skjerming }

    abstract fun build(): T
    protected fun build(r: Registrering) {
        r.systemID = systemID
        r.arkivdel = arkivdel
        r.referanseForelderMappe = referanseForelderMappe
        r.opprettetDato = opprettetDato
        r.opprettetAv = opprettetAv
        r.arkivertDato = arkivertDato
        r.arkivertAv = arkivertAv
        r.parts.addAll(parts)
        r.skjerming = skjerming
        r.gradering = gradering
        r.dokumentbeskrivelses.addAll(dokumentbeskrivelser)
        r.registreringsID = registreringsID
        r.tittel = checkNotNull(tittel) { "Tittel er påkrevd for Akrivnotat" }
        r.offentligTittel = offentligTittel
        r.beskrivelse = beskrivelse
        r.noekkelords.addAll(nokkelord)
        r.forfatters.addAll(forfattere)
        r.dokumentmedium = dokumentmedium?.value
        r.oppbevaringssteds.addAll(oppbevaringssteder)
        r.merknads.addAll(merknader)
        r.kryssreferanses.addAll(kryssreferanser)
        r.korrespondanseparts.addAll(korrespondanseparts)
        r.klassifikasjons.addAll(klassifikasjoner)
        r.referanseEksternNoekkel = referanseEksternNoekkel ?: throw IllegalStateException("ReferanseEksternNoekkel er påkrevd for Arkivnotat")
    }
}