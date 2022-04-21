package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.JournalStatus
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.JournalpostType
import no.ks.fiks.io.arkiv.model.arkivmelding.ArkivmeldingPartBuilder
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.DokumentmediumType
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SystemIDBuilder
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Journalpost
import java.time.ZonedDateTime
import java.util.*
import kotlin.collections.ArrayList

/**
 * Journalpost er en spesialisering av registrering og brukes for hendelser som skal inn i journalen, en oversikt over all korrespondanse til og fra organisasjonen, samt interne notater og rapporter. Oftest vil det kun være journalposter registrert i saksmapper.
 */
class JournalpostBuilder : IRegistrering {
    var referanseArkivdel: UUID? = null
        private set
    var journaldato: ZonedDateTime = ZonedDateTime.now()
        private set
    var journalpostnummer: Long? = null
        private set
    var journalsekvensnummer: Long? = null
        private set
    var journalaar: Int = ZonedDateTime.now().year
        private set
    var systemID: SystemIDBuilder? = null
        private set
    var journalposttype: JournalpostType = JournalpostType.INNGAENDE_DOKUMENT
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
    var referanseForelderMappe: SystemIDBuilder? = null
        private set
    var korrespondanseparts: List<KorrespondansepartBuilder> = ArrayList()
        private set
    var journalstatus: JournalStatus = JournalStatus.JOURNALFORT
        private set
    var referanseEksternNoekkel: EksternNoekkelBuilder? = null
        private set
    var parts: List<ArkivmeldingPartBuilder> = ArrayList()
        private set
    var beskrivelse: String? = null
        private set
    var dokumentetsDato: ZonedDateTime? = null
        private set
    var mottattDato: ZonedDateTime? = null
        private set
    var sendtDato: ZonedDateTime? = null
        private set
    var forfallsdato: ZonedDateTime? = null
        private set
    var offentlighetsvurdertDato: ZonedDateTime? = null
        private set
    var antallVedlegg: Long? = null
        private set
    var utlaantDato: ZonedDateTime? = null
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
    var elektroniskSignatur: ElektroniskSignaturBuilder? = null
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
    var kassasjon: KassasjonBuilder? = null
        private set

    fun systemID(systemID: SystemIDBuilder) = apply { this.systemID = systemID }
    fun journalposttype(journalposttype: JournalpostType) = apply { this.journalposttype = journalposttype }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun opprettetDato(opprettetDato: ZonedDateTime) = apply { this.opprettetDato = opprettetDato }
    fun opprettetAv(opprettetAv: String) = apply { this.opprettetAv = opprettetAv }
    fun arkivertDato(arkivertDato: ZonedDateTime) = apply { this.arkivertDato = arkivertDato }
    fun arkivertAv(arkivertAv: String) = apply { this.arkivertAv = arkivertAv }
    fun referanseForelderMappe(referanseForelderMappe: SystemIDBuilder) = apply { if(referanseArkivdel == null) this.referanseForelderMappe = referanseForelderMappe else throw IllegalArgumentException("ReferanseForelderMappe kan ikke settes i kombinasjon med ReferanseArkivdel") }
    fun referanseArkivdel(referanseArkivdel: UUID) = apply { if(referanseForelderMappe == null) this.referanseArkivdel = referanseArkivdel else throw IllegalArgumentException("ReferanseArkivdel kan ikke settes i kombinasjon med ReferanseForelderMappe") }
    fun korrespondanseparts(korrespondanseparts: List<KorrespondansepartBuilder>) = apply { this.korrespondanseparts = korrespondanseparts }
    fun journalstatus(journalstatus: JournalStatus) = apply { this.journalstatus = journalstatus }
    fun referanseEksternNoekkel(referanseEksternNoekkel: EksternNoekkelBuilder) = apply { this.referanseEksternNoekkel =  referanseEksternNoekkel}
    fun journalaar(journalaar: Int) = apply { this.journalaar = journalaar }
    fun journalsekvensnummer(journalsekvensnummer: Long) = apply { this.journalsekvensnummer = journalsekvensnummer }
    fun journalpostnummer(journalpostnummer: Long) = apply { this.journalpostnummer = journalpostnummer }
    fun journaldato(journaldato: ZonedDateTime) = apply { this.journaldato = journaldato }
    fun parts(parts: List<ArkivmeldingPartBuilder>) = apply { this.parts = parts }
    fun beskrivelse(beskrivelse: String) = apply { this.beskrivelse = beskrivelse }
    fun dokumentetsDato(dokumentetsDato: ZonedDateTime) = apply { this.dokumentetsDato = dokumentetsDato }
    fun mottattDato(mottattDato: ZonedDateTime) = apply { this.mottattDato = mottattDato }
    fun sendtDato(sendtDato: ZonedDateTime) = apply { this.sendtDato = sendtDato }
    fun forfallsdato(forfallsdato: ZonedDateTime) = apply { this.forfallsdato = forfallsdato }
    fun offentlighetsvurdertDato(offentlighetsvurdertDato: ZonedDateTime) = apply { this.offentlighetsvurdertDato = offentlighetsvurdertDato }
    fun antallVedlegg(antallVedlegg: Long) = apply { this.antallVedlegg = antallVedlegg }
    fun utlaantDato(utlaantDato: ZonedDateTime) = apply { this.utlaantDato = utlaantDato }
    fun utlaantTil(utlaantTil: String) = apply { this.utlaantTil = utlaantTil }
    fun journalenhet(journalenhet: String) = apply { this.journalenhet = journalenhet }
    fun avskrivninger(avskrivninger: List<AvskrivningBuilder>) = apply { this.avskrivninger = avskrivninger }
    fun dokumentflyt(dokumentflyt: List<DokumentflytBuilder>) = apply { this.dokumentflyt = dokumentflyt }
    fun presedens(presedens: List<PresedensBuilder>) = apply { this.presedens = presedens }
    fun elektroniskSignatur(elektroniskSignatur: ElektroniskSignaturBuilder) = apply { this.elektroniskSignatur = elektroniskSignatur }
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
    fun kassasjon(kassasjon: KassasjonBuilder) = apply { this.kassasjon = kassasjon }

    override fun buildApiModel() : Journalpost {
        return Journalpost().also {
            it.systemID = systemID?.build() ?: throw IllegalStateException(feilmeldingPakrevdFelt("SystemID"))
            it.opprettetDato = opprettetDato
            it.opprettetAv = checkNotNull(opprettetAv) { feilmeldingPakrevdFelt("OpprettetAv") }
            it.arkivertDato = arkivertDato
            it.arkivertAv = checkNotNull(arkivertAv) { feilmeldingPakrevdFelt("ArkivertAv") }
            it.referanseForelderMappe = referanseForelderMappe?.build()
            it.referanseArkivdel = referanseArkivdel?.toString()
            it.journalsekvensnummer = journalsekvensnummer?.toBigInteger() ?: throw IllegalStateException(feilmeldingPakrevdFelt("Journalsekvensnummer"))
            it.parts.addAll(parts.map { p -> p.build() })

            it.kassasjon = kassasjon?.build()
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
            it.journalpostnummer = journalpostnummer?.toBigInteger() ?: throw IllegalStateException(feilmeldingPakrevdFelt("Journalpostnummer"))
            it.journalposttype = journalposttype.value
            it.journalstatus = journalstatus.value
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
            it.elektroniskSignatur = elektroniskSignatur?.build()
        }
    }

    private fun feilmeldingPakrevdFelt(prop: String) = "$prop er påkrevd felt for ${Journalpost::class.java.name}"
}
