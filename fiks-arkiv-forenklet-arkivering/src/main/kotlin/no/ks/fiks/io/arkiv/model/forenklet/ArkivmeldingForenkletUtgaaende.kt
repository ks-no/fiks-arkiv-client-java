package no.ks.fiks.io.arkiv.model.forenklet

import no.ks.fiks.io.arkiv.model.arkivmelding.*
import no.ks.fiks.io.arkiv.model.arkivstruktur.*
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.*

const val SKJERMINGSHJEMMEL: String = "Offl. § 26.1"

class ArkivmeldingForenkletUtgaaende {

    var referanseSaksmappeForenklet: SaksmappeForenklet? = null
        private set
    var nyUtgaaendeJournalpost: UtgaaendeJournalpost? = null
        private set
    var sluttbrukerIdentifikator: String? = null
        private set

    fun referanseSaksmappeForenklet(saksmappe: SaksmappeForenklet) = apply { this.referanseSaksmappeForenklet = saksmappe }
    fun nyUtgaaendeJournalpost(journalpost: UtgaaendeJournalpost) = apply { this.nyUtgaaendeJournalpost = journalpost }
    fun sluttbrukerIdentifikator(sluttbrukerIdentifikator: String) = apply { this.sluttbrukerIdentifikator = sluttbrukerIdentifikator }

    fun byggArkivmelding(): ArkivmeldingBuilder {
        checkNotNull(nyUtgaaendeJournalpost) {"ArkivmeldingForenkletUtgaaende krever journalpost"}

        val journalpost = nyUtgaaendeJournalpost?.let {
            val builder = JournalpostBuilder()
                .avskrivningsdato(checkNotNull(it.avskrivningsdato) {"Avskrivningsdato er påkrevd for journalpost"} )
                .journalposttype(JournalpostType.UTGAENDE_DOKUMENT)
                .journalstatus(JournalStatus.JOURNALFORT)
            it.tittel?.let { tittel -> builder.tittel(tittel) }
            it.journalaar?.let { aar -> builder.journalaar(aar) }
            it.journalsekvensnummer?.let { nummer -> builder.journalsekvensnummer(nummer) }
            it.journalpostnummer?.let { post -> builder.journalpostnummer(post) }
            it.sendtDato?.let { dato -> builder.sendtDato(dato) }
            it.dokumentetsDato?.let { dato -> builder.dokumentetsDato(dato) }
            it.offentlighetsvurdertDato?.let { dato -> builder.offentlighetsvurdertDato(dato) }
            this.sluttbrukerIdentifikator?.let {bruker ->
                builder.opprettetAv(bruker)
                builder.opprettetAv(bruker)
            }?: throw IllegalStateException("SluttbrukerIdentifkator er påkrevd for Arkivmelding")

            if(it.skjermetTittel){
                builder.skjerming(SkjermingBuilder()
                    .skjermingshjemmel(SKJERMINGSHJEMMEL).build())
            }
            it.hoveddokument?.let {doc ->
                val dokumentBuilder = DokumentbeskrivelseBuilder()
                    .dokumentType(doc.dokumenttype ?: DokumentType.KORRESPONDANSE)
                    .dokumentStatus(DokumentStatus.DOKUMENTET_ER_FERDIGSTILT)
                    .tittel(checkNotNull(doc.tittel) { "Tittel er påkrevd for hoveddokument" })
                    .tilknyttetRegistreringSom(TilknyttetRegistreringSomType.HOVEDDOKUMENT)
                if(doc.skjermetDokument == true) {
                    dokumentBuilder.skjerming(SkjermingBuilder()
                        .skjermingshjemmel(SKJERMINGSHJEMMEL).build()
                    )
                }
                dokumentBuilder.dokumentobjekter(listOf(getDokumentObjektBuilder(doc).build()))
                builder.dokumentbeskrivelser(builder.dokumentbeskrivelser + dokumentBuilder.build())
            }
            it.vedlegg.forEach {
                    doc ->
                val dokumentBuilder = DokumentbeskrivelseBuilder()
                    .dokumentType(doc.dokumenttype ?: DokumentType.KORRESPONDANSE)
                    .dokumentStatus(DokumentStatus.DOKUMENTET_ER_FERDIGSTILT)
                    .tittel(checkNotNull(doc.tittel) { "Tittel er påkrevd for hoveddokument" })
                    .tilknyttetRegistreringSom(TilknyttetRegistreringSomType.VEDLEGG)

                dokumentBuilder.dokumentobjekter(listOf(getDokumentObjektBuilder(doc).build()))
                builder.dokumentbeskrivelser(builder.dokumentbeskrivelser + dokumentBuilder.build())
            }

            builder.antallVedlegg(builder.dokumentbeskrivelser.size)

            it.mottakere.forEach { mottaker ->
                val korrespondansepartBuilder = korrespondansepartMapper(KorrespondansepartType.MOTTAKER, mottaker)
                builder.korrespondanseparts(builder.korrespondanseparts + korrespondansepartBuilder.build())
            }
            it.avsender.forEach { avsender ->
                val korrespondansepartBuilder = korrespondansepartMapper(KorrespondansepartType.AVSENDER, avsender)
                builder.korrespondanseparts(builder.korrespondanseparts + korrespondansepartBuilder.build())
            }
            it.internAvsender.forEach { avsender ->
                val korrespondansepartBuilder = korrespondansepartMapper(avsender)
                builder.korrespondanseparts(builder.korrespondanseparts + korrespondansepartBuilder.build())
            }
            it.referanseEksternNoekkelForenklet?.let { rn ->
                val referanseEksternNoekkel = EksternNoekkelBuilder()
                rn.fagstystem?.let { fs -> referanseEksternNoekkel.fagstystem(fs) }
                rn.noekkel?.let { n -> referanseEksternNoekkel.noekkel(n) }
                builder.referanseEksternNoekkel(referanseEksternNoekkel.build())
            }
        } ?: throw IllegalStateException("UtgaaendeJournalpost er påkrevd for ForenkletUtgåendeArkvimelding")

        this.referanseSaksmappeForenklet?.let { saksmappe ->
            val saksMappeBuilder = opprettSaksmappe(saksmappe, journalpost)

            val arkivmelding = MappeArkivmeldingBuilder()
                .mappe(saksMappeBuilder.build())
                .antallFiler(journalpost.dokumentbeskrivelser.size)
            journalpost.referanseEksternNoekkel?.fagsystem?.let { fs -> arkivmelding.system(fs) }
            return arkivmelding
        } ?: run {
            val arkivmelding = RegistreringArkivmeldingBuilder()
                .registrering(journalpost)
                .antallFiler(journalpost.dokumentbeskrivelser.size)
            journalpost.referanseEksternNoekkel?.fagsystem?.let { fs -> arkivmelding.system(fs) }
            return arkivmelding
        }

    }

    private fun opprettSaksmappe(
        saksmappe: SaksmappeForenklet,
        journalpost: JournalpostBuilder
    ): SaksmappeBuilder {
        val saksMappeBuilder = SaksmappeBuilder()
        saksmappe.saksansvarlig?.let { a -> saksMappeBuilder.saksansvarlig(a) }
        saksmappe.administrativEnhet?.let { e -> saksMappeBuilder.administrativEnhet(e) }
        saksmappe.tittel?.let { t -> saksMappeBuilder.tittel(t) }
        saksmappe.saksaar?.let { aar -> saksMappeBuilder.saksaar(aar) }
        saksmappe.sakssekvensnummer?.let { sekvens -> saksMappeBuilder.sakssekvensnummer(sekvens) }
        saksmappe.saksdato?.let { dato -> saksMappeBuilder.saksdato(dato) }
        saksmappe.klasse.let { klasser ->
            saksMappeBuilder.klassifikasjoner(
                klasser.map { k ->
                    val klassifikasjonBuilder = KlassifikasjonBuilder()
                    k.klassifikasjonssystem?.let { klassifikasjonBuilder.klassifikasjonssystemID(it) }
                    k.klasseID?.let { klassifikasjonBuilder.klasseID(it) }
                    k.tittel?.let { klassifikasjonBuilder.tittel(it) }
                    klassifikasjonBuilder.build()
                }.toList()
            )
        }
        saksmappe.referanseEksternNoekkelForenklet?.let { noekkel ->
            mappeBuilder(noekkel, saksMappeBuilder)
        }

        return saksMappeBuilder
    }

    private fun mappeBuilder(
        noekkel: EksternNoekkelForenklet,
        saksMappeBuilder: SaksmappeBuilder
    ): MappeBuilder {
        val noekkelBuilder = EksternNoekkelBuilder()
        noekkel.fagstystem?.let { f -> noekkelBuilder.fagstystem(f) }
        noekkel.noekkel?.let { n -> noekkelBuilder.noekkel(n) }
        return saksMappeBuilder.referanseEksternNoekkel(noekkelBuilder.build())
    }

    private fun korrespondansepartMapper(part: KorrespondansepartIntern): KorrespondansepartBuilder {
        val type = KorrespondansepartType.INTER_AVSENDER
        val korrespondansepartBuilder = KorrespondansepartBuilder()
        part.saksbehandler?.let {
            it.navn?.let { navn ->
                korrespondansepartBuilder.korrespondansepartNavn(navn)
            }
            korrespondansepartBuilder.saksbehandler(it)
        } ?: part.administrativEnhet?.let {
            it.navn?.let { navn ->
                korrespondansepartBuilder.korrespondansepartNavn(navn)
            }
            korrespondansepartBuilder.administrativEnhet(it)
        }
        korrespondansepartBuilder.korrespondansepartType(type)
        return korrespondansepartBuilder
    }

    private fun korrespondansepartMapper(type: KorrespondansepartType, part: KorrespondansepartForenklet): KorrespondansepartBuilder {
        val korrespondansepartBuilder = KorrespondansepartBuilder()
        korrespondansepartBuilder.korrespondansepartType(type)
        korrespondansepartBuilder.korrespondansepartNavn(checkNotNull(part.navn) { "Navn er påkrevd for mottaker" })

        part.postadresse?.let { adresse ->
            adresse.adresselinje1?.let { a ->
                korrespondansepartBuilder.postadresse(korrespondansepartBuilder.postadresse + a)
            }
            adresse.adresselinje2?.let { a ->
                korrespondansepartBuilder.postadresse(korrespondansepartBuilder.postadresse + a)
            }
            adresse.adresselinje3?.let { a ->
                korrespondansepartBuilder.postadresse(korrespondansepartBuilder.postadresse + a)
            }
            adresse.landkode?.let { land -> korrespondansepartBuilder.land(land) }
            adresse.postnr?.let { postnr -> korrespondansepartBuilder.postnummer(postnr) }
            adresse.poststed?.let { poststed -> korrespondansepartBuilder.poststed(poststed) }
            part.kontaktperson?.let { kontaktperson -> korrespondansepartBuilder.kontaktperson(kontaktperson) }
            part.kontaktinformasjonForenklet?.epostadresse?.let { epost ->
                korrespondansepartBuilder.epostadresse(
                    epost
                )
            }
            part.kontaktinformasjonForenklet?.mobiltelefon?.let { m ->
                korrespondansepartBuilder.telefonnumre(korrespondansepartBuilder.telefonnumre + m)
            }
            part.kontaktinformasjonForenklet?.telefon?.let { t ->
                korrespondansepartBuilder.telefonnumre(korrespondansepartBuilder.telefonnumre + t)
            }
        }

        part.deresReferanse?.let { ref -> korrespondansepartBuilder.deresReferanse(ref) }
        part.forsendelsemåte?.let { fm -> korrespondansepartBuilder.forsendelsesmaate(fm) }
        part.enhetsidentifikator?.organisasjonsnummer?.let { org ->
            korrespondansepartBuilder.organisasjonid(org)
            part.enhetsidentifikator?.landkode?.let { land -> korrespondansepartBuilder.land(land) }
        }
        part.personid?.personidentifikatorNr?.let { person ->
            korrespondansepartBuilder.organisasjonid(person)
            part.enhetsidentifikator?.landkode?.let { land -> korrespondansepartBuilder.land(land) }
        }
        return korrespondansepartBuilder
    }

}

private fun getDokumentObjektBuilder(doc: ForenkletDokument) =
    DokumentObjektBuilder()
        .format(filtypeTilFormatKode(checkNotNull(doc.filnavn) { "Filnavn er påkrevd for hoveddokument" }))
        .variantformat(VariantFormatType.PRODUKSJONSFORMAT)
        .versjonsnummer(1)
        .filnavn(checkNotNull(doc.filnavn) { "Filnavn er påkrevd for hoveddokument" })
        .referanseDokumentfil(checkNotNull(doc.referanseDokumentFil) { "ReferanseDokumentFil er påkrevd for hoveddokument" })


private fun filtypeTilFormatKode(filnavn: String) : FormatType {
    val postfix = filnavn.substring(filnavn.lastIndexOf(".") )
    return when {
        postfix.equals(".txt", true) -> FormatType.REN_TEKST
        postfix.equals(".tiff", true) -> FormatType.TIFF_VERSJON_6
        postfix.equals(".pdf", true) -> FormatType.PDF_A_ISO_19005_1_2005
        postfix.equals(".xml", true) -> FormatType.XML
        postfix.equals(".jpeg", true) -> FormatType.JPEG
        postfix.equals(".mp3", true) -> FormatType.MP3
        postfix.equals(".sosi", true) -> FormatType.SOSI
        postfix.equals(".mpg", true) -> FormatType.MPEG_2
        postfix.equals(".mp2", true) -> FormatType.MPEG_2
        else -> throw IllegalStateException("Filformat er ukjent for hoveddokument: $filnavn")
    }
}