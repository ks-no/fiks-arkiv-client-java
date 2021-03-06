package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.Journalpost
import no.ks.fiks.io.arkiv.model.arkivstruktur.*
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.*
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.*
import kotlin.collections.ArrayList

/**
 * Journalpost er en spesialisering av registrering og brukes for hendelser som skal inn i journalen, en oversikt over all korrespondanse til og fra organisasjonen, samt interne notater og rapporter. Oftest vil det kun være journalposter registrert i saksmapper.
 * @see http://arkivverket.metakat.no/Objekttype/Index/EAID_EA66ABBF_7124_485c_AB57_BC5159EB8F56
 */
open class JournalpostBuilder : IRegistrering {
    var arkivdel: KodeBuilder? = null
        private set
    var journaldato: LocalDate = LocalDate.now()
        private set
    var journalpostnummer: Long? = null
        private set
    var journalsekvensnummer: Long? = null
        private set
    var journalaar: Int = ZonedDateTime.now().year
        private set
    var systemID: SystemIDBuilder? = null
        private set
    var journalposttype: JournalpostType? = null
        private set
    var tittel: String? = null
        private set
    var opprettetDato: ZonedDateTime = ZonedDateTime.now()
        private set
    var opprettetAv: String? = null
        private set
    var arkivertDato: ZonedDateTime = ZonedDateTime.now()
        private set
    var arkivertAv: String? = null
        private set
    var referanseForelderMappe: ReferanseForelderMappeBuilder? = null
        private set
    var korrespondanseparts: List<KorrespondansepartBuilder> = ArrayList()
        private set
    var journalstatus: JournalStatus? = null
        private set
    var referanseEksternNoekkel: EksternNoekkelBuilder? = null
        private set
    var parts: List<PartBuilder> = ArrayList()
        private set
    var beskrivelse: String? = null
        private set
    var dokumentetsDato: LocalDate? = null
        private set
    var mottattDato: ZonedDateTime? = null
        private set
    var sendtDato: ZonedDateTime? = null
        private set
    var forfallsdato: LocalDate? = null
        private set
    var offentlighetsvurdertDato: LocalDate? = null
        private set
    var antallVedlegg: Long? = null
        private set
    var utlaantDato: LocalDate? = null
        private set
    var utlaantTil: String? = null
        private set
    var journalenhet: String? = null
        private set
    var avskrivninger: List<AvskrivningBuilder> = ArrayList()
        private set
    var dokumentflyt: List<DokumentflytBuilder> = ArrayList()
        private set
    var presedens: List<PresedensBuilder> = ArrayList()
        private set
    var klassifikasjoner: List<KlassifikasjonBuilder> = ArrayList()
        private set
    var kryssreferanser: List<KryssreferanseBuilder> = ArrayList()
        private set
    var merknader: List<MerknadBuilder> = ArrayList()
        private set
    var oppbevaringssteder: List<String> = ArrayList()
        private set
    var dokumentmedium: DokumentmediumType? = null
        private set
    var forfattere: List<String> = ArrayList()
        private set
    var nokkelord: List<String> = ArrayList()
        private set
    var offentligTittel: String? = null
        private set
    var registreringsID: String? = null
        private set
    var dokumentbeskrivelser: List<DokumentbeskrivelseBuilder> = ArrayList()
        private set
    var gradering: GraderingBuilder? = null
        private set
    var skjerming: SkjermingBuilder? = null
        private set

    fun systemID(systemID: SystemIDBuilder) = apply { this.systemID = systemID }
    fun journalposttype(journalposttype: JournalpostType) = apply { this.journalposttype = journalposttype }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun opprettetDato(opprettetDato: ZonedDateTime) = apply { this.opprettetDato = opprettetDato }
    fun opprettetAv(opprettetAv: String) = apply { this.opprettetAv = opprettetAv }
    fun arkivertDato(arkivertDato: ZonedDateTime) = apply { this.arkivertDato = arkivertDato }
    fun arkivertAv(arkivertAv: String) = apply { this.arkivertAv = arkivertAv }
    fun referanseForelderMappe(referanseForelderMappe: ReferanseForelderMappeBuilder) = apply { if(arkivdel == null) this.referanseForelderMappe = referanseForelderMappe else throw IllegalArgumentException("ReferanseForelderMappe kan ikke settes i kombinasjon med ReferanseArkivdel") }
    fun arkivdel(arkivdel: KodeBuilder) = apply { if(referanseForelderMappe == null) this.arkivdel = arkivdel else throw IllegalArgumentException("ReferanseArkivdel kan ikke settes i kombinasjon med ReferanseForelderMappe") }
    fun korrespondanseparts(korrespondanseparts: List<KorrespondansepartBuilder>) = apply { this.korrespondanseparts = korrespondanseparts }
    fun journalstatus(journalstatus: JournalStatus) = apply { this.journalstatus = journalstatus }
    fun referanseEksternNoekkel(referanseEksternNoekkel: EksternNoekkelBuilder) = apply { this.referanseEksternNoekkel =  referanseEksternNoekkel}

    /**
     * Viser året journalposten ble opprettet Kilde: Registreres automatisk når journalposten opprettes Kommentar: (ingen) M013 journalaar
     */
    fun journalaar(journalaar: Int) = apply { this.journalaar = journalaar }
    fun journalsekvensnummer(journalsekvensnummer: Long) = apply { this.journalsekvensnummer = journalsekvensnummer }
    fun journalpostnummer(journalpostnummer: Long) = apply { this.journalpostnummer = journalpostnummer }
    fun journaldato(journaldato: LocalDate) = apply { this.journaldato = journaldato }
    fun parts(parts: List<PartBuilder>) = apply { this.parts = parts }
    fun beskrivelse(beskrivelse: String) = apply { this.beskrivelse = beskrivelse }
    fun dokumentetsDato(dokumentetsDato: LocalDate) = apply { this.dokumentetsDato = dokumentetsDato }
    fun mottattDato(mottattDato: ZonedDateTime) = apply { this.mottattDato = mottattDato }
    fun sendtDato(sendtDato: ZonedDateTime) = apply { this.sendtDato = sendtDato }
    fun forfallsdato(forfallsdato: LocalDate) = apply { this.forfallsdato = forfallsdato }
    fun offentlighetsvurdertDato(offentlighetsvurdertDato: LocalDate) = apply { this.offentlighetsvurdertDato = offentlighetsvurdertDato }
    fun antallVedlegg(antallVedlegg: Long) = apply { this.antallVedlegg = antallVedlegg }
    fun utlaantDato(utlaantDato: LocalDate) = apply { this.utlaantDato = utlaantDato }
    fun utlaantTil(utlaantTil: String) = apply { this.utlaantTil = utlaantTil }
    fun journalenhet(journalenhet: String) = apply { this.journalenhet = journalenhet }
    fun avskrivninger(avskrivninger: List<AvskrivningBuilder>) = apply { this.avskrivninger = avskrivninger }
    fun dokumentflyt(dokumentflyt: List<DokumentflytBuilder>) = apply { this.dokumentflyt = dokumentflyt }
    fun presedens(presedens: List<PresedensBuilder>) = apply { this.presedens = presedens }
    fun klassifikasjoner(klassifikasjoner: List<KlassifikasjonBuilder>) = apply { this.klassifikasjoner = klassifikasjoner }
    fun kryssreferanser(kryssreferanser: List<KryssreferanseBuilder>) = apply { this.kryssreferanser = kryssreferanser }
    fun merknader(merknader: List<MerknadBuilder>) = apply { this.merknader = merknader }
    fun oppbevaringssteder(oppbevaringssteder: List<String>) = apply { this.oppbevaringssteder = oppbevaringssteder }
    fun dokumentmedium(dokumentmedium: DokumentmediumType) = apply { this.dokumentmedium = dokumentmedium }
    fun forfattere(forfattere: List<String>) = apply { this.forfattere = forfattere }
    fun nokkelord(nokkelord: List<String>) = apply { this.nokkelord = nokkelord }
    fun offentligTittel(offentligTittel: String) = apply { this.offentligTittel = offentligTittel }
    fun registreringsID(registreringsID: String) = apply { this.registreringsID = registreringsID }
    fun dokumentbeskrivelser(dokumentbeskrivelser: List<DokumentbeskrivelseBuilder>) = apply { this.dokumentbeskrivelser = dokumentbeskrivelser }
    fun gradering(gradering: GraderingBuilder) = apply { this.gradering = gradering }
    fun skjerming(skjerming: SkjermingBuilder) = apply { this.skjerming = skjerming }

    override fun build() : Journalpost {
        return Journalpost().also {
            it.systemID = systemID?.build()
            it.opprettetDato = opprettetDato
            it.opprettetAv = opprettetAv
            it.arkivertDato = arkivertDato
            it.arkivertAv = arkivertAv
            it.referanseForelderMappe = referanseForelderMappe?.build()
            it.arkivdel = arkivdel?.build()
            it.journalsekvensnummer = journalsekvensnummer?.toBigInteger()
            it.parts.addAll(parts.map { p -> p.build() })

            it.skjerming = skjerming?.build()
            it.gradering = gradering?.build()
            it.dokumentbeskrivelses.addAll(dokumentbeskrivelser.map { d -> d.build() }.toList())
            it.registreringsID = registreringsID
            it.tittel = checkNotNull(tittel) { feilmeldingPakrevdFelt("Tittel") }
            it.offentligTittel = offentligTittel
            it.beskrivelse = beskrivelse
            it.noekkelords.addAll(nokkelord)
            it.forfatters.addAll(forfattere)
            it.dokumentmedium = dokumentmedium?.value
            it.oppbevaringssteds.addAll(oppbevaringssteder)
            it.merknads.addAll(merknader.map { m -> m.build() }.toList())
            it.kryssreferanses.addAll(kryssreferanser.map { k -> k.build() }.toList())
            it.korrespondanseparts.addAll(korrespondanseparts.map { part -> part.build() }.toCollection(ArrayList()) )
            it.klassifikasjons.addAll(klassifikasjoner.map { k -> k.build() }.toList())
            it.referanseEksternNoekkel = referanseEksternNoekkel?.build() ?: throw IllegalStateException(feilmeldingPakrevdFelt("ReferanseEksternNoekkel"))

            it.journalaar = journalaar.toBigInteger()
            it.journalpostnummer = journalpostnummer?.toBigInteger()
            it.journalposttype = journalposttype?.value ?: throw IllegalStateException(feilmeldingPakrevdFelt("Journalposttype"))
            it.journalstatus = journalstatus?.value ?: throw IllegalStateException(feilmeldingPakrevdFelt("Journalstatus"))
            it.journaldato = journaldato
            it.dokumentetsDato = dokumentetsDato
            it.mottattDato = mottattDato
            it.sendtDato = sendtDato
            it.forfallsdato = forfallsdato
            it.offentlighetsvurdertDato = offentlighetsvurdertDato
            it.antallVedlegg = antallVedlegg?.toBigInteger()
            it.utlaantDato = utlaantDato
            it.utlaantTil = utlaantTil
            it.journalenhet = journalenhet
            it.avskrivnings.addAll(avskrivninger.map { a -> a.build() }.toList())
            it.dokumentflyts.addAll(dokumentflyt.map { d -> d.build() }.toList())
            it.presedens.addAll(presedens.map { p -> p.build() }.toList())
        }
    }

    private fun feilmeldingPakrevdFelt(prop: String) = "$prop er påkrevd felt for ${Journalpost::class.java.name}"
}
